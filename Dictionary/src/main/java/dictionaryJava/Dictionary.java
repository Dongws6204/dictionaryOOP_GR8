package dictionaryJava;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> words;

    /**
     *constructor for Dictionary.
     *
     * @param
     */
    public Dictionary() {
        words = new ArrayList<>();
    }

    /**
     *addWord.
     *
     * @param
     */
    public void addWord(Word word) {
        words.add(word);
    }

    /**
     *get list.
     *
     * @return listWord
     */
    public List<Word> getWords() {
        return words;
    }

    /**
     *search for a word in the Dictionary.
     *
     * @param 
     */
    public Word searchWord(String wordTarget){
        for (int i = 0; i < words.size(); i++){
            if(words.get(i).getWordTarget().equalsIgnoreCase(wordTarget)){
                return words.get(i);
            }
        }
        return null;
    }

}
