package solitaire;

/*
 Simple Solitaire Card Game in Java
 Written by Tim Budd, Oregon State University, 1996
 */

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;
import java.util.Stack;

public class Solitaire extends Applet {
	static CardPile allPiles[];
	static DeckPile deckPile;
	static DiscardPile discardPile;
	static SuitPile suitPile[];
	static TablePile tableau[];
	
	static CardPile tmpPile;
	static Stack<Card> stack = new Stack<>();
	static boolean Click = false;
	
	boolean cardWasChoosed = false;

	@Override
	public void init() {
		
		
		
		this.setSize(400, 400);	//задаём размеры главного окна игры
		
		// first allocate the arrays
		allPiles = new CardPile[13];
		suitPile = new SuitPile[4];
		tableau = new TablePile[7];
		// then fill them in
		allPiles[0] = deckPile = new DeckPile(335, 5);
		allPiles[1] = discardPile = new DiscardPile(268, 5);
		for (int i = 0; i < 4; i++) {
			allPiles[2 + i] = suitPile[i] = new SuitPile(15 + 60 * i, 5);
		}
		for (int i = 0; i < 7; i++) {
			allPiles[6 + i] = tableau[i] = new TablePile(5 + 55 * i, 80, i + 1);
		}
	}

	@Override 
	public boolean mouseDown(final Event evt, final int x, final int y) {
		
		for (int i = 0; i < 13; i++) {
			if (allPiles[i].includes(x, y)) {
				if (!Click){
					if (!allPiles[i].top().isFaceUp()) { allPiles[i].top().flip(); break; }
					tmpPile = allPiles[i];
					tmpPile.setBoundedFlags();
					Click = true;
				}
				else{
					allPiles[i].select(x, y);
					allPiles[i].resetAllFlags();
					tmpPile.resetAllFlags();
					tmpPile.flagCard = null;
					tmpPile = null;
					Click = false;
					
					repaint();
					return true;
				}
				
			}
			repaint();
		}
		
		return true;
	}

	@Override
	public void paint(final Graphics g) {
		for (int i = 0; i < 13; i++) {
			allPiles[i].display(g);
		}
	}
}