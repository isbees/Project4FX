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
    TextArea custPhoneNumberTextArea;
    @FXML
    Label ordertotalLabel;
    @FXML
    TextArea orderTotalTextArea;
    @FXML
    ListView ordersListView;
    @FXML
    Button cancelOrderButton;
    @FXML
    Button exportStoreOrdersButton;
}
