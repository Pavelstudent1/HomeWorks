package crosses_and_zeros;

import java.util.Scanner;

public class Player {
	
	private int sign = 0;				//знак текущего игрока: 1 - нули, 2 - крестики. Или наоборот.
	static int numberOfPlayers = 0;
	Controller control;
	
	public static Player createPlayer(){ //ограничиваем количество игроков
		if (numberOfPlayers >= 2){
			System.out.println("Maximum of player was reached! Null will be returned!");
			return null;
		}
		
		numberOfPlayers++;
		if (numberOfPlayers == 1){
			return new Player(1);
		}else{
			return new Player(2);
		}
	}
	
	private Player(int sign) {
		this.sign = sign;
	}

	public int getSign() {
		return sign;
	}
	
	//Т.к. нужно как-то дёрнуть контроллер 
	public void setController(Controller controller){
		control = controller;
	}

	public void chooseWisely() {
		Scanner scanner = new Scanner(System.in);
		String[] choose;
		
		System.out.print("Turn of Player" + sign + ": ");
		choose = scanner.nextLine().split(","); //ввод вида "x,y" по отношению к board в GameBoard
		control.onTap(Integer.valueOf(choose[0]), Integer.valueOf(choose[1]));
	}
	
	
	
	
}
