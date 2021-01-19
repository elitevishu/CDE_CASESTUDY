package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest {

	public static void main(String[] args) throws ParseException {
		System.out.println("menu items");

		testGetMenuItemsAdmin();

		System.out.println("menu items customer");

		testGetMenuItemsCustomer();

		System.out.println("\nmenu items modified");

		testModifyMenuItem();
		System.out.println("\nItem by Id");

		testGetMenuItem((long) 101);

	}

	static void testGetMenuItemsAdmin() {

		MenuItemDaoCollectionImpl impl = new MenuItemDaoCollectionImpl();

		List<MenuItem> items = impl.getMenuItemListAdmin();

		for (MenuItem item : items) {
			System.out.println(item.toString());
		}

	}

	static void testGetMenuItemsCustomer() {

		MenuItemDaoCollectionImpl impl = new MenuItemDaoCollectionImpl();

		List<MenuItem> items = impl.getMenuItemListCustomer();

		for (MenuItem item : items) {
			System.out.println(item.toString());
		}

	}

	static void testModifyMenuItem() throws ParseException {

		MenuItem menuItem = new MenuItem(101, "Cheese Sandwich", 80.00f, true, DateUtil.convertToDate("11/12/2019"),
				"Main Course", false);
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl = new MenuItemDaoCollectionImpl();
		MenuItemDao menuItemDao = menuItemDaoCollectionImpl;
		menuItemDao.modifyMenuItem(menuItem);
		System.out.println("Modified MenuItem List :" + menuItemDao.getMenuItem(101));

	}

	static void testGetMenuItem(Long id) {

		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl = new MenuItemDaoCollectionImpl();
		MenuItemDao menuItemDao = menuItemDaoCollectionImpl;
		System.out.println(menuItemDao.getMenuItem(id));

	}

}
