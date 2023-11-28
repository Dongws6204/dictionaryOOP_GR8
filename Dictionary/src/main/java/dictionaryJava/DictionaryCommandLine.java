package dictionaryJava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DictionaryCommandLine {
    private DictionaryManagement dictionaryManagement;


    /**
     * constructor_commandline_Dictionary.
     *
     * @param dictionaryManagement
     */
    public DictionaryCommandLine(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    /**
     * Show_all_words.
     *
     * @void
     */

    public void showAllWords() {
        System.out.println("No | English | Vietnamese");
        List<Word> words = dictionaryManagement.getWords();

        for (int i = 0; i < words.size(); i++) {
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
     * searchDictionary.
     */
    public void dictionarySearcher(String searchStr) {
        List<Word> searchRes = new ArrayList<>();

        DictionaryManagement dm = new DictionaryManagement();
        List<Word> words = dm.getWords();

        for (Word word : words) {
            if (word.getWordTarget().startsWith(searchStr)) {
                searchRes.add(word);
            }
        }

        if (searchRes.isEmpty()) {
            System.out.println("Không tìm thấy từ nào.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            int count = 1;
            for (Word result : searchRes) {
                System.out.println(count + " | " + result.getWordTarget() + " | " + result.getWordExplain());
                count++;
            }
        }
    }

    public void dictionaryAdvanced() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to My Application!");
        int choice = -1;

        while (choice != 0) {
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.println("[10] Pronunciation");
            System.out.println("[11] Translate");
            System.out.print("Your action:");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine(); // Consume the invalid input
                System.out.println("Action not supported");
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println("Exiting application...");
                    break;
                case 1:
                    // Add word
                    dictionaryManagement.insertFromCommandline();
                    break;
                case 2:

                    // Remove word
                    Scanner scDelete = new Scanner(System.in);
                    System.out.println("Nhập từ bạn muốn xóa  : ");

                    String wordDelete = scDelete.nextLine();
                    dictionaryManagement.deleteWord(wordDelete);
                    break;
                case 3:
                    // Update word
                    System.out.println("Nhập từ  thay đổi :");
                    String wordFirst = scanner.nextLine();

                    System.out.println("Nhập từ  thay thế :");
                    String wordFirstUpdate = scanner.nextLine();

                    System.out.println("Nhập nghĩa mới của từ thay thế :");
                    String newExplain = scanner.nextLine();

                    dictionaryManagement.editWord(wordFirst, wordFirstUpdate, newExplain);
                    break;
                case 4:
                    // Display
//                    showAllWords();
                    dictionaryBasic();
                    break;
                case 5:
                    // Lookup
//                    dictionaryManagement.dictionaryLookup(scanner);
                    dictionaryManagement.dictionaryLookupTrie(scanner);
                    break;
                case 6:
                    // Search
                    System.out.println("Nhap ky tu can tim kiem: ");
                    String searchStr = scanner.nextLine();
                    dictionarySearcher(searchStr);
                    break;
                case 7:
                    // Game
//                    dictionaryManagement.dictionaryGame();
                    HangmanGame hangmanGame = new HangmanGame();
                    hangmanGame.play(); // Gọi phương thức play để bắt đầu trò chơi Hangman
                    break;
                case 8:
                    // Import from file
                    System.out.println("Bạn hãy nhập đường dẫn để nhập từ điển từ file!");

                    String path = scanner.nextLine();
                    File file = new File(path);

                    if (file.exists() && file.isFile()) {
                        dictionaryManagement.insertFromFile(path);
                    } else {
                        System.out.println("Đường dẫn không hợp lệ hoặc không phải là tệp.");
                    }
                    break;

                case 9:
                    // Export to file
                    System.out.println("Bạn hãy nhập đường dẫn để xuất từ điển sang file!");

                    String pathExport = scanner.nextLine();
                    File fileExport = new File(pathExport);

                    if (fileExport.exists() && fileExport.isFile()) {
                        dictionaryManagement.dictionaryExportToFile(pathExport);
                    } else {
                        System.out.println("Bạn hãy nhập đường dẫn để xuất từ điển sang file!");
                    }
                    break;
                case 10:
                    // Phát âm từ vựng
                    System.out.println("Nhập từ bạn muốn phát âm:");
                    String wordToSpeak = scanner.nextLine();
                    dictionaryManagement.speakWord(wordToSpeak);
                    break;
                case 11:
                    System.out.println("Bạn muốn dịch từ Anh sang Việt hay từ Việt sang Anh? (en-vi / vi-en): ");
                    String translationDirection = scanner.nextLine();

                    if (translationDirection.equals("en-vi")) {
                        System.out.print("Nhập từ bạn muốn dịch từ Anh sang Việt: ");
                        String wordToTranslate = scanner.nextLine();
                        dictionaryManagement.translateFromEnglishToVietnamese(wordToTranslate);
                    } else if (translationDirection.equals("vi-en")) {
                        System.out.print("Nhập từ bạn muốn dịch từ Việt sang Anh: ");
                        String wordToTranslate = scanner.nextLine();
                        dictionaryManagement.translateFromVietnameseToEnglish(wordToTranslate);
                    } else {
                        System.out.println("Hướng dịch không hợp lệ.");
                    }
                    break;



                default:
                    System.out.println("Action not supported");
                    break;
            }
        }
    }

}

