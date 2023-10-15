package dictionaryJava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryManagement {
    private ArrayList<Word> words;

    /**
     * constructor.
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

            Word word = new Word(wordTarget, wordExplain);
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

    /**
     * updateInsertFromFile.
     */
    public void insertFromFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String wordTarget = parts[0].trim();
                    String wordExplain = parts[1].trim();
                    Dictionary.addWord(new Word(wordTarget, wordExplain));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updateLookup.
     */
    public void dictionaryLookup(Scanner scanner) {
        System.out.print("Enter a word: ");
        String lookUpWord = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Word word : words) {
            if (word.getWordTarget().equals(lookUpWord)) {
                System.out.println("Vietnamese: " + word.getWordExplain());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Word not found in the dictionary.");
        }
    }


    /**
     * addWord.
     */
    public void addWord(String word, String explain) {
        Word newWord = new Word(word, explain);
        words.add(newWord);
    }

    /**
     * editWord.
     */
    public void editWord(String word, String newWord, String newExplain) {
        boolean check = false;
        for (Word w : words) {
            if (w.getWordTarget().equalsIgnoreCase(word)) {
                w.setWordTarget(newWord);
                w.setWordExplain(newExplain);
                check = true;
                break; // Đã tìm thấy và sửa.
            }
        }
        if(check) {
            System.out.println("Đã sửa từ" + word + "thành công.");
        } else {
            System.out.println("Không tìm thấy từ" + word + "để sửa.");
        }
    }


    /**
     * deleteWord.
     */
    public void deleteWord(String word) {
        boolean check = false;
        Iterator<Word> iterator = words.iterator();
        while (iterator.hasNext()) {
            Word w = iterator.next();
            if (w.getWordTarget().equalsIgnoreCase(word)) {
                iterator.remove();
                check = true;
                break; // Đã tìm thấy và xóa, không cần duyệt tiếp
            }
        }

        if (check) {
            System.out.println("Đã tìm thấy từ cần xóa và xóa thành công! Đã xóa : " + word);
        } else {
            System.out.println("không tìm  thấy" + word + "từ bạn cần xóa");
        }
    }

    /**
     * xuatFile.
     *
     * @param path
     */
    //xuat tu dien sang file
    public void dictionaryExportToFile(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            for (Word word : words) {
                writer.write(word.getWordTarget() + "\t" + word.getWordExplain() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * GameDictionary.
     *
     *
     */
    public void dictionaryGame() {
        Scanner scanner = new Scanner(System.in);
        String[] questions = {"What _ you doing?", "How _ you?"};
        String[][] options = {{"[A] are", "[B] do", "[C] is", "[D] have"}, {"[A] is", "[B] are", "[C] do", "[D] have"}};
        String[] answers = {"A", "B"};

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.println("Your choice [A/B/C/D]:");
            String userChoice = scanner.nextLine();

            if (userChoice.equalsIgnoreCase(answers[i])) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is " + answers[i]);
            }
        }
    }


}
