package crosses_and_zeros;

public class Controller implements ModelListener{
	
	Model model;
	View view;
	
	
	
	public void onTap(int x, int y){
		
		int numberOfTail = view.onTap(x, y);
		if (numberOfTail != 0){
			model.tailTapped(numberOfTail);
		}
	}
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public void setView(View view){
		this.view = view;
	}

	@Override
	public void onChange(GameBoard gb) {
		view.draw(gb);
	}
	
}
