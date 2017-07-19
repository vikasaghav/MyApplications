package com.zensar.banking.connectionprovider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionProvider {
public static Connection getConnection(){
	
	Properties properties=new Properties();
	try {
		properties.load(new FileReader("D:\\Java Training\\Vikas\\ZenBank\\resources\\zensarbanking.properties"));
		String driver=properties.getProperty("driver");
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		return con;



		
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	return null;
	
	
}
}
