package src.java.project4fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
public class CustomizationViewController {
   @FXML
    Button addButton;
   @FXML
    Button removeButton;
    @FXML
    ImageView pizzaTypePhoto;
    @FXML
    Label pizzaTypeLabel;
    @FXML
    ComboBox comboBoxSizes;
    @FXML
    ListView additionalToppings;
    @FXML
    ListView selectedToppings;
    @FXML
    Text priceText;
    @FXML
    TextField priceArea;
    @FXML
    Button addToOrderButton;

}
