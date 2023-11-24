package Controllers;

import javafx.beans.property.MapProperty;

public interface WordBookMarkProvider {
    MapProperty<String, String> getWordBookMark();
    void setWordBookMark(MapProperty<String, String> wordBookMark);
}
