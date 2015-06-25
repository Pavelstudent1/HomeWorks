package PokerCombo_ChainOfResp;

public class StraightFlush extends AbstractBaseCombo{
	
	
	public StraightFlush() {
		super("StraightFlush");
	}

	@Override
	public boolean isRightComboType(Card[] pile) {

		int[] royalflush = {1,0,0,0,0,0,0,0,0,1,1,1,1};
		int[] pileRank = getRankMask(pile);
		
		//проверяем на общую масть
		if (!isCommonSuit(pile)) return false;
		
		//проверяем маску,т.к. РоялФлеш частный случай СтритФлеша
		if (pileRank.equals(royalflush)) return false;
		
		//проверяем, что значения в маске последовательны
		if (!isRankStraight(pile)) return false;
		
		return true;
	}

}
