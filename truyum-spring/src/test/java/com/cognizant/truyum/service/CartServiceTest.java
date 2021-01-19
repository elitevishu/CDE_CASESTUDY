package com.cognizant.truyum.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.cognizant.truyum.dao.CartEmptyException;

/**
 * 
 * @author Shiyam
 *
 */
@ImportResource({ "spring-config.xml" })
@Configuration
public class CartServiceTest {

	private CartService cartService;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	/**
	 * initiazes the context scans the components and services intitalizes the
	 * service
	 */

	@Before
	public void initializeService() {
		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
		configApplicationContext.scan("com.cognizant.truyum");
		configApplicationContext.refresh();
		cartService = configApplicationContext.getBean(CartService.class);
		configApplicationContext.close();

	}

	/**
	 * Add the menu item to the cart
	 * 
	 * @result adds menu item to the cart without any errors
	 * @throws CartEmptyException
	 */
	@Test
	public void testAddCartItem() throws CartEmptyException {
		cartService.addCartItem(1, 3);
		assertNotNull(cartService.getAllCartItems(1));
		assertEquals(1, cartService.getAllCartItems(1).size());
	}

	/**
	 * Get all menu items from cart and throw exception if cart is empty
	 * 
	 * @result throws exception
	 * @throws CartEmptyException
	 */

	@Test
	public void testGetAllCartItems() throws CartEmptyException {

		exceptionRule.expect(CartEmptyException.class);
		exceptionRule.expectMessage("Cart is empty");
		assertTrue(cartService.getAllCartItems(1).isEmpty());

	}

	/**
	 * Add menu item to cart Check if cart is updated Remove the item from cart
	 * Check if the item is deleted
	 * 
	 * @result deleted the item from the cart
	 * @throws CartEmptyException when cart is empty
	 */

	@Test(expected = CartEmptyException.class)
	public void testRemoveCartItem() throws CartEmptyException {

		cartService.addCartItem(1, 3);
		assertFalse(cartService.getAllCartItems(1).isEmpty());
		cartService.removeCartItem(1, 3);
		assertTrue(cartService.getAllCartItems(1).isEmpty());

	}

}
