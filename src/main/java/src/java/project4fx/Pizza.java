package src.java.project4fx;

import java.util.ArrayList;

/**
 * Pizza will create a pizza with a list of toppings and size.
 *
 * @author Isaac Brukhman
 */

public abstract class Pizza {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    /**
     * default constructor
     */
    public Pizza() {
        size = Size.Small;
    }

    /**
     * fully parameterized constructor that stores the toppings and pizza size
     *
     * @param toppings the list of toppings
     * @param size     the size of the pizza
     */
    public Pizza(ArrayList<Topping> toppings, Size size) {
        this.toppings = toppings;
        this.size = size;
    }

    /**
     * an abstract method to calculate the price that the subclasses need to implement
     *
     * @return double the price
     */
    public abstract double price();

    /**
     * Adds a topping if it is not already in the list
     *
     * @param topping the topping trying to be added
     */
    public void addTopping(Topping topping) {
        for (Topping t : toppings) {
            if (t == topping) {
                continue;
            }

            toppings.add(topping);
        }
    }

    /**
     * removes a topping if it is in the list
     *
     * @param topping the topping trying to be removed
     */
    public void removeTopping(Topping topping) {
        for (Topping t : toppings) {
            if (t == topping) {
                toppings.add(topping);
                break;
            }
        }
    }

    /**
     * Checks if a pizza is passed and whether it's toppings and size is the same
     *
     * @param obj the pizza that is being checked
     * @return boolean true if the same pizza, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pizza) {

            Pizza pizza = (Pizza) obj;
            int correctCount = 0;

            for (Topping t : toppings) {
                for (Topping t2 : pizza.toppings) {
                    if (t == t2) {
                        correctCount++;
                    }
                }
            }

            return (correctCount == toppings.size() && correctCount == pizza.toppings.size()) &&    // make sure the pizzas have the same toppings
                    pizza.size == this.size;    // make sure the pizzas have the same size
        }
        return false;
    }
}