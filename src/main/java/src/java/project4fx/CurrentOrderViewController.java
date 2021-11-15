//3rd view
package src.java.project4fx;

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
    ListView orderList;

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
        ArrayList<String> listOfOrders = new ArrayList<String>();
        ObservableList<String> orders;
        for (int i = 0; i < currentOrderInSystem.getTotalPizzas(); i++) {
            listOfOrders.add(currentOrderInSystem.toString(i));
            System.out.println(currentOrderInSystem.toString(i));
        }
            orders = FXCollections.observableArrayList(listOfOrders);
        orderList.setItems((ObservableList) listOfOrders);
    }

    public void onPlaceOrderButtonClick() {
        //if(things not empty) addToStoreOrders(custphonenum, order);
        //and below. else (alert wrong info)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order has been sent to the store");
        alert.setHeaderText("Congrats, you've confirmed your order!");
        alert.setContentText("Prepare yourself for a pizza-licious experience. Nothing left to do but wait for our incredible delivery time!");
        alert.showAndWait();
    }

    public void onRemovePizzaButtonClick() {

        //if(things not empty) deletePizza(custphone#,pizza);
        //and below. else (alert wrong info)

    }
}
