package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
	
	static Scanner scanner;
	
	public static void init() {
		scanner = new Scanner(System.in);
	}
	
	public static int getIntInput() {
		int input = -1;
		try {
			input = scanner.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Wrong format.Try Again!");
			input = getIntInput();
		}
		
		return input;
	}
	
	public static String getStringInput() 
	{
		String input = new String(scanner.nextLine());
		return input;
	}
	
	public static Scanner getScanner()
	{
		return scanner;
	}
	
	public static void releaseResources() {scanner.close();}

}
