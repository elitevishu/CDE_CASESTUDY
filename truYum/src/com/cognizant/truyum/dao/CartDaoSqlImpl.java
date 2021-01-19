package com.cognizant.truyum.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {

	@Override
	public void addCartItem(long userId, long menuItemId) {
		try {
			Connection conn = ConnectionHandler.getConnection();
			String query = "insert into cart(user_id,item_id) values(?,?)";
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setLong(1, userId);
			sql.setLong(2, menuItemId);
			sql.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		// TODO Auto-generated method stub
		float total = 0;
		List<MenuItem> itemList = new ArrayList<MenuItem>();
		Cart newCart = new Cart(itemList, total);
		try {
			Connection conn = ConnectionHandler.getConnection();
			String query = "select i.item_id,i.item_name,i.price,i.active,i.Date_of_lunch,i.category,i.free_delivery from menu_items_lists i join cart c on i.item_id=c.item_id where user_id=?";
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setLong(1, userId);
			ResultSet rs = sql.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("item_id");
				String name = rs.getString("item_name");
				float price = rs.getFloat("price");
				total = total + price;
				String active = rs.getString("active");
				Date dateOfLaunch = rs.getDate("Date_of_lunch");
				String category = rs.getString("category");
				String freeDelivery = rs.getString("free_delivery");

				boolean boolActive = false;
				boolean boolDelivery = false;
				if (active.equals("yes")) {
					boolActive = true;
				}
				if (freeDelivery.equals("yes")) {
					boolDelivery = true;
				}

				MenuItem item = new MenuItem(id, name, price, boolActive, dateOfLaunch, category, boolDelivery);
				itemList.add(item);
				newCart.setTotal(total);
				newCart.setMenuItemList(itemList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemList;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		try {
			Connection conn = ConnectionHandler.getConnection();
			String sql = "delete from cart where user_id=? and item_id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			stmt.setLong(2, menuItemId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
