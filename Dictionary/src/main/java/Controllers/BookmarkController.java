package Controllers;

import dictionaryJava.DictionaryManagement;
import javafx.application.Platform;
import javafx.beans.property.MapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Map;

public class BookmarkController {

    @FXML
    private TextArea BookMarkExplanation;

    @FXML
    private ImageView BookmarkFilledStar;

    @FXML
    private ImageView BookmarkUnFilledStar;


    @FXML
    private TextField BookmarkSearchBar;
    @FXML
    private ImageView US;
    @FXML
    private javafx.scene.control.ListView<String> BookmarkSuggestListView;
    private String selectedSuggestion;
    private boolean isFirstKeyEvent = true;

    private ObservableList<String> suggestions = FXCollections.observableArrayList();
    private MapProperty<String, String> wordBookMark;

    public MapProperty<String, String> getWordBookMark() {
        return dataSharingManager.getWordBookMark();
    }

    public void setWordBookMark(MapProperty<String, String> wordBookMark) {
        dataSharingManager.setWordBookMark(wordBookMark);
    }

    private DataSharingManager dataSharingManager;
    public DictionaryManagement dm = new DictionaryManagement();

    public BookmarkController() {
        this.dataSharingManager = DataSharingManager.getInstance();
        this.wordBookMark = dataSharingManager.getWordBookMark();
        // Lắng nghe thay đổi của wordBookMark và cập nhật danh sách suggestions
        wordBookMark.addListener((MapChangeListener<String, String>) change -> {
            loadSuggestions();
        });
    }


    @FXML
    public void initialize() {
        suggestions = FXCollections.observableArrayList();
        if (!wordBookMark.isEmpty()) {
            Map.Entry<String, String> firstEntry = wordBookMark.entrySet().iterator().next();
            String firstKey = firstEntry.getKey();
            suggestions.add(firstKey);
        }

    }

    @FXML
    private void search(String searchTerm) {
//        if (isFirstKeyEvent) {
//            loadSuggestions();
//            isFirstKeyEvent = false;
//        }
        ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();

        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().contains(searchTerm)) {
                filteredSuggestions.add(suggestion);
            }
        }
        BookmarkSuggestListView.setItems(filteredSuggestions);
        BookmarkSuggestListView.setVisible(!filteredSuggestions.isEmpty());
    }


    @FXML
    void AddFavorite(String suggestion) {
        // Nếu từ đã được yêu thích, hiển thị sao đã được điền
        BookmarkUnFilledStar.setVisible(false);
        BookmarkFilledStar.setVisible(true);
        BookmarkFilledStar.setOnMouseClicked(event -> {
            wordBookMark.remove(suggestion, wordBookMark.get(suggestion));
            BookmarkUnFilledStar.setVisible(true);
            BookmarkFilledStar.setVisible(false);
            Platform.runLater(() -> loadSuggestions());
        });
        BookmarkUnFilledStar.setOnMouseClicked(event -> {
            wordBookMark.put(suggestion, wordBookMark.get(suggestion));
            BookmarkUnFilledStar.setVisible(false);
            BookmarkFilledStar.setVisible(true);
            Platform.runLater(() -> loadSuggestions());
        });
    }

    @FXML
    void handleSuggestionSelected(MouseEvent event) {
        selectedSuggestion = BookmarkSuggestListView.getSelectionModel().getSelectedItem();
        if (selectedSuggestion != null) {
            BookmarkSearchBar.setText(selectedSuggestion);
            String meaning = wordBookMark.get(selectedSuggestion.toLowerCase());
            meaning = formatMeaning(meaning);
            String formattedText = String.format("%s \n %s", selectedSuggestion, meaning);
            BookMarkExplanation.setText(formattedText);
            BookMarkExplanation.setPrefRowCount(meaning.split("\n").length);
            BookMarkExplanation.setWrapText(true);
        }
        US.setOnMouseClicked(event1 -> {
            dm.speakWord(selectedSuggestion);
        });
        AddFavorite(selectedSuggestion);
    }

    private String formatMeaning(String meaning) {
        meaning = meaning.replaceAll("\\(\\+ ", " (").replaceAll("- ", "\n- ");
        meaning = meaning.replaceAll("\\* ", "\n* ");
        meaning = meaning.replaceAll("=", "\n -> ");
        meaning = meaning.replaceAll("!", "\n! ");
        meaning = meaning.replaceAll("\\+ ", "\n  + ");
        meaning = meaning.replaceAll("@", "\n@ ");
        meaning = meaning.replaceAll("/ \\(", "/ \n( ");
        meaning = meaning.replaceFirst("(?i)(" + selectedSuggestion + ")", "");

        return meaning;
    }
    @FXML
    void showSuggestList(KeyEvent event) {
        String searchTerm = BookmarkSearchBar.getText().trim();
        BookmarkSuggestListView.setVisible(true);
        search(searchTerm);
    }

    public void show(MapProperty<String, String> bookMark) {
        for (String key : wordBookMark.keySet()) {
            System.out.println("Key: " + key + ", Value: " + getWordBookMark().get(key));
        }
    }

    private void loadSuggestions() {
        suggestions.clear();  // Xóa danh sách hiện tại để tránh thêm các phần tử trùng lặp
        for (Map.Entry<String, String> entry : wordBookMark.entrySet()) {
            String key = entry.getKey();
            suggestions.add(key);
        }
    }


}


