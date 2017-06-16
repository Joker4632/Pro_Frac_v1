package fractalator.main.surface.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fractalator.main.output.OutputManager;
import fractalator.main.surface.components.TitlePanel;
import fractalator.main.surface.userinterface.components.UserInterfaceBasePanel;
import fractalator.processing.mandelbrot.MandelbrotProcessor;

public class UserInterfaceV2 extends Abstract_UserInterface {
	/*
	 * General Fields
	 */
	
	/*
	 * Base Elements
	 */
	private JPanel BasePanel;
	private TitlePanel TitlePanel;
	private UserInterfaceBasePanel UserInterfacePanel;
	/*
	 * Action Elements
	 */
	JButton button_close;
	JButton button_minimize;
	/*
	 * Non-Action Elements
	 */
	
	/*
	 * Strings
	 */
	
	public UserInterfaceV2() {
		adjustSettings();
		initializeGUI();
	}
	
	@Override
	void adjustSettings(){
	}
	
	@Override
	void initializeGUI(){
		/*
		 * Default Operations
		 */
		setBounds(100, 100, 1000, 750);
		setUndecorated(true);
		
		/*
		 * Base Elements
		 */
		BasePanel = new JPanel();
		BasePanel.setLayout(null);
		setContentPane(BasePanel);
		
		TitlePanel = new TitlePanel("Userinterface", this, "MINIMIZEANDCLOSE", 50, new Color(64,64,64),new Color(255,255,255),new Font("Calibri",0,26),true,null, null);
		TitlePanel.setLocation(0, 0);
		TitlePanel.setLayout(null);
		BasePanel.add(TitlePanel);
		
		UserInterfacePanel = new UserInterfaceBasePanel(this,true);
		UserInterfacePanel.setLayout(null);
		UserInterfacePanel.setLocation(0, 50);
		BasePanel.add(UserInterfacePanel);
		/*
		 * Action Elements
		 */

		/*
		 * Non-Action Elements
		 */
		
	}

	@Override
	public void updateMPL(MandelbrotProcessor MP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void onExit() {
		// TODO Auto-generated method stub
		
	}

}
