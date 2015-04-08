package solitaire;

class DiscardPile extends CardPile {

	DiscardPile(final int x, final int y) {
		super(x, y);
	}

	@Override
	public void addCard(final Card aCard) {
		if (!aCard.isFaceUp()) {
			aCard.flip();
		}
		super.addCard(aCard);
	}

	@Override
	public void select(final int tx, final int ty) {
		if (empty()) {
			return;
		}
		
//		super.select(tx, ty);
		
		if (this.canTake(Solitaire.tmpPile.flagCard)){
				System.out.println("Given from DiscardPile");
				addCard(Solitaire.tmpPile.flagCard);
			}else{
				System.out.println("Can't take");
			}
	}
	
	@Override
	public void setBoundedFlags() {
		top().flag = true;
	}
	
}