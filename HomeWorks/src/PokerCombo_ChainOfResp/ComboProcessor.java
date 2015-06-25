package PokerCombo_ChainOfResp;

public class ComboProcessor {

	private ComboType head;
	
	public void addComboAnalyzer(ComboType pokerComboType){
		
		if (head != null){
			head.setNext(pokerComboType);
		}else{
			head = pokerComboType;
		}
		
	}
	
	public void startAnalyzePile(Card[] pile){
		if (head == null){
			System.out.println("No available analyzers!");
		}
		else{
			head.analyzePile(pile);
		}
		
		
	}
	
}
