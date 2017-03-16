import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CardRepresentation extends JPanel {

	double scalar = 1.5;

	final String FONT_NAME = "Segoe";

	final int SUIT_FONT_SIZE = 44, RANK_FONT_SIZE = 24;

	final Font SUIT_FONT = new Font(FONT_NAME, Font.PLAIN, (int) (SUIT_FONT_SIZE * scalar)),
			RANK_FONT = new Font(FONT_NAME, Font.PLAIN, (int) (RANK_FONT_SIZE * scalar));

	static final int CARD_WIDTH = 60, CARD_HEIGHT = 90, CARD_HORIZONTAL_MARGIN = 5, CARD_VERTICAL_MARGIN = 5, CARD_BACK_PADDING = 8, CARD_HORIZONTAL_PADDING = 5,
			CARD_VERTICAL_PADDING = 0;

	String rank;
	Suit suit;
	int x, y;

	boolean initialPaint = false, hidden;

	public CardRepresentation(String rank, Suit suit, int x, int y, int delay, boolean hidden) {
		this.rank = rank;
		this.suit = suit;
		this.x = x;
		this.y = y;
		this.hidden = hidden;

		ActionListener animation = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				initialPaint = true;
				repaint();

			}

		};

		Timer animationDelay = new Timer(delay, animation);
		animationDelay.setRepeats(false);
		animationDelay.start();
	}

	public static int getCardWidth() {
		return CARD_WIDTH;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	protected void paintComponent(Graphics g) {

		String unicode = new String();

		super.paintComponent(g);

		if (initialPaint) {

			// Card Background
			g.setColor(java.awt.Color.WHITE);
			g.fillRoundRect((int) (x + CARD_HORIZONTAL_MARGIN * scalar), (int) (y + CARD_VERTICAL_MARGIN * scalar), (int) (CARD_WIDTH * scalar),
					(int) (CARD_HEIGHT * scalar), (int) (CARD_WIDTH / 2 * scalar), (int) (CARD_WIDTH / 2 * scalar));

			switch (suit) {
			case Spades:
				unicode = "\u2660";
				g.setColor(java.awt.Color.BLACK);
				break;
			case Hearts:
				unicode = "\u2665";
				g.setColor(java.awt.Color.RED);
				break;
			case Diamonds:
				unicode = "\u2666";
				g.setColor(java.awt.Color.RED);
				break;
			case Clubs:
				unicode = "\u2663";
				g.setColor(java.awt.Color.BLACK);
				break;
			default:
				System.out.println("Invalid suit");
				break;
			}

			// Draw suit
			g.setFont(SUIT_FONT);

			// The multiplier to the SUIT_FONT_SIZE varies depending on the font
			// used; change it accordingly
			g.drawString(unicode, (int) ((x + (CARD_WIDTH - SUIT_FONT_SIZE * 0.82) / 2 + CARD_HORIZONTAL_MARGIN) * scalar),
					(int) (y + (SUIT_FONT_SIZE + CARD_HEIGHT) / 2 * scalar));

			// Draw rank
			g.setFont(RANK_FONT);
			g.drawString(rank, (int) ((x + CARD_HORIZONTAL_PADDING + CARD_HORIZONTAL_MARGIN) * scalar),
					(int) ((y + RANK_FONT_SIZE + CARD_VERTICAL_PADDING + CARD_VERTICAL_MARGIN) * scalar));

			// If hidden, hide the card
			if (hidden) {
				// Paint the whole card white
				g.setColor(java.awt.Color.WHITE);
				g.fillRoundRect((int) (x + CARD_HORIZONTAL_MARGIN * scalar), (int) (y + CARD_VERTICAL_MARGIN * scalar),
						(int) (CARD_WIDTH * scalar), (int) (CARD_HEIGHT * scalar), (int) (CARD_WIDTH / 2 * scalar),
						(int) (CARD_WIDTH / 2 * scalar));
				
				// Add a smaller red rectangle to represent the card's back
				g.setColor(java.awt.Color.RED);
				g.fillRect((int) ((x + CARD_BACK_PADDING + CARD_HORIZONTAL_MARGIN) * scalar), (int) ((y + CARD_BACK_PADDING + CARD_VERTICAL_MARGIN) * scalar),
						(int) ((CARD_WIDTH - 2 * CARD_BACK_PADDING) * scalar), (int) ((CARD_HEIGHT - 2 * CARD_BACK_PADDING) * scalar)); 
						// For rounded rectangle: (int) (CARD_WIDTH / 2 * scalar), (int) (CARD_WIDTH / 2 * scalar));
			}
			
			// Card border
			g.setColor(java.awt.Color.BLACK);
			// g.drawRect(CARD_GAP, CARD_GAP, CARD_GAP, CARD_HEIGHT);
			g.drawRoundRect((int) (x + CARD_HORIZONTAL_MARGIN * scalar), (int) (y + CARD_VERTICAL_MARGIN * scalar), (int) (CARD_WIDTH * scalar),
					(int) (CARD_HEIGHT * scalar), (int) (CARD_WIDTH / 2 * scalar), (int) (CARD_WIDTH / 2 * scalar));

		}

	}
}
