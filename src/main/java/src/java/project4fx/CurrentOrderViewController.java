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
    public void setMainController(HelloController controller){
        mainController= controller;
    }
    public void initialize(){
        System.out.println("HElLOLO");
    }
}
