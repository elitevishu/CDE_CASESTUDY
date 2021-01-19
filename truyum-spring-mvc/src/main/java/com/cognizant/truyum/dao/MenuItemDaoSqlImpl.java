package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cognizant.truyum.model.MenuItem;

/**
 *  MenuItemDaoSqlImpl class connects to database and implements service methods
 *  @See MenuItemDao
 *  @see MenuItem
 *
 */
@Repository
public class MenuItemDaoSqlImpl implements MenuItemDao {

	/**
	 * getMenuItemListAdmin() gets the items for admin
	 */
	@Override
	public ArrayList<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		final String QUERY = "select * from menu_item";
		Connection con = ConnectionHandler.getConnection();
		try 
		{ 
			PreparedStatement stmt = con.prepareStatement(QUERY);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("item_id");
				String name  = rs.getString("item_name");
				Float price = rs.getFloat("item_price");
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
				MenuItem m1 = new MenuItem(id,name,category,price,act,free,date1);
				menuItemList.add(m1);
			}
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
		
		return menuItemList ;
	}

	/**
	 * getMenuItemListCustomer() gets the available items for 
	 * customers
	 */
	@Override
	public ArrayList<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		ArrayList<MenuItem> menuItemListCust = new ArrayList<MenuItem>();
		Connection con = ConnectionHandler.getConnection();
		final String Query = "select * from menu_item where item_active='Yes' AND item_date_of_launch < CURDATE()";
		try {
			PreparedStatement stmt = con.prepareStatement(Query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				long id = rs.getLong("item_id");
				String name  = rs.getString("item_name");
				Float price = rs.getFloat("item_price");
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
				MenuItem m1 = new MenuItem(id,name,category,price,act,free,date1);
				menuItemListCust.add(m1);
			}
			
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
		
		return menuItemListCust;
		
	}
	
	/**
	 * modifyMenuItem method modify the menu items
	 * @param menuItem
	 */

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		Connection con = ConnectionHandler.getConnection();
		int id = (int)menuItem.getId();
		String name = menuItem.getName();
		Float price = menuItem.getPrice();
		boolean active = menuItem.isActive();
		
		java.sql.Date d =new java.sql.Date( menuItem.getDateOfLaunch().getTime());
		String category = menuItem.getCategory();
		boolean free = menuItem.isFreeDelivery();
		String act;
		if(active)
		{
			act = "Yes";
		}
		else
		{
			act = "No";
		}
		String fd;
		if(free)
		{
			fd = "Yes";
		}
		else
		{
			fd = "No";
		}
		final String query = "update menu_item  set item_name = ?, item_price= ?,item_active  = ?,item_date_of_launch = ?,item_category= ?,item_free_delivery = ? where item_id = ?";
		try 
		{
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setFloat(2, price);
			stmt.setString(3, act);
			stmt.setDate(4, d);
			stmt.setString(5,category);
			stmt.setString(6, fd);
			stmt.setLong(7, id);
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
	 * getMenuItem gets the menu item
	 * @param menuItemId
	 * @return MenuItem
	 */
	@Override
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		Connection con = ConnectionHandler.getConnection();
		MenuItem m1 = null;
		final String query = "select * from menu_item where item_id=?";
		try 
		{
			
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, menuItemId);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				long id = rs.getLong("item_id");
				String name  = rs.getString("item_name");
				Float price = rs.getFloat("item_price");
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
				m1 = new MenuItem(id,name,category,price,act,free,date1);
			}
			
			
		} catch (SQLException e) 
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
		return m1;
		
	}

}
