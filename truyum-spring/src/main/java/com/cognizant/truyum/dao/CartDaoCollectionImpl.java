package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;


/**
 * 
 * @author Shiyam
 *
 */
public class CartDaoCollectionImpl implements CartDao {

	@Autowired
	MenuItemDaoCollectionImpl menuItemDaoCollectionImpl;
	
	
	
	private HashMap<Long, Cart> userCarts;
	
	
	
	/**
	 * contructor for injection
	 */
	public CartDaoCollectionImpl(HashMap<Long, Cart> userCarts) {
       this.userCarts =userCarts;
	}

	public CartDaoCollectionImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * adds item to cart
	 */
	@Override
	public void addCartItem(long userid, long menuItemId) {

		List<MenuItem> menuItemList;

		MenuItemDao menuItemDao = menuItemDaoCollectionImpl;

		Long userId = new Long(userid);
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);

		if (userCarts.containsKey(userId)) {
			Cart cart = userCarts.get(userId);
			menuItemList = cart.getMenuItemList();
			menuItemList.add(menuItem);
			cart.setMenuItemList(menuItemList);
			cart.setTotal(cart.getTotal() + menuItem.getPrice());
			userCarts.put(userId, cart);

		} else {
			menuItemList = new ArrayList<MenuItem>();
			menuItemList.add(menuItem);

			Cart cart = new Cart(menuItemList, menuItem.getPrice());
			userCarts.put(userId, cart);

		}

	}
	
    /**
     * get the all cart items  from Cart
     */
	@Override
	public List<MenuItem> getAllCartItems(long userid) throws CartEmptyException {

		Cart cart = userCarts.get(new Long(userid));
		List<MenuItem> menuItemList = cart.getMenuItemList();
		if (menuItemList == null || menuItemList.size() == 0) {
			throw new CartEmptyException("Cart is empty");
		}
		double total = 0.0;
		for (MenuItem menuItem : menuItemList) {
			total = total + menuItem.getPrice();

		}
		cart.setTotal(total);

		return menuItemList;
	}
	
    /**
     * deletes the  item from the cart 
     */
	@Override
	public void removeCartItem(long userId, long menuitemid) {
		Cart cart = userCarts.get(userId);
		ListIterator<MenuItem> iterator = cart.getMenuItemList().listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId() == menuitemid) {
				iterator.remove();
			}
		}

	}

}
