package com.cognizant.truyum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

/**
 * 
 * @author Shiyam
 *
 */
@ImportResource({ "spring-config.xml" })
@Configuration
public class MenuItemServiceTest {

	MenuItemService menuItemService;

	/**
	 * Initiates the context scans the components and services intitalizes the
	 * service
	 */
	@Before
	public void initializeService() {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
		configApplicationContext.scan("com.cognizant.truyum");
		configApplicationContext.refresh();
		menuItemService = (MenuItemService) configApplicationContext.getBean("menuItemService");
		configApplicationContext.close();

	}

	/**
	 * Check the list size of menu items for admin
	 * 
	 * @result checks will be applied without any error
	 */
	@Test
	public void testGetMenuItemListAdminSize() {

		assertEquals(5, menuItemService.getMenuItemListAdmin().size());
	}

	/**
	 * Check if admin list contains Sandwich
	 * 
	 * @result checks are confirmed without any errors
	 * @throws ParseException in case of invalid date
	 */

	@Test
	public void testGetMenuItemListAdminContainsSandwich() throws ParseException {

		List<MenuItem> menuItemsAdmin = menuItemService.getMenuItemListAdmin();
		MenuItem sandwich = new MenuItem(1, "Sandwich", 59f, true, DateUtil.convertToDate("17/08/2017"), "Starters",
				true);
		assertEquals(true, sandwich.equals(menuItemsAdmin.get(0)));
	}

	/**
	 * Check the list size of menu items for customer
	 * 
	 * @result checks will be applied without any error
	 */

	@Test
	public void testGetMenuItemListCustomerSize() {

		assertEquals(3, menuItemService.getMenuItemListCustomer().size());
	}

	/**
	 * Check if list not contain french fries menu item
	 * 
	 * @result checks will be applied without any error
	 */

	@Test
	public void testGetMenuItemListCustomerNotContainsFrenchFries() {

		MenuItem menuItem = menuItemService.getMenuItem(4);
		assertFalse(menuItemService.getMenuItemListCustomer().contains(menuItem));
	}

	/**
	 * Get a particular menu item by Id
	 * 
	 * @result Proper menu item will be retrieved by the method without any error
	 */

	@Test
	public void testGetMenuItem() {

		assertEquals("Burger", menuItemService.getMenuItem(2).getName());
	}

	/**
	 * Edit menu item and check the updated menu item
	 * 
	 * @result menu item gets edited without any error
	 * @throws ParseException in case of invalid date
	 * 
	 */
	@Test
	public void testModifyMenuItem() throws ParseException {

		MenuItem newMenuItem = new MenuItem();
		newMenuItem.setId(4);
		newMenuItem.setName("Dosa");
		newMenuItem.setPrice(140f);
		newMenuItem.setDateOfLaunch(DateUtil.convertToDate("12/05/2018"));
		newMenuItem.setActive(true);
		newMenuItem.setCategory("Main Course");
		newMenuItem.setFreeDelivery(true);
		menuItemService.editMenuItem(newMenuItem);

		assertEquals(newMenuItem, menuItemService.getMenuItem(4));
	}

}
