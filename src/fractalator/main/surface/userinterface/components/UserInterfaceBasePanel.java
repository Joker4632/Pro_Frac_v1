package fractalator.main.surface.userinterface.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import fractalator.main.surface.userinterface.UserInterfaceV2;

/**
 * BasePanel for UserInterface Class. Includes the Background Desgin.
 * 
 * @author Johannes Scharrer
 *
 */
public class UserInterfaceBasePanel extends JPanel {

	private static final int SCALE_DEFAULT = 1;
	UserInterfaceV2 UI;

	public UserInterfaceBasePanel(UserInterfaceV2 UI, boolean titlebar) {
		this.UI = UI;
		if(titlebar){
			this.setSize(UI.getWidth(), UI.getHeight()-50);
		}else{
			this.setSize(UI.getWidth(), UI.getHeight());
		}
		
	}

	@Override
	public void paint(Graphics g) {
		//Background
		g.setColor(new Color(183,183,183));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//Logo Background Panel
		g.setColor(new Color(183, 56, 61));
		g.fillRect(0, 0, this.getWidth(), 100);
		
		//Logo
		Image img = Toolkit.getDefaultToolkit().getImage("./src/IncludedGraphics/fractalator_title.png");
		g.drawImage(img, 10, 5, 242, 90, this);
		repaint();
		
		//BottomLine
		g.setColor(new Color(60,60,60));
		g.fillRect(0, this.getHeight()-60, this.getWidth(), 6);
	}
	

}


