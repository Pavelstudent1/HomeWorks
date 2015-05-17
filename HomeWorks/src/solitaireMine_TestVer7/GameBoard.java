package solitaireMine_TestVer7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.JComponent;

public class GameBoard extends JComponent {
	
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 500;
	
	private LinkedList<CardPile> Allpiles;
	
	public boolean wasClick = false;
	public boolean GameIsDone = false;
	
	public Card flyCard;
	public CardPile flyCardPile;
	public CardPile sourcePile;
	public Card tappedCard;
	
	@Override
	public Dimension getPreferredSize()
	{ return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
	
	public GameBoard()
	{
		Allpiles = new LinkedList<CardPile>();
		
		DeckPile deckpile = new DeckPile(5, 5, null);
		DiscardPile discardpile = new DiscardPile(60, 5);
		deckpile.linkWithDiscardPile(discardpile);
		
		Allpiles.add(deckpile);
		Allpiles.add(discardpile);
		
		int suitPileDistanse = 15;
		for(int i = 1,
				stX = 200,
				stY = 5; i <= 4; i++, stX += Card.width + suitPileDistanse){
			Allpiles.add(new SuitPile(stX, stY));
		}
		
		int tablePileDistanse = 15;
		for(int i = 1, 
				stX = 5,
				stY = Card.height + 20; i <= 7; i++, stX += Card.width + tablePileDistanse){
			Allpiles.add(new TablePile(stX, stY, i, deckpile).setShiftXY(0, 30));
		}
		
		addMouseListener(new MouseButtonListener());
		addMouseMotionListener(new MouseDragListener());
		
	}
	
	
	public void isGameDone(){
		
		int winCounter = 0;
		
		for(int i = 2; i <= 5; i++){
			if (Allpiles.get(i).getTopCard().isKing()) ++winCounter;
		}
		
		if (winCounter == 4) GameIsDone = true;
	}
	

	@Override
	protected void paintComponent(final Graphics g) {
		
		for (CardPile cardPile : Allpiles) {
			cardPile.draw(g);
			if (flyCard != null) flyCard.draw(g);
			if (flyCardPile != null) flyCardPile.draw(g);
		}
		
		
		if (GameIsDone){
			g.setColor(Color.RED);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString("Congratulation!", (int)(getX() + DEFAULT_WIDTH * 0.3), 
											getY() + DEFAULT_HEIGHT / 2);
			g.drawString("You is winner!", (int)(getX() + DEFAULT_WIDTH * 0.32), 
											(int)(getY() + DEFAULT_HEIGHT * 0.6));
		}
	}
	
	
	private class MouseButtonListener extends MouseAdapter{
		
		@Override
		public void mouseClicked(final MouseEvent e) {
			if (e.getClickCount() >= 2){
				for (CardPile cp : Allpiles) {
					Card c = cp.contain(e.getX(), e.getY());
					if (c != null){
						System.out.println("Click on " + c);
						return;
					}
				}

			}
			
		repaint();
		}
		
//		private class MouseButtonListener extends MouseAdapter{
//			
//			@Override
//			public void mouseClicked(final MouseEvent e) {
//				if (e.getClickCount() >= 2){
//					for (CardPile cp : Allpiles) {
//						Card c = cp.contain(e.getX(), e.getY());
//						if (c != null && !c.isCardOfEmptyPile()){
//							
//							System.out.println("Clicked!");
//							for (CardPile tcp : Allpiles) {
//								if (tcp.canTakeCard(c)){
//									System.out.println("Card added!");
//									tcp.addCard(cp.popTop());
//									repaint();
//									return;
//								}
//							}
////							System.out.println("Click on " + c);
//							System.out.println("Nowhere to add!");
//							repaint();
//							return;
//						}
//					}
//
//				}
//				
//			repaint();
//			}
		
		@Override
		public void mousePressed(final MouseEvent e) {
			
			System.out.println("Pressed");
			
			for (CardPile cp : Allpiles) {
				Card card = cp.contain(e.getX(), e.getY());
				if (card != null){
					System.out.println("Tap on " + card);
					cp.tapOnCard(card);
					repaint();
					return;
				}
			}
		
		}
		
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
			//если нечего класть в колоды, то и дальнейшие вычисления ни к чему
			if (flyCard == null && flyCardPile == null) return;
			
			System.out.println("Release");
			for (CardPile cp : Allpiles) {
				Card c = cp.contain(e.getX(), e.getY());
					if (c != null){

						if (flyCard != null && cp.canTakeCard(flyCard)){
							cp.addCard(flyCard);
							System.out.println("Card Added!");
							flyCard = null;
							
							isGameDone();
							
							repaint();
							return;
						}
						if (flyCardPile != null && cp.canTakePile(flyCardPile)){
							while(!flyCardPile.isEmpty()){
								cp.addCard(flyCardPile.popHeadCard());
							}
							flyCardPile = null;
							repaint();
							return;
							
						}else break;
					}
			}
			
			System.out.println("Card/Cards not added! Return to homepile");
			
			if (flyCard != null || flyCardPile != null){
				sourcePile.takeBackHomeAdv(flyCard != null ? flyCard : flyCardPile);
			}
			
//			if (flyCard != null) sourcePile.takeBackHome(flyCard);
//			else sourcePile.takeBackHome(flyCardPile);
			flyCard = null;
			flyCardPile = null;
			
			repaint();
		}
	}
	
	private class MouseDragListener extends MouseMotionAdapter{
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			if (flyCard != null){
				flyCard.setCardLocation(e.getX() - Card.width / 2, e.getY() - Card.height / 2);
				repaint();
				return;
			}
			if (flyCardPile != null){
				flyCardPile.setPileCoord(e.getX() - Card.width / 2, e.getY() - Card.height / 3);
				repaint();
				return;
			}
		
			
		for (CardPile cp : Allpiles) {
				Card c = cp.contain(e.getX(), e.getY());
				if (c != null && c.isFaceUp() && !c.isCardOfEmptyPile() && cp.canGiveCard()){
					
					System.out.println("Dragging...");
					sourcePile = cp;
					if (c == cp.getTopCard()){
						flyCard = cp.popTop();
						return;
					}else{
						flyCardPile = ((TablePile)cp).popCardsFrom(c);
						return;
					}
					
					
				}
			
		}
			
			
		repaint();
		}
		
	}
}