package solitaireMine_TestVer7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public abstract class CardPile {

	
	
	private int x;
	private int y;
	
	private int shiftX;
	private int shiftY;
	
	private int pileWidth = Card.width;
	private int pileHeight = Card.height;
	
	private Rectangle2D emptyCardPileShape;
	
	private LinkedList<Card> pile;
	
	
	//*******************************************************************************
	/** Конструкторы **/
	protected CardPile() {
		
		this.x = 0;
		this.y = 0;
		
		shiftX = 0;
		shiftY = 0;
		
		emptyCardPileShape = new Rectangle2D.Double(x, y, pileWidth, pileHeight);
		pile = new LinkedList<Card>();
	}
	
	public CardPile(final int x, final int y) {
		
		this.x = x;
		this.y = y;
		
		shiftX = 0;
		shiftY = 0;
		
		emptyCardPileShape = new Rectangle2D.Double(x, y, pileWidth, pileHeight);
		pile = new LinkedList<Card>();
	}
	
	
	//*******************************************************************************
	/** Запрос/установка координат стопки **/
	public CardPile setShiftXY(final int sh_x, final int sh_y){
		shiftX = sh_x;
		shiftY = sh_y;
		return this;
	}
	
	public void setPileCoord(final int X, final int Y){
		x = X;												
		y = Y;
	}
	
	public int getPileX(){
		return x;
	}

	public int getPileY(){
		return y;
	}
	
	
	//*******************************************************************************
	/** Запрос карты по её смещению или смещение карты по самой карте **/
	public Card getCard(final int num){
		//сначала проверяем не пуста ли колода, затем допустимость значения num
		return pile.isEmpty() ? null : (num > pile.size() - 1 ? null : pile.get(num));
	}
	
	public int getIndexOf(Card c){
		return pile.indexOf(c);
	}

	
	//********************************************************************************
	/**		
	 	Отрисовка стопки и вычисление перекрытий
	 	
	 		В колоде по умолчанию, карты расположены друг под дружкой,
	   поэтому нет смысла рисовать все: переворачивается лицом к верху и
	   отрисовывается лишь верхняя карта.
	   		Если в колоде нет - карт рисуется контур прямоугольника с зелёным кругом
	**/
	public void draw(final Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		
		if (pile.isEmpty()){
			g2.setColor(Color.black);
			g2.drawRect(x, y, Card.width, Card.height);
			g2.setColor(Color.green);
			g2.fillOval(x + 5, y + Card.height / 4, Card.width - 10,  Card.width - 10);
			g2.setColor(Color.black);
			g2.drawOval(x + 5, y + Card.height / 4, Card.width - 10,  Card.width - 10);
			return;
		}
		
		if (shiftX == 0 && shiftY == 0){
			pile.getLast().setCardLocation(x, y).draw(g2);	
		}else{
			
		int adderX = 0;	//два сумматора, предоставляющие на каждой итерации вывода карты 
		int adderY = 0;	//значение смещения её координат, в зависимости от введёных значений
		
		for(int i = 0; i < pile.size(); i++){
			
			if (!pile.get(i).isClicked()){
				pile.get(i).setCardLocation(x + adderX, y + adderY);
			}
			
			//если карт больше, чем одна - производится вычисление пересечений
			if (i > 0){
				pile.get(i - 1).intersectWith(pile.get(i));
			}
			
			pile.get(i).draw(g);
			
			adderX += shiftX;
			adderY += shiftY;
			}
		
		}	
	}	
	
//*********************************************************************************************
	/** Методы добавления/изъятия кард из колоды**/
	
	/** ВАЖНО: методы add не предполагают проверку того, можно ли по правилам к данной колоде добавить карту(ы) **/
	/**	Методы add предполагают, что добавляемая ими карта изъята любым из методов
	pop, которые предусматривают восстановление realShape карты.
	А добавление в начало или конец или ещё куда в колоду не вызовет ошибок,
	т.к. realShape пересчитывается в draw **/

	public Card popTop(){
			
			Card out;
			if (pile.isEmpty()){
				System.out.println("Pile is Empty! Can't pop!");
				return null;
			}
				if (pile.size() == 1){
					out = pile.removeLast().cardRealShapeReset();
				}else {
					out = pile.removeLast();
					pile.getLast().cardRealShapeReset();
				}
			
			return out;
		}
	

	public boolean addCard(final Card card){
	
		pile.add(card);	//в конце, карта добавляется в колоду и становится новым top
		
		return true;
	}
	
	public boolean addCardToHead(Card c){

		pile.addFirst(c);
		
		return true;
	}
	
	public Card popHeadCard(){
		
		Card c = this.pile.removeFirst().cardRealShapeReset();
		return c;
	}

	
//*****************************************************************************
/**	Выделение нескольких карт сразу **/
	
	public CardPile clicksFrom(Card card){
		if (!card.isFaceUp()){
			System.out.println("Can't click this card!");
			return this;
		}
		int i = pile.indexOf(card);	//индекст возвращает именно индекс(смещение) карты в списке, начиная с 0 !
		do{
			pile.get(i++).ClickOn();
		}while(i < pile.size());
		
		return this;
	}
	
	public CardPile unclickAll(){
		for (Card card : pile) {
			card.Click();
		}
		
		return this;
	}
	
	
//*****************************************************************************	
	
	public boolean isEmpty(){
		return pile.isEmpty();
	}
	
	public boolean isHead(Card c){
		return c == pile.getFirst();
	}
	
	public boolean isTop(Card c){
		return c == pile.getLast();
	}
	
	
	//*****************************************************************************	
	/**Проверка принятия/изъятия карты в/из колоды **/
	public abstract boolean canTakeCard(Card c);
	
	public boolean canTakePile(CardPile cp){ return false; }
	
	public abstract boolean canGiveCard();
	
	
	
	//*****************************************************************************	
	/** Проверка был ли тык мышкой по какой-либо карте в данной стопке**/
	
	public Card contain(final int x, final int y){
		
		if (isEmpty()){
			if (emptyCardPileShape.contains(x,y)) return new Card();
			else return null;
		}
		
		if (shiftX == 0 && shiftY == 0){
			if (getTopCard().containCoord(x, y)) return getTopCard();
			else return null;
		}
		for (Card card : pile) {
			if (card.containCoord(x, y)) return card;
		}
		return null;
	}
	
	//*****************************************************************************	
	/** Различные геттеры **/
	
	public int getLength(){
		return pile.size();
	}
	
	public Card getTopCard(){
		return pile.isEmpty() ? new Card() : pile.getLast();
	}
	
	public Card getBottomCard(){
		return pile.isEmpty() ? new Card() : pile.getFirst();
	}
	
	public int getShiftX(){
		return shiftX;
	}
	
	public int getShiftY(){
		return shiftY;
	}
	
	
	
	
	public void takeBackHomeAdv(Object obj){
		
			if (obj instanceof Card){
				this.addCard((Card) obj);				
			}
			else{
				while(!((CardPile) obj).isEmpty()){
					this.addCard(((CardPile) obj).popHeadCard());
				}
			}	
	}
	
	
	public void tapOnCard(Card card){
		if (card.isCardOfEmptyPile()) {
			System.out.println("Pile is empty!");
		}
		else if (!card.isFaceUp() && isTop(card)) card.flip();
	}
	
}
