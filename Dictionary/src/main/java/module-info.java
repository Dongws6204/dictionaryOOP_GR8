module com.example.Dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Application to javafx.fxml;
    exports Application;
    exports Controllers;
    opens Controllers to javafx.fxml;
}