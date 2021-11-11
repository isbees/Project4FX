package src.java.project4fx;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    public Pizza(){
        super();
    }
    public abstract double price();
}