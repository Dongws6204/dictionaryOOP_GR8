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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryController extends DictionaryManagement {
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
//    private DictionaryManagement dm = new DictionaryManagement();

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
        List<Word> words = getWords(); // Sử dụng generics để xác định kiểu dữ liệu trong danh sách
        for (Word w : words) {
            suggestions.add(w.getWordTarget());
            wordMap.put(w.getWordTarget(), w.getWordExplain());
            wordStatus.put(w.getWordTarget(), false);
        }
        DictionarySearchBar.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleEnterKeyPress();
            }
        });
        dictionaryUnFilledStar.setVisible(false);
        DictionaryFilledStar.setVisible(false);
        US.setVisible(false);
        AddWordPane.setVisible(false);
        AddWordPane.setDisable(true);
        RemoveAlertPane.setVisible(false);
        RemoveAlertPane.setDisable(true);
        RemoveUpdatePane.setVisible(false);
        RemoveUpdatePane.setDisable(true);
        AlertPane.setVisible(false);
        AlertPane.setDisable(true);
    }
    private void handleEnterKeyPress() {
        String searchTerm = DictionarySearchBar.getText().trim().toLowerCase();

        // Check if the entered term is in the suggestions
        if (suggestions.contains(searchTerm)) {
            // Display the meaning for the selected suggestion
            int index = suggestions.indexOf(searchTerm);
            showMeaning(searchTerm);
            selectedSuggestion = suggestions.get(index);
        } else {
            // Show an alert if the suggestion is not found
            showAlert("Từ không được tìm thấy.", 3);
        }
    }

    private void showMeaning(String selectedSuggestion) {
        // Logic to display the meaning for the selected suggestion
        String meaning = wordMap.get(selectedSuggestion.toLowerCase());
        meaning = formatMeaning(meaning);
        meaning = meaning.replaceFirst("(?i)(" + selectedSuggestion + ")", "");

        String formattedText = String.format(" %s \n %s", selectedSuggestion, meaning);
        DictionaryExplanation.setText(formattedText);
        DictionaryExplanation.setPrefRowCount(meaning.split("\n").length);
        DictionaryExplanation.setWrapText(true);

        US.setOnMouseClicked(event1 -> {
            speakWord(selectedSuggestion);
        });

        addFavorite(selectedSuggestion);
        dictionaryUnFilledStar.setVisible(true);
        US.setVisible(true);
    }


    @FXML
    private void search(String searchTerm) {

        ObservableList<String> filteredSuggestions = FXCollections.observableArrayList();

        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().startsWith(searchTerm.toLowerCase())) {
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


            DictionarySearchBar.setText(selectedSuggestion);
            US.setOnMouseClicked(event1 -> {
                speakWord(selectedSuggestion);
            });
        }
        addFavorite(selectedSuggestion);
        dictionaryUnFilledStar.setVisible(true);
        US.setVisible(true);
    }


    private String formatMeaning(String meaning) {
        if (meaning == null) {
            return ""; // hoặc thực hiện xử lý khác tùy thuộc vào yêu cầu của bạn
        }
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

    private void reloadDictionary() {
        // Cập nhật giao diện người dùng
        DicSuggestListView.getItems().setAll(suggestions);
        DicSuggestListView.setVisible(false);
        DictionaryExplanation.clear();
        DictionarySearchBar.clear();
        dictionaryUnFilledStar.setVisible(false);
        DictionaryFilledStar.setVisible(false);
        US.setVisible(false);

    }

    @FXML
    void AddWordAndMeaning(ActionEvent event) {
        String word = AddWordTextArea.getText().trim().toLowerCase();
        String meaning = AddMeaningTextArea.getText().trim().toLowerCase();

        if (word.isEmpty() || meaning.isEmpty()) {
            showAlert("Vui lòng nhập cả từ và nghĩa trước khi thêm.",3);
        } else {
            wordMap.put(word, meaning);
            showAlert("Đã thêm từ "+word+" thành công.",3);
            suggestions.add(word);
            addWord(word,meaning);


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

        if (selectedSuggestion != null) {
            String meaning = wordMap.get(selectedSuggestion.toLowerCase());
            RemoveUpdateMeaning.setText(meaning);
            RemoveUpdateWord.setText(selectedSuggestion);
            RemoveUpdateMeaning.setWrapText(true);

            RemoveUpdatePane.setVisible(true);
            RemoveUpdatePane.setDisable(false);
            DictionaryPane.setDisable(true);
        } else {
            // Xử lý khi selectedSuggestion là null, ví dụ: hiển thị một thông báo hoặc không làm gì cả.
            showAlert("Hãy chọn từ để sửa.",3);
        }
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
            wordMap.remove(oldWord);
            suggestions.remove(oldWord);
            wordMap.put(newWord, newExplain);
            suggestions.add(newWord);
            showAlert("Đã sửa từ " + selectedSuggestion + " thành công.",3);

            editWord(oldWord,newWord,newExplain);

        }
        reloadDictionary();
        CloseRemoveUpdatePane(event);
    }
    @FXML
    void ShowRemoveAlertPane(MouseEvent event)
    {
        if (selectedSuggestion != null) {
            RemoveAlertPane.setVisible(true);
            RemoveAlertPane.setDisable(false);
            DictionaryPane.setDisable(true);
        }
        else {
            showAlert("Hãy chọn từ để xóa.",3);
        }
    }
    @FXML
    void CloseRemoveAlertPane(MouseEvent event) {
        RemoveAlertPane.setVisible(false);
        RemoveAlertPane.setDisable(true);
        DictionaryPane.setDisable(false);
    }
    @FXML
    void RemoveWord(MouseEvent event)
    {
        reloadDictionary();
        suggestions.remove(selectedSuggestion);

        deleteWordDb(selectedSuggestion);

        showAlert("Đã xóa từ thành công",3);
        CloseRemoveUpdatePane(event);
        CloseRemoveAlertPane(event);
    }

    @FXML
    private void showAlert(String content, int durationInSeconds) {
        AlertText.setText(content);

        AlertPane.setVisible(true);
        AlertPane.setDisable(false);


        // Tạo một Timeline để tự động đóng AlertPane sau một khoảng thời gian
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(durationInSeconds), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeAlert();
            }
        }));
        timeline.setCycleCount(1); // Chỉ chạy một lần
        timeline.play();
    }
    @FXML
    private void closeAlert() {
        AlertPane.setVisible(false);
        AlertPane.setDisable(true);
    }
}

