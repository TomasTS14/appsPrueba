package main;

import javax.swing.SwingUtilities;

import gui.MainFrame;

public class app {

	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(()-> new MainFrame());

	}

}
