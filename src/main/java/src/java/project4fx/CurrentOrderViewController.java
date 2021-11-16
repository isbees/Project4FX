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
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

/**
 * @author Zachary Goldman, Isaac Bruhkstein
 * The CurrentOrderViewController shows the user's current order and lets them
 * remove items, or bring the order to the store's server!
 */

public class CurrentOrderViewController {
    Order current = new Order();
    private double total, subtotal, tax;
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

    /**
     * setCustPhoneNumber takes in a string to set as the customer's phone number
     *
     * @param number
     */
    public void setCustPhoneNumber(String number) {
        custPhoneNumber.setText(number);
    }

    /**
     * setSubtotal displays the subtotal on the view
     *
     * @param subTotal
     */
    public void setSubtotal(double subTotal) {
        subtotalTextField.setText(String.format("%1.2f", subTotal));
    }

    /**
     * setTax displays the subtotal on the view
     *
     * @param tax
     */
    public void setTax(double tax) {
        salestaxTextField.setText(String.format("%1.2f", tax));
    }

    /**
     * setOrderTotal displays the total on the view
     *
     * @param total
     */
    public void setOrderTotal(double total) {
        ordertotalTextField.setText(String.format("%1.2f", total));
    }

    /**
     * setMainController sets the HelloController as its instance variable
     * so it can reference things from it such as the phone and current order
     *
     * @param controller
     */
    public void setMainController(HelloController controller) {
        mainController = controller;
    }

    /**
     * setListViews() sets up the list view by filling it with the orders in
     * the user's current order
     */
    public void setListViews() {
        Order currentOrderInSystem = mainController.getCurrentOrder();
        String[] listOfOrders = new String[currentOrderInSystem.getTotalPizzas()];
        ObservableList<String> orders;
        for (int i = 0; i < currentOrderInSystem.getTotalPizzas(); i++) {
            listOfOrders[i] = currentOrderInSystem.toString(i);
        }
        orders = FXCollections.observableArrayList(listOfOrders);
        orderPizzas.setItems(orders);
    }

    /**
     * onPlaceorderButtonClick()
     * Places the order in the StoreOrder in the Main controller
     */
    public void onPlaceOrderButtonClick() {
        if (mainController.getCurrentOrder() == null) {
            return;
        }
        if (mainController.getCurrentOrder().getTotalPizzas() == 0) {
            reset();
            return;
        }
        if (orderPizzas == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order cannot be completed");
            alert.setHeaderText("You must have a pizza in the list!");
        } else {
            System.out.println("Here");
            mainController.addToStoreOrder(mainController.getCurrentOrder());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order has been sent to the store");
            alert.setHeaderText("Congrats, you've confirmed your order!");
            alert.setContentText("Prepare yourself for a pizza-licious experience. Nothing left to do but wait for our incredible delivery time!");
            alert.showAndWait();
            reset();
        }

    }

    /**
     * reset() makes all of the displays be wiped and empty -
     * done when the order is sent or every pizza is removed
     */
    void reset() {
        orderPizzas.setItems(null);
        subtotalTextField.setText("");
        salestaxTextField.setText("");
        ordertotalTextField.setText("");
        mainController.setCurrentOrder(null);
    }

    /**
     * onRemovePizzaButtonClick()
     * Will remove a pizza that is selected in the list of pizzas
     */
    public void onRemovePizzaButtonClick() {
        int index = orderPizzas.getSelectionModel().getSelectedIndex(); // will get the index of the item selected

        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("There is no selection made");
            alert.setHeaderText("Either add a pizza or select a row.");
            alert.showAndWait();
        } else {
            Order o = mainController.getCurrentOrder();
            o.remove(index);
            mainController.setCurrentOrder(o);
            updatePrices();
            setListViews();
        }
    }

    /**
     * updatePrices() sets the displays to the new correct values. This is called
     * after removing a pizza.
     */
    private void updatePrices() {
        if (mainController.getCurrentOrder() == null) {
            reset();
            return;
        }
        Order o = mainController.getCurrentOrder();

        //Redo the prices
        setSubtotal(o.calcSubTotal());
        setTax(o.calcTax());

        setOrderTotal(o.calcSubTotal() + o.calcTax());

    }
}
