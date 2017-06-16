package fractalator.main;

import fractalator.main.surface.userinterface.UserInterfaceV1;

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
public class MainV1 {

	public static void main(String[] args) {
		/*
		 * Launch User Interface
		 */
		UserInterfaceV1 UI = new UserInterfaceV1();
		UI.setLocationRelativeTo(null);
		UI.setVisible(true);
	}

}
