package PokerCombo_ChainOfResp;

import java.util.Arrays;


public class RoyalFlush extends AbstractBaseCombo {

	public static final String comboName = "RoyalFlush";
	
	public RoyalFlush() {
		super(comboName);
	}
	
	@Override
	public boolean isRightComboType(final Card[] pile)
	{
		if (!isCommonSuit(pile)) return false;
		
		int[] winMask = {1,0,0,0,0,0,0,0,0,1,1,1,1}; //маска Роял Флеша, где 0-й элемент - Туз, 1-й - Двойка и т.д.
		int[] comboMask = getRankMask(pile);
		
		if (!Arrays.equals(winMask, comboMask)) return false;
		
		return true;
	}
	
}
