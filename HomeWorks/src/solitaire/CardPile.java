package solitaire;

import java.awt.Color;
import java.awt.Graphics;

class CardPile {

	private Card firstCard; //ссылка на верхнюю карту в стопке
	int num_of_cards;
	
	//*********************************
	public Card flagCard;
	
	public Card getFlagCard(){
		return flagCard == null ? top() : flagCard;
	}
	
	public void resetAllFlags(){
		Card tmp = top();
		while(tmp != null){
			tmp.flag = false;
			tmp = tmp.link;
		}
	}
	
	public void setBoundedFlags(){
		Card tmp = top();
		while(tmp != flagCard){
			tmp.flag = true;
			tmp = tmp.link;
		}
		if (tmp != null) tmp.flag = true;
	}
	
	
	//*********************************

	// coordinates of the card pile
	protected int x;
	protected int y;

	CardPile(final int xl, final int yl) {
		x = xl;
		y = yl;
		firstCard = null;
	}

	// the following are sometimes overridden

	public void addCard(final Card aCard) {
		
		++num_of_cards;
		
		aCard.link = firstCard;
		firstCard = aCard;
	}

	public boolean canTake(final Card aCard) {
		return false; //default implementation
	}

	public void display(final Graphics g) {
		g.setColor(Color.black);
		if (firstCard == null) {
			g.drawRect(x, y, Card.width, Card.height);
		} else {
			firstCard.draw(g, x, y);
		}
	}

	public boolean empty() {
		return firstCard == null;
	}

	public boolean includes(final int tx, final int ty) {
		return x <= tx && tx <= x + Card.width && y <= ty
				&& ty <= y + Card.height;
	}

	public Card pop() {
		Card result = null;
		if (firstCard != null) {
			result = firstCard;
			firstCard = firstCard.link;
		}
		
		--num_of_cards;
		
		return result;
	}

	
	public void select(final int tx, final int ty) {
		//do nothing
	}

	public Card top() {
		return firstCard;
	}
}