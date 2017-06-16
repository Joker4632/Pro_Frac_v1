package fractalator.main.surface.log;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ProcessInterface extends JFrame {

	public JScrollPane JsP;
	public JTextArea textarea_console;
	
	public ProcessInterface(){
		initializeGUI();
	}
	

	private void initializeGUI(){
		/*
		 * Default Operations
		 */
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Image icon = new ImageIcon("./src/IncludedGraphics/fractalator_icon.png").getImage();
		setIconImage(icon);
		setTitle("Fractalator - Log");
		setSize(550,300);
		/*
		 * Base Elements
		 */
		JsP = new JScrollPane();
		setContentPane(JsP);
		JsP.getVerticalScrollBar().setUnitIncrement(15);
		{
			textarea_console = new JTextArea("");
			textarea_console.setForeground(Color.GREEN);
			textarea_console.setBackground(Color.BLACK);
			textarea_console.setEditable(false);
			JsP.setViewportView(textarea_console);
		}
		
		//setContentPane(textarea_console);
		
	}


}
