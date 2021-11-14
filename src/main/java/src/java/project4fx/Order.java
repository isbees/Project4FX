package src.java.project4fx;

import java.util.ArrayList;

/**
 * Order holds the pizzas and phone number for one customer
 *
 * @author Isaac Brukhman
 */

public class Order {

    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private String phoneNumber;

    /**
     * default constructor
     */
    public Order() {
        pizzas = new ArrayList<Pizza>();
        phoneNumber = "";
    }

    /**
     * parameterized constructor that only stores the phone number
     *
     * @param phoneNumber the customer's phone number
     */
    public Order(String phoneNumber) {
        this.pizzas = new ArrayList<Pizza>();
        this.phoneNumber = phoneNumber;
    }


    /**
     * Fully parameterized constructor that stores the list of pizzas and phone number
     *
     * @param pizzas      the list of pizzas
     * @param phoneNumber the customer's phone number
     */
    public Order(ArrayList<Pizza> pizzas, String phoneNumber) {
        this.pizzas = new ArrayList<Pizza>(pizzas);
        this.phoneNumber = phoneNumber;
    }

    /**
     * Fully parameterized constructor that stores one pizza and phone number
     *
     * @param pizza      the list of pizzas
     * @param phoneNumber the customer's phone number
     */
    public Order(Pizza pizza, String phoneNumber) {
        this.pizzas.add(pizza);
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
    public double calcSubTotal() {
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
    public String getNumber() {
        return phoneNumber;
    }

    /**
     * Prints the orders financial information
     *
     * @return output the full order information
     */
    public String printOrder() {
        String output = toString();
        double subTotal = calcSubTotal();

        output += String.format("Subtotal: %1.2f \nTax: %1.2f \n Order Total: %1.2f \n",subTotal,subTotal*.06625,subTotal+subTotal*.06625);

        return output;
    }

    /**
     * returns a list of the pizza flavors and toppings
     *
     * @return output the Pizza type and all the toppings
     */
    public String toString() {

        String output = "Phone number: " + phoneNumber + "\nFull Order: \n";
        for (Pizza p : pizzas)
            output += findType(p) + "\n Toppings: \n" + p.toString();

        return output;
    }


    /**
     * Returns the pizza flavor
     *
     * @param p the pizza you want to find the type of
     * @return s the Pizza type
     */
    private String findType(Pizza p) {
        String s = "";
        if (p instanceof Deluxe) {
            s = "Deluxe";
        } else if (p instanceof Hawaiian) {
            s = "Hawaiian";
        } else if (p instanceof Pepperoni) {
            s = "Pepperoni";
        }
        return s;
    }
}
