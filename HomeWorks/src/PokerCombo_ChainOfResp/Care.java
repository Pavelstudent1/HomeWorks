package PokerCombo_ChainOfResp;

public class Care extends AbstractBaseCombo {

	public Care() {
		super("Care");
	}

	@Override
	public boolean isRightComboType(Card[] pile) {

		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == 4) return true;
		}

		return false;
	}

}
