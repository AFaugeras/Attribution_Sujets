package controllers;

import javax.swing.UIManager;

import views.MainFrame;

public class Launcher {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e){
			
		}
		
		new MainFrame();
	}

}
