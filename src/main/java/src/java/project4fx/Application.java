package src.java.project4fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        String num = "7325168892";
        Pizza p1 = PizzaMaker.createPizza("Deluxe");
        Pizza p2 = PizzaMaker.createPizza("Hawaiian");
        Pizza p3 = PizzaMaker.createPizza("Pepperoni");

        StoreOrders so = new StoreOrders();

        Order me = new Order(p1,num);
        System.out.println(me.printOrder());
        /*me = new Order(p2,num);
        System.out.println(me.printOrder());
        me = new Order(p3,num);
        System.out.println(me.printOrder());
        */
        so.add(me,p1);
        so.add(me,p2);
        so.add(me,p3);

        System.out.println("\n\nhere");
        so.printOrders();
        launch();
    }
}
