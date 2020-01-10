module com.put.poznan {//.Controllers {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
    requires javafx.graphics;

    opens com.put.poznan.Controllers to javafx.fxml;
    exports com.put.poznan.Controllers;

    exports com.put.poznan.SchemaObjects;
    opens com.put.poznan.SchemaObjects to javafx.base;

}