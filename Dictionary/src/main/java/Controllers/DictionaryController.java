package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryController {

    @FXML
    private javafx.scene.control.ListView<String> DicSuggestListView;

    private ObservableList<String> suggestions = FXCollections.observableArrayList();

    @FXML
    private AnchorPane Dictionary;

    @FXML
    private TextArea DictionaryExplanation;

    @FXML
    private ImageView DictionaryFilledStar;

    @FXML
    private ScrollPane DictionaryPane;

    @FXML
    private TextField DictionarySearchBar;

    @FXML
    private ImageView dictionaryUnFilledStar;


    @FXML
    private void initialize() {
        DicSuggestListView.setVisible(false);
        // Khởi tạo danh sách gợi ý (suggestions) từ tệp dictionaries.txt
        suggestions = FXCollections.observableArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/txt/dictionaries.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" "); // Tách các từ theo dấu cách
                for (String word : words) {
                    suggestions.add(word); // Thêm từ vào danh sách gợi ý
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    @FXML
    void AddFavorite(MouseEvent event) {
        if (dictionaryUnFilledStar.isVisible()) {
            // If unfilled star is visible, user clicked on it
            dictionaryUnFilledStar.setVisible(false);
            DictionaryFilledStar.setVisible(true);
            // Add your logic for handling the favorite action when star is filled
        } else {
            // If filled star is visible, user clicked on it
            dictionaryUnFilledStar.setVisible(true);
            DictionaryFilledStar.setVisible(false);
            // Add your logic for handling the action when star is unfilled
        }
    }


}
