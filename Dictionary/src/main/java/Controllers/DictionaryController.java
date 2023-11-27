package Controllers;

import dictionaryJava.DictionaryManagement;
import dictionaryJava.Word;
import javafx.beans.property.MapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryController {
    // -------------DictionaryPane--------------------
    @FXML
    private AnchorPane DictionaryPane;
    @FXML
    private Button addWordButton;
    @FXML
    private javafx.scene.control.ListView<String> DicSuggestListView;
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
    //-------------End of DictionaryPane-------------------

    //-------------AddWordPane----------------------------
    @FXML
    private AnchorPane AddWordPane;
    @FXML
    private Button AddButton;
    @FXML
    private TextArea AddMeaningTextArea;
    @FXML
    private TextArea AddWordTextArea;
    @FXML
    private ImageView CloseAWbutton;

    //------------End of AddWordPane------------------------

    //------------RemoveUpdatePane--------------------------
    @FXML
    private AnchorPane RemoveUpdatePane;
    @FXML
    private TextArea RemoveUpdateWord;
    @FXML
    private TextArea RemoveUpdateMeaning;
    @FXML
    private Button RemoveButton;
    @FXML
    private Button updateButton;
    @FXML
    private ImageView CloseRUbutton;

    //------------End of RemoveUpdatePane--------------------

    //------------RemoveAlertPane----------------------------
    @FXML
    private AnchorPane RemoveAlertPane;
    @FXML
    private Label RemoveAlertTextArea;
    @FXML
    private Button RemoveAlertYesButton;
    @FXML
    private Button RemoveAlertCancelButton;
    @FXML
    private ImageView CloseRAbutton;
    //-------------End of RemoveAlertPane--------------------
    @FXML
    private Pane AlertPane;

    @FXML
    private Text AlertText;







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
        AddWordPane.setVisible(false);
        AddWordPane.setDisable(true);
        RemoveAlertPane.setVisible(false);
        RemoveAlertPane.setDisable(true);
        RemoveUpdatePane.setVisible(false);
        RemoveUpdatePane.setDisable(true);
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

    private void updateDictionaryPane(String word, String meaning) {
        String formattedText = String.format("%s \n %s", word, formatMeaning(meaning));
        DictionaryExplanation.setText(formattedText);
        DictionaryExplanation.setPrefRowCount(meaning.split("\n").length);
        DictionaryExplanation.setWrapText(true);
    }// chưa dùng đến

    private void reloadDictionary() {
        // Xóa dữ liệu cũ
        suggestions.clear();
        wordMap.clear();
        wordStatus.clear();

        // Tải lại danh sách từ và gợi ý
        List<Word> words = dm.getWords();
        for (Word w : words) {
            suggestions.add(w.getWordTarget());
            wordMap.put(w.getWordTarget(), w.getWordExplain());
            wordStatus.put(w.getWordTarget(), false);
        }

        // Cập nhật giao diện người dùng
        DicSuggestListView.getItems().setAll(suggestions);
        DicSuggestListView.setVisible(false);
        DictionaryExplanation.clear();
        DictionarySearchBar.clear();
        dictionaryUnFilledStar.setVisible(false);
        DictionaryFilledStar.setVisible(false);

    }

    @FXML
    void AddWordAndMeaning(ActionEvent event) {
        String word = AddWordTextArea.getText().trim().toLowerCase();
        String meaning = AddMeaningTextArea.getText().trim().toLowerCase();

        if (word.isEmpty() || meaning.isEmpty()) {
            //showAlert("Vui lòng nhập cả từ và nghĩa trước khi thêm.");
        } else {
            dm.addWord(word, meaning);
            //showAlert("Đã thêm từ thành công");

            reloadDictionary();
            AddWordTextArea.clear();
            AddMeaningTextArea.clear();
        }
    }
    @FXML
    void ShowAddWordPane(MouseEvent event) {
        AddWordPane.setVisible(true);
        AddWordPane.setDisable(false);
        DictionaryPane.setDisable(true);
    }
    @FXML
    void CloseAddWordPane(MouseEvent event) {
        AddWordPane.setVisible(false);
        AddWordPane.setDisable(true);
        DictionaryPane.setDisable(false);
    }

    @FXML
    void showRemoveUpdatePane(MouseEvent event) {

        String meaning = wordMap.get(selectedSuggestion.toLowerCase());
        RemoveUpdateMeaning.setText(meaning);
        RemoveUpdateWord.setText(selectedSuggestion);
        RemoveUpdateMeaning.setWrapText(true);


        RemoveUpdatePane.setVisible(true);
        RemoveUpdatePane.setDisable(false);
        DictionaryPane.setDisable(true);
    }
    @FXML
    void CloseRemoveUpdatePane(MouseEvent event) {
        RemoveUpdatePane.setVisible(false);
        RemoveUpdatePane.setDisable(true);
        DictionaryPane.setDisable(false);
    }
    @FXML
    void UpdateWord(MouseEvent event)
    {
        String oldWord = selectedSuggestion;
        String newWord = RemoveUpdateWord.getText().trim();
        String newExplain = RemoveUpdateMeaning.getText().trim();

        // Kiểm tra xem từ mới và nghĩa mới có được nhập không
        if (!oldWord.equalsIgnoreCase(newWord) || !wordMap.get(oldWord.toLowerCase()).equalsIgnoreCase(newExplain)) {
            // Nếu có sự thay đổi, thực hiện cập nhật từ trong DictionaryManagement
            dm.deleteWord(oldWord);
            dm.addWord(newWord, newExplain);
        }
        reloadDictionary();
        CloseRemoveUpdatePane(event);
    }
    @FXML
    void ShowRemoveAlertPane(MouseEvent event)
    {
        RemoveAlertPane.setVisible(true);
        RemoveAlertPane.setDisable(false);
        DictionaryPane.setDisable(true);
    }
    @FXML
    void CloseRemoveAlertPane(MouseEvent event) {
        RemoveAlertPane.setVisible(false);
        RemoveAlertPane.setDisable(true);
    }
    @FXML
    void RemoveWord(MouseEvent event)
    {
        dm.deleteWord(selectedSuggestion);
        reloadDictionary();
        CloseRemoveUpdatePane(event);
        CloseRemoveAlertPane(event);

    }
}
