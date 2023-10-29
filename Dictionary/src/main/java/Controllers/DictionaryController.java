package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class DictionaryController {

    @FXML
    private HBox DictionaryPane;

    public void initialize()
    {
       DictionaryPane.setVisible(false);
    }

}

