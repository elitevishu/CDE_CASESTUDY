package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	public static void main(String[] args) throws ParseException {

		testRemoveCartItem();
	}

	public static void testRemoveCartItem() throws ParseException {
		double total = 0;
		CartDaoCollectionImpl cartDaoCollectionImpl = new CartDaoCollectionImpl();
		List<MenuItem> list;
		try {
			list = cartDaoCollectionImpl.getAllCartItems(1);
			for (MenuItem menuItem : list) {
				System.out.println(menuItem.getId() + " " + menuItem.getName() + " " + menuItem.getCategory() + " "
						+ menuItem.getPrice() + " " + menuItem.getDateOfLaunch());
				total = total + menuItem.getPrice();
			}

			System.out.println("Cart Total " + total);

		} catch (CartEmptyException e1) {
		}

		cartDaoCollectionImpl.removeCartItem(1, 101);
		System.out.println("After removing item");
		total = 0;
		try {
			list = cartDaoCollectionImpl.getAllCartItems(1);

			for (MenuItem menuItem : list) {
				System.out.println(menuItem.getId() + " " + menuItem.getName() + " " + menuItem.getCategory() + " "
						+ menuItem.getPrice() + " " + menuItem.getDateOfLaunch());

				total = total + menuItem.getPrice();

			}
			System.out.println("Cart Total " + total);

		} catch (CartEmptyException e) {
			e.printStackTrace();
		}

	}

}
