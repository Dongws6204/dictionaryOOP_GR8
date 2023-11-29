package Controllers;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;



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
    @FXML
    private Button addWordButton;
    //----------//


    @FXML
    public Pane TranslateControllers;
    @FXML
    public Pane DictionaryControllers;
    @FXML
    public Pane BookMarkControllers;
    @FXML
    public Pane GameControllers;

    //----------//

    //others



    @FXML
    void ShowDictionaryController(MouseEvent event) {

        DictionaryControllers.setVisible(true);
        TranslateControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        GameControllers.setVisible(false);
    }

    @FXML
    void ShowTranslateController(MouseEvent event) {
        TranslateControllers.setVisible(true);
        DictionaryControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        GameControllers.setVisible(false);
    }
    @FXML
    void ShowBookmarkController(MouseEvent event) {
        BookMarkControllers.setVisible(true);
        TranslateControllers.setVisible(false);
        DictionaryControllers.setVisible(false);
        GameControllers.setVisible(false);
    }

    @FXML
    void ShowGameController(MouseEvent event) {
        GameControllers.setVisible(true);
        DictionaryControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        TranslateControllers.setVisible(false);
        GameControllers gameControllers = new GameControllers(GameControllers);
        gameControllers.play(GameControllers);
        gameControllers.play2();
        if (gameControllers.isCheck() == true) {
            GameControllers.setVisible(false);
        }
    }

}
