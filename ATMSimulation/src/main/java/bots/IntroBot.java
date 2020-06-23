package bots;

public class IntroBot extends AbstractBot{
	
	
	@Override
	public void startup() {
		
		System.out.println("Welcome to ATM Simulation!");
		printOptions("Enter 0 to register", "Enter 1 to login");
		
		
	}

	@Override
	public void performTask(int x) {
		switch (x) {
		case 0:
			System.out.println("Register");
			break;
			
		case 1:
			LoginBot lbot = new LoginBot();
			lbot.startup();
			break;

		default:
			end();
			return;
		}
		
		end();
		
	}

	@Override
	public void end() {
		System.out.println("Exiting from ATM");	
	}

}