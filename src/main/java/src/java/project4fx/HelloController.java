//1st view
package src.java.project4fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloController {
    //Instance variable
    private String pizzaType;
    private StoreOrders storeorder;
    private ArrayList<Order> currentOrder;

    //Assets
    @FXML
    Label labelZachIsaacPizzeria;
    @FXML
    Label labelCustPhoneNumber;
    @FXML
    TextField custPhoneNumber;
    @FXML
    Button deluxeBtn;
    @FXML
    ImageView deluxeImgView;
    @FXML
    ImageView hawaiianImgView;
    @FXML
    Button hawaiianBtn;
    @FXML
    ImageView pepperoniImgView;
    @FXML
    Button currentOrderBtn;
    @FXML
    Button storeOrdersBtn;



    public void initialize(){
        storeorder= new StoreOrders();
    }
    //Get pizzatype selected
    String getPizzaType() {
        return this.pizzaType;
    }
    //Navigation

    //Goes to 2nd view

    @FXML
    void openNewHawaiian() throws IOException {
        pizzaType = "Hawaiian";
        checkOpenNewCustomization();
    }

    @FXML
    void openNewPepperoni() throws IOException {
        pizzaType = "Pepperoni";
        checkOpenNewCustomization();
    }

    @FXML
    void openNewDeluxe() throws IOException {
        pizzaType = "Deluxe";
        checkOpenNewCustomization();
    }

    public void checkOpenNewCustomization() throws IOException {
        String phoneNumText = custPhoneNumber.getText();
        try {
            if (phoneNumText.length() != 10) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Phone number must be 10 digits long.");
                alert.showAndWait();
                return;
            }
            int a = Integer.valueOf(phoneNumText);
            //add it to our next order!! ->>>> maybe add it as instance var?

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Phone number must be only numbers.");
            alert.showAndWait();
            return;
        }
        openCustomization(phoneNumText);
    }
    void openCustomization(String phoneNumber) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("customization-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        CustomizationViewController cView = fxmlLoader.getController();
        cView.setMainController(this);
        cView.setPhoneNumber(phoneNumber);
        stage.setTitle("Customizing-Your-Pizza-Order!");
        stage.setScene(scene);
        cView.setPhotoListViewsComboBoxPrice();
        stage.show();
    }
    //Goes to 3rd view
    public void openCurrentOrder() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("currentorder-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Current-Orders!");
        stage.setScene(scene);
        stage.show();
    }

    //Goes to 4th view
    public void openStoreOrders() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("storeorders-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setTitle("Your-Store-Orders!");
        stage.setScene(scene);
        stage.show();
    }


    HelloController getController() {
        return this;
    }
    public void setCurrentOrder(CustomizationViewController cView, ArrayList<Order> currentOrder){
        currentOrder= cView.getCurrentOrder();
    }
    public ArrayList<Order> getCurrentOrder(){
        return this.currentOrder;
    }
}

  /*  Click on a pizza

  Order order = findOrder(custPhoneNumber);
if(order==null){
        Order newOrder=new Order(custPhoneNumber)
        };
Pizzamaker p = new PizzaMaker
    Deluxe deluxe1 =    p.newDeluxe();

order.add(deluxe1)

    Deluxe

   */
