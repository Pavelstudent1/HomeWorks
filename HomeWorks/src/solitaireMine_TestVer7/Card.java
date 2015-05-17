package solitaireMine_TestVer7;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Card {
	
	private Rectangle2D cardShape;	//отрисовываемый прямоугольник карты
	private Area cardRealShape;		//многоугольник, отражающий доступную для клика область карты,
									//как результат наложения на неё следующей в колоде карты
	private int x;
	private int y;
	
	final static int club = 3;
	final static int diamond = 2;
	final static int spade = 1;
	final static int heart = 0;
	
	final static int height = 70;
	final static int width = 50;
	
	final static int blue = 1;
	final static int red = 0;

	private static String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private static String[] SUITS = 	{"HEART", "SPADE", "DIAMOND", "CLUB"};
	private static String[] SUITS_UNI = {"\u2665","\u2660","\u2666","\u2663"}; 
	
	private boolean faceup;
	private int rank;	
	private int suit;

	private boolean clicked = false;
	private boolean grubbed = false;
	
	//конструктор карты с координатами по умолчанию (0, 0)
	Card(final int suitValue, final int rankValue) {
		suit = suitValue;
		rank = rankValue;
		faceup = false;
		
		x = y = 0;		
		cardShape = new Rectangle2D.Double(x, y, Card.width, Card.height);
		cardRealShape = new Area(cardShape);
	}
	
	//Конструктор карты с произвольными координатами
	Card(final int suitValue, final int rankValue, final int X, final int Y) {
		suit = suitValue;
		rank = rankValue;
		faceup = false;
		
		x = X;
		y = Y;
		
		cardShape = new Rectangle2D.Double(x, y, Card.width, Card.height);
		cardRealShape = new Area(cardShape);
	}
	
	//конструктор карты, отражающей пустую колоду
	//(конечно, по логике, такая карта должна принадлежать только классу CardPile и быть описана в нём)
	Card(){
		suit = rank = -1;
	}
	
	public boolean isCardOfEmptyPile(){
		return suit == -1 || rank == -1 ? true : false;
	}
	
	public void draw(final Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
//		cardShape.setFrame(x, y, Card.width, Card.height); //рассмотреть вариант переноса изменения координат Area сюда
		g2.setColor(clicked ? Color.cyan : Color.white);
		g2.fill(cardShape);
		g2.setColor(clicked ? Color.red : Color.black);
		g2.draw(cardShape);
		
		if (faceup){
			g2.setColor( suit == diamond || suit == heart ? Color.red : Color.blue);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			g2.drawString(RANKS[rank], (int)(x + Card.width * 0.09), (int)(y + Card.height * 0.25));
			g2.setFont(new Font("Times New Roman", Font.BOLD, 50));
			g2.drawString(SUITS_UNI[suit], (int)(x + Card.width * 0.18), (int)(y + Card.height * 0.75));
			
		}else{
			/**		Рубашка карты	 **/
			g2.setColor(Color.blue);
			//линии рубашки по горизонали
			g2.drawLine(x + 5, y + (int)(Card.height * 0.2), x + Card.width - 5, y + (int)(Card.height * 0.2));
			g2.drawLine(x + 5, y + (int)(Card.height * 0.5), x + Card.width - 5, y + (int)(Card.height * 0.5));
			g2.drawLine(x + 5, y + (int)(Card.height * 0.8), x + Card.width - 5, y + (int)(Card.height * 0.8));
			//линии рубашки по вертикали
			g2.drawLine(x + (int)(Card.width * 0.2), y + 5, x + (int)(Card.width * 0.2), y + Card.height - 5);
			g2.drawLine(x + (int)(Card.width * 0.5), y + 5, x + (int)(Card.width * 0.5), y + Card.height - 5);
			g2.drawLine(x + (int)(Card.width * 0.8), y + 5, x + (int)(Card.width * 0.8), y + Card.height - 5);
		}
	}
//************************************************************************************************************
/** Взятия/отпускания карт **/
	
	public boolean isGrubbed(){
		return grubbed;
	}
	
	public void setGrub(){
		grubbed = true;
	}
	
	public void resetGrub(){
		grubbed = false;
	}

	
//************************************************************************************************************
/** Пересечение карт **/
	public void intersectWith(Card card){
		cardRealShape.subtract(card.getCardRealShape());
	}
	
	
	public Area getCardRealShape(){
		return cardRealShape;
	}
	
	public Card cardRealShapeReset(){
		cardRealShape = new Area(cardShape);
		return this;
	}
	
//*****************************************************************
	/** Установка вверх/вниз лицом **/
	
	public Card setFaceUp(){
		faceup = true;
		return this;
	}
	
	public Card setFaceDown(){
		faceup = false;
		return this;
	}
	
	
//************************************************************************************************************
	public Card setCardLocation(final int X, final int Y){
		x = X;
		y = Y;
		cardShape.setFrame(X, Y, Card.width, Card.height);
		cardRealShape = new Area(cardShape);
		return this;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isClicked(){
		return clicked;
	}
	
	public void Click(){
		clicked = !clicked;
	}
	
	public Card ClickOn(){
		clicked = true;
		return this;
	}
	
	public Card ClickOff(){
		clicked = false;
		return this;
	}
	
	public boolean containCoord(final int x, final int y){
//		return isFaceUp() ? cardRealShape.contains(x, y) : false;
		return cardRealShape.contains(x,y);
	}

	public boolean isFaceUp() {
		return faceup;
	}

	public void flip() {
		faceup = !faceup;
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	final boolean isAce() {
		return getRank() == 0;
	}

	final boolean isKing() {
		return getRank() == 12;
	}
	
	final int getColor(){
		return suit == diamond || suit == heart ? 0 : 1;
	}
	
	@Override
	public String toString() {
		String s;
		s = (this.isCardOfEmptyPile() ? "Card of Empty Pile" : 
			(isFaceUp() ? RANKS[rank] + " of " + SUITS[suit] : "Hiden Card"));
		return s;
	}
	
}
