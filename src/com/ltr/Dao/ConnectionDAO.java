package com.ltr.Dao;

import static com.ltr.Dao.DBConnection.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class ConnectionDAO implements DBConnection
{
    
	public static Connection con ;
	
	
	public static Connection getDBConnection()
	{try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
	         con = DriverManager.getConnection(url, uname, pwd);
				
		
	} catch (Exception e) 
	{
		System.out.println(" sql exception");
	}
	
		System.out.println("connection......."+con);
		return  con;
		
	}
	
	

}
 