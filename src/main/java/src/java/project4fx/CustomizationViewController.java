package src.java.project4fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

/**
 * @author Zachary Goldman, Isaac Brukhman
 * CustomizationViewController lets the user add pizzas to their current order,
 * by choosing which type of pizza, toppings, and size.
 */
public class CustomizationViewController {
    private HelloController mainController;
    private String phoneNumber;
    private Order currentOrder;
    private Pizza newPizza;
    private int numOrders;
    private ArrayList<Pizza> myPizzas = new ArrayList<Pizza>();

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

    /**
     * setListViews sets up the options of toppings based on what type of pizza it is
     */
    @FXML
    public void setListViews(String pizzaType) {
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

    /**
     * setComboBox() defaults the combobox to small
     */
    @FXML
    void setComboBox() {
        ObservableList<String> items = FXCollections.observableArrayList("Small", "Medium", "Large");
        comboBoxSizes.setItems(items);
        comboBoxSizes.setValue("Small");
    }

    /**
     * changePrice() changes the displayed price based on our newPizza instance variable
     * and its settings
     */
    @FXML
    void changePrice() {
        Double price = 0.0;
        price = newPizza.price();

        DecimalFormat f = new DecimalFormat("##.00");
        String priceToSet = f.format(price);
        priceArea.setText(priceToSet);
    }

    /**
     * setMainController gives us access to the HelloController by passing it in
     * from that class
     *
     * @param controller
     */
    @FXML
    public void setMainController(HelloController controller) {
        mainController = controller;
    }

    /**
     * setPhoneNumber gives us access to the user's phone number from the
     * HelloController
     *
     * @param number
     */
    public void setPhoneNumber(String number) {
        phoneNumber = number;
    }

    /**
     * setPhotoListViewsComboBoxPrice initializes all of those methods to fill
     * in the view with the defaults -> small, 5 toppings for deluxe for example,
     * and the price as a result.
     * It also fills in the correct photo and initializes instance variables.
     *
     */
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
        newPizza = PizzaMaker.createPizza(mainController.getPizzaType());
        changePrice();
    }

    /**
     * onComboBoxSizesClick() changes the size of newPizza when a size is selected
     */
    @FXML
    void onComboBoxSizesClick() {
        String size = String.valueOf(comboBoxSizes.getSelectionModel().getSelectedItem());
        newPizza.size = sizeToEnum(size);
        changePrice();
    }

    /**
     * onAddButtonClick() adds a new topping to our newPizza then changes the display
     * and moves toppings added to the selected toppings listview
     */
    @FXML
    void onAddButtonClick() {
        if (null == additionalToppings.getSelectionModel().getSelectedItem()) {
            return;
        }
        String newTopping = (String) additionalToppings.getSelectionModel().getSelectedItem();
        Topping addedTopping = convertToppingToEnum(newTopping);
        newPizza.addTopping(addedTopping);
        changePrice();
        selectedToppings.getItems().add(additionalToppings.getSelectionModel().getSelectedItem());
        additionalToppings.getItems().remove(additionalToppings.getSelectionModel().getSelectedItem());
    }

    /**
     * onAddButtonClick() removes a  topping from our newPizza then changes the display
     * and moves toppings removed to the optional toppings listview
     */
    @FXML
    void onRemoveButtonClick() {
        if (null == selectedToppings.getSelectionModel().getSelectedItem()) {
            return;
        }
        if (selectedToppings.getItems().size() == 1) {
            return;
        }
        String newTopping = (String) selectedToppings.getSelectionModel().getSelectedItem();
        Topping removedTopping = convertToppingToEnum(newTopping);
        newPizza.removeTopping(removedTopping);
        changePrice();
        additionalToppings.getItems().add(selectedToppings.getSelectionModel().getSelectedItem());
        selectedToppings.getItems().remove(selectedToppings.getSelectionModel().getSelectedItem());
    }

    /**
     * onAddToOrderButtonClick() takes our order if we have it, then adds
     * the pizza we're dealing with to that order based on our selections.
     * Then it sends that order to the HelloController to use.
     *
     * @throws IOException if bad load
     */
    @FXML
    public void onAddToOrderButtonClick() throws IOException {
        if (numOrders == 0) {
            //This is first popup order, but have shopping cart already so grab it
            if (mainController.getCurrentOrder() != null) {
                myPizzas = mainController.getCurrentOrder().getPizzas();
            }
        }
        Pizza a = PizzaMaker.createPizza(pizzaTypeLabel.getText());
        String sized = (String) comboBoxSizes.getSelectionModel().getSelectedItem();
        a.size = sizeToEnum(sized);
        a.toppings = convertAllToppingsToEnums(selectedToppings);
        myPizzas.add(a);
        currentOrder = new Order(myPizzas, phoneNumber);
        numOrders++;
        mainController.setCurrentOrder(currentOrder);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza added to order");
        alert.setHeaderText("Congrats, you've added a pizza to your order!");
        alert.setContentText(mainController.getCurrentOrder().toString());
        alert.showAndWait();
    }

    /**
     * sizeToEnum takes in a string version of a size enum then returns
     * the correct enum back.
     *
     * @param size of it
     * @return size thing
     */
    public Size sizeToEnum(String size) {
        if (size.equals("Small")) {
            return Size.Small;
        }
        if (size.equals("Medium")) {
            return Size.Medium;
        } else return Size.Large;
    }

    /**
     * convertAllToppingsToEnums takes in a ListView of our selected toppings
     * and returns an arraylist of toppings to put in our pizza we'll add to
     * the current order by calling convertToppingToEnum for each topping
     *
     * @param selectedToppings of the thing
     * @return toppings
     */
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

    /**
     * convertToppingToEnum takes in a string and converts it to the enum version similar
     * to the sizeToEnum method
     *
     * @param topping of the pizza
     * @return newTopping in Enum form
     */
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



