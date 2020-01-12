module com.put.poznan {//.Controllers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javax.persistence;
    //requires java.persistence;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;

    opens com.put.poznan.Controllers to javafx.fxml;
    exports com.put.poznan.Controllers;
    opens com.put.poznan.SchemaObjects to javafx.base, org.hibernate.orm.core;
    exports com.put.poznan.SchemaObjects;
}