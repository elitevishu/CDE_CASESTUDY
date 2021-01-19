package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.*;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoSqlImplTest {

	public static void main(String[] args) throws ParseException {
		MenuItemDaoSqlImplTest M = new MenuItemDaoSqlImplTest();
		System.out.println("For admin");
		M.testGetMenuItemListAdmin();
		System.out.println();
		System.out.println("For Customer");
		M.testGetMenuItemListCustomer();
		System.out.println();
		System.out.println("Modified");
		M.testModifyMenuItem();
		System.out.println();
		System.out.println("Item By Id");
		M.GetMenuItem(1);
	}

	public void testGetMenuItemListAdmin() {

		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		List<MenuItem> menuItems = menuItemDao.getMenuItemListAdmin();
		for (MenuItem m : menuItems) {
			System.out.println(m.toString());
		}
	}

	public void testGetMenuItemListCustomer() {

		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		List<MenuItem> menuItems = menuItemDao.getMenuItemListCustomer();
		for (MenuItem m : menuItems) {
			System.out.println(m.toString());
		}
	}

	public void testModifyMenuItem() throws ParseException {
		MenuItem menuItem = new MenuItem(101, "Sandwich", (float) 99.00, true, DateUtil.convertToDate("15/03/2017"),
				"Main Course", true);
		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		menuItemDao.modifyMenuItem(menuItem);
		System.out.println(menuItemDao.getMenuItem(1));
	}

	public void GetMenuItem(long id) {

		MenuItemDao menuItemDao = new MenuItemDaoSqlImpl();
		System.out.println(menuItemDao.getMenuItem(id));

	}
}