package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {

	static List<MenuItem> menuItemList;

	public MenuItemDaoCollectionImpl() {

		if (menuItemList == null) {
			try {

				menuItemList = new ArrayList<MenuItem>();
				menuItemList.add(new MenuItem(101, "Sandwich", 99.00f, true, DateUtil.convertToDate("15/03/2017"),
						"Main Course", true));
				menuItemList.add(new MenuItem(102, "Burger", 129.00f, true, DateUtil.convertToDate("23/12/2017"),
						"Main Course", false));
				menuItemList.add(new MenuItem(103, "Pizza", 149.00f, true, DateUtil.convertToDate("21/08/2018"),
						"Main Course", false));
				menuItemList.add(new MenuItem(104, "French Fries", 200.00f, false, DateUtil.convertToDate("02/07/2017"),
						"Main Course", false));
				menuItemList.add(new MenuItem(105, "Choclate Brownie", 32.00f, true,
						DateUtil.convertToDate("02/11/2022"), "Dessert", true));

			} catch (ParseException e) {
				System.out.println("Parse Exception" + e.getMessage());
			}
		}

	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {
		// TODO Auto-generated method stub
		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		List<MenuItem> menuItemListCust = new ArrayList<MenuItem>();

		Date current = new Date();

		for (MenuItem item : menuItemList) {

			if (item.getDateOfLaunch().getTime() <= current.getTime() && item.isActive()) {
				menuItemListCust.add(item);
			}
		}
		return menuItemListCust;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		for (MenuItem item : menuItemList) {
			if (item.getId() == menuItem.getId()) {
				item.setName(menuItem.getName());
				item.setCategory(menuItem.getCategory());
				item.setDateOfLaunch(menuItem.getDateOfLaunch());
				item.setFreeDelivery(menuItem.isFreeDelivery());
				item.setPrice(menuItem.getPrice());
				item.setActive(menuItem.isActive());
			}
		}
	}

	@Override
	public MenuItem getMenuItem(long menuitemId) {

		for (MenuItem item : menuItemList) {
			if (menuitemId == item.getId()) {
				return item;

			}

		}
		return null;
	}

}