package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() {

		List<MenuItem> adminList = new ArrayList<MenuItem>();
		try {
			Connection conn = ConnectionHandler.getConnection();
			String query = "select * from menu_items_lists";
			PreparedStatement sql = conn.prepareStatement(query);
			ResultSet rs = sql.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("item_id");
				String name = rs.getString("item_name");
				float price = rs.getFloat("price");
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
				adminList.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adminList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		List<MenuItem> custList = new ArrayList<MenuItem>();
		try {
			Connection conn = ConnectionHandler.getConnection();
			String query = "select * from menu_items_lists where active='yes' and Date_of_lunch<'2020-12-15' ";
			PreparedStatement sql = conn.prepareStatement(query);
			ResultSet rs = sql.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("item_id");
				String name = rs.getString("item_name");
				float price = rs.getFloat("price");
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
				custList.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return custList;

	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		// TODO Auto-generated method stub
		long id = menuItem.getId();
		String name = menuItem.getName();
		float price = menuItem.getPrice();
		boolean active = menuItem.isActive();
		Date dateOfLaunch = menuItem.getDateOfLaunch();
		String category = menuItem.getCategory();
		boolean freeDelivery = menuItem.isFreeDelivery();
		String boolActive;
		String boolDelivery;
		if (active) {
			boolActive = "yes";
		} else {
			boolActive = "no";
		}
		if (freeDelivery) {
			boolDelivery = "yes";
		} else {
			boolDelivery = "no";
		}

		try {
			Connection conn = ConnectionHandler.getConnection();
			String query = "update menu_items_lists set item_name=?, price=?, active=?, Date_of_lunch=?, category=?, free_delivery=? where item_id=?";
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setString(1, name);
			sql.setFloat(2, price);
			sql.setString(3, boolActive);
			sql.setDate(4, new java.sql.Date(dateOfLaunch.getTime()));
			sql.setString(5, category);
			sql.setString(6, boolDelivery);
			sql.setLong(7, id);
			sql.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public MenuItem getMenuItem(long menuitemId) {
		MenuItem item = null;
		try {
			Connection conn = ConnectionHandler.getConnection();
			String query = "select * from menu_items_lists where item_id=?";
			PreparedStatement sql = conn.prepareStatement(query);
			sql.setLong(1, menuitemId);
			ResultSet rs = sql.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("item_id");
				String name = rs.getString("item_name");
				float price = rs.getFloat("price");
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

				item = new MenuItem(id, name, price, boolActive, dateOfLaunch, category, boolDelivery);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;

	}

}
