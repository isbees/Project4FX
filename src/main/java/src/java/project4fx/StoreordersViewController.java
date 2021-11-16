package src.java.project4fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;

/**
 * @author Zachary Goldman, Isaac Brukhman
 * StoreordersViewController deals with the storeorder view
 */

public class StoreordersViewController {

    private HelloController mainController;
    private StoreOrders storeOrders;
    private ArrayList<String> phoneNumbers = new ArrayList<String>();

    @FXML
    Label custPhoneNumberLabel;
    @FXML
    Label ordertotalLabel;
    @FXML
    TextField orderTotal;
    @FXML
    ComboBox phoneNumbersComboBox;
    @FXML
    Button cancelOrderButton;
    @FXML
    Button exportStoreOrdersButton;
    @FXML
    ListView ordersList;

    public void setMainController(HelloController controller) {
        mainController = controller;
    }

    public void setStoreOrder(StoreOrders orders) {
        storeOrders = orders;
    }

    public void setOrderTotal() {
        Order a = storeOrders.getOrders().get(storeOrders.getTotalOrders() - 1);
        double firstSum = a.calcTax() + a.calcSubTotal();
        String s = String.format("%1.2f", firstSum);
        orderTotal.setText(s);
    }

    /**
     * setListViews() sets up the list view by filling it with the orders in
     * the user's current order
     * @param number takes in photo number
     */
    public void setListView(String number) {
        Order orderToDisplay = new Order();
        for (int i = 0; i < storeOrders.getTotalOrders(); i++) {
            if (storeOrders.getOrders().get(i).getNumber().equals(number)) {
                orderToDisplay = storeOrders.getOrders().get(i);
                break;
            }
        }
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < orderToDisplay.getTotalPizzas(); i++) {
            output.add(orderToDisplay.toString(i));
        }

        ObservableList<String> listOfOrders = FXCollections.observableArrayList(output);
        ordersList.setItems(listOfOrders);
    }

    @FXML
    void setDefaultListView() {
        setListView(storeOrders.getOrders().get(storeOrders.getTotalOrders() - 1).getNumber());
    }

    @FXML
    void setUpComboBox() {
        if(storeOrders.getTotalOrders()==0){
            ObservableList<String> items = FXCollections.observableArrayList("");
            phoneNumbersComboBox.setItems(items);
            phoneNumbersComboBox.setValue("");
            return;
        }
        //Sets up the phone numbers in combobox
        for (int i = 0; i < storeOrders.getTotalOrders(); i++) {
            phoneNumbers.add(storeOrders.getOrders().get(i).getNumber());
        }
        ObservableList<String> items = FXCollections.observableArrayList(phoneNumbers);
        phoneNumbersComboBox.setItems(items);

        //Defaults the most recent phone number
        int numOrders = storeOrders.getTotalOrders();
        phoneNumbersComboBox.setValue(storeOrders.getOrders().get(numOrders - 1).getNumber());
    }

    //When you change combobox selection -> change orders
    @FXML
    void onComboBoxNumberClick() {
        String phone = String.valueOf(phoneNumbersComboBox.getSelectionModel().getSelectedItem());
        setListView(phone);
        resetTotalPrice(phone);
    }

    //When you select a different order -> change price
    @FXML
    void resetTotalPrice(String phoneN) {
        Order o = findOrder(phoneN);
        double newSum = o.calcTax() + o.calcSubTotal();
        String s = String.format("%1.2f", newSum);
        orderTotal.setText(s);
    }
    Order findOrder(String phoneN){
        Order o = new Order();
        for (int i = 0; i < storeOrders.getTotalOrders(); i++) {
            if (storeOrders.getOrders().get(i).getNumber().equals(phoneN)) {
                o = storeOrders.getOrders().get(i);
                break;
            }
        }
        return o;
    }
    @FXML
    void onCancelOrderButtonClick() {
        //get the order's phone number
        String phoneN = String.valueOf(phoneNumbersComboBox.getSelectionModel().getSelectedItem());
        if(phoneN.equals("")){
            return;
        }
        Order o = findOrder(phoneN);

        if(storeOrders.getOrders()!=null&&storeOrders.getOrders().size()>0) {
            storeOrders.remove(o);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Your order has been cancelled");
        alert.setHeaderText("We've gone ahead and cancelled your order.");
        alert.setContentText("We all make mistakes, feel free to order another one when you feel ready.");
        alert.showAndWait();
        if(storeOrders.getOrders().size()!=0) {
            phoneNumbersComboBox.setValue(storeOrders.getOrders().get(0).getNumber());
        }
        phoneNumbers = new ArrayList<String>();
        setUpComboBox();
    }

    @FXML
    void exportFile(ActionEvent event) throws FileNotFoundException {
        String selectedNum =(String) phoneNumbersComboBox.getSelectionModel().getSelectedItem();
        if(selectedNum.equals("")){
            return;
        }
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file

        //Write out the info to the file
        writeToFileExport(targetFile);

    }

    void writeToFileExport(File file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        //Write it all to the file->
        for (int i = 0; i < storeOrders.getTotalOrders(); i++) {
            pw.println(storeOrders.printOrder(i));
        }
        pw.close();
    }


}
