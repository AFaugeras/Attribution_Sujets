package controllers;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import models.bean.Model;
import views.MainFrame;

/**
 * Lanceur et contr�leur principal de l'application.
 */
public class Launcher {


	/**
	 * Point d'entr�e de l'application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainCtrl(new Model(), new MainFrame());
			}
		});
	}
}