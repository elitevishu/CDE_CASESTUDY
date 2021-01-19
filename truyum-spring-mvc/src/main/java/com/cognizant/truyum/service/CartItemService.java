package com.cognizant.truyum.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartDaoSqlImpl;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

/**
 * 
 * 
 *CartItemService class is used to implement CartSqlDao methods
 *@see CartDaoSqlImpl
 */
@Service
@ComponentScan("com.*")
public class CartItemService {
	
	//@Autowired
	CartDao cartDao = new CartDaoSqlImpl();
	
	/**
	 * addCartItem method add the item to users cart
	 * @param userId
	 * @param menuItemId
	 */
	public void addCartItem(long userId,Long menuItemId)
	{
		cartDao.addCartItem(userId, menuItemId);
	}
	
	/**
	 * getAllCartItems method get all the cart items for users
	 * @param userId
	 * @return
	 * @throws CartEmptyException
	 */
	public Cart getAllCartItems(long userId) throws CartEmptyException
	{
		//if
		Cart cart = cartDao.getAllCartItems(userId);
		if(cart.getMenuItemList().isEmpty())
			throw new CartEmptyException();
		else
			return cart;
	}
	
	/**
	 * removeCartItem removes item from cart
	 * @param userId
	 * @param menuItemId
	 */
	public void removeCartItem(long userId,long menuItemId)
	{
		cartDao.removeCartItem(userId, menuItemId);
	}
}
