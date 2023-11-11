package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class DictionaryController {


    @FXML
    private TextArea Explanation;

    @FXML
    private Pane Dictionary;




    public void initialize()
    {
       Dictionary.setVisible(false);
    }

}

