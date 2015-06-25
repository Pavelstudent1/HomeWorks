package solitaireMine_TestVer7;

import java.util.Random;

public class DeckPile extends CardPile {
	
	private CardPile discardPile;
	
	public DeckPile(final int x, final int y, final CardPile dp) {
		super(x, y);
		discardPile = dp;
		
		int count = 0;
		Card[] tempPile = new Card[52];
		for(int suit = 0; suit < 4; suit++){	//создаём массив карт
			for(int rank = 0; rank <= 12; rank++){
				tempPile[count++] = new Card(suit, rank);
			}
		}
		
		Random random = new Random();
		for(int i = 0; i < count; i++){		//перемешиваем по Фишеру-Йетсу
			int r = random.nextInt(count);
			Card tmp = tempPile[i];
			tempPile[i] = tempPile[r];
			tempPile[r] = tmp;
		}
		
		
		for (Card card : tempPile) {		//заполняем уже непосредственно колоду
			super.addCard(card);			//перемешанными картами
		}
		
	}

	@Override
	public boolean canTakeCard(final Card c) {
		return false;
	}
	
	@Override
	public boolean canGiveCard() {
		return false;
	}
	
	
	
	@Override
	public void tapOnCard(final Card card) {
		
		if (isEmpty()){
			while(!discardPile.isEmpty()){
				discardPile.getTopCard().flip();
				addCard(discardPile.popTop());
			}
		}else{
			getTopCard().flip();
			discardPile.addCard(popTop());
		}
	}
	
	public void linkWithDiscardPile(final CardPile cp){
		discardPile = cp;
	}
	
	

}
