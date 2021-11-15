//2nd view

package src.java.project4fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    private ArrayList<Order> currentOrder;
    private String phoneNumber;
    private StoreOrders storeorder;
    private int maxToppings;
    private int numToppings;
    private int price;
    @FXML
    public void setListViews(String pizzaType) {
        ObservableList<String> selectedToppingOptions;
        ObservableList<String> additionalToppingOptions;
       if (pizzaType.equals("Deluxe")) {
            selectedToppingOptions =
                    FXCollections.observableArrayList("Pepperoni",
                            "Mushroom","Pineapple","Mozzarella","Spinach");
            maxToppings=5;
            additionalToppingOptions =
                    FXCollections.observableArrayList("Olives", "Ham");
        }
        else if(pizzaType.equals("Hawaiian")){
            selectedToppingOptions =
                    FXCollections.observableArrayList("Pineapple","Mozzarella");
           maxToppings=2;

           additionalToppingOptions = FXCollections.observableArrayList(
                    "Olives", "Ham","Mushroom","Spinach","Pepperoni");
        }
        else {
            selectedToppingOptions =
                    FXCollections.observableArrayList("Pepperoni");
           maxToppings=1;

            additionalToppingOptions = FXCollections.observableArrayList(
                            "Olives", "Ham","Mushroom","Spinach", "Pineapple","Mozzarella");
        }
        selectedToppings.setItems(selectedToppingOptions);
        additionalToppings.setItems(additionalToppingOptions);
    }
    void setComboBox(){
        ObservableList<String> items = FXCollections.observableArrayList("Small","Medium","Large");
        comboBoxSizes.setItems(items);
        comboBoxSizes.setValue("Small");
    }
    public void setMainController(HelloController controller) {
        mainController = controller;
    }
    void setPrice(){
        //call to set price
    }
    public void setPhoneNumber(String number) {
        phoneNumber = number;
    }
    @FXML
    public void setPhotoListViewsComboBoxPrice() {
        String pizzaType = mainController.getPizzaType();
        pizzaTypeLabel.setText(pizzaType);
        Image img;
        if (pizzaType.equals("Deluxe")) {
            File file = new File("src/main/resources/PizzaPics/Deluxe.jpg");
            img = new Image(file.toURI().toString());
        } else if (pizzaType.equals("Hawaiian")) {
            File file = new File("src/main/resources/PizzaPics/Hawaiian.jpg");
            img = new Image(file.toURI().toString());
        } else {
            File file = new File("src/main/resources/PizzaPics/Pepperoni.png");
            img = new Image(file.toURI().toString());
        }
        setListViews(pizzaType);
        setComboBox();
        pizzaTypePhoto.setImage(img);
        setPrice();
    }
    @FXML
    void onComboBoxSizesClick() {
        String size = String.valueOf(comboBoxSizes.getSelectionModel().getSelectedItem());
        //Call to price change for size change
    }
    @FXML
    void onAddButtonClick(){
        if(null==additionalToppings.getSelectionModel().getSelectedItem()){
            return;
        }
        selectedToppings.getItems().add(additionalToppings.getSelectionModel().getSelectedItem());
        additionalToppings.getItems().remove(additionalToppings.getSelectionModel().getSelectedItem());
        numToppings++;
        //call to price change for topping amount change- can use maxToppings and numToppings
    }
    @FXML
    void onRemoveButtonClick(){
        if(null==selectedToppings.getSelectionModel().getSelectedItem()){
            return;
        }
        additionalToppings.getItems().add(selectedToppings.getSelectionModel().getSelectedItem());
        selectedToppings.getItems().remove(selectedToppings.getSelectionModel().getSelectedItem());
        numToppings--;
        //call to price change for topping amount change- can use maxToppings and numToppings
    }
    @FXML
    public void onAddToOrderButtonClick() {
        //Get our existing current orders from main page
        ArrayList<Order> currentOrdersInSystem = mainController.getCurrentOrder();

        //If we have no orders, make a new arraylist for our main
        if(currentOrder==null){
            currentOrder = new ArrayList<Order>();
            Order newOrder = new Order(phoneNumber,pizzafromThis);
            currentOrder.add(newOrder);
            mainController.setCurrentOrder(this,currentOrder);
        }
        //If we have another order in shopping cart, check if same number
        //if not, trash that one, and start a new one.
        else{
            //Go through each order to see if any has our number, and add to that order.
            for(int i = 0; i<currentOrdersInSystem.size(); i++){
                //if we've already got an order from this guy
                if(currentOrdersInSystem.get(i).getNumber().equals(phoneNumber)){
                    currentOrdersInSystem.get(i).add(pizzafromThis);
                }
                //It's a new thing!
                else{
                    Order newOrder = new Order(phoneNumber, pizzafromThis);
                    currentOrdersInSystem.add(newOrder);
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza added to order");
        alert.setHeaderText("Congrats, you've added a pizza to your order!");
        alert.setContentText("Lets confirm this pizza-licious experience.");
        alert.showAndWait();
    }
    public ArrayList<Order> getCurrentOrder(){
        return this.currentOrder;
    }
}
