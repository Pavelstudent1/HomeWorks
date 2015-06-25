package PokerCombo_ChainOfResp;

import java.util.Arrays;

public class Flush extends AbstractBaseCombo {

	public Flush() {
		super("Flush");
	}

	@Override
	public boolean isRightComboType(final Card[] pile) {
		
		int[] rf = {1,0,0,0,0,0,0,0,0,1,1,1,1};
		
		//сравнивать массивы надо через Arrays.equals(), а не через int[].equals() !
		if (Arrays.equals(getRankMask(pile), rf)) return false; //если комбинация является Роялем
		
		if (isRankStraight(pile)) return false;	   //если комбинация последовательна
		if (!isCommonSuit(pile)) return false;	   //если масти разные
		
		return true;
	}

}
