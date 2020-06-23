package bots;

import database.DataManager;
import sessionManagement.Session;

public class FinancialBot extends AbstractBot {

	private Session session;
	private DataManager dataManager;
	
	public FinancialBot(Session session) {
		
		this.session = session;
		dataManager = new DataManager();
		
	}
	
	@Override
	public void startup() {
		// TODO: Make another option for sending money to a bank account
		
		printOptions("Enter 1 to view balance: ", "Enter 2 to credit money: ", "Enter 3 to deposit money",
				"Enter 4 to send money to another bank account.");
		
	}

	
	@Override
	public void performTask(int x) 
	{
		switch(x)
		{
		case 1:
			try {
				System.out.println("Balance: " +  dataManager.getBalance(session));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			
			default:
				end();
				return;
		}
		end();
	}

	@Override
	public void end() {
		System.out.println("Exiting from Financial menu...");
	}

}
