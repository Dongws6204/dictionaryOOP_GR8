package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class DictionaryController {


    @FXML
    private TextField DictionarySearchBar;
    @FXML
    private ScrollPane DictionaryPane;
    @FXML
    private TextArea Explanation;
    @FXML
    private Pane Dictionary;
    @FXML
    private ImageView FilledStar;

    @FXML
    private ImageView unFilledStar;


    private ObservableList<String> suggestions = FXCollections.observableArrayList();
    @FXML
    private javafx.scene.control.ListView<String> DicSuggestListView;

    public void initialize()
    {
        DicSuggestListView.setVisible(false);
        // Khởi tạo tạm danh sách gợi ý (suggestions)
        suggestions = FXCollections.observableArrayList(
                "Apple", "Banana", "Cherry", "Date", "Grape", "Lemon", "Mango", "Orange", "Peach", "Pear", "Strawberry"
        );
    }

    @FXML
    void AddFavorite(MouseEvent event) {
        boolean isFilled = FilledStar.isVisible();
        // Đảo ngược trạng thái Star
        FilledStar.setVisible(!isFilled);
        unFilledStar.setVisible(isFilled);
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

}

