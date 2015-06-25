package crosses_and_zeros;

//Игровая доска это просто игровая доска
public class GameBoard {

	private int[][] board = { { 0, 0, 0 }, 
							  { 0, 0, 0 }, 
							  { 0, 0, 0 } };

	public final int[][] getData() {
		return board;
	}

	// проверяем, занята ли клетка
	public boolean isTailEmpty(int numberOfTail) {

		int counter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				++counter;
				if (counter == numberOfTail && board[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public void setTail(int numberOfTail, int sign) {

		int counter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				++counter;
				if (counter == numberOfTail) {
					board[i][j] = sign;
					return;
				}
			}
		}
	}

}
