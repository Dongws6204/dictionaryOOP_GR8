package Controllers;

import dictionaryJava.DictionaryManagement;
import dictionaryJava.Word;
import javafx.beans.property.MapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryController {
    @FXML
    private javafx.scene.control.ListView<String> DicSuggestListView;
    @FXML
    private AnchorPane Dictionary;

    @FXML
    private TextArea DictionaryExplanation;

    @FXML
    private ImageView US;
    @FXML
    private ImageView DictionaryFilledStar;

    @FXML
    private TextField DictionarySearchBar;

    @FXML
    private ImageView dictionaryUnFilledStar;
    private ObservableList<String> suggestions = FXCollections.observableArrayList();
    private String selectedSuggestion;
    private DictionaryManagement dm = new DictionaryManagement();

    private Map<String, String> wordMap = new HashMap<>();
    private Map<String, Boolean> wordStatus = new HashMap<>();
    private DataSharingManager dataSharingManager;
    public MapProperty<String, String> wordBookMark;

    public MapProperty<String, String> getWordBookMark() {
        return dataSharingManager.getWordBookMark();
    }

    public void setWordBookMark(MapProperty<String, String> wordBookMark) {
        dataSharingManager.setWordBookMark(wordBookMark);
    }

    public DictionaryController() {
        this.dataSharingManager = DataSharingManager.getInstance();
        this.wordBookMark = dataSharingManager.getWordBookMark();
    }


    @FXML
    private void initialize() {
        DicSuggestListView.setVisible(false);
        // Khởi tạo danh sách gợi ý (suggestions) từ tệp dictionaries.txt
        suggestions = FXCollections.observableArrayList();
        List<Word> words = dm.getWords(); // Sử dụng generics để xác định kiểu dữ liệu trong danh sách
        for (Word w : words) {
            suggestions.add(w.getWordTarget());
            wordMap.put(w.getWordTarget(), w.getWordExplain());
            wordStatus.put(w.getWordTarget(), false);
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
        selectedSuggestion = DicSuggestListView.getSelectionModel().getSelectedItem();
        if (selectedSuggestion != null) {


            String meaning = wordMap.get(selectedSuggestion.toLowerCase());

            meaning = formatMeaning(meaning);
            meaning = meaning.replaceFirst("(?i)(" + selectedSuggestion + ")", "");

            String formattedText = String.format("%s \n %s", selectedSuggestion, meaning);
            DictionaryExplanation.setText(formattedText);
            DictionaryExplanation.setPrefRowCount(meaning.split("\n").length);
            DictionaryExplanation.setWrapText(true);


            US.setOnMouseClicked(event1 -> {
                dm.speakWord(selectedSuggestion);
            });
        }
        addFavorite(selectedSuggestion);
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
    private void showSuggestList(KeyEvent event) {
        String searchTerm = DictionarySearchBar.getText().trim();

        DicSuggestListView.setVisible(true);

        search(searchTerm);
    }

    private void addFavorite(String suggestion) {
        Boolean status = wordStatus.get(suggestion);
        if (status != null && status) {
            // Nếu từ đã được yêu thích, hiển thị sao đã được điền
            dictionaryUnFilledStar.setVisible(false);
            DictionaryFilledStar.setVisible(true);
            DictionaryFilledStar.setOnMouseClicked(event -> {
                // Thêm mã xử lý sự kiện của bạn ở đây
                wordStatus.put(suggestion, false);
                dictionaryUnFilledStar.setVisible(true);
                DictionaryFilledStar.setVisible(false);
                wordBookMark.remove(suggestion, wordMap.get(suggestion));
//                    setWordBookMark(wordBookMark);
            });
        } else {
            // Nếu từ chưa được yêu thích, hiển thị sao chưa được điền
            dictionaryUnFilledStar.setVisible(true);
            DictionaryFilledStar.setVisible(false);
            dictionaryUnFilledStar.setOnMouseClicked(mouseEvent -> {
                wordStatus.put(suggestion, true);
                dictionaryUnFilledStar.setVisible(false);
                DictionaryFilledStar.setVisible(true);
                wordBookMark.put(suggestion, wordMap.get(suggestion));
//              setWordBookMark(wordBookMark);
            });
        }
    }


    public void show() {
        for (Map.Entry<String, String> entry : getWordBookMark().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
}
