package PokerCombo_ChainOfResp;

public class Pair extends AbstractBaseCombo {

	public Pair() {
		super("Pair");
	}

	@Override
	public boolean isRightComboType(final Card[] pile) {

	boolean wasThree = false;
	int comboCount = 0;
	int[] comboMask = getRankMask(pile);
	
	for (int i : comboMask) {
		if (i == 2) ++comboCount; //если пар будет две, то не пройдёт конечное условие
		if (i == 3) wasThree = true; //если колода является ФуллХаусом
	}
		
	if (comboCount == 1 && wasThree == false) return true;
	
		return false;
	}

}
