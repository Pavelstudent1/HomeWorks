package PokerCombo_ChainOfResp;


public class App {
	
	
	public static void main(final String[] args) {
		
		//Card(suit, rank)
		Card[] testRoyalFlush = {new Card(0,0), new Card(0,9), new Card(0,10), new Card(0,11), new Card(0,12)}; //[A, 10, J, Q, K]
		Card[] testStraightFlush = {new Card(0,1), new Card(0,2), new Card(0,3), new Card(0,4), new Card(0,5)};	//[2, 3, 4, 5, 6]
		Card[] testCaRe = {new Card(0,9), new Card(1,9), new Card(2,9), new Card(3,9), new Card(0,5)};			//[10, 10, 10, 10, 6]
		Card[] testFullHouse = {new Card(0,10), new Card(1,10), new Card(2,10), new Card(3,6), new Card(0,6)};	//[J, J, J, 7, 7]
		Card[] testFlush = {new Card(0,2), new Card(0,3), new Card(0,5), new Card(0,9), new Card(0,11)};		//[3, 4, 6, 10, Q]
		Card[] testStraight = {new Card(0,0), new Card(1,1), new Card(2,2), new Card(3,3), new Card(0,4)};		//[A, 2, 3, 4, 5]
		Card[] testThree = {new Card(0,11), new Card(3,11), new Card(1,11), new Card(0,6), new Card(1,10)};		//[Q, Q, Q, 7, J]
		Card[] testTwoPair = {new Card(0,12), new Card(3,12), new Card(1,8), new Card(0,8), new Card(1,0)};		//[K, K, 9, 9, A]
		Card[] testPair = {new Card(0,7), new Card(3,7), new Card(1,10), new Card(0,5), new Card(1,1)};			//[8, 8, J, 6, 2]
		
		ComboProcessor processor = new ComboProcessor();
		
		//анализаторы лучше добавлять в порядке убывания вероятности их выпада
		processor.addComboAnalyzer(new Pair());
		processor.addComboAnalyzer(new TwoPair());
		processor.addComboAnalyzer(new Three());
		processor.addComboAnalyzer(new Straight());
		processor.addComboAnalyzer(new Flush());
		processor.addComboAnalyzer(new FullHouse());
		processor.addComboAnalyzer(new Care());
		processor.addComboAnalyzer(new StraightFlush());
		processor.addComboAnalyzer(new RoyalFlush());
		
		
		processor.startAnalyzePile(testRoyalFlush);
	}
	
	
	
	
}
