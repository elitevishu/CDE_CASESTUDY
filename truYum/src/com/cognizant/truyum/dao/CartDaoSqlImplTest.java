package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

	public static void main(String[] args) {

		CartDaoSqlImplTest C = new CartDaoSqlImplTest();
		C.testAddCartItem();
		System.out.println("Items in Cart:");
		C.testGetAllCartItems();
		System.out.println("Remove Item:");
		C.testRemoveCartItem();
		C.testGetAllCartItems();

	}

	public void testAddCartItem() {

		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem(1, 1);
		cartDao.addCartItem(1, 3);
		cartDao.addCartItem(1, 2);

	}

	public void testGetAllCartItems() {
		double total = 0;
		CartDao cartDao = new CartDaoSqlImpl();
		List<MenuItem> list;
		try {
			list = cartDao.getAllCartItems(1);
			for (MenuItem item : list) {
				System.out.println(item);
				total = total + item.getPrice();
			}

			System.out.println("Cart Total: " + total);
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			System.out.println("Cart is Empty!!! ADD ITEMS");
		}
	}

	public void testRemoveCartItem() {

		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem(1, 103);

	}

}
