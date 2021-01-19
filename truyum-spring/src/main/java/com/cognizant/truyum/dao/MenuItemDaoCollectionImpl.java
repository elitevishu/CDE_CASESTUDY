
package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

/**
 * 
 * @author Shiyam
 *
 */
public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private List<MenuItem> menuItemList;

	/**
	 * Constructor for dependency injection
	 */
	public MenuItemDaoCollectionImpl() {

	}

	public MenuItemDaoCollectionImpl(List<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;

	}

	/**
	 * @return list of menu items for admin
	 */
	@Override
	public List<MenuItem> getMenuItemListAdmin() {

		return menuItemList;
	}

	/**
	 * @return list of menu items for customer
	 */
	@Override
	public List<MenuItem> getMenuItemListCustomer() {

		List<MenuItem> menuItemListCust = new ArrayList<MenuItem>();

		Date today = new Date();
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getDateOfLaunch().getTime() <= today.getTime() && menuItem.isActive()) {
				menuItemListCust.add(menuItem);
			}
		}

		return menuItemListCust;
	}

	/**
	 * edits/modifies the menu item
	 */
	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		for (MenuItem menuItemForModification : menuItemList) {

			if (menuItem.getId() == menuItemForModification.getId()) {

				menuItemForModification.setName(menuItem.getName());
				menuItemForModification.setCategory(menuItem.getCategory());
				menuItemForModification.setDateOfLaunch(menuItem.getDateOfLaunch());
				menuItemForModification.setFreeDelivery(menuItem.isFreeDelivery());
				menuItemForModification.setPrice(menuItem.getPrice());
				menuItemForModification.setActive(menuItem.isActive());
			}

		}

	}
	
	/**
	 * Get menu item based on Id
	 * @return menu item
	 */

	@Override
	public MenuItem getMenuItem(long menuitemId) {

		for (MenuItem menuItem : menuItemList) {
			if (menuitemId == menuItem.getId()) {
				return menuItem;

			}

		}
		return null;

	}

}
