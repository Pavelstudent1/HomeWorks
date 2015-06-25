package crosses_and_zeros;

public class View {
	
	
	int[][] tails = { {1, 0, 0}, {2, 0, 1}, {3, 0, 2}, //как бы клетки: первое число - ID клетки, последующие - её координаты
					  {4, 1, 0}, {5, 1, 1}, {6, 1, 2},
					  {7, 2, 0}, {8, 2, 1}, {9, 2, 2}};

	//проверка входных координат на попадание в незанятую клетку.
	//Возвращает ID клетки
	public int onTap(int x, int y) {
		
		for (int[] is : tails) {
			if (is[1] == x && is[2] == y){
				return is[0];
			}
		}
		
		return 0;
	}

	public void draw(GameBoard gb) {
		
		int[][] data = gb.getData();
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("-----------------");
	}
	
	
	
}
