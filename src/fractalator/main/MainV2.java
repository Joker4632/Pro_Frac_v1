package fractalator.main;

import fractalator.main.surface.userinterface.UserInterfaceV2;

/**
 * Main Class for the Fractalator Programm.
 * 
 * IMPORTANT INFORMATION to read the Code!
 * 
 * FIRST I use Interface as term for a (interactive) graphical Surface, mostly
 * represented by JFrames. If i created an Java Interface Class, they will be
 * completely written in capital letters.
 * 
 * SECOND At the top of bigger classes will be an overview of the parts the class
 * is sectioned in
 * 
 * @author Johannes Scharrer
 *
 */
public class MainV2 {

	public static void main(String[] args) {
		/*
		 * Launch User Interface
		 */
		UserInterfaceV2 UI = new UserInterfaceV2();
		UI.setLocationRelativeTo(null);
		UI.setVisible(true);
		

	}

}
