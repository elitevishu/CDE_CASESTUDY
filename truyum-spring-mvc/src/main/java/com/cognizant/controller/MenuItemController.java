package com.cognizant.controller;

//import java.text.SimpleDateFormat;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

/**
 * MenuItemController class used to handle request from menu items pages
 * @see MenuItemService
 * @author Shiyam
 */

@SessionAttributes("userId")
@Controller
public class MenuItemController {
	
	@Autowired
	MenuItemService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);
	
	/**
	 * showMenuItemListAdmin method shows items for admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/show-menu-list-admin")
	public String showMenuItemListAdmin(ModelMap model) /*throws SystemException*/{
		LOGGER.info("showMenuItemListAdmin -Start");
		List<MenuItem> items=service.getMenuItemListAdmin();
		model.addAttribute("itemList", items);		
		LOGGER.info("showMenuItemListAdmin -End");
		return "menu-item-list-admin";
}
	
	/**
	 * showEditMenuItem method handles get request and 
	 * display page to edit menu items for admin
	 * @param menuItemId
	 * @param model
	 * @return
	 */
	@GetMapping("/show-edit-menu-item")
	public String showEditMenuItem(@RequestParam("id") long  menuItemId,ModelMap model) {
		LOGGER.info("showEditMenuItem -Start");
		MenuItem item=service.getMenuItem(menuItemId);
		model.addAttribute("menuItem", item);
		LOGGER.info("showEditMenuItem -End");
		return "edit-menu-item";
	}
	
	/**
	 * editMenuItem method handles post request from edit menu page
	 * it edit the item in database 
	 * @param menuItem
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/edit-menu-item")
	public String editMenuItem( @Valid MenuItem menuItem, BindingResult bindingResult)
	{
		LOGGER.info("showEditMenuItemPost -Start");
		if(bindingResult.hasErrors())
			return "edit-menu-item";
		service.editMenuItem(menuItem);
		LOGGER.info("showEditMenuItemPost -End");
		return "edit-menu-item-status";
	}
	
	/**
	 * showMenuItemListCustomer method display menu items for customer
	 * @param model
	 * @return
	 */
	@GetMapping("/show-menu-list-customer")
	public String showMenuItemListCustomer(ModelMap model) 
	{
		LOGGER.info("showMenuItemListCustomer -Start");
		List<MenuItem> item=service.getMenuItemListCustomer();
		model.addAttribute("itemList",item);
		LOGGER.info("showMenuItemListCustomer -End");
		return "menu-item-list-customer";
		
		
	}
	
	/**
	 *  populateCategory method used to populate the category
	 *  in edit menu jsp
	 * @return
	 */
	@ModelAttribute("categoryList")
	public List<String> populateCategory()
	{
		LOGGER.info("populateCategory -Start");
		List<String> categoryList = new ArrayList<String>();
		categoryList.add("Starters");
		categoryList.add("Main Course");
		categoryList.add("Dessert");
		categoryList.add("Drinks");
		LOGGER.info("populateCategory -End");
		return categoryList;
	}
	
	
}
