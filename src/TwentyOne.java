import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TwentyOne extends JFrame implements ActionListener {

	final int HAND_WIDTH = 240, HAND_HEIGHT = 105, CONSOLE_WIDTH = 300, CONSOLE_HEIGHT = 150,
			CARD_APPEARANCE_DELAY = 100;

	final String PROGRAM_TITLE = "TwentyOne", HIT_COMMAND = "Hit", STAND_COMMAND = "Stand",
			NEW_GAME_COMMAND = "New Game";

	Deck deck = new Deck();

	CardRepresentation hiddenCard;

	ArrayList<Card> dealerCards = new ArrayList<Card>(), playerCards = new ArrayList<Card>();

	JScrollPane dealerScrollPane, playerScrollPane, consoleScrollPane;
	
	JTextArea consoleTextArea;

	Container playerHand = new Container(), dealerHand = new Container();

	boolean gameStarted = false;

	public static void main(String[] args) {

		new TwentyOne();

		// Checking fonts
		/*
		 * String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().
		 * getAvailableFontFamilyNames();
		 * 
		 * for (int i = 0; i < fonts.length; i++) {
		 * System.out.println(fonts[i]); }
		 */
	}

	public TwentyOne() {

		Container contentPane = this.getContentPane();

		// Labels
		Font titleLabelFont = new Font("Times New Roman", Font.PLAIN, 24);
		Font regularLabelFont = new Font("Times New Roman", Font.PLAIN, 16);
		Font smallLabelFont = new Font("Times New Roman", Font.PLAIN, 14);

		// Program Title Label
		JLabel programLabel = new JLabel(PROGRAM_TITLE);
		programLabel.setHorizontalAlignment(SwingConstants.CENTER);
		programLabel.setVerticalAlignment(SwingConstants.CENTER);
		programLabel.setFont(titleLabelFont);

		// Dealer Label
		JLabel dealerLabel = new JLabel("Dealer");
		dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerLabel.setVerticalAlignment(SwingConstants.CENTER);
		dealerLabel.setFont(smallLabelFont);

		// Player Label
		JLabel playerLabel = new JLabel("Player");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setVerticalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(smallLabelFont);

		// Scroll Pane (Hands)

		// Dealer Hand
		dealerScrollPane = new JScrollPane();
		dealerScrollPane.setPreferredSize(new Dimension(HAND_WIDTH, HAND_HEIGHT));

		// Player Hand
		playerScrollPane = new JScrollPane();
		playerScrollPane.setPreferredSize(new Dimension(HAND_WIDTH, HAND_HEIGHT));

		// Console
		consoleTextArea = new JTextArea();
		consoleTextArea.setEditable(false);
		
		consoleScrollPane = new JScrollPane(consoleTextArea);
		consoleScrollPane.setPreferredSize(new Dimension(CONSOLE_WIDTH, CONSOLE_HEIGHT));
		//consoleScrollPane.getViewport().setBackground(Color.WHITE);

		// Buttons

		// Hit Button
		JButton hitButton = new JButton(HIT_COMMAND);
		hitButton.setMnemonic(KeyEvent.VK_H);
		hitButton.setActionCommand(HIT_COMMAND);
		hitButton.setToolTipText("Hit me.");
		hitButton.addActionListener(this);
		hitButton.setBackground(Color.LIGHT_GRAY);

		// Stand Button
		JButton standButton = new JButton(STAND_COMMAND);
		standButton.setMnemonic(KeyEvent.VK_S);
		standButton.setActionCommand(STAND_COMMAND);
		standButton.setToolTipText("Stand.");
		standButton.addActionListener(this);
		standButton.setBackground(Color.LIGHT_GRAY);

		// New Game Button
		JButton newGameButton = new JButton(NEW_GAME_COMMAND);
		newGameButton.setMnemonic(KeyEvent.VK_G);
		newGameButton.setActionCommand(NEW_GAME_COMMAND);
		newGameButton.setToolTipText("Start a new game.");
		newGameButton.addActionListener(this);
		newGameButton.setBackground(Color.LIGHT_GRAY);

		// Layout

		// Hands
		playerHand.setLayout(new GridBagLayout());
		dealerHand.setLayout(new GridBagLayout());

		// Program
		GroupLayout layout = new GroupLayout(contentPane);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(programLabel)
				.addComponent(consoleScrollPane)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(dealerLabel)
								.addComponent(dealerScrollPane).addComponent(playerLabel)
								.addComponent(playerScrollPane))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(newGameButton, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(hitButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(standButton, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)))));

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(programLabel)
				.addComponent(consoleScrollPane).addComponent(dealerLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(dealerScrollPane)
						.addComponent(newGameButton))
				.addComponent(playerLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(playerScrollPane)
						.addGroup(layout.createSequentialGroup().addComponent(hitButton).addComponent(standButton))));

		this.setLayout(layout);
		this.setResizable(false);
		this.setSize(600, 600);
		this.setTitle(PROGRAM_TITLE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * // Checking whether bust or not private boolean checkPlayerStatus() {
	 * 
	 * boolean bust = false;
	 * 
	 * for (int i = 0; i < playerCards.size(); i++) {
	 * 
	 * } }
	 */

	private int calculateHandValue(ArrayList<Card> hand) {

		int total = 0, acesCount = 0;

		for (int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if (c.getStringValue().equals("Ace")) {
				acesCount++;
			} else {
				total += c.getValue();
			}
		}

		int remainingAces = acesCount;

		for (int i = 0; i < acesCount; i++) {
			if (total + 11 <= 21 && remainingAces == 1) {
				total += 11;
			} else {
				total++;
			}

			remainingAces--;
		}

		return total;
	}

	private void hit() {

		if (gameStarted) {

			// Player
			Card drawnCard = deck.draw();

			if (drawnCard.getValue() == 0) {
				//System.out.println("\nNo cards left.");
				//consoleScrollPane.set
				return;
			}

			CardRepresentation playerCard = new CardRepresentation(drawnCard.getDisplayValue(), drawnCard.getSuit(), 0,
					0, CARD_APPEARANCE_DELAY, false);

			playerCards.add(drawnCard);

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = GridBagConstraints.RELATIVE;
			constraints.gridy = 0;
			constraints.weightx = 0.5;
			constraints.weighty = 0.5;
			playerHand.add(playerCard, constraints);

			playerScrollPane.setViewportView(playerHand);

			//System.out.println("--------------------\n");
			//System.out.println("Player: " + calculateHandValue(playerCards));
			writeLine("--------------------");
			writeLine("Player: " + calculateHandValue(playerCards));

			if (calculateHandValue(playerCards) > 21) {
				//System.out.println("\nBust!");
				//System.out.println("Dealer wins!");
				writeLine("\nBust!");
				writeLine("Dealer wins!");
				endGame();
			} else if (calculateHandValue(playerCards) == 21) {

				// Natural 21
				//System.out.println("\nBlackjack!");
				writeLine("\nBlackjack!");
				stand();
			}
		}

	}

	private void stand() {

		if (gameStarted) {

			// Dealer only draws if his hand totals less than 17

			while (calculateHandValue(dealerCards) < 17) {
				// Dealer
				Card drawnCard = deck.draw();

				if (drawnCard.getValue() == 0) {
					//System.out.println("No cards left.");
					writeLine("No cards left.");
					return;
				}

				CardRepresentation dealerCard = new CardRepresentation(drawnCard.getDisplayValue(), drawnCard.getSuit(),
						0, 0, CARD_APPEARANCE_DELAY, false);

				dealerCards.add(drawnCard);

				GridBagConstraints constraints = new GridBagConstraints();
				constraints.fill = GridBagConstraints.BOTH;
				constraints.gridx = GridBagConstraints.RELATIVE;
				constraints.gridy = 0;
				constraints.weightx = 0.5;
				constraints.weighty = 0.5;
				dealerHand.add(dealerCard, constraints);

				dealerScrollPane.setViewportView(dealerHand);

				//System.out.println("--------------------\n");
				//System.out.println("Dealer: " + calculateHandValue(dealerCards));
				writeLine("--------------------");
				writeLine("Dealer: " + calculateHandValue(dealerCards));

				if (calculateHandValue(dealerCards) > 21) {
					//System.out.println("\nDealer bust!");
					//System.out.println("Player wins!\n");
					writeLine("\nDealer bust!");
					writeLine("Player wins!");
					endGame();
					return;
				} else if (calculateHandValue(dealerCards) == 21) {
					//System.out.println("\nDealer blackjack!");
					writeLine("\nDealer blackjack!");
					compareHands();
					return;
				}
			}

			compareHands();
		}

	}

	private void revealHiddenCard() {

		// Reveal dealer's hidden card
		hiddenCard.setHidden(false);
		hiddenCard.repaint();
	}

	private void compareHands() {

		int dealerTotal = calculateHandValue(dealerCards), playerTotal = calculateHandValue(playerCards);

		//System.out.println("\n--------------------");
		//System.out.println("Results\n");
		writeLine("--------------------");
		writeLine("Results\n");

		//System.out.println("Dealer: " + dealerTotal);
		//System.out.println("Player: " + playerTotal + "\n");
		
		writeLine("Dealer: " + dealerTotal);
		writeLine("Player: " + playerTotal);

		if (dealerTotal > playerTotal) {
			//System.out.println("Dealer wins!");
			writeLine("Dealer wins!");
		} else if (dealerTotal == playerTotal) {
			//System.out.println("Push!");
			//System.out.println("Tie!");
			writeLine("Push!");
			writeLine("Tie!");
		} else {
			//System.out.println("Player wins!");
			writeLine("Player wins!");
		}

		endGame();
	}

	private void startGame() {

		refreshWindow();
		//System.out.println("----------------------------------------");
		//System.out.println("Starting new game...\n");
		writeLine("----------------------------------------");
		writeLine("Starting new game...\n");

		deck.clearDeck();
		deck.populateDeck();

		playerCards.clear();
		dealerCards.clear();

		playerHand.removeAll();
		dealerHand.removeAll();

		boolean hidden = false;

		for (int i = 0; i < 2; i++) {

			// Player
			Card drawnCard = deck.draw();

			if (drawnCard.getValue() == 0) {
				//System.out.println("No cards left.");
				writeLine("No cards left.");
				return;
			}

			CardRepresentation playerCard = new CardRepresentation(drawnCard.getDisplayValue(), drawnCard.getSuit(), 0,
					0, CARD_APPEARANCE_DELAY * 2 * i, hidden);

			playerCards.add(drawnCard);

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = GridBagConstraints.RELATIVE;
			constraints.gridy = 0;
			constraints.weightx = 0.5;
			constraints.weighty = 0.5;
			playerHand.add(playerCard, constraints);

			playerScrollPane.setViewportView(playerHand);

			// Dealer
			drawnCard = deck.draw();

			if (drawnCard.getValue() == 0) {
				//System.out.println("No cards left.");
				writeLine("No cards left.");
				return;
			}

			if (i == 0) {
				hidden = true;
			}
			CardRepresentation dealerCard = new CardRepresentation(drawnCard.getDisplayValue(), drawnCard.getSuit(), 0,
					0, CARD_APPEARANCE_DELAY + CARD_APPEARANCE_DELAY * 2 * i, hidden);

			if (i == 0) {
				hiddenCard = dealerCard;
			}

			hidden = false;

			dealerCards.add(drawnCard);

			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = GridBagConstraints.RELATIVE;
			constraints.gridy = 0;
			constraints.weightx = 0.5;
			constraints.weighty = 0.5;
			dealerHand.add(dealerCard, constraints);

			dealerScrollPane.setViewportView(dealerHand);
		}

		//System.out.println("Player: " + calculateHandValue(playerCards));
		writeLine("Player: " + calculateHandValue(playerCards));
		
		boolean compareHands = false;

		if (calculateHandValue(playerCards) == 21) {
			//System.out.println("Blackjack!");
			writeLine("Blackjack!");
			compareHands = true;
		}

		// Check dealer's cards for a natural 21
		if (calculateHandValue(dealerCards) == 21) {

			// Dealer automatically wins without player being able to act
			// (unless player also has a blackjack, in which case it is a
			// tie)
			// Natural 21
			//System.out.println("Dealer blackjack!");
			writeLine("Dealer blackjack!");
			compareHands = true;
		}
		
		if(compareHands) {
			compareHands();
			return;
		}

		gameStarted = true;

	}

	private void endGame() {
		revealHiddenCard();
		gameStarted = false;
	}

	private void refreshWindow() {
		this.repaint();
	}
	
	private void writeLine(String line) {
		JScrollBar vertical = consoleScrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		consoleTextArea.append(line + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand() == HIT_COMMAND) {
			hit();
		} else if (event.getActionCommand() == STAND_COMMAND) {
			stand();
		} else if (event.getActionCommand() == NEW_GAME_COMMAND) {
			startGame();
		}

	}

}
