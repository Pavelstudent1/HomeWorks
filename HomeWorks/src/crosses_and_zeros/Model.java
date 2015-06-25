package crosses_and_zeros;

import java.util.ArrayList;
import java.util.List;

//Модель, по своей сути, лишь раздаёт приказы
public class Model {
	
	private GameBoard gameBoard;
	private Logic logic;
	private Player currentPlayer;
	
	public Model() {
		gameBoard = new GameBoard();
		logic = new Logic(gameBoard);
	}

	void tailTapped(int numberOfTail) {
		if (logic.tailTapped(numberOfTail, currentPlayer.getSign())){
			fireChangedEvent();
		}
	}
	
	
	public void setCurrentPlayer(Player p){
		currentPlayer = p;
	}
	
	
	
	List<ModelListener> listeners = new ArrayList<ModelListener>();
	
	public void addListener(final ModelListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(final ModelListener listener){
		listeners.remove(listener);
	}
	
	void fireChangedEvent(){ //выстреливаем событие, что модель изменилась
		for(final ModelListener listeners : listeners){
			listeners.onChange(gameBoard);
		}
	}

	public int signOfWinner() {
		
		int winner = logic.checkWinner();
		
		return winner;
	}
	
}
