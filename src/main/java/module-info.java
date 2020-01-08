module com.put.poznan {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.put.poznan to javafx.fxml;
    exports com.put.poznan;
}