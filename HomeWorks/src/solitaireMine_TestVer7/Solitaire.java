package solitaireMine_TestVer7;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Solitaire {
	
	public static void main(final String[] args) 
	{
	EventQueue.invokeLater(new Runnable()
	    {
	       @Override
		public void run()
	       {
	          MainFrame frame = new MainFrame("Solitare v7");
	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	          frame.setVisible(true);
	       }
	    });

	}
	
}
