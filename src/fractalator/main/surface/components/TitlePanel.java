package fractalator.main.surface.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fractalator.main.output.OutputManager;

public class TitlePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	JFrame frame;
	int height;
	Color background;
	Color foreground;
	Font font;
	ExecutorService service = null;
	OutputManager output = null;
	
	Point point;
	int xOff = 0;
	
	/**
	 * Defines TitlePanel without Title for JFrames. Implements Methods to allow moving the window and title buttons (close, maximize, minimize)
	 * @param frame typed Object this TitlePanel is part of. All Methods based on this frame.
	 * @param type FULL, MINIMIZEANDCLOSE, CLOSEONLY and EMPTY to define which buttons are showed (Minimize, Maximize, Close)
	 * @param heigt of the panel
	 * @param background color of the panel
	 * @param exit TRUE for exit, FALSE for dispose
	 */
	public TitlePanel(JFrame frame, String type, int height, Color background, boolean exit, ExecutorService service, OutputManager output) {
		new TitlePanel("", frame, type, height, background, new Color(255,255,255), new Font("Calibri",0,26), exit, service, output);
	}
	
	/**
	 * Defines a TitlePanel for JFrames. Implements Methods to allow moving the window and title buttons (close, maximize, minimize)
	 * @param title String displayed at top
	 * @param frame typed Object this TitlePanel is part of. All Methods based on this frame.
	 * @param type FULL, MINIMIZEANDCLOSE, CLOSEONLY and EMPTY to define which buttons are showed (Minimize, Maximize, Close)
	 * @param heigt of the panel
	 * @param background color of the panel
	 * @param foreground color the font has
	 * @param exit TRUE for exit, FALSE for dispose
	 */	
	public TitlePanel(String title, JFrame frame, String type, int height, Color background, Color foreground, Font font, boolean exit, ExecutorService service, OutputManager output) {
		this.title = title;
		this.frame = frame;
		this.height = height;
		this.background = background;
		this.foreground = foreground;
		this.font = font;
		
		this.setBackground(background);
		this.setSize(frame.getWidth(), height);
		
		point = frame.getLocation();

		
		//Create Close Button
		JButton button_close = new JButton(new ImageIcon(((new ImageIcon("./src/IncludedGraphics/closebutton_icon.png")).getImage()).getScaledInstance(height/2, height/2, java.awt.Image.SCALE_SMOOTH)));
		button_close.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
				if(exit){
					try {
						if(output!=null){
							output.print("Shuting down Fractalator ... Bye!");
						}
					} catch (InterruptedException ignored) {
					} finally {
						if(output!=null){
							output.shutdown();
						}
						if(service!=null){
							service.shutdown();
							while (true) {
								try {
									service.awaitTermination(2, TimeUnit.DAYS);
									break;
								} catch (InterruptedException ignored) {
								}
							}
						}
						System.err.println("Shuting down Output and ExecutorServices was sucsessfully. Exit Fractalator . . .");
						System.exit(0);
					}
				}else{frame.dispose();}}});
		button_close.setRolloverIcon(new ImageIcon(((new ImageIcon("./src/IncludedGraphics/closebuttonfocused_icon.png")).getImage()).getScaledInstance(height/2, height/2, java.awt.Image.SCALE_SMOOTH)));
		button_close.setBackground(background);
		button_close.setFocusable(false);
		button_close.setMargin(new Insets(0, 0, 0, 0));
		button_close.setContentAreaFilled(false);
		button_close.setBorderPainted(false);
		this.add(button_close);
		
		//Create maximize Button
		JButton button_maximize = new JButton(new ImageIcon(((new ImageIcon("./src/IncludedGraphics/maximizebutton_icon.png")).getImage()).getScaledInstance(height/2, height/2, java.awt.Image.SCALE_SMOOTH)));
		button_maximize.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
			frame.setState(JFrame.MAXIMIZED_BOTH);
		}});
		button_maximize.setRolloverIcon(new ImageIcon(((new ImageIcon("./src/IncludedGraphics/maximizebuttonfocused_icon.png")).getImage()).getScaledInstance(height/2, height/2, java.awt.Image.SCALE_SMOOTH)));
		button_maximize.setBackground(background);
		button_maximize.setFocusable(false);
		button_maximize.setMargin(new Insets(0, 0, 0, 0));
		button_maximize.setContentAreaFilled(false);
		button_maximize.setBorderPainted(false);
		this.add(button_maximize);
		
		//Create minimize Button
		JButton button_minimize = new JButton(new ImageIcon(((new ImageIcon("./src/IncludedGraphics/minimizebutton_icon.png")).getImage()).getScaledInstance(height/2, height/2, java.awt.Image.SCALE_SMOOTH)));
		button_minimize.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {
			frame.setState(JFrame.ICONIFIED);
		}});
		button_minimize.setRolloverIcon(new ImageIcon(((new ImageIcon("./src/IncludedGraphics/minimizebuttonfocused_icon.png")).getImage()).getScaledInstance(height/2, height/2, java.awt.Image.SCALE_SMOOTH)));
		button_minimize.setBackground(background);
		button_minimize.setFocusable(false);
		button_minimize.setMargin(new Insets(0, 0, 0, 0));
		button_minimize.setContentAreaFilled(false);
		button_minimize.setBorderPainted(false);
		this.add(button_minimize);
		
		//sets the Buttons Bounds based on TitlePanel Type
		switch (type){
		case "FULL":
			button_close.setBounds(frame.getWidth()-height*2/3-10, 0, height*2/3, height);
			button_maximize.setBounds(frame.getWidth()-2*height*2/3-10, 0, height*2/3, height);
			button_minimize.setBounds(frame.getWidth()-3*height*2/3-10, 0, height*2/3, height);
			xOff = 3*height;
			break;
		case "MINIMIZEANDCLOSE":
			button_close.setBounds(frame.getWidth()-height*2/3-10, 0, height*2/3, height);
			button_minimize.setBounds(frame.getWidth()-2*height*2/3-10, 0, height*2/3, height);
			xOff = 2*height;
			break;
		case "CLOSEONLY":
			button_close.setBounds(frame.getWidth()-height*2/3-10, 0, height*2/3, height);
			xOff = height;
			break;
		case "EMPTY":
			break;
		default:
			break;
		}
		
		 addMouseListener(new MouseAdapter() {  
			 public void mousePressed(MouseEvent e) {  
				 if(!e.isMetaDown()){  
				 point.x = e.getX();  
				 point.y = e.getY();  
				 }  
			 }  
		 });  
		 addMouseMotionListener(new MouseMotionAdapter() {  
			 public void mouseDragged(MouseEvent e) {  
				 if(!e.isMetaDown()){  
				 Point p = frame.getLocation();  
				 frame.setLocation(p.x + e.getX() - point.x,  p.y + e.getY() - point.y);  
				 }  
			 }
		 });
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//TitleBar
		g.setColor(background);
		g.fillRect(0, 0, this.getWidth()-xOff, 50);
		g.setColor(foreground);
		g.setFont(font);
		g.drawString(title, 20, 35);
	}
	
	public void setTitle(String title){
		this.title = title;
		repaint();
	}
	
	public void setFont(Font font){
		this.font = font;
		repaint();
	}
	
	public void setBackgroundColor(Color color){
		this.background = color;
		repaint();
	}
	
	public void setForegroundColor(Color color){
		this.foreground = color;
		repaint();
	}
	
	public void setHeight(int height){
		this.height = height;
		repaint();
	}

}
