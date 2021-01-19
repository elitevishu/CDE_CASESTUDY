package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	static Connection connection = null;

	public static Connection getConnection() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/database.properties"));
			Class.forName(prop.getProperty("DB_DRIVER_CLASS"));
			connection = DriverManager.getConnection(prop.getProperty("DB_URL"), prop.getProperty("DB_USERNAME"),
					prop.getProperty("DB_PASSWORD"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		System.out.println(ConnectionHandler.getConnection());
	}

}
