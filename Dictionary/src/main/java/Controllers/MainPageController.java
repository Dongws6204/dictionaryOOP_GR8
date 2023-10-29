package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class MainPageController {


    @FXML
    private HBox DictionaryPane;

    @FXML
    private Button Button1;

    @FXML
    private Button Button2;
    @FXML
    private Button Button3;


    // commandLine
    @FXML
    private TextField searchBar;
    @FXML
    private javafx.scene.control.ListView<String> ListView;
    private ObservableList<String> suggestions = FXCollections.observableArrayList();


    ///

    @FXML
    private void initialize() {
        ListView.setVisible(false);

        // Khởi tạo tạm danh sách gợi ý (suggestions)
        suggestions = FXCollections.observableArrayList(
                "Apple", "Banana", "Cherry", "Date", "Grape", "Lemon", "Mango", "Orange", "Peach", "Pear", "Strawberry"
        );
    }



    // các hàm xử lý commandLine
    @FXML
    private void search(String searchTerm) {

        ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();

        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().contains(searchTerm)) {
                filteredSuggestions.add(suggestion);
            }
        }

        ListView.setItems(filteredSuggestions);

        ListView.setVisible(!filteredSuggestions.isEmpty());



    }

    @FXML
    private void handleSuggestionSelected(MouseEvent event) {
        String selectedSuggestion = ListView.getSelectionModel().getSelectedItem();
        if (selectedSuggestion != null) {
            searchBar.setText(selectedSuggestion);
            DictionaryPane.setVisible(true); // Hiển thị DictionaryPane
            ListView.setVisible(false);
        }
    }


    @FXML
    private void showSuggestList (KeyEvent event) {
        String searchTerm = searchBar.getText().trim();
        if (!searchTerm.isEmpty()) {
            search(searchTerm);
            ListView.setVisible(true);
        } else {
            ListView.setVisible(false);
        }
    }
    /////


    @FXML
    void ShowTranslatePane(MouseEvent event) {

    }

    @FXML
    void showGamePane(MouseEvent event) {

    }


}
