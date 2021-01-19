//package com.cognizant.truyum.dao;
//
//public class ConnectionHandler {
//
//}

package com.cognizant.truyum.dao;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	public static Connection getConnection()
	{
		Connection con = null;
		Properties props = new Properties();
		
		
	    try
	    {
	    	FileInputStream fis = null;
			fis = new FileInputStream("src\\main\\resources\\connection.properties");
			props.load(fis);
			
			// load the Driver Class
			Class.forName(props.getProperty("DB_DRIVER_CLASS"));
			con = DriverManager.getConnection(props.getProperty("DB_URL"),props.getProperty("DB_USERNAME"),props.getProperty("DB_PASSWORD"));
	    	
	    	
	    }
	    catch (SQLException e) 
	    {
		
	    	System.out.println("Not connected to the database");
			e.printStackTrace();
		} catch (IOException e) 
	    {
			System.out.println("Connection Properties file not found");
			e.printStackTrace();
		}
	    catch(ClassNotFoundException e)
	    {
	    	e.printStackTrace();
	    }
	    return con;
	}

}

