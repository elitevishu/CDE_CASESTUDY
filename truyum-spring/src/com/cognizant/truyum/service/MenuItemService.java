package com.cognizant.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;

/**
 * 
 * @author Ayush Srivastava
 *
 */

@Service("menuItemService")
public class MenuItemService {
	/**
	 * MenuItemService class is the implementation of the service layer
	 * menuItemDao attribute is injected using spring-config.xml
	 * bean menuItemDao is autowired with MenuItemCollectionDaoImpl object
	 */
	@Autowired
	private MenuItemDao menuItemDao;

	public void setMenuItemDao(MenuItemDao menuItemDao) {
		this.menuItemDao = menuItemDao;
	}

	/**
	 * getMenuItemListAdmin() method return the list of menu items which are
	 * displayed to admin
	 * 
	 * @return list of menu items for admin
	 */

	public List<MenuItem> getMenuItemListAdmin() {

		return menuItemDao.getMenuItemListAdmin();
	}

	/**
	 * getMenuItemListCustomer() returns list of menu items which are available for
	 * customers
	 * 
	 * @return list of menu items for customers
	 */

	public List<MenuItem> getMenuItemListCustomer() {

		return menuItemDao.getMenuItemListCustomer();
	}

	/**
	 * getMenuItem() returns the MenuItem based on menuItemId menuItemDao is used
	 * for invoking the methods for getting MenuItem
	 * 
	 * @param menuItemId
	 * @return MenuItem for the given menuItemId
	 */

	public MenuItem getMenuItem(long menuItemId) {

		return menuItemDao.getMenuItem(menuItemId);
	}

	/**
	 * editMenuItem() takes
	 * 
	 * @param menuItem modifies the menu item and gets the updated menu item
	 */

	public void editMenuItem(MenuItem menuItem) {
		menuItemDao.modifyMenuItem(menuItem);
		menuItemDao.getMenuItem(menuItem.getId());

	}

}
