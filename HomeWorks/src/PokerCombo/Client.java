package PokerCombo;

import java.util.Random;

public class Client {

	public static void main(final String[] args) {
		
		Card[] cardPile = generatFullPile(3);		//генерируем полную колоду с перемешиванием по Фишеру-Йетсу
		Card[] comboPile = getComboPile(cardPile);	//забираем из колоду 5-ть карт
		
		for (Card card : comboPile) {
			System.out.print(card + " | ");
		}
		System.out.println();
		System.out.println(ComboHelper.comboName(comboPile));	//узнаем колоду
	}
	
	
	private static char[] pokerCombo(final Card[] comboPile) {
		
		
		return null;
	}


	private static Card[] getComboPile(final Card[] srcPile) {
		
		Card[] tmpPile = new Card[5];
		Random random = new Random();
		
		for(int i = 0; i < 5; i++){
			tmpPile[i] = srcPile[random.nextInt(srcPile.length)];
		}
		
		return tmpPile;
	}


	public static Card[] generatFullPile(final int numOfshuffle){
		
		int count = 0;
		Card[] tempPile = new Card[52];
		for(int suit = 0; suit < 4; suit++){	//создаём массив карт
			for(int rank = 0; rank <= 12; rank++){
				tempPile[count++] = new Card(suit, rank);
			}
		}
		
		
		for(int t = 1; t <= numOfshuffle; t++){	//количество проходов перемешиваний
			
			Random random = new Random();
			for(int i = 0; i < count; i++){		//перемешиваем по Фишеру-Йетсу
				int r = random.nextInt(count);
				Card tmp = tempPile[i];
				tempPile[i] = tempPile[r];
				tempPile[r] = tmp;
			}
		}
		
		return tempPile;
	}
	
	
}
