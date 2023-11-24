package Controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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



    //Pane
    @FXML
    private Pane labelPane1;
    @FXML
    public Pane TranslateControllers;
    //    public Pane getTranslateControllers() {
//        return TranslateControllers;
//    }
    @FXML
    public Pane DictionaryControllers;
    //    public Pane getDictionaryControllers() {
//        return DictionaryControllers;
//    }
    @FXML
    public Pane BookMarkControllers;
    //    public Pane getBookMarkControllers() {
//        return BookMarkControllers;
//    }
    @FXML
    private Pane sourceTextAreaPane;
    @FXML
    public Pane GameControllers;
    //    public Pane getGameControllers() {
//        return GameControllers;
//    }
//    public void setGameControllers(Pane GameControllers) {
//        this.GameControllers = GameControllers;
//    }
    @FXML
    private ScrollPane DictionaryPane;
    @FXML
    private ScrollPane BookExplanationPane;

    //----------//

    //others
    @FXML
    private TextField DictionarySearchBar;
    @FXML
    private TextField BookmarkSearchBar;

    private DictionaryController dictionaryController;
    private BookmarkController bookmarkController;
    public void setDictionaryController(DictionaryController dictionaryController) {
        this.dictionaryController = dictionaryController;
    }




    @FXML
    void ShowDictionaryControllers(MouseEvent event) {

        DictionaryControllers.setVisible(true);
        TranslateControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        GameControllers.setVisible(false);

    }

    @FXML
    void ShowTranslateControllers(MouseEvent event) {
        TranslateControllers.setVisible(true);
        DictionaryControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        GameControllers.setVisible(false);
    }
    @FXML
    void ShowBookmarkControllers(MouseEvent event) {
//        BookMarkControllers.setVisible(true);
//        TranslateControllers.setVisible(false);
//        DictionaryControllers.setVisible(false);
//        GameControllers.setVisible(false);
//        if (bookmarkController == null) {
//            bookmarkController = new BookmarkController();
//            bookmarkController.setDictionaryController(dictionaryController);
//            // Các cài đặt khác nếu cần
//        }

        BookMarkControllers.setVisible(true);
        TranslateControllers.setVisible(false);
        DictionaryControllers.setVisible(false);
        GameControllers.setVisible(false);
    }

    @FXML
    void ShowAddWordControllers(MouseEvent event) {

    }

    @FXML
    void ShowGameControllers(MouseEvent event) {


        GameControllers.setVisible(true);
        DictionaryControllers.setVisible(false);
        BookMarkControllers.setVisible(false);
        TranslateControllers.setVisible(false);
        GameControllers gameControllers = new GameControllers(GameControllers);
        gameControllers.play(GameControllers);
        if (gameControllers.isCheck() == true) {
            GameControllers.setVisible(false);
        }
    }

}
