package fractalator.main.surface.graphicalinterface.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class FractalPanel extends JPanel {

	BufferedImage image;
	int xPosImg, yPosImg;
	int xOff, yOff;
	int wPanel, hPanel;
	double zoom=1;

	boolean bMouseOverImage = false;

	/**
	 * Create the panel.
	 */
	public FractalPanel(BufferedImage image) {
		this.image = image;
		xPosImg = 0;
		yPosImg = 0;
		xOff = 0;
		yOff = 0;
		
		Move bw = new Move();
		addMouseMotionListener(bw);
		addMouseListener(bw);
	}

	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(image, xPosImg, yPosImg,(int) (image.getWidth()*zoom),(int)(image.getHeight()*zoom), this);
	}
	
	public void updateSize(){
		wPanel=this.getWidth();
		hPanel=this.getHeight();
	}
	
	public void zoomIn(){
		zoom+=0.02;
		repaint();
	}
	
	public void zoomOut(){
		zoom-=0.02;
		repaint();
	}
	

	class Move extends MouseMotionAdapter implements MouseListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (bMouseOverImage) { 
				int xTemp = e.getX() - xOff;
				int yTemp = e.getY() - yOff;
				if(xTemp <0 && xTemp > (wPanel/2-(image.getWidth()*zoom))){
					xPosImg = xTemp;
				}
				if(yTemp <0 && yTemp > (hPanel/2-(image.getHeight()*zoom))){
					yPosImg = yTemp;
				}
				repaint();
			}
		}

		public void mousePressed(MouseEvent e) {
			xOff = e.getX() - xPosImg;
			yOff = e.getY() - yPosImg;
		}

		@Override
		public void mouseMoved(MouseEvent me) {
			Rectangle r = new Rectangle(xPosImg, yPosImg, image.getWidth(null), image.getHeight(null));
			if (r.contains(new Point(me.getX(), me.getY()))) {
				bMouseOverImage = true;
				getParent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				bMouseOverImage = false;
				getParent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		public void mouseClicked(MouseEvent me) {
			// throw new UnsupportedOperationException("Not supported yet.");
		}

		public void mouseReleased(MouseEvent me) {
			// throw new UnsupportedOperationException("Not supported yet.");
		}

		public void mouseEntered(MouseEvent me) {
			// throw new UnsupportedOperationException("Not supported yet.");
		}

		public void mouseExited(MouseEvent me) {
			// throw new UnsupportedOperationException("Not supported yet.");
		}

	}

}
