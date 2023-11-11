package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BookmarkController {
    @FXML
    private AnchorPane Bookmark;

    @FXML
    private ScrollPane BookmarkPane;

    @FXML
    private TextField BookmarkSearchBar;

    @FXML
    private ListView<?> BookmarkSuggestListView;

    @FXML
    private TextArea Explanation;

    @FXML
    private ImageView FilledStar;

    @FXML
    private ImageView unFilledStar;

    @FXML
    void AddFavorite(MouseEvent event) {

    }

    @FXML
    void handleSuggestionSelected(MouseEvent event) {

    }

    @FXML
    void showSuggestList(KeyEvent event) {

    }
}
