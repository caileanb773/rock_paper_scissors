import java.security.SecureRandom;
import java.util.Scanner;

/** Class "Logic". Contains all methods to play the game.
 * @author Cailean Bernard.
 */
public class Logic {

	/** SecureRandom object for generating random number used as Computer's choice.
	 */
	private SecureRandom random = new SecureRandom();

	/** Player's choice. 0 = rock, 1 = paper, 2 = scissors.
	 */
	private int playerChoice = 0;

	/** Computer's choice. Randomly generated integer between 0 and 2 inclusive.
	 */
	private int computerChoice = 0;

	/** Player's score. First to 2 wins.
	 */
	private int playerScore = 0; 

	/** String representation of player's input.
	 */
	private String playerInput = null;

	/** Computer's score. First to 2 wins.
	 */
	private int computerScore = 0;

	/** Displays an introductory menu explaining the rules and win condition of
	 * the game.
	 */
	public void mainMenu() {
		System.out.printf("Welcome to rock, paper, scissors.\n"
				+ "Choose either rock, paper, or scissors by typing\n"
				+ "'r', 'p', or 's'. First to 2 points wins.\n");
	}

	/** Randomly generates an integer between 0 and 2 inclusive to represent the
	 * computer's choice
	 * @return int - The generated integer representing computer's choice.
	 */
	public int getCompChoice() {
		this.computerChoice = random.nextInt(3);
		switch (computerChoice) {
		case 0:
			System.out.printf("Computer chose Rock.\n");
			break;
		case 1:
			System.out.print("Computer chose Paper.\n");
			break;
		case 2:
			System.out.printf("Computer chose scissors.\n");
			break;
		default:
			System.out.println("Default case reached.");
			break;
		}
		return computerChoice;
	}

	/** Gets the player's choice via user input (keyboard). Filters out all input
	 * that is not "r", "p", or "s" ignoring capitalization.
	 * @return int - The integer representation of the player's choice.
	 * @param userIn - Scanner object to accept input via keyboard.
	 */
	public int getPlayerChoice(Scanner userIn) {
		/* Input validation flag.
		 */

		// Parse user's input as either 0, 1, or 2 (Rock, Paper, or Scissors)
		do {
			System.out.printf("Enter choice:\n> ");
			playerInput = userIn.nextLine();

			// If they enter "r" or "S"
			if (playerInput.equalsIgnoreCase("r")) {
				System.out.printf("You chose Rock.\n");
				playerChoice = 0;
				break;

				// If they enter "p" or "S"
			} else if (playerInput.equalsIgnoreCase("p")) {
				System.out.printf("You chose Paper.\n");
				playerChoice = 1;
				break;

				// If they enter "s" or "S"
			} else if (playerInput.equalsIgnoreCase("s")) {
				System.out.printf("You chose Scissors.\n");
				playerChoice = 2;
				break;

				// If they entered anything else
			} else {
				System.out.printf("Invalid Choice.\n");
				continue;
			}
		} while (true);
		return playerChoice;
	}

	/** Plays one round of the game. Gets the player's choice, then the computer's
	 * choice, then checks if the round resulted in a tie. If it didn't, checks
	 * if the player won. If they didn't, the computer wins the round. The winner
	 * of the round has their score incremented.
	 * @param userIn - Scanner object to accept input via keyboard.
	 */
	public void playRound(Scanner userIn) {
		getPlayerChoice(userIn);
		getCompChoice();
		if (this.playerChoice == this.computerChoice) {
			System.out.printf("It's a tie.\n");
			
			// Handle all outcomes where player could win
		} else if (this.playerChoice == 0 && this.computerChoice == 2 ||
				this.playerChoice == 1 && this.computerChoice == 0 ||
				this.playerChoice == 2 && this.computerChoice == 1) {
			this.playerScore++;
			System.out.printf("Player won this round.\n");
			
			// If player didn't win, computer won
		} else {
			this.computerScore++;
			System.out.printf("Computer won this round.\n");
		}
	}

	/** Plays the game while neither player has two points. Starts by printing
	 * the main menu with rules, then plays rounds until there is a winner. After
	 * one of either the player or the computer has 2 points, the method checks
	 * who won.
	 * @param userIn - Scanner object to accept input via keyboard.
	 */
	public void playGame(Scanner userIn) {
		mainMenu();
		
		// Play rounds until either computer or player's score is 2
		while (true) {
			playRound(userIn);
			if (this.playerScore == 2 || this.computerScore == 2) {
				break;
			} else {
				continue;
			}
		}
		if (this.playerScore > this.computerScore) {
			System.out.printf("Player has won the game!\n");
		} else if (this.playerScore < this.computerScore) {
			System.out.printf("Computer has won the game.\n");
		} else {
			System.out.printf("Game has resulted in a tie.\n");
		}
	}

	/** Method to play another game. Re-runs the game if the player enters y, or
	 * exits if the user enters n.
	 * @return boolean - Plays the game again if true, exits the program if false.
	 * @param userIn - Gets input from user via keyboard.
	 */
	public boolean playAgain(Scanner userIn) {
		while (true) {
			System.out.printf("Would you like to play again? Y/N\n> ");
			playerInput = userIn.nextLine();
			if (playerInput.equalsIgnoreCase("y")) {
				System.out.println("Playing again...");
				this.playerScore = 0;
				this.computerScore = 0;
				return true;
			} else if  (playerInput.equalsIgnoreCase("n")) {
				System.out.println("Exiting game...");
				return false;
			} else {
				System.out.println("Invalid selection.");
			}
		}
	}
}