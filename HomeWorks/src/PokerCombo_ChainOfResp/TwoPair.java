package PokerCombo_ChainOfResp;

public class TwoPair extends AbstractBaseCombo {

	public TwoPair() {
		super("TwoPair");
	}

	@Override
	public boolean isRightComboType(final Card[] pile) {

		int comboCount = 0;
		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == 2) ++comboCount;
		}
		
		if (comboCount != 2) return false;
		
		return true;
	}

}
