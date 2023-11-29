package Controllers;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class MainPageController {

    //Button
    @FXML
    private Button DictionaryButton;
    @FXML
    private Button BookmarkButton;
    @FXML
    private Button TranslateButton;
    @FXML
    private Button GameButton;
    private Button activeButton;

    //----------//


    @FXML
    public Pane TranslateControllers;
    @FXML
    public Pane DictionaryControllers;
    @FXML
    public Pane BookMarkControllers;
    @FXML
    public Pane GameControllers;
    @FXML
    public Pane Welcome;

    //----------//

    //others


    @FXML
    void ShowDictionaryController(MouseEvent event) {
        showController(DictionaryControllers);
    }

    @FXML
    void ShowTranslateController(MouseEvent event) {
        showController(TranslateControllers);
    }

    @FXML
    void ShowBookmarkController(MouseEvent event) {
        showController(BookMarkControllers);
    }

    @FXML
    void ShowGameController(MouseEvent event) {
        showController(GameControllers);
        GameControllers gameControllers = new GameControllers(GameControllers);
        gameControllers.play(GameControllers);
        if (gameControllers.isCheck() == true) {
            GameControllers.setVisible(false);
        }

    }

    private void showController(Pane controller) {
        DictionaryControllers.setVisible(controller == DictionaryControllers);
        TranslateControllers.setVisible(controller == TranslateControllers);
        BookMarkControllers.setVisible(controller == BookMarkControllers);
        GameControllers.setVisible(controller == GameControllers);

        Welcome.setVisible(!(controller == DictionaryControllers || controller == TranslateControllers ||
                controller == BookMarkControllers || controller == GameControllers));


        if (activeButton != null) {
            activeButton.setStyle("");
        }

        if (controller == DictionaryControllers) {
            activeButton = DictionaryButton;
        } else if (controller == TranslateControllers) {
            activeButton = TranslateButton;
        } else if (controller == BookMarkControllers) {
            activeButton = BookmarkButton;
        } else if (controller == GameControllers) {
            activeButton = GameButton;
        }

        if (activeButton != null) {
            activeButton.setStyle("-fx-background-color: #7e7e7e;" +
                    "    -fx-text-fill: #ffffff;");
        }
    }






}
