package database;

import static database.DatabaseConnector.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sessionManagement.Session;

public class DataManager {

	
	
	public DataManager()
	{
		
	}
	
	public ResultSet executeQuery(String QUERY) throws SQLException, Exception {
			Statement statement = getConnection().createStatement();
			ResultSet result = statement.executeQuery(QUERY);
			return result;
	}
	
	public boolean login(String accNumber, int pin) throws SQLException,Exception
	{
		ResultSet result = executeQuery("Select customers.pin from customers"
										+ " where customers.account_number = " + accNumber);
		result.next();
		
		return result.getInt(1) == pin;
		
	}
	
	public float getBalance(Session session) throws SQLException, Exception
	{
		ResultSet result = executeQuery("select balance from customers "
				+ "where account_number =" +session.getAccNumber() +" and pin = " + session.getPin());
				
				result.next();
				return result.getFloat(1);
		
	}
	
	public void updateBalance(float newBalance, Session session) throws SQLException, Exception
	{
		executeQuery("UPDATE customers SET balance = " + newBalance + "WHERE account_number = " 
	+ session.getAccNumber() + " and pin = " + session.getPin());
		
	}
	
}
