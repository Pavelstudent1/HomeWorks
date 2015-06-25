package crosses_and_zeros;

public class GameImitation {
	
	//имитатор игры
	
	public static void main(String[] args) {
		
		Player p1 = Player.createPlayer();
		Player p2 = Player.createPlayer();
		int winnerSign = 0;
		
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller();
		
		p1.setController(controller);
		p2.setController(controller);
		
		model.addListener(controller);
		controller.setModel(model);
		controller.setView(view);
		
		//Imitation
		int turn = 0; //число шагов всех игроков
		
		while(true){ //в конечном коде, здесь будет некий цикл, ждущий тапа от пользователя
					 //или как и в Тетрисе, реальный контроллер будет взят из GDXа и дёргать Controller
			
			if (turn % 2 == 0){
				model.setCurrentPlayer(p1);
				p1.chooseWisely();
			}else{
				model.setCurrentPlayer(p2);
				p2.chooseWisely();
			}
			
			if (turn > 3){ //после 4-х шагов уже можно отслеживать победителя
				winnerSign = model.signOfWinner();
				if (winnerSign == 0) {
					turn++;
					continue;
				}
				else break;
			}
			
			if (turn == 5) break; //если за 6-ть шагов нет победителя
			
			turn++;
		}
		
		if (winnerSign == 0){
			System.out.println("No one win");
		}else{
			System.out.println("The winner is " + winnerSign); //пока не определяется, какой  именно игрок выйграл, лишь его знак
		}
		
		
		
		
		
	}
}
