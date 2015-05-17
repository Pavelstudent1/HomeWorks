package solitaireMine_TestVer7;

public class DiscardPile extends CardPile {
	
	public DiscardPile(int x, int y) {
		super(x, y);
		
		
	}
	
	
	
	@Override
	public boolean canTakeCard(Card c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean canGiveCard() {
		return true;
	}

}
