package dictionaryJava;

import java.util.Random;
import java.util.Scanner;

public class HangmanGame extends DictionaryManagement {

    private static String[] wordList = {"Apple",
            "Elephant",
            "Tiger",
            "Rabbit",
            "Television",
            "Nest",
            "Table",
            "Elephant"
    };

    private static String[] wordListVietNam = {
            "Táo",
            "Voi",
            "Hổ",
            "Thỏ",
            "Ti vi",
            "Tổ",
            "Bàn",
            "Voi",
    };
    private static Random random = new Random();
    private static String wordToGuess;
    private static StringBuilder wordDisplay;
    private static int attemptsLeft = 6;
    private static int soccer = 100;
    private static int check = 1;
    private static int i = 1;
    private static int level = 0;
    private static StringBuilder guessedLetters = new StringBuilder();

    public String generateWordToGuess(int level) {
        return wordList[level];
    }

    public StringBuilder generateWordDisplay(String wordToGuess, int i) {
        StringBuilder wordDisplay = new StringBuilder("_".repeat(wordToGuess.length()));
        for (int j = 0; j < i; j++) {
            wordDisplay.setCharAt(j, wordToGuess.charAt(j));
        }
        return wordDisplay;
    }

    private void drawHangman(int attemptsLeft) {
        if (attemptsLeft == 5) {

            System.out.println("Wrong guess!  0");
            System.out.print("\n");

        } else if (attemptsLeft == 4) {

            System.out.println("Wrong guess!  0");
            System.out.println("              |");
            System.out.print("\n");

        } else if (attemptsLeft == 3) {

            System.out.println("Wrong guess!  0 ");
            System.out.println("              |\\");
            System.out.println("               \\");
            System.out.print("\n");

        } else if (attemptsLeft == 2) {

            System.out.println("Wrong guess!  0");
            System.out.println("             /|\\");
            System.out.println("             / \\");
            System.out.print("\n");

        } else if (attemptsLeft == 1) {

            System.out.println("Wrong guess!  0__|");
            System.out.println("             /|\\");
            System.out.println("             / \\");
            System.out.print("\n");
        }
    }

    public void play() {

        System.out.println("Welcome to Hangman!");
        String you = "";
        while (attemptsLeft > 0 && soccer >= 0) {

            wordToGuess = generateWordToGuess(level);
            wordDisplay = generateWordDisplay(wordToGuess, i);

            System.out.println("Attempts left | Guessed word  | Word   | soccer");
            System.out.println(String.format("%-13d | %-13s | %-6s | %d", attemptsLeft, guessedLetters, wordDisplay, soccer));

            String guess = getValidGuess();

            if (guess.equals(wordToGuess.toLowerCase())) {
                System.out.println("Congratulations! You guessed the word: " + wordToGuess + " | " + wordListVietNam[level]);
                System.out.print("\n");
                soccer += 5 * wordToGuess.length();
                check = 1;
                i = 1;
                level = level + 1;
                attemptsLeft++;

            } else {
                attemptsLeft--;
                check++;
                drawHangman(attemptsLeft);
            }

            if (check % 3 == 0) {
                System.out.print("Do you need support: ");
                Scanner sc = new Scanner(System.in);
                String fake = sc.nextLine();
                you = fake;

                if (you.equals("y".toLowerCase())) {
                    soccer -= 10;
                    check = check - 1;
                    i++;
                }

            }

            if (guessedLetters.length() == 0) {
                guessedLetters.append(guess);
            } else {
                guessedLetters.setLength(0);
                guessedLetters.append(guess);
            }

        }

        if (attemptsLeft == 0 || soccer < 0) {
            System.out.println("Out of attempts! The word was: " + wordToGuess);
            System.out.println(" |__0__|");
            System.out.println("   /|\\");
            System.out.println("   / \\");
            System.out.println("You lose! The hangman is complete.");
        }
    }


    private String getValidGuess() {
        Scanner scanner = new Scanner(System.in);
        String guess;
        System.out.print("Guess a word: ");
        guess = scanner.nextLine().toLowerCase();
//        do {
//            System.out.print("Guess a letter: ");
//            guess = scanner.nextLine().toLowerCase().charAt(0);
//        } while (!Character.isLetter(guess) || guessedLetters.toString().contains(String.valueOf(guess)));
        return guess;
    }

    private void updateWordDisplay(int i) {
        while (i < wordDisplay.length() && wordDisplay.charAt(i) != '_') {
            i++; // Di chuyển đến vị trí ký tự tiếp theo nếu đã được hiển thị.
        }

        if (i < wordDisplay.length()) {
            wordDisplay.setCharAt(i, wordToGuess.charAt(i)); // Hiển thị ký tự chưa được hiển thị.
        }
    }

    public int getSoccer() {
        return soccer;
    }

    public int getLevel() {
        return level;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getCheck() {
        return check;
    }
}
