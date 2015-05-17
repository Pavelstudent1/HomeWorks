package PokerCombo;

import java.util.Arrays;

public class ComboHelper {
	
	public static boolean isRoyalFlush(final Card[] pile){
		
		if (!isCommonSuit(pile)) return false;
		
		//дошли сюда, значит все карты одной масти

		int[] winMask = {1,0,0,0,0,0,0,0,0,1,1,1,1}; //маска Роял Флеша, где 0-й элемент - Туз, 1-й - Двойка и т.д.
		int[] comboMask = getRankMask(pile);
		
		if (!Arrays.equals(winMask, comboMask)) return false;
		
		return true;
	}
	
	
	public static boolean isStraightFlush(final Card[] pile){
		
		if (isRoyalFlush(pile)) return false; //по правмлам покера, комбинация [10,J,Q,K,A] всегда является роял-флешем, 
											  //хоть и подпадает под условия стрит флеша
		
		if (!isCommonSuit(pile)) return false; //если масти не совпадают
		
		int winCount = 5,
			comboCount = 0;
		int[] comboMask = getRankMask(pile);
		
		for(int i = 0; i < comboMask.length; i++){
			if (comboMask[i] == 1){ 		//если замечена единственная карта
				int stop = (i + 5 > comboMask.length ? 0 : 5);	//если карта найдена, но просмотр 5-ти карт вперёд выйдет за массив,
																//то и искать ничего не нужно
				while(stop-- != 0){
					comboCount += (comboMask[i++] == 1 ? 1 : 0);
				}
				break;
			}
		}
		
		if (winCount != comboCount) return false;
		
		
		return true;
	}
	
	
	public static boolean isCare(final Card[] pile){
		
		int winNumber = 4;
		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == 4) return true;
		}
		
		return false;
	}
	
	
	
	
	protected static boolean isCommonSuit(final Card[] pile){
		int commonSuit = pile[0].getSuit();
		
		for (Card card : pile) {
			if (card.getSuit() != commonSuit) return false; //выход, если хоть одна карта другой масти
		}
		
		return true;
	}
	
	//прочёсывает колоду и собирает частоту нахождений карт в колоде
	protected static int[] getRankMask(final Card[] pile) {
		
		int[] tmp = new int[13];
		
		for (Card card : pile) {
			++tmp[card.getRank()]; //установка единицы в соответствие с рангом карты
		}
		
		return tmp;
	}

	public static boolean isFullHouse(final Card[] pile) {
		
		int winNumberOne = 3, winNumberTwo = 2, comboCount = 0;
		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == winNumberOne) comboCount += winNumberOne;
			if (i == winNumberTwo) comboCount += winNumberTwo;
		}
		
		if (comboCount != 5) return false;
		
		return true;
	}
	
	public static boolean isFlush(final Card[] pile){
		//условие одинаковости всех карт по масти подходит как к Роял-Флешу, так и к Стрит-Флешу
		if (isRoyalFlush(pile)) return false;
		if (isStraightFlush(pile)) return false;
		
		if (!isCommonSuit(pile)) return false;
		
		return true;
	}

	public static boolean isStraight(final Card[] pile) {
		
		//Простой стрит не зависит от одинаковости всех мастей,
		//а потому Стрит-Флешем или Роял-Флешем быть не может
		if (isCommonSuit(pile)) return false;
		
		int winCount = 5,
			comboCount = 0;
		int[] comboMask = getRankMask(pile);
			
		
		for(int i = 0; i < comboMask.length; i++){
			if (comboMask[i] == 1){ 		//если замечена единственная карта
				int stop = (i + 5 > comboMask.length ? 0 : 5);	//если карта найдена, но просмотр 5-ти карт вперёд выйдет за массив,
																//то и искать ничего не нужно
				while(stop-- != 0){
					comboCount += (comboMask[i++] == 1 ? 1 : 0);
				}
				break;
			}
		}
		
		if (winCount != comboCount) return false;
		
		return true;
	}


	public static boolean isThree(final Card[] pile) {
		
		//Фулл-Хаус тоже имеет три карты одинакового ранга
		if (isFullHouse(pile)) return false;
		
		int winNumber = 3;
		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == winNumber) return true;
		}
		
		return false;
	}
	
	public static boolean isTwoPair(final Card[] pile) {
		
		int winCounter = 2, comboCounter = 0;
		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == 2) comboCounter++;
		}
		
		if (comboCounter != winCounter) return false;
		
		return true;
	}
	
	public static boolean isPair(final Card[] pile) {
		
		//условие одной пары карт пересекается с условием Фулл-Хауса
		if (isFullHouse(pile)) return false;
		
		int comboCounter = 0; //счётчик защитит от комбы Две Пары
		int[] comboMask = getRankMask(pile);
		
		for (int i : comboMask) {
			if (i == 2) comboCounter += i;
		}
		
		if (comboCounter != 2) return false;
		
		return true;
	}
	
	public static String comboName(final Card[] pile){
		
		//методы вызываются по вероятности их выпадения. Первый - самый вероятный
		
		if (isPair(pile)) return "Pair";
		if (isTwoPair(pile)) return "TwoPair";
		if (isThree(pile)) return "Three";
		if (isStraight(pile)) return "Straight";
		if (isFlush(pile)) return "Flush";
		if (isFullHouse(pile)) return "Full House";
		if (isCare(pile)) return "Care";
		if (isStraightFlush(pile)) return "Straight Flush";
		if (isRoyalFlush(pile)) return "Royal Flush";
		
		return "Not a combo at all!";
	}
	
	
}
