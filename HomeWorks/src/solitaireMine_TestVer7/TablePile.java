package solitaireMine_TestVer7;

public class TablePile extends CardPile{

	public TablePile(int x, int y, int howManyCards, CardPile srcPile) {
		super(x, y);
		
		while(howManyCards != 0){
			addCard(srcPile.popTop());
			--howManyCards;
		}
		
//		srcPile.getTopCard().setFaceUp();
		getTopCard().setFaceUp();
		
	}
	
	private TablePile(){	//приватный конструктор для внутренних нужд
		super();
	}
	
	
	//т.к. только TablePile имеют возможность принимать/отдавать несколько карт,
	//реализованы оба метода. Все остальные колоды взаимодействуют только
	//с одной картой
	@Override
	public boolean canTakeCard(Card c) {
		
		if (isEmpty()) return true;
		
		return c.getColor() != this.getTopCard().getColor() &&
			   c.getRank() + 1 == this.getTopCard().getRank();
	}
	
	@Override
	public boolean canGiveCard() {
		return true;
	}
	
	@Override
	public boolean canTakePile(CardPile cp) {
		
		if (isEmpty()) return true;
		
		return cp.getBottomCard().getColor() != this.getTopCard().getColor() &&
			   cp.getBottomCard().getRank() + 1 == this.getTopCard().getRank();
	}
	
	/** Реализация методов массового забора и добавления карт **/
	public CardPile popCardsFrom(Card c){
		CardPile tmp = new TablePile();
		tmp.setShiftXY(this.getShiftX(), this.getShiftY());
		
		int i = this.getIndexOf(c),
			stop = this.getLength();
		while(i < stop){
			tmp.addCardToHead(this.popTop());
			i++;
		}
		
		return tmp;
	}
	
	public void addCardsFrom(CardPile p){
	
		while(!p.isEmpty()){
			this.addCard(p.popHeadCard()); 
		}
	}
	
	

}
