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
    private Pane labelPane1;
    @FXML
    private Pane TranslateControllers;
    @FXML
    private Pane DictionaryControllers;
    @FXML
    private Pane BookMarkControllers;
    @FXML
    private Pane sourceTextAreaPane;
    @FXML
    private Pane GameControllers;
    @FXML
    private ScrollPane DictionaryPane;
    @FXML
    private ScrollPane BookExplanationPane;

    //----------//

    //others
    @FXML
    private TextField DictionarySearchBar;
    @FXML
    private TextField BookmarkSearchBar;

    @FXML
    private javafx.scene.control.ListView<String> DicSuggestListView;
    @FXML
    private javafx.scene.control.ListView<String> BookSuggestListView;
    private ObservableList<String> suggestions = FXCollections.observableArrayList();
    //---------//
    @FXML
    private void initialize() {
        DicSuggestListView.setVisible(false);
        // Khởi tạo tạm danh sách gợi ý (suggestions)
        suggestions = FXCollections.observableArrayList(
                "Apple", "Banana", "Cherry", "Date", "Grape", "Lemon", "Mango", "Orange", "Peach", "Pear", "Strawberry"
        );
    }



    @FXML
    private void search(String searchTerm) {

        ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();

        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().contains(searchTerm)) {
                filteredSuggestions.add(suggestion);
            }
        }
        DicSuggestListView.setItems(filteredSuggestions);
        DicSuggestListView.setVisible(!filteredSuggestions.isEmpty());
    }

    @FXML
    private void handleSuggestionSelected(MouseEvent event) {
        String selectedSuggestion = DicSuggestListView.getSelectionModel().getSelectedItem();
        if (selectedSuggestion != null) {
            DictionarySearchBar.setText(selectedSuggestion);
        }

    }


    @FXML
    private void showSuggestList (KeyEvent event) {
        String searchTerm = DictionarySearchBar.getText().trim();

        DicSuggestListView.setVisible(true);

        search(searchTerm);
    }
    /////

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
