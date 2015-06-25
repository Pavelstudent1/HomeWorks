package PokerCombo_ChainOfResp;

public interface ComboType {
	
	public void setNext(ComboType pokerComboType);
	public void analyzePile(Card[] pile);
	
}
