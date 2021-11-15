//3rd view
package src.java.project4fx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class CurrentOrderViewController {
    Order current = new Order();
    double total, subtotal, tax;

    private HelloController mainController;

    @FXML
    TextField custPhoneNumber;
    @FXML
    Label custPhoneNumberLabel;
    @FXML
    Button placeOrderButton;
    @FXML
    Button removePizzaButton;
    @FXML
    Text subtotalText;
    @FXML
    TextField subtotalTextField;
    @FXML
    Text salestaxText;
    @FXML
    TextField salestaxTextField;
    @FXML
    Text orderTotalText;
    @FXML
    TextField ordertotalTextField;
    @FXML
    Label pizzaorderLabel;
    @FXML
    ListView orderPizzas = new ListView();

    public void setCustPhoneNumber(String number) {
        custPhoneNumber.setText(number);
    }

    public void setSubtotal(double subTotal) {
        subtotalTextField.setText(String.format("%1.2f", subTotal));
    }

    public void setTax(double tax) {
        salestaxTextField.setText(String.format("%1.2f", tax));
    }

    public void setOrderTotal(double total) {
        ordertotalTextField.setText(String.format("%1.2f", total));
    }

    public void setMainController(HelloController controller) {
        mainController = controller;
    }

    public void setListViews() {
        Order currentOrderInSystem = mainController.getCurrentOrder();
        String[] listOfOrders = new String[currentOrderInSystem.getTotalPizzas()];
        ObservableList<String> orders;
        for (int i = 0; i < currentOrderInSystem.getTotalPizzas(); i++) {
            listOfOrders[i] = currentOrderInSystem.toString(i);
            System.out.println(listOfOrders[i]);
        }
        orders = FXCollections.observableArrayList(listOfOrders);


        orderPizzas.setItems(orders);
    }

    /**
     * Will place the order in the StoreOrder in the Main controller
     */
    public void onPlaceOrderButtonClick() {
        //if(things not empty) addToStoreOrders(custphonenum, order);
        if(orderPizzas == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order cannot be completed");
            alert.setHeaderText("You must have a pizza in the list!");
        } else {
            mainController.addToStoreOrder(mainController.getCurrentOrder());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order has been sent to the store");
            alert.setHeaderText("Congrats, you've confirmed your order!");
            alert.setContentText("Prepare yourself for a pizza-licious experience. Nothing left to do but wait for our incredible delivery time!");
            alert.showAndWait();
        }
    }

    /**
     * Will remove a pizza that is selected in the list of pizzas
     */
    public void onRemovePizzaButtonClick() {
        int index = orderPizzas.getSelectionModel().getSelectedIndex(); // will get the index of the item selected

        if(index == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("There is no selection made");
            alert.setHeaderText("Either add a pizza or select a row.");
            alert.showAndWait();
        } else {
            Order o = mainController.getCurrentOrder();
            o.remove(index);
            mainController.setCurrentOrder(o);
            setListViews();
        }


        //if(things not empty) deletePizza(custphone#,pizza);
        //and below. else (alert wrong info)

    }
}
