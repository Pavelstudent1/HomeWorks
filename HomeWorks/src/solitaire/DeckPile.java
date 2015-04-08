package solitaire;


class DeckPile extends CardPile {

	DeckPile(final int x, final int y) {
		// first initialize parent
		super(x, y);
		// then create the new deck
		// first put them into a local pile
		CardPile pileOne = new CardPile(0, 0);
		CardPile pileTwo = new CardPile(0, 0);
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <= 12; j++) {
				pileOne.addCard(new Card(i, j));
				count++;
			}
		}
		// then pull them out randomly
		for (; count > 0; count--) {
			int limit = ((int) (Math.random() * 1000)) % count;
			// move down to a random location
			for (int i = 0; i < limit; i++) {
				pileTwo.addCard(pileOne.pop());
			}
			// then add the card found there
			addCard(pileOne.pop());
			// then put the decks back together
			while (!pileTwo.empty()) {
				pileOne.addCard(pileTwo.pop());
			}
		}
		
		num_of_cards = 24;
		
	}

	@Override
	public boolean includes(final int tx, final int ty) {
		if (empty()) {
			
			/** Перенос карт из DiscardPile в DeckPile, когда в последнем карты кончились **/
			while(!Solitaire.discardPile.empty()){
				Card flipped = Solitaire.discardPile.pop(); //забираем карту
				flipped.flip();								//переворачиваем
				addCard(flipped);
			}
			
			return false;
		}
		
		if (x <= tx && tx <= x + Card.width && y <= ty && ty <= y + Card.height){
			Solitaire.discardPile.addCard(pop());
			Solitaire.discardPile.flagCard = Solitaire.discardPile.top();
		}
		
		return false;
	}
}