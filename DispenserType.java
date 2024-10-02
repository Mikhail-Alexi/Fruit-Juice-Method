/*
============================================================================================
FILE : DispenserType.java
AUTHOR : Mikhail Alexi D. Hatulan & Korinne Margaret V. Sasil
DESCRIPTION : Functions as a fruit juice vending machine implemented with Java Swing GUI.
COPYRIGHT : 2024
REVISION HISTORY
Date:           By:             	    Description:
09/25/2024      Hatulan & Sasil        	Prototype class and methods built
10/02/2024      Hatulan        		    Javadoc and single-line documentation added
============================================================================================
*/

/**
 * The DispenserType class represents a dispenser for a specific type of beverage,
 * managing its stock and cost. It allows for retrieval and modification of item count
 * and cost, as well as processing sales.
 */
public class DispenserType {
    private int numberOfItems; // Number of items available in the dispenser
    private double cost; // Cost of each item

    /**
     * Gets the current number of items in the dispenser.
     * 
     * Written by: Sasil
     * 
     * @return the number of items available
     */
    public int getNoOfItems() {
        return numberOfItems; // Return the number of items
    }

    /**
     * Gets the cost of each item in the dispenser.
     * 
     * Written by: Sasil
     * 
     * @return the cost of each item
     */
    public double getCost() {
        return cost; // Return the cost of the item
    }

    /**
     * Sets the number of items available in the dispenser.
     * 
     * Written by: Sasil
     * 
     * @param numItem the new number of items to set
     */
    private void setNoOfItems(int numItem) {
        numberOfItems = numItem; // Update the number of items
    }

    /**
     * Sets the cost of each item in the dispenser.
     * 
     * Written by: Sasil
     * 
     * @param costInput the new cost to set
     */
    private void setCost(double costInput) {
        cost = costInput; // Update the cost
    }

    /**
     * Processes a sale by decrementing the number of items based on the count sold.
     * 
     * Written by: Sasil
     * 
     * @param count the number of items sold
     */
    public void makeSale(int count) {
        numberOfItems = numberOfItems - count; // Decrease the item count
    }

    /**
     * Verifies if there is stock available in the dispenser.
     * 
     * Written by: Hatulan
     * 
     * @return true if there are items in stock, false otherwise
     */
    public boolean verifyStock() {
        // Check if there are items available
        return numberOfItems > 0; // Return true if stock is available
    }

    /**
     * Default constructor that initializes the dispenser with 50 items and a cost of 70.00.
     * 
     * Written by: Hatulan
     */
    public DispenserType() {
        setNoOfItems(50); // Set default number of items
        setCost(70.00); // Set default cost
    }

    /**
     * Constructor that initializes the dispenser with 50 items and a specified cost.
     * 
     * Written by: Hatulan
     * 
     * @param newCost the cost of each item
     */
    public DispenserType(double newCost) {
        setNoOfItems(50); // Set default number of items
        setCost(newCost); // Set specified cost
    }

    /**
     * Constructor that initializes the dispenser with a specified number of items and cost.
     * 
     * Written by: Hatulan
     * 
     * @param newNo the number of items to set
     * @param newCost the cost of each item
     */
    public DispenserType(int newNo, double newCost) {
        setNoOfItems(newNo); // Set specified number of items
        setCost(newCost); // Set specified cost
    }
}
