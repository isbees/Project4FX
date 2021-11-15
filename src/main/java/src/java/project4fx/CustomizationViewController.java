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
import java.text.DecimalFormat;
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
    private Order currentOrder;
    private Pizza newPizza;
    private Order tempOrder;
    private boolean multipleOrders;
    private int numOrders;
    private ArrayList<Pizza> myPizzas = new ArrayList<Pizza>();
    private Pizza multPizza;
    private int i =0;

    @FXML
    public void setListViews(String pizzaType) {
        System.out.println("in setlistvidws");

        ObservableList<String> selectedToppingOptions;
        ObservableList<String> additionalToppingOptions;
        if (pizzaType.equals("Deluxe")) {
            selectedToppingOptions =
                    FXCollections.observableArrayList("Pepperoni",
                            "Mushroom", "Spinach", "Mozzarella", "Olives");
            additionalToppingOptions =
                    FXCollections.observableArrayList("Pineapple", "Ham");
        } else if (pizzaType.equals("Hawaiian")) {
            selectedToppingOptions =
                    FXCollections.observableArrayList("Pineapple", "Ham");

            additionalToppingOptions = FXCollections.observableArrayList(
                    "Olives", "Mozzarella", "Mushroom", "Spinach", "Pepperoni");
        } else {
            selectedToppingOptions =
                    FXCollections.observableArrayList("Pepperoni");

            additionalToppingOptions = FXCollections.observableArrayList(
                    "Olives", "Ham", "Mushroom", "Spinach", "Pineapple", "Mozzarella");
        }
        selectedToppings.setItems(selectedToppingOptions);
        additionalToppings.setItems(additionalToppingOptions);
    }

    void setComboBox() {
        System.out.println("in setcombobox");
        ObservableList<String> items = FXCollections.observableArrayList("Small", "Medium", "Large");
        comboBoxSizes.setItems(items);
        comboBoxSizes.setValue("Small");
    }

    void changePrice() {
        System.out.println("in changeprice");
        //Sets up price
        Double price = 0.0;
        if (multipleOrders == false) {
            price = newPizza.price();
        } else {
            price = multPizza.price();
        }
        DecimalFormat f = new DecimalFormat("##.00");
        String priceToSet = f.format(price);
        priceArea.setText(priceToSet);
    }

    @FXML
    public void setMainController(HelloController controller) {
        mainController = controller;
    }

    public void setPhoneNumber(String number) {
        phoneNumber = number;
    }

    @FXML
    public void setPhotoListViewsComboBoxPrice() {
        System.out.println("in setphoto");
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
        newPizza = PizzaMaker.createPizza(mainController.getPizzaType());
        multPizza = PizzaMaker.createPizza(mainController.getPizzaType());
        changePrice();
        int numOrders=0;
    }

    @FXML
    void onComboBoxSizesClick() {
        System.out.println("in changecombo");
        String size = String.valueOf(comboBoxSizes.getSelectionModel().getSelectedItem());
        if (multipleOrders == false) {
            newPizza.size = sizeToEnum(size);
        } else {
            multPizza.size = sizeToEnum(size);
        }
        changePrice();

    }

    @FXML
    void onAddButtonClick() {
        System.out.println("in add");
        if (null == additionalToppings.getSelectionModel().getSelectedItem()) {
            return;
        }
        String newTopping = (String) additionalToppings.getSelectionModel().getSelectedItem();
        Topping addedTopping = convertToppingToEnum(newTopping);
        if (multipleOrders == false) {
            newPizza.addTopping(addedTopping);
        } else {
            multPizza.addTopping(addedTopping);
        }
        changePrice();
        selectedToppings.getItems().add(additionalToppings.getSelectionModel().getSelectedItem());
        additionalToppings.getItems().remove(additionalToppings.getSelectionModel().getSelectedItem());
        //call to price change for topping amount change- can use maxToppings and numToppings
    }

    @FXML
    void onRemoveButtonClick() {
        System.out.println("in remove");
        if (null == selectedToppings.getSelectionModel().getSelectedItem()) {
            return;
        }
        if (selectedToppings.getItems().size() == 1) {
            return;
        }
        String newTopping = (String) selectedToppings.getSelectionModel().getSelectedItem();
        Topping removedTopping = convertToppingToEnum(newTopping);
        if (multipleOrders == false) {
            newPizza.removeTopping(removedTopping);
        } else {
            multPizza.removeTopping(removedTopping);
        }
        changePrice();
        additionalToppings.getItems().add(selectedToppings.getSelectionModel().getSelectedItem());
        selectedToppings.getItems().remove(selectedToppings.getSelectionModel().getSelectedItem());
    }

    @FXML
    void firstOrder() {
        //Completely new order
        if(mainController.getCurrentOrder()==null){
            myPizzas.add(newPizza);
            Order newOrder = new Order(myPizzas,phoneNumber);
            currentOrder = newOrder;
            mainController.setCurrentOrder(newOrder);
            setListViews(pizzaTypeLabel.getText());
            changePrice();
            setComboBox();
            multipleOrders=true;
            numOrders++;
            System.out.println("First ever order: "+currentOrder.toString());
            doAlert();
            return;
        }
        //New popup pizza but not completely new order
        if(numOrders==0){
            Order current = mainController.getCurrentOrder();
            current.add(newPizza);
            setListViews(pizzaTypeLabel.getText());
            changePrice();
            setComboBox();
            multipleOrders=true;
            numOrders++;
            currentOrder = current;
            mainController.setCurrentOrder(currentOrder);
            System.out.println("First popup order: "+currentOrder.toString());
            doAlert();
        }
    }
    @FXML
    void evenOrder(){
        System.out.println("even order: myPizzas = "+myPizzas.toString());
        //Add our pizza
        Pizza a = PizzaMaker.createPizza(pizzaTypeLabel.getText());
        String sized = (String) comboBoxSizes.getSelectionModel().getSelectedItem();
        a.size = sizeToEnum(sized);
        a.toppings = convertAllToppingsToEnums(selectedToppings);
        myPizzas.add(a);

        Order newOrder = new Order(myPizzas, phoneNumber);
        currentOrder = newOrder;
        mainController.setCurrentOrder(currentOrder);
        setListViews(pizzaTypeLabel.getText());
        changePrice();
        multipleOrders = true;
        numOrders++;
        doAlert();
        System.out.println("EVENORDER with "+numOrders+" orders so far. Here we go: "+currentOrder.toString());

    }
    @FXML
    void oddOrder() {
        System.out.println("odd order: myPizzas = "+myPizzas.toString());
        Pizza a = PizzaMaker.createPizza(pizzaTypeLabel.getText());
        String sized = (String) comboBoxSizes.getSelectionModel().getSelectedItem();
        a.size = sizeToEnum(sized);
        a.toppings = convertAllToppingsToEnums(selectedToppings);
        myPizzas.add(a);
        Order newOrder = new Order(myPizzas, phoneNumber);
        currentOrder = newOrder;
        setListViews(pizzaTypeLabel.getText());
        changePrice();
        doAlert();
        multipleOrders=false;
        numOrders++;
        System.out.println("ODDORDERRRRRRR with "+numOrders+" orders so far. Here we go: "+currentOrder.toString());
    }

    void doAlert() {
      /*  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza added to order");
        alert.setHeaderText("Congrats, you've added a pizza to your order!");
        alert.setContentText(mainController.getCurrentOrder().toString());
        alert.showAndWait(); */
    }

    @FXML
    public void onAddToOrderButtonClick() throws IOException {
        if(numOrders==0){
            //This is first popup order, but have shopping cart already so grab it
            if(mainController.getCurrentOrder()!=null){
                myPizzas = mainController.getCurrentOrder().getPizzas();
            }
        }
        Pizza a = PizzaMaker.createPizza(pizzaTypeLabel.getText());
        String sized = (String) comboBoxSizes.getSelectionModel().getSelectedItem();
        a.size = sizeToEnum(sized);
        a.toppings = convertAllToppingsToEnums(selectedToppings);
        myPizzas.add(a);
        currentOrder = new Order(myPizzas,phoneNumber);
        numOrders++;
        mainController.setCurrentOrder(currentOrder);
        System.out.println(currentOrder.toString());
        /*
        if(mainController.getCurrentOrder()==null||numOrders==0){
            firstOrder();
        }
        else if (multipleOrders == false) {
            evenOrder();
        }
        else {
            oddOrder();
        }
         */
    }

    public Size sizeToEnum(String size) {
        if (size.equals("Small")) {
            return Size.Small;
        }
        if (size.equals("Medium")) {
            return Size.Medium;
        } else return Size.Large;
    }

    public Order getCurrentOrder() {
        return this.currentOrder;
    }

    public ArrayList<Topping> convertAllToppingsToEnums(ListView selectedToppings) {
        ObservableList a = selectedToppings.getItems();
        ArrayList<Topping> toppings = new ArrayList<Topping>();
        for (int i = 0; i < a.size(); i++) {
            String b = (String) a.get(i);
            Topping enumTopping = convertToppingToEnum(b);
            toppings.add(enumTopping);
        }
        return toppings;
    }

    public Topping convertToppingToEnum(String topping) {
        Topping newTopping = Topping.Pepperoni;
        switch (topping) {
            case "Pepperoni":
                newTopping = Topping.Pepperoni;
                break;
            case "Mushroom":
                newTopping = Topping.Mushroom;
                break;
            case "Pineapple":
                newTopping = Topping.Pineapple;
                break;
            case "Mozzarella":
                newTopping = Topping.Mozzarella;
                break;
            case "Olives":
                newTopping = Topping.Olives;
                break;
            case "Spinach":
                newTopping = Topping.Spinach;
                break;
            case "Ham":
                newTopping = Topping.Ham;
                break;
        }
        return newTopping;
    }
}



