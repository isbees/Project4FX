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

/**
 * The Main Controller for the whole pizzeria
 *
 * @author Zack Goldman
 */
public class HelloController {
    //Instance variable
    private String pizzaType;
    private StoreOrders storeOrder;
    private Order currentOrder;
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
        storeOrder= new StoreOrders();
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
        if(currentOrder==null||phoneNumText.equals(currentOrder.getNumber())){
            openCustomization(phoneNumText);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You have an existing order with diff #.");
            alert.showAndWait();
            return;
        }
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
        CurrentOrderViewController coView = fxmlLoader.getController();
        coView.setMainController(this);
        try {
            double subTotal = currentOrder.calcSubTotal(), tax = currentOrder.calcTax();
            double total = subTotal + tax;

            coView.setCustPhoneNumber(currentOrder.getNumber());
            coView.setSubtotal(subTotal);
            System.out.println(subTotal);
            coView.setOrderTotal(total);
            coView.setTax(tax);
            System.out.println(total);
            stage.setTitle("Current-Orders!");
            stage.setScene(scene);
            coView.setListViews();
            stage.show();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("There Are no orders!");
            alert.showAndWait();
            return;
        }
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

    public void setCurrentOrder(Order newOrder){
        currentOrder= newOrder;

    }

    public Order getCurrentOrder(){
        return this.currentOrder;
    }

    public void addToStoreOrder(Order order){
        storeOrder.add(order);
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
