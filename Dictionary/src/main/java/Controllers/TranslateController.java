package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import dictionaryJava.DictionaryManagement;

public class TranslateController {
    @FXML
    private Label EnglishLabel;

    @FXML
    private ImageView SwitchButton;

    @FXML
    private Label VietnameseLabel;

    @FXML
    private TextArea sourceTextArea;

    @FXML
    private Button TranslateButton;

    @FXML
    private TextArea transalationTextArea;

    private DictionaryManagement dictionaryManager = new DictionaryManagement();
    private boolean isEnglish = false;

    @FXML
    private void initialize() {
        sourceTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            String translation;
            translation = dictionaryManager.translateFromVietnameseToEnglish(newValue);
            transalationTextArea.setText(translation);
        });
    }

    @FXML
    void Switch(javafx.scene.input.MouseEvent event) {
        isEnglish = !isEnglish;

        if (isEnglish) {
            EnglishLabel.setText("TIẾNG ANH");
            VietnameseLabel.setText("TIẾNG VIỆT");
        } else {
            EnglishLabel.setText("TIẾNG VIỆT");
            VietnameseLabel.setText("TIẾNG ANH");
        }

        translate(); // Cập nhật lại dịch vụ khi chuyển đổi ngôn ngữ
    }

    @FXML
    public void translate() {
        String textToTranslate = sourceTextArea.getText().trim();
        String translation = dictionaryManager.translateFromVietnameseToEnglish(textToTranslate);
        transalationTextArea.setText(translation);
    }

}
