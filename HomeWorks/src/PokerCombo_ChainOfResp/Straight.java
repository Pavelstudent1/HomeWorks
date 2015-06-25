package PokerCombo_ChainOfResp;

public class Straight extends AbstractBaseCombo {

	public Straight() {
		super("Straight");
	}

	@Override
	public boolean isRightComboType(final Card[] pile) {

		if (isCommonSuit(pile)) return false;		
		if (!isRankStraight(pile)) return false;

		return true;
	}

}
