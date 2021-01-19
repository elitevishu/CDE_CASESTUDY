package com.cognizant.truyum.dao;

import java.util.ArrayList;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

/**
 * 
 * @see CartDao
 * @author Shiyam
 */
public interface CartDao {
	public void addCartItem(long userId,Long menuItemId);
	public Cart getAllCartItems(long userId) throws CartEmptyException;
	public void removeCartItem(long userId,long menuItemId);
}
