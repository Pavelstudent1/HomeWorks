package crosses_and_zeros;


//Логика отрабатывает запросы модели и взаимодействует лишь с GameBoard
public class Logic {
	
	private GameBoard gb;

	public Logic(GameBoard gb) {
		this.gb = gb;
	}

	public boolean tailTapped(int numberOfTail, int sign) {
		if (!gb.isTailEmpty(numberOfTail)){	//если клетка НЕ занята
			gb.setTail(numberOfTail, sign);		//отмечаем её знаком текущего игрока
			return true;
		}
		
		return false;
	}

	public int checkWinner() { //пока, проверка не учитывает, что оба игрока сговорятся и не будут друг другу мешать

		int[][] data = gb.getData();
		int sign = 0;
		
		//Сначала проверим все строки
		for (int[] is : data) {
			if (is[0] == is[1] && is[1] == is[2]){
				return is[0];
			}
		}
		
		//Затем столбцы
		for (int i = 0; i < data.length; i++) {
			if (data[0][i] == data[1][i] && data[1][i] == data[2][i]){
				return data[0][1];
			}
		}
		
		//И наконец обе диагонали
		if (data[0][0] == data[1][1] && data[1][1] == data[2][2]){
			return data[0][0]; 
		}
		
		if (data[0][2] == data[1][1] && data[1][1] == data[2][0]){
			return data[0][2]; 
		}
	
		return 0;
	}
	
	
	
	
	
}
