package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

/**
 * CartDaoSqlImpl class connects to database and implements service methods
 * @See CartDao
 * @see Cart
 * @author Shiyam
 *
 */
@Repository
public class CartDaoSqlImpl implements CartDao {

	/**
	 * addCartItem methods add the user item to his/her
	 * cart in database
	 * @param userId
	 * @param menuItemId
	 */
	@Override
	public void addCartItem(long userId, Long menuItemId)  {
		Connection con = ConnectionHandler.getConnection();
		MenuItem item = new MenuItemDaoSqlImpl().getMenuItem(menuItemId);
		
		String val = null;
		if(item.isFreeDelivery())
			val = "Yes";
		else
			val = "No";
		
		
		String Query = "insert into cart(cart_name,cart_free_delivery,cart_price ,cart_category,"
				+"quantity,cart_user_id,cart_item_id)"
				+ " value(?,?,?,?,?,?,?)";
		try 
		{
			PreparedStatement stmt = con.prepareStatement(Query);
			stmt.setString(1, item.getName());
			stmt.setString(2, val);
			stmt.setFloat(3, item.getPrice());
			stmt.setString(4, item.getCategory());
			stmt.setInt(5, 1);
			stmt.setLong(6, userId);
			stmt.setLong(7, menuItemId);
			stmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * getAllCartItems retrieves cart items for a user
	 * @param userId
	 * @throws CartEmptyException
	 */
	@Override
	public Cart getAllCartItems(long userId) throws CartEmptyException {
		// TODO Auto-generated method stub

		Connection con = ConnectionHandler.getConnection();
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		Cart cart = new Cart(0,menuItemList);
		Float value  = 0.00f;
		String query = "select * from menu_item JOIN cart ON menu_item.item_id = cart.cart_item_id where cart.cart_user_id = ?";
		try
		{
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();
			
		
			while(rs.next())
			{
				
				long id = rs.getLong("item_id");
				String name  = rs.getString("item_name");
				float price = rs.getFloat("item_price");
				value = value + price;
				String active = rs.getString("item_active");
				Date date1 = rs.getDate("item_date_of_launch");
				String category = rs.getString("item_category");
				String free_delivery = rs.getString("item_free_delivery");
				boolean act = false;
				if(active.equalsIgnoreCase("yes"))
				{
					act = true;
				}
				boolean free=false;
				if(free_delivery.equalsIgnoreCase("yes"))
				{
					free = true;
				}
				MenuItem m1  = new MenuItem(id,name,category,price,act,free,date1);
				menuItemList.add(m1);
			 }
			 System.out.println("Total bill for the user = "+value);
			 cart.setTotal(value);
			 cart.setMenuItemList(menuItemList);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return cart;
	}

	/**
	 * removeCartItem deletes an item from cart of a user
	 * @param userId
	 * @param menuItemId
	 */
	@Override
	public void removeCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		Connection con = ConnectionHandler.getConnection();
		String query = "delete from  cart where cart_user_id = ? AND cart_item_id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1,userId);
			stmt.setLong(2, menuItemId);
			stmt.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
