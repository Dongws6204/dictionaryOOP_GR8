package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import dictionaryJava.DictionaryCommandLine;
import dictionaryJava.Word;
import dictionaryJava.DictionaryManagement;

public class DictionaryApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load FXML file
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        // Set the title of the stage
        primaryStage.setTitle("Dictionary Application");

        // Set the scene
        primaryStage.setScene(new Scene(root));

        // Show the stage
        primaryStage.show();

        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {

        launch(args);
        DictionaryManagement dm = new DictionaryManagement();
        DictionaryCommandLine cmd = new DictionaryCommandLine(dm);
        cmd.dictionaryAdvanced();


    }
}
