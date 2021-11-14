//2nd view

package src.java.project4fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
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

    private HelloController mainController;

    public void setMainController(HelloController controller){
        mainController = controller;
    }

    public void setPhoto(){
     String pizzaType = mainController.getPizzaType();
     pizzaTypeLabel.setText(pizzaType);
     Image img;
     if(pizzaType.equals("Deluxe")){
           File file = new File("src/main/resources/PizzaPics/Deluxe.jpg");
           img = new Image(file.toURI().toString());
     }
     else if(pizzaType.equals("Hawaiian")){
      File file = new File("src/main/resources/PizzaPics/Hawaiian.jpg");
      img = new Image(file.toURI().toString());
     }
     else{
      File file = new File("src/main/resources/PizzaPics/Pepperoni.png");
      img = new Image(file.toURI().toString());
     }
     pizzaTypePhoto.setImage(img);
     System.out.println("DONE!");
    }
    public void onAddToOrderButtonClick(){
     //if(things not empty) addToOrder(custphonenum, pizza);
     //and below. else (alert wrong info)
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Pizza added to order");
     alert.setHeaderText("Congrats, you've added a pizza to your order!");
     alert.setContentText("Lets confirm this pizza-licious experience.");
     alert.showAndWait();
    }
}
