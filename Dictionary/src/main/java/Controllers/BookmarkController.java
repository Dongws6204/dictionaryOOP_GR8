package Controllers;

import dictionaryJava.DictionaryManagement;
import dictionaryJava.Word;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
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
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookmarkController {

    @FXML
    private TextArea BookMarkExplanation;

    @FXML
    private ImageView BookMarkFilledStar;

    @FXML
    private ImageView BookMarkUnFilledStar;

    @FXML
    private AnchorPane Bookmark;

    @FXML
    private ScrollPane BookmarkPane;

    @FXML
    private TextField BookmarkSearchBar;
    @FXML
    private ImageView US;
    @FXML
    private javafx.scene.control.ListView<String> BookmarkSuggestListView;
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
        BookMarkUnFilledStar.setVisible(false);
        BookMarkFilledStar.setVisible(true);
        BookMarkFilledStar.setOnMouseClicked(event -> {
            wordBookMark.remove(suggestion, wordBookMark.get(suggestion));
            BookMarkUnFilledStar.setVisible(true);
            BookMarkFilledStar.setVisible(false);
            Platform.runLater(() -> loadSuggestions());
        });
        BookMarkUnFilledStar.setOnMouseClicked(event -> {
            wordBookMark.put(suggestion, wordBookMark.get(suggestion));
            BookMarkUnFilledStar.setVisible(false);
            BookMarkFilledStar.setVisible(true);
            Platform.runLater(() -> loadSuggestions());
        });
    }

    @FXML
    void handleSuggestionSelected(MouseEvent event) {
        String selectedSuggestion = BookmarkSuggestListView.getSelectionModel().getSelectedItem();
        if (selectedSuggestion != null) {
            BookmarkSearchBar.setText(selectedSuggestion);
            BookMarkExplanation.setText(getWordBookMark().get(selectedSuggestion.toLowerCase()));
        }
        US.setOnMouseClicked(event1 -> {
            dm.speakWord(selectedSuggestion);
        });
        AddFavorite(selectedSuggestion);
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


