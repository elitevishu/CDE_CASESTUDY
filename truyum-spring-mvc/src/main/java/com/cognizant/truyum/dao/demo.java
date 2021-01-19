package com.cognizant.truyum.dao;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.truyum.service.CartItemService;
import com.cognizant.truyum.service.MenuItemService;

public class demo {
	
	//@Autowired
	//private static MenuItemService obj;
	
	
	public static void main(String arg[]) throws CartEmptyException
	{
		Connection con = ConnectionHandler.getConnection();
	
		System.out.println(con);

	}
}
