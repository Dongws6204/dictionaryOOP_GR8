package dictionaryJava;

import java.sql.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class DictionaryManagement {
    protected List<Word> words = new ArrayList<>();
    private Connection connection;
    private TrieNode root;


    private static final String API_KEY = "AIzaSyCgWfL_ke9if8Cm7qaK-Ft_lXKAF-G5A_U";
    /**
     * constructor.
     *
     * @
     */
    public DictionaryManagement() {
        this.root = new TrieNode();
        initializeDatabaseConnection();
        loadWordsFromDatabase();
    }
    public DictionaryManagement(List<Word> words) {
        this.words = words;
    }


    private void initializeDatabaseConnection() {
        try {

//            //Chi
//            String url = "jdbc:mysql://localhost:3306/dictionary";
//            String username = "root";
//            String password = "1616lclc";

            //Dongws
            String url = "jdbc:mysql://localhost:3306/dictionaryDb";
            String username = "root";
            String password = "Cu0602@";

            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void loadWordsFromDatabase() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM words");

            while (resultSet.next()) {
                String wordTarget = resultSet.getString("word_target");
                String wordExplain = resultSet.getString("word_explain");

                Word word = new Word(wordTarget, wordExplain);
                words.add(word);
                // Thêm từ vào Trie
                root.insert(wordTarget.toLowerCase(), wordExplain);
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
            root.insert(word.getWordTarget(),wordExplain);
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
       words.clear();
       loadWordsFromDatabase();
        System.out.print("Enter a word: ");
        String lookUpWord = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Word word : words) {
            if (word.getWordTarget().equals(lookUpWord)) {
                System.out.println("Vietnamese: " + word.getWordExplain() +"\n");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Word not found in the dictionary.");
        }
    }
    public void dictionaryLookupTrie(Scanner scanner) {
//        root.clear();
//        loadWordsFromDatabase();
        System.out.print("Enter a word: ");
        String lookUpWord = scanner.nextLine().trim().toLowerCase();

        String wordExplain = root.search(lookUpWord);

        if (wordExplain != null) {
            System.out.println("Vietnamese: " + wordExplain + "\n");
        } else {
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
        root.insert(word.toLowerCase(),explain);
    }
    public void addWordTrie(String word, String explain) {
        root.insert(word.toLowerCase(), explain);
    }


    /**
     * editWord.
     */
    public void editWord(String word, String newWord, String newExplain) {
//        words.clear();
//        loadWordsFromDatabase();
        try {
            // Kiểm tra xem từ có tồn tại trong danh sách không
            boolean wordExists = false;
            for (Word w : words) {
                if (w.getWordTarget().equalsIgnoreCase(word)) {
                    wordExists = true;
                    break;
                }
            }

            if (!wordExists) {
                System.out.println("Không tìm thấy từ " + word + " để sửa.");
                return;
            } else {
            }

            // Thực hiện cập nhật trong cơ sở dữ liệu
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE words SET word_target = ?, word_explain = ? WHERE word_target = ?");
            preparedStatement.setString(1, newWord);
            preparedStatement.setString(2, newExplain);
            preparedStatement.setString(3, word);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                root.insert(newWord,newExplain);
                System.out.println("Đã sửa từ " + word + " thành công.");
                // Làm mới danh sách từ
                loadWordsFromDatabase();
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
    public void deleteWordDb(String word) {
        try {
            for (Word w : words) {
                if (w.getWordTarget().equals(word)) {
                    Word x = new Word(w);
                    words.remove(x);

//                    System.out.println("Đã xóa từ " + word + " thành công.2");
                }

            }

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
        root.remove(word);

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

   //phát âm
   public void speakWord(String word) {
       System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

       VoiceManager voiceManager = VoiceManager.getInstance();
       Voice[] voices = voiceManager.getVoices();

       // Chọn một giọng nói từ danh sách (nếu có)
       Voice selectedVoice = null;
       for (Voice voice : voices) {
           if (voice.getName().equals("kevin")) { // Đổi thành tên giọng nói tương ứng
               selectedVoice = voice;
               break;
           }
       }

       if (selectedVoice != null) {
           selectedVoice.allocate();
           selectedVoice.speak(word);
           selectedVoice.deallocate();
       } else {
           System.out.println("Không tìm thấy giọng nói.");
       }
   }


    //translate

  public String translateFromEnglishToVietnamese(String wordToTranslate) {
        return translateWord(wordToTranslate, "en", "vi");
    }

    public String translateFromVietnameseToEnglish(String wordToTranslate) {
        return translateWord(wordToTranslate, "vi", "en");
    }

    public String translateWord(String wordToTranslate, String sourceLang, String targetLang) {
        try {
            String encodedText = URLEncoder.encode(wordToTranslate, "UTF-8");

            String urlStr = "https://translation.googleapis.com/language/translate/v2?key=" + API_KEY +
                    "&source=" + sourceLang + "&target=" + targetLang + "&q=" + encodedText;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject data = jsonObject.getAsJsonObject("data");
            JsonArray translations = data.getAsJsonArray("translations");

            if (translations.size() > 0) {
                JsonObject translationObject = translations.get(0).getAsJsonObject();
                String translatedText = translationObject.get("translatedText").getAsString();
                return translatedText; // Trả về chuỗi dịch
            } else {
                return "Không thể dịch từ '" + wordToTranslate + "' sang " + targetLang + ".";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi thực hiện dịch.";
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
