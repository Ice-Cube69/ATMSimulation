package bots;

import database.DataManager;
import main.Input;
import sessionManagement.Session;

public class FinancialBot extends AbstractBot {

	private Session session;
	private DataManager dataManager;
	private boolean exit;

	public FinancialBot(Session session) {

		this.session = session;
		dataManager = new DataManager();
		exit = false;

	}

	@Override
	public void startup() {

		printOptions("Enter 1 to view balance: ", "Enter 2 to credit money: ", "Enter 3 to deposit money",
				"Enter 4 to send money to another bank account.");

	}

	@Override
	public void performTask(int x) {
		switch (x) {
			case 1:
				try {
					System.out.println("Balance: " + dataManager.getBalance(session));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2: // Crediting Money
			try{
				// Taking input from the user
				System.out.println("Enter the amount of money to be credited: ");
				float creditedMoney = Input.getScanner().nextFloat();
				float balance = dataManager.getBalance(session) - creditedMoney;
				
				// Updating the balance
				dataManager.updateBalance(balance, session);
				
				// Printing the new balance
				System.out.println("Balance: " + dataManager.getBalance(session));
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
		case 3:
			try{
				// Taking input from the user
				System.out.println("Enter the amount of money to be debited: ");
				float debitedMoney = Input.getScanner().nextFloat();
				float balance = dataManager.getBalance(session) + debitedMoney;

				// Updating the balance
				dataManager.updateBalance(balance, session);
				
				// Printing the new balance
				System.out.println("Balance: " + dataManager.getBalance(session));
				} 
			catch (Exception e) {
					e.printStackTrace();
				}
			break;
		case 4:
		// Taking input from user about reciever
		try{
		System.out.println("Enter account number of reciever: ");
		String accountNumberOfReciever = Input.getStringInput();

		System.out.println("Enter amount of money to be transferred: ");
		float money = Input.getScanner().nextFloat();
		
		dataManager.transferMoney(session, accountNumberOfReciever, money);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			break;
			
			default:
				end();
				return;
		}
	}

	@Override
	public void end() {
		System.out.println("Exiting from Financial menu...");
		exit = true;
	}

	public boolean getExit(){
		return exit;
	}

}
