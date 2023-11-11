module com.example.Dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires marytts.runtime;
    requires marytts.client;


    opens Application to javafx.fxml;
    exports Application;
    exports Controllers;
    opens Controllers to javafx.fxml;
}