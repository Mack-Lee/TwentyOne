import java.util.Scanner;

public class DeckTest {

	public static void main(String[] args) {

		System.out.println("Welcome to TwentyOne!\n");

		Deck d = new Deck();
		d.populateDeck();

		System.out.println("\nPress Enter to draw a card.");

		Card c;

		Scanner k = new Scanner(System.in);
		String input = k.nextLine();
		while (input != null) {
			if (d.getSize() > 0) {
				c = d.draw();
				String key = c.getStringValue();
				System.out.println("You drew a" + (key.equals("Ace") || key.equals("Eight") ? "n " : " ") + key + " of "
						+ c.getSuit());
				System.out.println("Cards remaining: " + d.getSize());
			} else {
				System.out.println("Deck is empty.");
				d.clearDeck();
				d.populateDeck();
			}
			input = k.nextLine();
		}
		k.close();

	}

}
