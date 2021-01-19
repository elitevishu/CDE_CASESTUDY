package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class CartDaoCollectionImpl implements CartDao {

	private static HashMap<Long, Cart> userCarts;

	public static HashMap<Long, Cart> getUserCarts() {
		return userCarts;
	}

	public static void setUserCarts(HashMap<Long, Cart> userCarts) {
		CartDaoCollectionImpl.userCarts = userCarts;
	}

	public CartDaoCollectionImpl() throws ParseException {
		if (userCarts == null) {
			HashMap<Long, Cart> cart = new HashMap<>();
			List<MenuItem> list = new ArrayList<>();
			list.add(new MenuItem(101, "Sandwich", 99.00f, true, DateUtil.convertToDate("15/03/2017"), "Main Course",
					true));
			list.add(new MenuItem(102, "Burger", 129.00f, true, DateUtil.convertToDate("23/12/2017"), "Main Course",
					false));
			double total = 0;
			for (int i = 0; i < list.size(); i++) {
				total += list.get(i).getPrice();
			}
			Cart ob = new Cart(list, total);
			cart.put((long) 1, ob);
			total = 0;
			ob = null;
			list = new ArrayList<>();
			list.add(new MenuItem(101, "Sandwich", 99.00f, true, DateUtil.convertToDate("15/03/2017"), "Main Course",
					true));
			list.add(new MenuItem(102, "Burger", 129.00f, true, DateUtil.convertToDate("23/12/2017"), "Main Course",
					false));
			list.add(new MenuItem(103, "Pizza", 149.00f, true, DateUtil.convertToDate("21/08/2018"), "Main Course",
					false));
			for (int i = 0; i < list.size(); i++) {
				total += list.get(i).getPrice();
			}
			ob = new Cart(list, total);
			cart.put((long) 2, ob);

			setUserCarts(cart);
		}
	}

	@SuppressWarnings("null")
	@Override
	public void addCartItem(long userId, long menuItemId) {
		Cart ob = userCarts.get(userId);
		MenuItemDaoCollectionImpl menuItemDaoCollectionImpl = new MenuItemDaoCollectionImpl();

		if (ob == null) {
			List<MenuItem> list = new ArrayList<>();
			list.add(menuItemDaoCollectionImpl.getMenuItem(menuItemId));
			double total = menuItemDaoCollectionImpl.getMenuItem(menuItemId).getPrice();
			ob.setMenuItemList(list);
			ob.setTotal(total);
			userCarts.put(userId, ob);
		} else {
			List<MenuItem> list = ob.getMenuItemList();
			list.add(menuItemDaoCollectionImpl.getMenuItem(menuItemId));
			double total = ob.getTotal() + menuItemDaoCollectionImpl.getMenuItem(menuItemId).getPrice();
			ob.setMenuItemList(list);
			ob.setTotal(total);
			userCarts.put(userId, ob);
		}

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {

		if (userCarts.isEmpty()) {
			throw new CartEmptyException("Empty List found exception");
		}
		return userCarts.get(userId).getMenuItemList();
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		Cart ob = userCarts.get(userId);
		if (ob == null) {
			try {
				throw new CartEmptyException("No user found");
			} catch (CartEmptyException e) {
				System.out.println(e.toString());
			}
		}
		List<MenuItem> list = ob.getMenuItemList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == menuItemId) {
				double total = ob.getTotal() - list.get(i).getPrice();
				ob.setTotal(total);
				list.remove(i);
				break;
			}
		}
		ob.setMenuItemList(list);
		userCarts.put(userId, ob);

	}

}