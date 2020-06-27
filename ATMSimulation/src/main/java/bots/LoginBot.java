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
		while (true) {
			System.out.print("Enter account Number:");
			String accNumber = Input.getStringInput();

			try {
				if (Integer.parseInt(accNumber) == -1)
					break;// Checking if we want to exit

			} catch (NumberFormatException e) {
				// We are not printing as we do not want to print stacktrace if the user
				// did not enter '-1'

			}

			System.out.println("Enter Pin: ");
			int pin = Input.getIntInput();

			Session session = new Session(accNumber, pin);
			try {

				if (dataManager.login(accNumber, pin)) {
					System.out.println("Welcome, " + dataManager.getName(session) + "!");

					FinancialBot financialBot = new FinancialBot(new Session(accNumber, pin));
					while(!financialBot.getExit())
					{
					financialBot.startup();
					financialBot.performTask(Input.getIntInput());
					}

				} else {
					System.out.println("Wrong input, try again!(Enter -1 to exit in account number)");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
