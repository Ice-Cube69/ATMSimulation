package bots;

import java.sql.ResultSet;

import database.DataManager;
import main.Input;

public class RegisterBot extends AbstractBot {

    private DataManager dataManager;
    
    @Override
    public void startup() {
        System.out.println("Enter name: ");
        Input.getStringInput();// TODO: Delete the below statement
        String name = Input.getStringInput();
        dataManager = new DataManager();
        
        ResultSet result;
        String accNumber;
        int pin;

        try {
            dataManager.register(name);
            result = dataManager.executeQuery("Select * from customers where name = \'" + name + "\'");
           
            result.next();

            accNumber = result.getString(1);
            pin = result.getInt(3);

            System.out.println("Account Number: " + accNumber);
            System.out.println("Pin: " + pin);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void performTask(int x) {

    }

    @Override
    public void end() {
    }
    
}