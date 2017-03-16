
public class Card {

	private int value;
	private Suit suit;
	
	// The "specialValue" flag differentiates Jacks, Queens, Kings and Aces from the other cards
	private String specialValue;
	
	public Card(int value, Suit suit) {
		
		this.value = value;
		this.suit = suit;
		specialValue = "";
		
	}
	
	public void setSpecialValue(String specialValue) {
		
		this.specialValue = specialValue;
		
	}
	
	public int getValue() {
		
		return value;
		
	}
	
	public Suit getSuit() {
		
		return suit;
		
	}
	
	public String getStringValue() {
		
		String strValue = "";
		
		switch(this.value) {
		
			// There ain't no one in a deck, dummy!
			//case 1: strValue = "One";
			//break;
			case 2: strValue = "Two";
			break;
			case 3: strValue = "Three";
			break;
			case 4: strValue = "Four";
			break;
			case 5: strValue = "Five";
			break;
			case 6: strValue = "Six";
			break;
			case 7: strValue = "Seven";
			break;
			case 8: strValue = "Eight";
			break;
			case 9: strValue = "Nine";
			break;
			case 10: 
				if(specialValue.equals("")) {
					strValue = "Ten";
				}
				else if(specialValue.equals("j")) {
					strValue = "Jack";
				}
				else if(specialValue.equals("q")) {
					strValue = "Queen";
				}
				else if(specialValue.equals("k")) {
					strValue = "King";
				}
			break;
			case 11: strValue = "Ace";
			break;
		}
		
		return strValue;
		
	}
	
	public String getDisplayValue() {
		
		String strValue = "";
		
		switch(this.value) {
			
			case 2: strValue = "2";
			break;
			case 3: strValue = "3";
			break;
			case 4: strValue = "4";
			break;
			case 5: strValue = "5";
			break;
			case 6: strValue = "6";
			break;
			case 7: strValue = "7";
			break;
			case 8: strValue = "8";
			break;
			case 9: strValue = "9";
			break;
			case 10: 
				if(specialValue.equals("")) {
					strValue = "10";
				}
				else if(specialValue.equals("j")) {
					strValue = "J";
				}
				else if(specialValue.equals("q")) {
					strValue = "Q";
				}
				else if(specialValue.equals("k")) {
					strValue = "K";
				}
			break;
			case 11: strValue = "A";
			break;
		}
		
		return strValue;
		
	}
	
}
