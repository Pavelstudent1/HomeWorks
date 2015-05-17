package solitaireMine_TestVer7;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	
	public MainFrame(final String title) {
		setTitle(title);
		add(new GameBoard());
		pack();
	}
	
	
	
	
}
