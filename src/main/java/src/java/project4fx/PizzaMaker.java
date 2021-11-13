package src.java.project4fx;

/**
 * PizzaMaker will create a pizza based off the type of flavor.
 *
 * @author Isaac Brukhman
 */
public class PizzaMaker {

    /**
     * creates an instance of a pizza according to the type of pizza selected. Method assumes the flavor selected is always a correct value.
     *
     * @param flavor the type of pizza that is desired
     * @return Pizza the pizza type that was created
     */
    public static Pizza createPizza(String flavor) {
        Pizza p = new Pepperoni();  // default is pepperoni so that I don't need to initialize the pizza

        switch (flavor){
            case "Deluxe": p = new Deluxe(); break;
            case "Hawaiian": p = new Hawaiian(); break;
        }

        return p;
    }

}