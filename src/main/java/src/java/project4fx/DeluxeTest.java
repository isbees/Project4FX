package src.java.project4fx;

import static org.junit.Assert.*;

/**
 * @author Zachary Goldman, Isaac Brukhman
 * DeluxeTest tests the price() method of Deluxe
 */
class DeluxeTest {
    /**
     * main calls to the price that we'll be testing in
     * @param args
     */
    public static void main(String[] args){
        price();
    }
    /**
     * Tests the price() method in Deluxe class
     */
    @org.junit.Test
    static void price() {
        //---Tests 1-3 numToppings = 5 defaults, tests different Sizes

        String type = "Deluxe";

        //Test 1: 5 default toppings, defaulted size small -> 12.99
        Pizza one = PizzaMaker.createPizza(type);
        assertTrue(one.price()==12.99);

        //Test 2: 5 toppings, size medium -> 14.99
        Pizza two = PizzaMaker.createPizza(type);
        two.setSize(Size.Medium);
        assertTrue(two.price()==14.99);

        //Test 3: 5 toppings, size large -> 16.99
        Pizza three = PizzaMaker.createPizza(type);
        three.setSize(Size.Large);
        assertTrue(three.price()==16.99);

        //Test 4: 5 toppings but one isn't default - same price as test 3
        // -> 16.99 for large
        three.removeTopping(Topping.Olives);
        three.addTopping(Topping.Ham);
        assertTrue(three.price()==16.99);

        //---Test 5: 4 toppings -> check that it turns it to 5, size small defaulted -> 12.99
        Pizza five = PizzaMaker.createPizza(type);
        five.removeTopping(Topping.Spinach);
        assertTrue(five.price()==12.99);

        //Test 6: 6 toppings -> check it does (6-5)*1.49 + small (12.99) -> 14.48
        Pizza six = PizzaMaker.createPizza(type);
        six.addTopping(Topping.Ham);
        assertTrue(six.price()==12.99+(6-5)*1.49);

        //Test 7: 8 toppings -> Will never happen as I prevent it in the customizationController and the rules
        //But if it did, that it'd return the correct amount - assuming we allowed for more toppings than 7
        Pizza seven = PizzaMaker.createPizza(type);
        seven.addTopping(Topping.Pineapple);
        seven.addTopping(Topping.Ham);
        seven.addTopping(Topping.Spinach);
        assertTrue(seven.price()==12.99+(8-5)*1.49);

    }
}