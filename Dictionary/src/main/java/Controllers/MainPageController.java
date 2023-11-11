package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MainPageController {





    //Button
    @FXML
    private Button DictionaryButton;
    @FXML
    private Button BookmarkButton;
    @FXML
    private Button TranslateButton;
    @FXML
    private Button GameButton;
    @FXML
    private Button addWordButton;
    //----------//



    //Pane
    @FXML
    private Pane TranslateControllers;
    @FXML
    private Pane DictionaryControllers;
    @FXML
    private Pane BookMarkControllers;
    @FXML
    private Pane GameControllers;

    @FXML
    private ScrollPane BookExplanationPane;

    //----------//

    //others

    @FXML
    private TextField BookmarkSearchBar;

    @FXML
    private javafx.scene.control.ListView<String> BookSuggestListView;

    //---------//
    @FXML
    private void initialize() {

    }


    @FXML
    void ShowDictionaryControllers(MouseEvent event) {
        DictionaryControllers.setVisible(true);
        TranslateControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        GameControllers.setVisible(false);
    }

    @FXML
    void ShowTranslateControllers(MouseEvent event) {
        TranslateControllers.setVisible(true);
        DictionaryControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        GameControllers.setVisible(false);
    }

    @FXML
    void ShowBookmarkControllers(MouseEvent event) {
        BookMarkControllers.setVisible(true);
        TranslateControllers.setVisible(false);
        DictionaryControllers.setVisible(false);
        GameControllers.setVisible(false);
    }

    @FXML
    void ShowAddWordControllers(MouseEvent event) {

    }

    @FXML
    void ShowGameControllers(MouseEvent event) {
        GameControllers.setVisible(true);
        DictionaryControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        TranslateControllers.setVisible(false);
    }

}
