package src.java.project4fx;

public class PizzaMaker {


    public static Pizza createPizza(String flavor) {
        Pizza p = new Pizza() {
            @Override
            public double price() {
                return 0;
            }
        };
        return p;
    }
    public double price(){
        double price = 0;
        return price;
    }
}