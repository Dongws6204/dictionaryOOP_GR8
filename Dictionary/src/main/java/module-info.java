module com.example.Dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires freetts;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires com.google.gson;


    opens Application to javafx.fxml;
    exports Application;
    exports Controllers;
    opens Controllers to javafx.fxml;
}
