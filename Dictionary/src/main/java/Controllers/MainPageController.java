package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class MainPageController {


    public int level = 1;

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
    public Pane TranslateControllers;
//    public Pane getTranslateControllers() {
//        return TranslateControllers;
//    }
    @FXML
    public Pane DictionaryControllers;
//    public Pane getDictionaryControllers() {
//        return DictionaryControllers;
//    }
    @FXML
    public Pane BookMarkControllers;
//    public Pane getBookMarkControllers() {
//        return BookMarkControllers;
//    }
    @FXML
    private Pane sourceTextAreaPane;
    @FXML
    public Pane GameControllers;
//    public Pane getGameControllers() {
//        return GameControllers;
//    }
//    public void setGameControllers(Pane GameControllers) {
//        this.GameControllers = GameControllers;
//    }
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
        GameControllers gameControllers = new GameControllers(GameControllers);
        gameControllers.play(GameControllers);
        if (gameControllers.isCheck() == true) {
            GameControllers.setVisible(false);
        }
    }

}
