//  Mark Anthony Start  -  180140208  -  C01109 CW1  -  page 2 of 3 

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Random;



public class NumberGuessingGame {


  private static Scanner userInput = new Scanner(System.in);
  private static PrintStream outputToUser = System.out;
	private static final int upperLimitOfRandomNumber = 5001;
	private static Random randomNumberGeneratorObject = new Random();
	private static String correctGuessWinningGameMessage = "Correct! The number of guesses you made was ... ";
	private static String usersGuessIsTooLowGuessAgainMessage = "too low - guess again: ";
	private static String usersGuessIsTooHighGuessAgainMessage = "too high - guess again: ";


	public static void play() {
		gameIntroAndInstructionsHowToPlay();
		loopGetUsersGuessesAndFeedbackForUsersGuessesUntilUserGuessesCorrectlyThenReturnToGameMenu();
	}

	private static void gameIntroAndInstructionsHowToPlay() {
		outputToUser.println();
		outputToUser.println("Welcome ! Welcome ! Welcome ! Try to guess the computer's randomly generated number !");
		outputToUser.println("Enter guesses with the number keys followed by the enter key. The number to guess is between 0 and 5000, good luck ! and good guessing !");
		outputToUser.println();
	}


	private static void loopGetUsersGuessesAndFeedbackForUsersGuessesUntilUserGuessesCorrectlyThenReturnToGameMenu() {

		int computersNumber = generateRandomNumberForGame();
		boolean hasTheUserGuessedCorrectly = false;
		int numberOfGuessesMade = 0;
		int usersGuess;
		String messageForUser = "";
		boolean isUsersGuessIsTooLow;

		askUserToGuessRandomNumber();

		while (!hasTheUserGuessedCorrectly) {
			usersGuess = readAndReturnUserGuessAtRandomNumber();
			numberOfGuessesMade ++;
			hasTheUserGuessedCorrectly = compareUsersAndComputeresNumbers(computersNumber,usersGuess);
			isUsersGuessIsTooLow = evaluateIfUsersGuessIsTooLow(usersGuess, computersNumber);
			messageForUser = getAndReturnFeedbackMessageForUser(hasTheUserGuessedCorrectly, isUsersGuessIsTooLow);
			showFeedbackMessageToUser(messageForUser);
		}

		printNumberOfGuessesMadeAndThanksForPlayingMessageAtEndOfGame(numberOfGuessesMade);
		
	}
	

	private static int generateRandomNumberForGame() {
		return randomNumberGeneratorObject.nextInt(upperLimitOfRandomNumber);
	}

	private static void askUserToGuessRandomNumber() {
		outputToUser.println("Try to guess the number I'm thinking of:");
	}

	private static int readAndReturnUserGuessAtRandomNumber() {
		return  userInput.nextInt();
	// userInput.nextLine();	
	}

	private static boolean compareUsersAndComputeresNumbers( int cpusNumber, int usersNumber ) {
		return cpusNumber == usersNumber;
	}

	private static boolean evaluateIfUsersGuessIsTooLow(int usersNum, int computersNum) {
		return (usersNum < computersNum);
	}


	private static String getAndReturnFeedbackMessageForUser( boolean userIsRight, boolean guessIsTooLow ) {

		if (userIsRight) {
			return correctGuessWinningGameMessage;
		} else if ( guessIsTooLow ) {
			return usersGuessIsTooLowGuessAgainMessage;
		} else {
			return usersGuessIsTooHighGuessAgainMessage;
		}

	}

	private static void showFeedbackMessageToUser( String feedbackMessageForUser) {
		outputToUser.print( feedbackMessageForUser );
	}

	private static void printNumberOfGuessesMadeAndThanksForPlayingMessageAtEndOfGame(int numberOfGuesses) {
		outputToUser.print( numberOfGuesses + " ! You Win !!! Thanks for playing ! : ) " );	
		outputToUser.println();
	}


	public static void main(String [] args) {
		NumberGuessingGame.play();
	}

}		

