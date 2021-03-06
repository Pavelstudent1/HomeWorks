package PokerCombo_ChainOfResp;

import java.awt.Color;
import java.awt.Graphics;

class Card {
	
	final static String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	final static String[] SUITS = {"HEART", "SPADE", "DIAMOND", "CLUB"};
	
	final static int club = 3;
	final static int diamond = 2;
	final static int spade = 1;
	final static int heart = 0;
	
	// data fields for colors and suits
	final static int height = 70;
	final static int width = 50;
	
	final static int black = 1;
	final static int red = 0;

	// data fields
	private boolean faceup; //лицом вверх?
	private int rank;	//
	private int suit;	//

	public Card link; //ссылка на следующую карту
	public boolean flag = false;
	
	// constructor
	Card(final int suitValue, final int rankValue) {
		suit = suitValue; //масть
		rank = rankValue;
		faceup = false; //изначально карта лежит лицом вниз
	}

	public int color() {
		if (getSuit() == heart || getSuit() == diamond) {
			return red;
		}
		return black;
	}

	public void draw(final Graphics g, final int x, final int y) {
		String names[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"J", "Q", "K" };
		// clear rectangle, draw border
		g.clearRect(x, y, width, height);
//		g.setColor(Color.black);
		
		if (flag){
			g.setColor(Color.red);
		}else{
			g.setColor(Color.black);
		}
		g.drawRect(x, y, width, height);
		// draw body of card
		if (isFaceUp()) {
			if (color() == red) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.blue);
			}
			g.drawString(names[getRank()], x + 3, y + 15);
			if (getSuit() == heart) {
				g.drawLine(x + 25, y + 30, x + 35, y + 20);
				g.drawLine(x + 35, y + 20, x + 45, y + 30);
				g.drawLine(x + 45, y + 30, x + 25, y + 60);
				g.drawLine(x + 25, y + 60, x + 5, y + 30);
				g.drawLine(x + 5, y + 30, x + 15, y + 20);
				g.drawLine(x + 15, y + 20, x + 25, y + 30);
			} else if (getSuit() == spade) {
				g.drawLine(x + 25, y + 20, x + 40, y + 50);
				g.drawLine(x + 40, y + 50, x + 10, y + 50);
				g.drawLine(x + 10, y + 50, x + 25, y + 20);
				g.drawLine(x + 23, y + 45, x + 20, y + 60);
				g.drawLine(x + 20, y + 60, x + 30, y + 60);
				g.drawLine(x + 30, y + 60, x + 27, y + 45);
			} else if (getSuit() == diamond) {
				g.drawLine(x + 25, y + 20, x + 40, y + 40);
				g.drawLine(x + 40, y + 40, x + 25, y + 60);
				g.drawLine(x + 25, y + 60, x + 10, y + 40);
				g.drawLine(x + 10, y + 40, x + 25, y + 20);
			} else if (getSuit() == club) {
				g.drawOval(x + 20, y + 25, 10, 10);
				g.drawOval(x + 25, y + 35, 10, 10);
				g.drawOval(x + 15, y + 35, 10, 10);
				g.drawLine(x + 23, y + 45, x + 20, y + 55);
				g.drawLine(x + 20, y + 55, x + 30, y + 55);
				g.drawLine(x + 30, y + 55, x + 27, y + 45);
			}
		} else { // face down
			g.setColor(Color.yellow);
			g.drawLine(x + 15, y + 5, x + 15, y + 65);
			g.drawLine(x + 35, y + 5, x + 35, y + 65);
			g.drawLine(x + 5, y + 20, x + 45, y + 20);
			g.drawLine(x + 5, y + 35, x + 45, y + 35);
			g.drawLine(x + 5, y + 50, x + 45, y + 50);
		}
	}

	public boolean isFaceUp() {
		return faceup;
	}

	public void flip() {
		faceup = !faceup;
	}

	// access attributes of card
	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	final boolean isAce() {
		return getRank() == 0;
	}

	final boolean isKing() {
		return getRank() == 12;
	}
	
	@Override
	public String toString() {

		String s =RANKS[getRank()] + " of " + SUITS[getSuit()];
		return s;
	}
}