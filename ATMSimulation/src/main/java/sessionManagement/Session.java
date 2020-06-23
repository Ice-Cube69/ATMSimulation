package sessionManagement;

public class Session {
	private String accNumber;
	private int pin;
	
	public Session(String accNumber, int pin) {
		this.accNumber = accNumber;
		this.pin = pin;
		
	}
	
	public String getAccNumber() {
		return accNumber;
	}

	public int getPin() {
		return pin;
	}
	
	
}
