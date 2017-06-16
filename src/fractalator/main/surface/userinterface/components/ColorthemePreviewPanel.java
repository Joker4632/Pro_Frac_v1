package fractalator.main.surface.userinterface.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import fractalator.converter.colors.Colortheme;

public class ColorthemePreviewPanel extends JPanel {
	
	Colortheme colortheme;
	
	public ColorthemePreviewPanel(Colortheme ct){
		colortheme = ct;
		repaint();
	}

	public void update(Colortheme ct){
		colortheme = ct;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i<this.getWidth();i++){
			g2d.setColor(colortheme.getColorOf((int)((i*255/this.getWidth()))));
			g2d.drawLine(i, 0, i, this.getHeight());
		}
	}

}
