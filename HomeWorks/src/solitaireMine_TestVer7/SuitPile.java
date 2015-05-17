package solitaireMine_TestVer7;

public class SuitPile extends CardPile{
	
	public SuitPile(int x, int y) {
		super(x, y);
	}
	
	
	
	@Override
	public boolean canTakeCard(Card c) {
		
		if (isEmpty()){
			if (c.isAce()) return true;
			else return false;
		}else
			return c.getSuit() == getTopCard().getSuit() && 
				   c.getRank() - 1 == getTopCard().getRank();
	}



	@Override
	public boolean canGiveCard() {
		return false;
	}
	

}
