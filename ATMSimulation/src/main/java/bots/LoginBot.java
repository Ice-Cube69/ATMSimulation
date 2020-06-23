package bots;

import database.DataManager;
import main.Input;
import sessionManagement.Session;

public class LoginBot extends AbstractBot {

	private DataManager dataManager;

	public LoginBot() {
		dataManager = new DataManager();
	}

	@Override
	public void startup() {
		login();

	}

	@Override
	public void performTask(int x) {

	}

	@Override
	public void end() {
		System.out.println("Exiting login Menu");
	}

	private void login() {
		
		System.out.print("Enter account Number:");
		Input.getStringInput();// TODO: Figure out why we have to do this!
		String accNumber = Input.getStringInput();
		
		  try { if(Integer.parseInt(accNumber)== -1)
			  return;// Checking if we want to exit
		  
		  } catch (NumberFormatException e) {
		  
		  }
		 

		System.out.println("Enter Pin: ");
		int pin = Input.getIntInput();
		try {
			if (dataManager.login(accNumber, pin)) {
				System.out.println("Login sucessful!");
				
				FinancialBot FinancialBot = new FinancialBot(new Session(accNumber, pin));
				FinancialBot.startup();
				FinancialBot.performTask(Input.getIntInput());
				
			} else {
				System.out.println("Wrong input, try again!(Enter -1 to exit in account number)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
