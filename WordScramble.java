
/*  Mark Anthony Start  -  180140208  -  C01109 CW1  -  page 3 of 3

Word Scramble, although functional is short, abrupt and unclear/unintroduced to the player and with only a small selection of 7 words to be guessed considerably reducing the re-playability and potential enjoyment of the game.The games low amount of ‘lives’ are not displayed or explained. A greater number of lives with their status fed back to the user, a game introduction describing the games outline and objective or alternatively, developing the games 'here's a hint' feature by continuing to loop the guessing cycle, revealing the next letter of the word and finally exiting the loop when the either the player wins or the entire word is displayed would make a more gently flowing and improved interface. 
These features together with a greater number of 'anagram-less' words to guess (all the current words only have one solution after being scrambled except 'scramble', which can be correctly guessed to be the word 'clambers' which would not result in winning the game, although not very frustrating). Consideration would have to be made when adding new words to the list, as some words have many anagrams and this would cause frustration and greatly reduce the functionality of the game. also cleaner and better spaced out output for the user would make the game clearer and easier to read.
*/


import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class WordScramble {
    private static Random random = new Random();
    private static String[] words = { "formula", "mechanic", "embolden", "scramble", "medicine", "autopilot", "photocopier" };
    private static final int NUMBER_OF_LIVES = 2;

    public static void play(Scanner input, PrintStream output) {
        String wordToGuess = getWordToGuess();
        String scrambledWord = scramble(wordToGuess);

        showScrambledWord(output, scrambledWord);
        loopUntilUserGuessesCorrectlyOrRunsOutOfLives(input, output, wordToGuess);
    }

    private static String getWordToGuess() {
        int i = random.nextInt(words.length);
        return words[i];
    }

    private static String scramble(String word) {
        char[] scramble = word.toCharArray();

        int length = scramble.length;
        int counter = length * 2;
        int x, y;
        while (counter > 0) {
            x = random.nextInt(length);
            y = random.nextInt(length);          
            swapChars(x, y, scramble);
            counter--;
        }

        return new String(scramble);
    }
    
    private static void swapChars(int x, int y, char[] scramble){
		 char z = scramble[x];
		 scramble[x] = scramble[y];
         scramble[y] = z;
	}

    private static void showScrambledWord(PrintStream output, String scrambledWord) {
        output.println("Unscramble this word: " + scrambledWord);
    }

    private static void loopUntilUserGuessesCorrectlyOrRunsOutOfLives(Scanner input, PrintStream output, String wordToGuess) {
        int guesses = NUMBER_OF_LIVES;
        boolean guessedCorrectly;
        do {
            askUserToGuess(output);
            String guess = getUserGuess(input);
            guesses--;
            guessedCorrectly = didUserGuessWord(wordToGuess, guess);
            String message = getFeedbackMessageForUser(guessedCorrectly, guesses, wordToGuess);
            output.println(message);
        } while (userHasNotGuessedWordAndHasGuessesRemaining(guesses, guessedCorrectly));
    }

    private static void askUserToGuess(PrintStream output) {
        output.print("Enter your guess: ");
    }

    private static String getUserGuess(Scanner in) {
        return in.nextLine();
    }

    private static boolean didUserGuessWord(String wordToGuess, String guess) {
        return wordToGuess.equalsIgnoreCase(guess);
    }

    private static String getFeedbackMessageForUser(boolean correct, int guessesLeft, String wordBeingGuessed) {
        if (correct) {
            return "Correct. You win!";
        } else if (!correct && guessesLeft == 1) {
            return "That's not the answer. Here's a hint: " + getHint(wordBeingGuessed);
        } else {
            return "Incorrect. You Lose! The word was '" + wordBeingGuessed + "'.";
        }
    }

    private static boolean userHasNotGuessedWordAndHasGuessesRemaining(int numberOfGuessesLeft, boolean guessedTheWordCorrectly) {
        return numberOfGuessesLeft > 0 && !guessedTheWordCorrectly;
    }

    private static String getHint(String word) {
        return "the first letter of the word is " + word.charAt(0);
    }

    public static void main(String[] args) {
        WordScramble.play(new Scanner(System.in), System.out);
    }
}

