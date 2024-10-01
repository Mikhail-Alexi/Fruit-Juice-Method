/*
============================================================================================
FILE : CashRegister.java
AUTHOR : Mikhail Alexi D. Hatulan & Arnold Joseph C. Najera Jr.
DESCRIPTION : Functions as a fruit juice vending machine implemented with Java Swing GUI.
COPYRIGHT : 2024
REVISION HISTORY
Date:           By:             		Description:
09/25/2024      Hatulan & Najera        Prototype class and methods built
10/02/2024      Hatulan        			Javadoc and single-line documentation added
============================================================================================
*/

/**
 * The CashRegister class represents a cash register that manages the cash on hand.
 * It allows for retrieving the current balance and accepting cash amounts.
 */
public class CashRegister {
    private double cashOnHand; // Current amount of cash in the register

    /**
     * Gets the current balance of cash in the register.
     * 
     * Written by: Najera
     * 
     * @return the current balance of cash
     */
    public double getCurrentBalance() {
        return cashOnHand; // Return the current cash balance
    }

    /**
     * Sets the current balance of cash in the register.
     * 
     * Written by: Najera
     * 
     * @param newCash the new cash amount to set
     */
    private void setCurrentBalance(double newCash) {
        cashOnHand = newCash; // Update the cash balance
    }

    /**
     * Accepts an amount of cash and adds it to the current balance.
     * 
     * Written by: Najera
     * 
     * @param amountIn the amount of cash to accept
     */
    public void acceptAmount(double amountIn) {
        setCurrentBalance(cashOnHand + amountIn); // Increase the cash balance
    }

    /**
     * Default constructor that initializes the cash register with a balance of 5000.00.
     * 
     * Written by: Hatulan
     */
    public CashRegister() {
        cashOnHand = 5000.00; // Set default cash balance
    }

    /**
     * Constructor that initializes the cash register with a specified cash amount.
     * 
     * Written by: Hatulan
     * 
     * @param cashIn the initial cash amount to set
     */
    public CashRegister(double cashIn) {
        cashOnHand = cashIn; // Set initial cash balance
    }
}
