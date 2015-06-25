package PokerCombo_ChainOfResp;

public abstract class AbstractBaseCombo implements ComboType{
	
	ComboType next;
	String comboName;
	
	public AbstractBaseCombo(String comboName) {
		this.comboName = comboName;
	}
	
	@Override
	public void setNext(final ComboType pokerComboType) {
		
		if (next != null){
			next.setNext(pokerComboType);
		}
		else{
			next = pokerComboType;
		}
	}

	@Override
	public void analyzePile(final Card[] pile) {

		if (!isRightComboType(pile)){
			System.out.println("This isn't " + comboName);
			
			if (next != null) {
				next.analyzePile(pile);
			}
			else {
				System.out.println("This isn't a combo at all!");
			}
			return;
		}
		
		System.out.println("----- This is " + comboName + " -----");
		
	}
	
	
	
	public static boolean isCommonSuit(final Card[] pile){
		int commonSuit = pile[0].getSuit();
		
		for (Card card : pile) {
			if (card.getSuit() != commonSuit) return false; //выход, если хоть одна карта другой масти
		}
		
		return true;
	}
	
	
	
	//прочёсывает колоду и собирает частоту нахождений карт в колоде
	public static int[] getRankMask(final Card[] pile) {
		
		int[] tmp = new int[13];
		
		for (Card card : pile) {
			++tmp[card.getRank()]; //установка единицы в соответствие с рангом карты
		}
		
		return tmp;
	}
	
	//смотрим, последовательны ли ранги карт
	public boolean isRankStraight(final Card[] pile){
		
		int needCount = 5,
			resultCount = 0;
		int[] comboMask = getRankMask(pile);
			
		for(int i = 0; i < comboMask.length; i++){
			if (comboMask[i] == 1){  //как только натыкаемся на карту, 
									 //смотрим дальнейшую последовательность
				int stop = (i + 5 > comboMask.length ? 0 : 5);	//если просмотр 5-ти карт вперёд выйдет за массив,
																//то и искать ничего не нужно
				while(stop-- != 0){
					resultCount += (comboMask[i++] == 1 ? 1 : 0);
				}
				break;
			}
		}
			
		if (needCount != resultCount) return false;
		
		return true;
	}
	
	
	public abstract boolean isRightComboType(final Card[] pile);


	
	
	
}
