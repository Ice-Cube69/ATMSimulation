package main;

import java.sql.SQLException;
import database.DatabaseConnector;

public class Main {

	public static void main(String[] args) {
		init();
		ATM atm = new ATM();
		atm.start();
		releaseResources();
		
	}
	
	private static void init() {
		Input.init();
		try {
			DatabaseConnector.init();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void releaseResources() {
		Input.releaseResources();
	}

}
