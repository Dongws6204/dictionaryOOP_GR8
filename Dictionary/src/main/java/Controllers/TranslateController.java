package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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


    private boolean isEnglish = true; // Biến để theo dõi ngôn ngữ hiện tại

    @FXML
    void Switch(MouseEvent event) {
        // Đảo ngôn ngữ và cập nhật văn bản của hai Label
        if (isEnglish) {
            EnglishLabel.setText("TIẾNG VIỆT");
            VietnameseLabel.setText("TIẾNG ANH");
        } else {
            EnglishLabel.setText("TIẾNG VIỆT");
            VietnameseLabel.setText("TIẾNG ANH");
        }

        // Đảo giá trị của biến isEnglish
        isEnglish = !isEnglish;
    }

}
