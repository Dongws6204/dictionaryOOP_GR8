package Controllers;

import dictionaryJava.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class AddWordController {

    @FXML
    private Pane AlertPane;

    private FadeTransition fadeTransition;
    private Timeline timeline;


    @FXML
    private Button AddButton;

    @FXML
    private TextArea AddMeaningTextArea;

    @FXML
    private TextArea AddWordTextArea;

    @FXML
    private Text AlertText;

    @FXML
    private Pane ArlertPane;
    private DictionaryManagement dm = new DictionaryManagement();


    // Default constructor without parameters
    public AddWordController() {
        // Initialize the fade transition
        fadeTransition = new FadeTransition(Duration.millis(2000), AlertPane);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        // Initialize the timeline
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> hideAlert()));
        // This constructor can be empty or used for any default initialization
    }

    @FXML
    void AddWordAndMeaning(ActionEvent event) {
        String word = AddWordTextArea.getText().trim().toLowerCase();
        String meaning = AddMeaningTextArea.getText().trim().toLowerCase();

        // Check if word and meaning are not empty
        if (word.isEmpty() || meaning.isEmpty()) {
            showAlert("Vui lòng nhập cả từ và nghĩa trước khi thêm.");
        } else {
            // Call the addWord method from DictionaryManagement
            dm.addWord(word, meaning);
            showAlert("Đã thêm từ thành công");

            // Optionally, you can clear the input fields after adding a word
            AddWordTextArea.clear();
            AddMeaningTextArea.clear();
        }
    }
    private void showAlert(String alertText) {
        // Set the alert text
        AlertText.setText(alertText);

        // Reset the opacity and play the fade transition
        AlertPane.setVisible(true);
        fadeTransition.play();

        // Start the timeline to hide the alert after 3 seconds
        timeline.play();
    }

    private void hideAlert() {
        // Stop the fade transition
        fadeTransition.stop();

        // Reset the timeline
        timeline.stop();

        // Hide the alert
        AlertPane.setVisible(false);
    }
}


