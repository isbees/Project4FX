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
    //Goes to 2nd view
    public void openNewOrder() throws IOException {
        open("customization-view.fxml");
    }
    //Goes to 3rd view
    public void openCurrentOrder() throws IOException {
        open("currentorder-view.fxml");
    }
    public void openStoreOrders() throws IOException {
        open("storeorders-view.fxml");
    }
    public void open(String nameOfFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(nameOfFile));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        Stage stage = new Stage();
        if(nameOfFile.equals("currentorder-view.fxml")){
            stage.setTitle("Current-Orders!");

        }
        else if(nameOfFile.equals("customization-view.fxml")){
            stage.setTitle("Customizing-Your-Pizza-Order!");
        }
        else if(nameOfFile.equals("storeorders-view.fxml")){
            stage.setTitle("Your-Store-Orders!");
        }
        else{
            return;
        }
        stage.setScene(scene);
        stage.show();
    }

}