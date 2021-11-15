//3rd view
package src.java.project4fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
public class CurrentOrderViewController {
    @FXML
    TextField custPhoneNumber;
    @FXML
    Label custPhoneNumberLabel;
    @FXML
    Text orderTotalText;
    @FXML
    TextField ordertotalTextField;
    @FXML
    Button placeOrderButton;
    @FXML
    Button removePizzaButton;
    @FXML
    Text salestaxText;
    @FXML
    TextField salestaxTextField;
    @FXML
    Text subtotalText;
    @FXML
    TextField subtotalTextField;
    @FXML
    Label pizzaorderLabel;
    private HelloController mainController;
    public void setCustPhoneNumber(String number){
        custPhoneNumber.setText(number);
    }
    public void setMainController(HelloController controller){
        mainController= controller;
    }
    public void onPlaceOrderButtonClick(){
        //if(things not empty) addToStoreOrders(custphonenum, order);
        //and below. else (alert wrong info)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order has been sent to the store");
        alert.setHeaderText("Congrats, you've confirmed your order!");
        alert.setContentText("Prepare yourself for a pizza-licious experience. Nothing left to do but wait for our incredible delivery time!");
        alert.showAndWait();
    }
    public void onRemovePizzaButtonClick(){
        //if(things not empty) deletePizza(custphone#,pizza);
        //and below. else (alert wrong info)

    }
}
