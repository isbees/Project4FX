//4th view

package src.java.project4fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class StoreordersViewController {

    private HelloController mainController;

    @FXML
    Label custPhoneNumberLabel;
    @FXML
    TextField customerPhoneNumber;
    @FXML
    Label ordertotalLabel;
    @FXML
    TextField orderTotal;
    @FXML
    TextField custPhoneNumber;
    @FXML
    ListView ordersListView;
    @FXML
    Button cancelOrderButton;
    @FXML
    Button exportStoreOrdersButton;
    @FXML

    public void setMainController(HelloController controller) {
        mainController = controller;
    }

    public void setCustPhoneNumber(String number) {
        custPhoneNumber.setText(number);
    }

    public void setOrderTotal(double total) {
        orderTotal.setText(String.format("%1.2f", total));
    }

    void onCancelOrderButtonClick(){
        //if(things not empty) storeOrders.cancelOrder(order);
        //and below. else (alert wrong info)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Your order has been cancelled");
        alert.setHeaderText("We've gone ahead and cancelled your order.");
        alert.setContentText("We all make mistakes, feel free to order another one when you feel ready.");
        alert.showAndWait();
    }
    @FXML
    void exportFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        //write code to write to the file.
    }
}
