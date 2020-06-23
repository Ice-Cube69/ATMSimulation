package bots;


public abstract class AbstractBot {
	
	
	public AbstractBot() {
		
	}
	
	public abstract void startup();
	public abstract void performTask(int x);
	public abstract void end();
	
	protected void printOptions(String... options)
	{
		for(String option : options)
		{
			System.out.println(option);
		}
	}
	

}
