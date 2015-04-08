package solitaire;

import java.awt.Graphics;

class TablePile extends CardPile {
	
//	int number_of_cards = 0;
	final int value_of_shifting = 35;

	TablePile(final int x, final int y, final int c) { //c - номер стопки по счёту
		// initialize the parent class
		super(x, y);
		// then initialize our pile of cards
		for (int i = 0; i < c; i++) {
			addCard(Solitaire.deckPile.pop());
		}
		
		top().flip();
		num_of_cards = c;
	}

	@Override
	public boolean canTake(final Card aCard) {
		if (empty()) {
			return aCard.isKing();
		}
		Card topCard = top();
		return (aCard.color() != topCard.color())
				&& (aCard.getRank() == topCard.getRank() - 1);
	}

	@Override
	public void display(final Graphics g) {
		stackDisplay(g, top());
	}

	@Override
	public boolean includes(final int tx, final int ty) { //попадает ли тычёк мышки по картам
		// don't test bottom of card
		
	if (empty()){
		return false;
	}
	
	if (!(x <= tx && tx <= x + Card.width && y <= ty)) return false;
	
	int counterDown = num_of_cards;
	int topY = y + (value_of_shifting * (--counterDown));
	if (x <= tx && tx <= x + Card.width && topY <= ty && topY <= topY + Card.width){
		System.out.println("This is top card");
//		top().flag = true;
		flagCard = top();
		return true;
	}
	
	Card tmp = top().link;
	while(tmp.isFaceUp() && tmp != null){
		topY = y + (value_of_shifting * (--counterDown));
		if (x <= tx && tx <= x + Card.width && topY <= ty && ty <= topY + value_of_shifting){
			System.out.println("This is " + (counterDown + 1) + " card");
//			tmp.flag = true;
			flagCard = tmp;
			return true;
		}
		tmp = tmp.link;
	}
	
	return false;
	}

	@Override
	public void select(final int tx, final int ty) {
		if (empty()) {
			return;
		}
		
		if (this.canTake(Solitaire.tmpPile.flagCard)){
			
			Card tmp = Solitaire.tmpPile.top();
			while(tmp != Solitaire.tmpPile.flagCard.link){
				Solitaire.stack.push(Solitaire.tmpPile.pop());
				tmp = tmp.link;
			}
			
			while(!Solitaire.stack.isEmpty()){
				this.addCard(Solitaire.stack.pop());
			}
		}
		else{
			System.out.println("Can't take!");
		}
	}

	private int stackDisplay(final Graphics g, final Card aCard) {
		int localy;
		if (aCard == null) {
			return y;
		}
		localy = stackDisplay(g, aCard.link);
		aCard.draw(g, x, localy);
		return localy + 35;
	}

}