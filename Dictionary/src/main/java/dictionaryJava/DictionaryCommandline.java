package dictionaryJava;

import java.util.ArrayList;
import java.util.Dictionary;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    /**
     * constructor_commandline_Dictionary.
     *
     * @param dictionaryManagement
     */
    public DictionaryCommandline(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    /**
     * Show_all_words.
     *
     * @void
     */
    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        ArrayList<Word> words = dictionaryManagement.getWords();

        for (int i = 0; i < words.size(); i++ ) {
            Word word = words.get(i);
            System.out.println(i + 1 + " | " + word.getWordTarget() + " | " + word.getWordExplain());
        }
    }

    /**
     * Dictionary_basic.
     *
     * @insert
     */
    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    /**
     * main.
     *
     * @param args
     */
}
