module src.java.project4fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens src.java.project4fx to javafx.fxml;
    exports src.java.project4fx;
}