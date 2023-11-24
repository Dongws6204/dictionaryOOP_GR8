package Controllers;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;

public class DataSharingManager {
    private static DataSharingManager instance;
    private MapProperty<String, String> wordBookMark;

    private DataSharingManager() {
        // Khởi tạo đối tượng wordBookMark ở đây
        this.wordBookMark = new SimpleMapProperty<>(FXCollections.observableHashMap());
    }

    public static synchronized DataSharingManager getInstance() {
        if (instance == null) {
            instance = new DataSharingManager();
        }
        return instance;
    }

    public MapProperty<String, String> getWordBookMark() {
        return wordBookMark;
    }

    public void setWordBookMark(MapProperty<String, String> wordBookMark) {
        this.wordBookMark.set(wordBookMark);
    }
}
