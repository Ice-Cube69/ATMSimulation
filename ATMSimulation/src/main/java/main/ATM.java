package main;

import bots.IntroBot;

public class ATM 
{
	private IntroBot introBot;
	
	public ATM() 
	{
		introBot = new IntroBot();
	}
	
	public void start() 
	{
		introBot.startup();
		introBot.performTask(Input.getIntInput());
	}
	

}
