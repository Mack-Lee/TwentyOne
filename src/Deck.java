import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> list;
	
	public Deck() {
		
		list = new ArrayList<Card>();
		
	}
	
	public void populateDeck() {
		
		Suit suit = null;
		
		// Regular cards
		for(int i = 0; i < 4; i++) {
			if(i == 0) {
				suit = Suit.Spades;
			}
			else if(i == 1)
			{
				suit = Suit.Hearts;
			}
			else if(i == 2)
			{
				suit = Suit.Diamonds;
			}
			else if(i == 3)
			{
				suit = Suit.Clubs;
			}
			for(int j = 2; j < 11; j++) {
				Card c = new Card(j, suit);
				list.add(c);
			}			
		}
		
		// Special cards
		for(int i = 0; i < 4; i++) {
			if(i == 0) {
				suit = Suit.Spades;
			}
			else if(i == 1)
			{
				suit = Suit.Hearts;
			}
			else if(i == 2)
			{
				suit = Suit.Diamonds;
			}
			else if(i == 3)
			{
				suit = Suit.Clubs;
			}
			int value = 10;
			for(int j = 0; j < 4; j++) {
				String specialValue = "";
				if(j == 0) {
					specialValue = "j";
				}
				else if(j == 1)
				{
					specialValue = "q";
				}
				else if(j == 2)
				{
					specialValue = "k";
				}
				else if(j == 3)
				{
					value = 11;
					specialValue = "a";
				}
				Card c = new Card(value, suit);
				c.setSpecialValue(specialValue);
				list.add(c);
			}			
		}
		
		System.out.println("Cards added to deck.\n");
		
	}
	
	public void clearDeck() {
		
		list.clear();
		System.out.println("Deck cleared.");
		
	}
	
	public int getSize() {
		
		return list.size();
		
	}
	
	// If deck is empty, returns a null card
	public Card draw() {
		
		int size = list.size();
		Card c = new Card(0, null);
		if(size > 0) {
			Random randomGenerator = new Random();
			int n = randomGenerator.nextInt(size);
			c = list.get(n);
			list.remove(n);
		}
		return c;
		
	}
	
}
