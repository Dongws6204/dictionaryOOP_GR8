package dictionaryJava;

import java.sql.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    private List<Word> words;
    private Connection connection;


    /**
     * constructor.
     *
     * @
     */
    public DictionaryManagement() {
        words = new ArrayList<>();
        initializeDatabaseConnection();
        loadWordsFromDatabase();
    }


    private void initializeDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/dictionarydb";
            String username = "root";
            String password = "PHW#84#jeor";

            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadWordsFromDatabase() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM words");

            while (resultSet.next()) {
                String wordTarget = resultSet.getString("word_target");
                String wordExplain = resultSet.getString("word_explain");

                Word word = new Word(wordTarget, wordExplain);
                words.add(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * insertFromCommandline.
     *
     * @return
     * @Insert
     */
    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số lượng từ vựng: ");
        int numWords = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numWords; i++) {
            System.out.println("Nhập từ tiếng anh:");
            String wordTarget = scanner.nextLine().trim().toLowerCase();

            System.out.println("Nhập nghĩa giải thích bằng tiếng Việt: ");
            String wordExplain = scanner.nextLine().trim().toLowerCase();

            insertWordToDatabase(wordTarget, wordExplain);

            Word word = new Word(wordTarget, wordExplain);
            words.add(word);
        }
    }


    private void insertWordToDatabase(String wordTarget, String wordExplain) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO words (word_target, word_explain) VALUES (?, ?)");
            preparedStatement.setString(1, wordTarget);
            preparedStatement.setString(2, wordExplain);
            preparedStatement.executeUpdate();
            System.out.println("Đã thêm từ vào cơ sở dữ liệu.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * getWords.
     *
     * @return
     */
    public List<Word> getWords() {
        return words;
    }

    /**
     * updateInsertFromFile.
     */

    public void insertFromFile(String path) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String wordTarget = parts[0].trim();
                    String wordExplain = parts[1].trim();
                    words.add(new Word(wordTarget, wordExplain));
                }
            }

            for (int i = 0; i < words.size(); i++) {
                Word word = words.get(i);
                System.out.println(i + 1 + " | " + word.getWordTarget() + " | " + word.getWordExplain());
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

    /* search word*/

    /**
     * addWord.
     */
    public void addWord(String word, String explain) {
        insertWordToDatabase(word, explain);
        Word newWord = new Word(word, explain);
        words.add(newWord);
    }

    /**
     * editWord.
     */
    public void editWord(String word, String newWord, String newExplain) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE words SET word_target = ?, word_explain = ? WHERE word_target = ?");
            preparedStatement.setString(1, newWord);
            preparedStatement.setString(2, newExplain);
            preparedStatement.setString(3, word);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Đã sửa từ " + word + " thành công.");
            } else {
                System.out.println("Không tìm thấy từ " + word + " để sửa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * deleteWord.
     */
    public void deleteWord(String word) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM words WHERE word_target = ?");
            preparedStatement.setString(1, word);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Đã xóa từ " + word + " thành công.");
            } else {
                System.out.println("Không tìm thấy từ " + word + " để xóa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    //    public void dictionaryGame() {
////        Scanner scanner = new Scanner(System.in);
////        String[] questions = {"What _ you doing?", "How _ you?"};
////        String[][] options = {{"[A] are", "[B] do", "[C] is", "[D] have"}, {"[A] is", "[B] are", "[C] do", "[D] have"}};
////        String[] answers = {"A", "B"};
////
////        for (int i = 0; i < questions.length; i++) {
////            System.out.println(questions[i]);
////            for (String option : options[i]) {
////                System.out.println(option);
////            }
////
////            System.out.println("Your choice [A/B/C/D]:");
////            String userChoice = scanner.nextLine();
////
////            if (userChoice.equalsIgnoreCase(answers[i])) {
////                System.out.println("Correct!");
////            } else {
////                System.out.println("Incorrect. The correct answer is " + answers[i]);
////            }
//        }


//    }



}
