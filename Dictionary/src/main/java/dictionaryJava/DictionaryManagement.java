package dictionaryJava;

import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private ArrayList<Word> words;

    /**
     *constructor.
     *
     * @
     */
    public DictionaryManagement() {
        words = new ArrayList<>();
    }

    /**
     * insertFromCommandline.
     *
     * @return
     * @Insert
     */
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);

//        Nhập số lượng từ vựng.
        System.out.print("Nhập số lượng từ vựng: ");
        int numWords = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numWords; i++) {
            System.out.println("Nhập từ tiếng anh:");
            String wordTarget = scanner.nextLine().trim().toLowerCase();

            System.out.println("Nhập nghĩa giải thích bằng tiếng Viêt: ");
            String wordExplain = scanner.nextLine().trim().toLowerCase();

            Word word  = new Word(wordTarget, wordExplain);
            words.add(word);
        }

    }

    /**
     * getWords.
     *
     * @return
     */
    public ArrayList<Word> getWords() {
        return words;
    }
}
