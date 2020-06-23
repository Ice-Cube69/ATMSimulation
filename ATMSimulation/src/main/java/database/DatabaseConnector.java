package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector 
{
	private static Connection connection;
	private static boolean isInit;
	

	
	public static Connection getConnection() throws Exception
	{
		if(!isInit) {
			throw new Exception("Database Connector has not been initialized.");
		}
		
		return connection;
	}
	
	public static void init() throws SQLException {
		isInit = true;
		String DATABASE_URL = "jdbc:mysql://localhost:3306/java";
		connection = DriverManager.getConnection(DATABASE_URL, "root", "1234");
		
	}

}