package src.java.project4fx;

import java.util.ArrayList;

/**
 * StoreOrders holds a list of all the orders of the customers.
 *
 * @author Isaac Brukhman
 */

public class StoreOrders {

    private ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * Default constructor
     */
    public StoreOrders() {
    }

    /**
     * Parameterized constructor that stores the list of orders
     *
     * @param orders the list of orders
     */
    public StoreOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * Adds another customer to the order list
     *
     * @param customer that wants to order
     */
    public void add(Order customer) {
        for (Order a : orders) {
            if (a.getNumber() == customer.getNumber()) {
                continue;
            }
            orders.add(customer);
        }
    }

    /**
     * removes a customer to the order list
     *
     * @param customer that wants to cancel the order
     */
    public void remove(Order customer) {
        for (Order a : orders) {
            if (a.getNumber() == customer.getNumber()) {
                orders.remove(customer);
            }
        }
    }

    /**
     * Setter for the arraylist orders
     *
     * @param orders the orders that will be set
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * Prints the orders financial information
     *
     * @return output the full order information
     */
    public String printOrders() {
        String output = "";

        for (Order o : orders) {
            output += o.printOrder();
        }

        return output;
    }
}
