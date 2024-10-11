import java.util.Scanner;

/** Class "Driver". Contains main method and method call to play the game.
 * @author Cailean Bernard
 */
public class Driver {

	/** Main method. Creates object to access game methods, a scanner for user
	 * input, the call to run the game, and then closes the scanner.
	 * @param args - A String array of arguments
	 */
	public static void main(String[] args) {

		Logic game = new Logic();
		Scanner input = new Scanner(System.in);

		// Run the game
		while (true) {
			game.playGame(input);
			if (game.playAgain(input)) {
				continue;
			} else {
				break;
			}
		}
		
		// Close scanner to prevent resource leaks
		input.close();
	}
}