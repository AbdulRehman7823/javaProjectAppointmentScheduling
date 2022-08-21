module com.scheduleproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires java.sql;
    opens com.scheduleproject.controller to javafx.fxml;
    opens com.scheduleproject.model to javafx.fxml;
    exports com.scheduleproject;
    exports com.scheduleproject.controller;
    exports com.scheduleproject.model;
    exports com.scheduleproject.DBA;
    exports com.scheduleproject.Application;
    opens com.scheduleproject.Application to javafx.fxml;
}