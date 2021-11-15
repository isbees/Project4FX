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
    private String phoneNumber;
    private int maxToppings;
    private int numToppings;
    private int price;
    private Order currentOrder;
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
        //call to set price -> based on selected pizza, and small initially.
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
        Order currentOrderInSystem = mainController.getCurrentOrder();
        Pizza p = PizzaMaker.createPizza(pizzaTypeLabel.getText());
        ArrayList<Topping> pizzaToppings = new ArrayList<Topping>();
        pizzaToppings = convertToEnums(selectedToppings.getItems());
        p.addToppings(pizzaToppings);
        //If we have no orders, make a new order
        if(currentOrderInSystem==null){
            Order newOrder = new Order(p,phoneNumber);
            mainController.setCurrentOrder(newOrder);
            this.currentOrder=newOrder;
        }
        //We have an order for this person
        else{
            currentOrder=currentOrderInSystem;
            currentOrder.add(p);
            mainController.setCurrentOrder(currentOrder);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza added to order");
        alert.setHeaderText("Congrats, you've added a pizza to your order!");
        alert.setContentText(mainController.getCurrentOrder().toString());
        alert.showAndWait();
    }

    public Order getCurrentOrder(){
        return this.currentOrder;
    }

    public ArrayList<Topping> convertToEnums(ObservableList<String> selectedItems){
        String a;
        ArrayList<Topping> toppings = new ArrayList<Topping>();
        for(int i = 0; i<selectedItems.size(); i++){
            a = selectedItems.get(i);
            Topping newTopping = Topping.Pepperoni;
            switch (a){
                case "Pepperoni": newTopping= Topping.Pepperoni; break;
                case "Mushroom":  newTopping= Topping.Mushroom; break;
                case "Pineapple":  newTopping= Topping.Pineapple; break;
                case "Mozzarella":  newTopping= Topping.Mozzarella; break;
                case "Olives":  newTopping= Topping.Olives; break;
                case "Spinach":  newTopping= Topping.Spinach; break;
                case "Ham":  newTopping= Topping.Ham; break;
            }
            toppings.add(newTopping);
        }
        return toppings;
    }
}



