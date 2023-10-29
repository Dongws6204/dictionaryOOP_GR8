module com.example.Dictionary {
    requires javafx.controls;
    requires javafx.fxml;


    opens Application to javafx.fxml;
    exports Application;
    exports Controllers;
    opens Controllers to javafx.fxml;
}