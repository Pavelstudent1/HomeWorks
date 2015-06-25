package PokerCombo_ChainOfResp;

public class Three extends AbstractBaseCombo {

	public Three() {
		super("Three");
	}

	@Override
	public boolean isRightComboType(final Card[] pile) {
		
		boolean wasThree = false, wasTwo = false;
		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == 3) wasThree = true;
			if (i == 2) wasTwo = true;
		}
		
		if (wasThree == true && wasTwo == false) return true; //только такое сочетание говорит, 
															  //что колода является Тройкой
		
		return false;
	}

}
