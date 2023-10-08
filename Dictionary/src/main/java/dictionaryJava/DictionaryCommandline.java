package dictionaryJava;

import java.util.ArrayList;
import java.util.Dictionary;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        ArrayList<Word> words = dictionaryManagement.getWords();

        for (int i = 0; i < words.size(); i++ ) {
            Word word = words.get(i);
            System.out.println(i + 1 + " | " + word.getWordTarget() + " | " + word.getWordExplain());
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandline commandline = new DictionaryCommandline(dictionaryManagement);
        commandline.dictionaryBasic();
    }
}
