package PokerCombo_ChainOfResp;

public class FullHouse extends AbstractBaseCombo {

	public FullHouse() {
		super("FullHouse");
	}

	@Override
	public boolean isRightComboType(final Card[] pile) {
		
		int comboCount = 0;
		int[] comboMask = getRankMask(pile);
	
		for (int i : comboMask) {
			if (i == 3) comboCount += 3;
			if (i == 2) comboCount += 2;
		}
		
		if (comboCount != 5) return false;
		
		return true;
	}

}
