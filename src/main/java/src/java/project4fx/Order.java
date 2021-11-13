package src.java.project4fx;

import java.util.ArrayList;

/**
 * Order holds the pizzas and phone number for one customer
 *
 * @author Isaac Brukhman
 */

public class Order {

    private ArrayList<Pizza> pizzas;
    private int phoneNumber;

    /**
     * default constructor
     */
    public Order() {
        pizzas = new ArrayList<Pizza>();
        phoneNumber = 0;
    }

    /**
     * parameterized constructor that only stores the phone number
     *
     * @param phoneNumber the customer's phone number
     */
    public Order(int phoneNumber) {
        this.pizzas = new ArrayList<Pizza>();
        this.phoneNumber = phoneNumber;
    }


    /**
     * Fully parameterized constructor that stores the list of pizzas and phone number
     *
     * @param pizzas      the list of pizzas
     * @param phoneNumber the customer's phone number
     */
    public Order(ArrayList<Pizza> pizzas, int phoneNumber) {
        this.pizzas = new ArrayList<Pizza>(pizzas);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Adds a pizza to the order list. Duplicates allowed
     *
     * @param pizza the pizza that will be added to the customer's order
     */
    public void add(Pizza pizza) {
        for (Pizza p : pizzas) {
            pizzas.add(pizza);
        }
    }

    /**
     * removes one pizza from the list even if it occurs more than once
     *
     * @param pizza that wants to cancel the order
     */
    public void remove(Pizza pizza) {
        for (Pizza p : pizzas) {
            if (p.equals(pizza)) {
                pizzas.remove(pizza);
                break;
            }
        }
    }

    /**
     * adds the price of every pizza into the total
     */
    public double calcTotal() {
        double total = 0;

        for (Pizza p : pizzas) {
            total += p.price();
        }

        return total;
    }

    /**
     * Getter for phone number
     *
     * @return phoneNumber the person's phone number
     */
    public int getNumber() {
        return phoneNumber;
    }
}
