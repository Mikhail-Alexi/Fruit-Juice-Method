/*
============================================================================================
FILE : FruitJuiceMethod.java
AUTHOR : Mikhail Alexi D. Hatulan, Arnold Joseph C. Najera Jr., & Korinne Margaret V. Sasil
DESCRIPTION : Functions as a fruit juice vending machine implemented with Java Swing GUI.
COPYRIGHT : 2024
REVISION HISTORY
Date:           	By:             			Description:
09/25/2024      	Hatulan         			Prototype console class built and tested
09/26/2024      	Najera & Sasil        		Initial Java Swing GUI implementation
09/30/2024      	Hatulan & Sasil     		Debugging of Java Swing GUI Implementation
10/01/2024      	Najera      				Modification of GUI messages and looping
10/02/2024      	Hatulan    					Modification of cancel button and O.o.S.
												message condition
10/02/2024     		Hatulan        				Javadoc and single-line documentation added
============================================================================================
*/

import javax.swing.*;
import java.text.DecimalFormat;

/**
 * The FruitJuiceMethod class simulates a fruit juice vending machine,
 * allowing users to select and purchase juices while handling stock management
 * and payment processing.
 */
public class FruitJuiceMethod {

    private static DispenserType apple;
    private static DispenserType orange;
    private static DispenserType mango;
    private static DispenserType punch;
    private static CashRegister vendor;
    
    private static DecimalFormat df;

    /**
     * The main method to start the Fruit Juice Machine application.
     * It displays a welcome message and continuously shows the stock until the user decides to exit.
     * 
     * Written by: Hatulan, Najera, & Sasil
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
    	initializeComponents();
    	
    	boolean continuing;

        // Show welcome message
        JOptionPane.showMessageDialog(null, "Fruit Juice Machine");

        // Loop until user chooses to exit
        do {
            showStock(); // Display available stock
            continuing = programTerminator(); // Ask if the user wants to continue
        } while (continuing);

        // Thank the user for using the machine
        JOptionPane.showMessageDialog(null, "Thank you for using the Fruit Juice Machine!");
    }

    /** 
     * Initializes the dispenser and cash register components. 
    */ 
    private static void initializeComponents() { 
    	apple = new DispenserType(); // Initialize dispenser for apple juice 
    	orange = new DispenserType(60.00); // Initialize dispenser for orange juice 
    	mango = new DispenserType(75.00); // Initialize dispenser for mango juice 
    	punch = new DispenserType(80.00); // Initialize dispenser for punch juice 
    	vendor = new CashRegister(); // Initialize cash register 
    	df = new DecimalFormat("0.00"); // Format for currency
    }
    
    /**
     * Displays the available juice stock and prompts the user to select a juice.
     * If the user cancels, it returns to the menu.
     * 
     * Written by: Hatulan & Najera
     */
    public static void showStock() {
        String stockInfo = "a"; // Initialize stock info
        stockInfo = JOptionPane.showInputDialog("Select from the juices available:\nID - | - ITEM NAME - | - ITEM QTY\n1    |  Apple Juice  | " + apple.getNoOfItems() + "\n2    |  Orange Juice | " + orange.getNoOfItems() + "\n3    |  Mango Juice  | " + mango.getNoOfItems() + "\n4    |  Punch Juice  | " + punch.getNoOfItems() + "\n\nEnter juice choice (input num)");

        // Check for cancellation
        if (stockInfo == null) {
            return; // Go back to menu without exiting
        }

        selectProduct(stockInfo); // Proceed to product selection
    }

    /**
     * Processes the user's choice of juice and verifies stock before proceeding with the order.
     * 
     * Written by: Hatulan & Najera
     * 
     * @param input the user's input representing their juice choice
     */
    public static void selectProduct(String input) {
        int choice;
        try {
            choice = Integer.parseInt(input); // Parse the user's choice
        } catch (NumberFormatException e) {
            // Handle invalid input
            JOptionPane.showMessageDialog(null, "The inputted choice is invalid. Please try again.");
            showStock(); // Go back to menu if invalid choice
            return; // Exit the method
        }

        DispenserType selectedJuice = null; // Initialize selected juice

        // Determine which juice was selected based on user input
        switch (choice) {
            case 1:
                selectedJuice = apple; // Set selected juice to apple
                break;
            case 2:
                selectedJuice = orange; // Set selected juice to orange
                break;
            case 3:
                selectedJuice = mango; // Set selected juice to mango
                break;
            case 4:
                selectedJuice = punch; // Set selected juice to punch
                break;
            default:
                // Handle invalid choice
                JOptionPane.showMessageDialog(null, "The inputted choice is invalid. Please try again.");
                showStock(); // Go back to menu if out of stock
                return; // Exit the method
        }

        // Check stock before processing order
        if (selectedJuice.getNoOfItems() <= 0) {
            JOptionPane.showMessageDialog(null, "Sorry, this product is out of stock."); // Inform user of stock issue
            showStock(); // Go back to menu if out of stock
        } else {
            processOrder(selectedJuice, choice); // Process the order for the selected juice
        }
    }

    /**
     * Processes the order for the selected juice, handling quantity input and payment.
     * 
     * Written by: Hatulan & Najera
     * 
     * @param juice the selected juice to process the order for
     * @param choice the user's choice of juice ID
     */
    private static void processOrder(DispenserType juice, int choice) {
        String countInput;

        // Prompt for quantity of juice to purchase
        countInput = JOptionPane.showInputDialog("Juice choice:\nID - | - ITEM NAME - | - ITEM QTY - | - ITEM PRICE\n1    |  " + getJuiceName(choice) + "   |           " + juice.getNoOfItems() + "         | Php. " + df.format(juice.getCost()) + "\nHow many items would you like to purchase?");

        // Check for cancellation
        if (countInput == null) {
            showStock(); // Go back to menu
            return; // Exit the method
        }

        // Get validated count of juice to purchase
        int count = receiveCount(juice.getNoOfItems(), countInput);
        double actualCost = count * juice.getCost(); // Calculate total cost

        // Prompt for cash input
        String cashInput = JOptionPane.showInputDialog("Total cost to pay: Php. " + df.format(actualCost) + "\nEnter amount to pay: Php.");

        // Check for cancellation
        if (cashInput == null) {
            showStock(); // Go back to menu
            return; // Exit the method
        }

        double cash = receiveCash(actualCost, cashInput); // Get validated cash input

        // Update stock and register
        juice.makeSale(count);
        vendor.acceptAmount(actualCost);

        double change = returnChange(cash, actualCost); // Calculate change to return
        JOptionPane.showMessageDialog(null, "Your change is: Php. " + df.format(change)); // Display change

        double currentBalance = vendor.getCurrentBalance() - change; // Get current balance in register
        JOptionPane.showMessageDialog(null, "Current balance in register: Php. " + df.format(currentBalance)); // Display current balance
    }

    /**
     * Retrieves the name of the juice based on the user's choice.
     * 
     * Written by: Najera & Sasil
     * 
     * @param choice the user's choice of juice ID
     * @return the name of the juice
     */
    private static String getJuiceName(int choice) {
        switch (choice) {
            case 1: return "Apple Juice"; // Return name for apple juice
            case 2: return "Orange Juice"; // Return name for orange juice
            case 3: return "Mango Juice"; // Return name for mango juice
            case 4: return "Punch Juice"; // Return name for punch juice
            default: return "Unknown Juice"; // Default case
        }
    }

    /**
     * Receives the quantity input from the user and validates it against available stock.
     * 
     * Written by: Hatulan, Najera, & Sasil
     * 
     * @param stock the current stock of the selected juice
     * @param input the user's input for quantity
     * @return the validated quantity
     */

    public static int receiveCount(int stock, String input) {

        if (input == null) {
            showStock(); // Go back to menu if canceled
            return 0; // Return a dummy value, as this will not be used
        }

        int newValue;

        try {
            newValue = Integer.parseInt(input); // Parse the user's input
            if (newValue > 0 && newValue <= stock) {
                return newValue; // Valid quantity
            } else {
                // Handle invalid quantity input
                JOptionPane.showMessageDialog(null, "Please enter a valid quantity between 1 and " + stock + ".");
                return receiveCount(stock, JOptionPane.showInputDialog("Enter quantity (1 to " + stock + "):")); // Prompt again
            }
        } catch (NumberFormatException e) {
            // Handle non-integer input
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            return receiveCount(stock, JOptionPane.showInputDialog("Enter quantity (1 to " + stock + "):")); // Prompt again
        }
    }


    /**
     * Receives and validates the cash amount input from the user.
     * 
     * @param actualCost the total cost of the order
     * @param input the user's cash input
     * @return the validated cash amount
     */

    public static double receiveCash(double actualCost, String input) {
        if (input == null) {
            showStock(); // Go back to menu if canceled
            return 0; // Return a dummy value, as this will not be used
        }

        double newValue;

        try {
            newValue = Double.parseDouble(input); // Parse cash input
            double change = newValue - actualCost; // Calculate change
            double currentBalance = vendor.getCurrentBalance(); // Get current balance in register

            if (verifyCashAmount(newValue, actualCost) && change <= currentBalance) {
                return newValue; // Valid cash amount
            } else if (change > currentBalance) {
                // Handle insufficient cash for change
                JOptionPane.showMessageDialog(null, "Insufficient cash in the register to give change of Php. " + df.format(change) + ". Please enter a different amount.");
                return receiveCash(actualCost, JOptionPane.showInputDialog("Enter amount to pay (at least Php. " + df.format(actualCost) + "): Php.")); // Prompt again
            } else {
                // Handle insufficient cash input
                JOptionPane.showMessageDialog(null, "Please enter an amount greater than or equal to Php. " + df.format(actualCost));
                return receiveCash(actualCost, JOptionPane.showInputDialog("Enter amount to pay (at least Php. " + df.format(actualCost) + "): Php.")); // Prompt again
            }
        } catch (NumberFormatException e) {
            // Handle non-numeric input
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid cash amount.");
            return receiveCash(actualCost, JOptionPane.showInputDialog("Enter amount to pay (at least Php. " + df.format(actualCost) + "): Php.")); // Prompt again
        }
    }

    /**
     * Verifies if the entered cash amount is sufficient to cover the actual cost.
     * 
     * Written by: Hatulan
     * 
     * @param newValue the cash amount provided by the user
     * @param actualCost the total cost of the order
     * @return true if sufficient, false otherwise
     */
    private static boolean verifyCashAmount(double newValue, double actualCost) {
        return newValue >= actualCost; // Return true if cash is sufficient
    }

    /**
     * Calculates the change to return to the user after a successful payment.
     * 
     * Written by: Hatulan
     * 
     * @param newCash the cash amount provided by the user
     * @param actualCost the total cost of the order
     * @return the change to return
     */
    private static double returnChange(double newCash, double actualCost) {
        return newCash - actualCost; // Calculate and return change
    }

    /**
     * Prompts the user to decide whether to continue using the machine.
     * 
     * Written by: Hatulan, Najera, & Sasil
     * 
     * @return true if the user wants to continue, false otherwise
     */
    private static boolean programTerminator() {
        boolean validInput = true; // Initialize input validity

        // Options for user to continue or not
        String[] options = {"Yes", "No"};
        int choice = JOptionPane.showOptionDialog(null,
            "Do you like to purchase again? (Y/N) ",
            "Continuing Choice",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        // Determine user choice
        switch (choice) {
            case 0:
                break; // User chose to continue
            case 1:
                validInput = false; // User chose not to continue
                break;
        }

        return validInput; // Return user's choice
    }
    
}
