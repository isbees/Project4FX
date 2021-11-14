//1st view
package src.java.project4fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloController {
    //Instance variable
    private String pizzaType;

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

    //Get pizzatype selected
    String getPizzaType() {
        return this.pizzaType;
    }
    //Navigation

    //Goes to 2nd view

    @FXML
    void openNewHawaiian() throws IOException {
        pizzaType = "Hawaiian";
        openNewOrder();
    }

    @FXML
    void openNewPepperoni() throws IOException {
        pizzaType = "Pepperoni";
        openNewOrder();
    }

    @FXML
    void openNewDeluxe() throws IOException {
        pizzaType = "Deluxe";
        openNewOrder();
    }

    public void openNewOrder() throws IOException {
        try {
            String phoneNumText = custPhoneNumber.getText();
            if (phoneNumText.length() != 10) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Phone number should be 10 digits long");
                alert.showAndWait();
                return;
            }
            int phoneNum = Integer.valueOf(phoneNumText);
            //add it to our next order!! ->>>> maybe add it as instance var?

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Make sure your phone number is only numbers");
            alert.showAndWait();
            return;
        }
        open("customization-view.fxml");
    }

    //Goes to 3rd view
    public void openCurrentOrder() throws IOException {
        open("currentorder-view.fxml");
    }

    //Goes to 4th view
    public void openStoreOrders() throws IOException {
        open("storeorders-view.fxml");
    }

    /**
     * open(String nameOfFile) takes the fxml file and loads it with the
     * correct stage-title
     *
     * @param nameOfFile as a string of the fxml file
     */
    public void open(String nameOfFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(nameOfFile));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        if (nameOfFile.equals("currentorder-view.fxml")) {
            stage.setTitle("Current-Orders!");

        } else if (nameOfFile.equals("customization-view.fxml")) {
            CustomizationViewController cView = fxmlLoader.getController();
            cView.setMainController(this);
            stage.setTitle("Customizing-Your-Pizza-Order!");
            stage.setScene(scene);
            cView.setPhoto();
            stage.show();
            return;
        } else if (nameOfFile.equals("storeorders-view.fxml")) {
            stage.setTitle("Your-Store-Orders!");

        }
        else {
            return;
        }
        stage.setScene(scene);
        stage.show();
    }

    HelloController getController() {
        return this;
    }

}
  /*  Order order = findOrder(custPhoneNumber);
if(order==null){
        Order newOrder=new Order(custPhoneNumber)
        };
Pizzamaker p = new PizzaMaker
    Deluxe deluxe1 =    p.newDeluxe();

order.add(deluxe1)

    Deluxe

   */
