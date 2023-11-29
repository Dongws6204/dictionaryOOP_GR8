package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import dictionaryJava.DictionaryManagement;
import java.util.List;

public class TranslateController extends DictionaryManagement{
    @FXML
    private TextArea sourceTextArea;

    @FXML
    private TextArea transalationTextArea;

//    private DictionaryManagement dictionaryManagement;

//    public TranslateController() {
//        dictionaryManagement = new DictionaryManagement();
//    }

    @FXML
    private void initialize() {
        // Thêm lắng nghe sự kiện khi người dùng nhập dữ liệu vào sourceTextArea
        sourceTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            // Kiểm tra người dùng đã nhập từ tiếng Anh hay tiếng Việt
            if (isEnglishText(newValue)) {
                // Nếu là tiếng Anh, dịch sang tiếng Việt và hiển thị kết quả trong transalationTextArea
                String translation = translateFromEnglishToVietnamese(newValue);
                transalationTextArea.setText(translation);
            } else {
                String translation = translateFromVietnameseToEnglish(newValue);
                transalationTextArea.setText(translation);
            }
        });
    }


    private boolean isEnglishText(String text) {
        return text.matches("^[a-zA-Z0-9\\s]+$");
    }
}
