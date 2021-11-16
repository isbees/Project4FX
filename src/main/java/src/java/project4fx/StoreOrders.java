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
     * finds the customer based on their phone number
     *
     * @param customer the customer's order
     * @return int if not found return -1
     */
    private int find(Order customer) {
        if (orders.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < orders.size() + 1; i++) {
            if (orders.get(i).getNumber().equals(customer.getNumber())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * finds the customer based on their phone number
     *
     * @param number the customer's phone number
     * @return int if not found return -1
     */
    public int find(String number) {
        for (int i = 0; i < orders.size() + 1; i++) {
            if (orders.get(i).getNumber().equals(number)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds another customer to the order list
     *
     * @param customer that wants to order
     */
    public void add(Order customer) {
        orders.add(customer);
    }

    /**
     * Overloaded add that finds the customer and adds a pizza to their
     *
     * @param customer that wants to order
     */
    public void add(Order customer, Pizza pizza) {
        int i = find(customer);

        System.out.println(i);

        if (orders.isEmpty()) {
            orders.add(customer);
            System.out.println("Adding pizza! Empty");
        } else {
            if (i > -1) {
                System.out.println("Adding pizza! -1");
                orders.get(i).add(pizza);
            } else {    // case that the customer was not found and the list is initialized
                System.out.println("Adding pizza!");
                orders.add(customer);
                orders.get(find(customer)).add(pizza);
            }
        }
    }

    /**
     * removes a customer to the order list
     *
     * @param customer that wants to cancel the order
     */
    public void remove(Order customer) {
        for (Order a : orders) {
            if (a.getNumber().equals(customer.getNumber())) {
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

        for (int i = 0; i < orders.size() - 1; i++) {
            //System.out.println("order: "+ o.printOrder());
            output += orders.get(i).printOrder();
        }

        return output;
    }

    /**
     * Prints one of the orders by index
     *
     * @return String the full order information
     */
    public String printOrder(int i) {
        return orders.get(i).toString();
    }

    /**
     * Gets the total number of orders in the list
     *
     * @return int the number of orders
     */
    public int getTotalOrders() {
        return orders.size();
    }

    /**
     * Gets one order by index
     *
     * @return Order the order
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }
}
