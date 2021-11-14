//4th view

package src.java.project4fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class StoreordersViewController {
    @FXML
    Label custPhoneNumberLabel;
    @FXML
    TextField customerPhoneNumber;
    @FXML
    Label ordertotalLabel;
    @FXML
    TextField orderTotal;
    @FXML
    ListView ordersListView;
    @FXML
    Button cancelOrderButton;
    @FXML
    Button exportStoreOrdersButton;
}
