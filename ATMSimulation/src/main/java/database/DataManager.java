package database;

import static database.DatabaseConnector.*;

import java.security.SecureRandom;
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
	
	public int executeUpdate(String QUERY) throws SQLException, Exception {
		Statement statement = getConnection().createStatement();
		int result = statement.executeUpdate(QUERY);
		return result;
}
	
	public void register(String name) throws SQLException, Exception
	{
		
		// Let us make a random generated account_number and pin
		SecureRandom random = new SecureRandom();
		String accNumber;

		// We are checking wether this account number exists or not
		while(true){
			accNumber = String.valueOf(random.nextInt(10000)) + String.valueOf(random.nextInt(10000))
		 + String.valueOf(random.nextInt(10000));
		 
			ResultSet result = executeQuery("SELECT * FROM customers where account_number = " + accNumber);

			if(!result.next())// if result set is empty, we will break from the loop
			{
				break;
			}

		}

		 int pin = random.nextInt(10000);// generating a pin

		 String QUERY = "INSERT INTO customers(account_number, name, pin) VALUES(" + accNumber + ", \'"  + name + "\', " + pin + ")";						  

		 executeUpdate(QUERY);
						 
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

	public float getBalance(String accountNumber) throws SQLException, Exception
	{
		ResultSet result = executeQuery("select balance from customers "
				+ "where account_number =" + accountNumber);
				
				result.next();
				return result.getFloat(1);	
	}
	
	public void updateBalance(float newBalance, Session session) throws SQLException, Exception
	{
		executeUpdate("UPDATE customers SET balance = " + newBalance + "WHERE account_number = " 
	+ session.getAccNumber() + " and pin = " + session.getPin());
		
	}

	public void updateBalance(float newBalance, String accountNumber) throws SQLException, Exception
	{
		executeUpdate("UPDATE customers SET balance = " + newBalance + "WHERE account_number = " + accountNumber);
		
	}

	// if transaction was successfull, returns true, else returns false
	public boolean transferMoney(Session session, String recieverAccountNumber, float money)
			throws SQLException, Exception
	{
		if(getBalance(session) < money)
		return false;

		// Verifying if payment details are correct
		ResultSet result1 = executeQuery("SELECT * FROM customers WHERE account_number = " + recieverAccountNumber);
		if(!result1.next())
		return false;
		
		float balanceOfPayer = getBalance(session);
		balanceOfPayer -= money;

		float balanceOfReciever = getBalance(recieverAccountNumber);
		balanceOfReciever += money;

		updateBalance(balanceOfPayer, session);
		updateBalance(balanceOfReciever, recieverAccountNumber);

		System.out.println("Transaction successful");

		return true;
		
	}
	
}
