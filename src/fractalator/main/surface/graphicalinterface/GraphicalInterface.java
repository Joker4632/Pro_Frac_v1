package fractalator.main.surface.graphicalinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fractalator.main.surface.dialogs.SavingDialog;
import fractalator.main.surface.graphicalinterface.components.FractalPanel;

import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class GraphicalInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int mx; // Minimal X Position
	private ExecutorService service;
	private FractalPanel fractalPanel;
	private JButton button_save;
	private JPanel basePanel;
	private long time;
	private Point mousePos;
	private String string_type;
	private String string_colortheme;
	private String string_resolution;
	private String string_depth;
	private String string_zoom;
	private String string_button_save;
	private String string_label_type;
	private String string_label_colortheme;
	private String string_label_resolution;
	private String string_label_depth;
	private String string_label_zoom;
	private String string_label_time;
	
	private volatile boolean isMousePressed = false;

	public GraphicalInterface(BufferedImage Fractal, ExecutorService service, String Type, String Colortheme, String Resolution, String Depth,
			String Zoom, long time) {
		image = Fractal;
		this.service = service;
		string_type = Type;
		string_colortheme = Colortheme;
		string_resolution = Resolution;
		string_depth = Depth;
		string_zoom = Zoom;
		this.time = time;
		adjustSettings();
		initializeGUI();
	}

	public void adjustSettings() {

		string_button_save = "Save";
		string_label_type = "Fractal Type:";
		string_label_colortheme = "Colortheme:";
		string_label_resolution = "Resolution";
		string_label_depth = "Depth:";
		string_label_zoom = "Zoom";
		string_label_time = "Process Duration:";

	}

	private void initializeGUI() {
		/*
		 * Default Operations
		 */
		Image icon = new ImageIcon("./src/IncludedGraphics/fractalator_icon.png").getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Fractalator - Graphical Interface");

		if (image.getHeight() > 1000 || image.getWidth() > 1600) {
			setSize(1200, 975); // Bildausschnit von 900x900
			mx = 940;
		} else if (image.getHeight() < 300) {
			setSize(image.getWidth() + 300, 400);
			mx = image.getWidth() + 40;
		} else {
			setSize(image.getWidth() + 300, image.getHeight() + 75);
			mx = image.getWidth() + 40;
		}

		/*
		 * Base Elements
		 */
		basePanel = new JPanel();
		basePanel.setLayout(null);
		basePanel.setBackground(new Color(64, 64, 64));
		setContentPane(basePanel);
		
		fractalPanel = new FractalPanel(image);
		fractalPanel.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() < 0) {
                    fractalPanel.zoomIn();
                } else {
                    fractalPanel.zoomOut();
                }

			}
		});
		fractalPanel.setLocation(20, 20);
		if (image.getHeight() > 1000 || image.getWidth() > 1600) {
			fractalPanel.setSize(900, 900);
		} else {
			fractalPanel.setSize(image.getWidth(), image.getHeight());
		}
		fractalPanel.updateSize();
		basePanel.add(fractalPanel);

		/*
		 * Action Elements
		 */
		button_save = new JButton(string_button_save);
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SavingDialog SD = new SavingDialog(image);
				SD.setLocationRelativeTo(null);
				SD.setVisible(true);

			}
		});
		button_save.setBounds(this.getWidth() - 210, this.getHeight() - 95, 140, 40);
		button_save.setFont(new Font("Calibri Light", 1, 22));
		button_save.setFocusable(false);
		basePanel.add(button_save);
		/*
		 * NonAction Elements
		 */
		JLabel label_fractaltype = new JLabel(string_label_type);
		label_fractaltype.setBounds(mx, 20, 100, 20);
		label_fractaltype.setForeground(Color.WHITE);
		label_fractaltype.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_fractaltype);

		JLabel label_fractaltype2 = new JLabel(string_type);
		label_fractaltype2.setBounds(mx + 120, 20, 100, 20);
		label_fractaltype2.setForeground(Color.WHITE);
		label_fractaltype2.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_fractaltype2);

		JLabel label_colortheme = new JLabel(string_label_colortheme);
		label_colortheme.setBounds(mx, 40, 100, 20);
		label_colortheme.setForeground(Color.WHITE);
		label_colortheme.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_colortheme);

		JLabel label_colortheme2 = new JLabel(string_colortheme);
		label_colortheme2.setBounds(mx + 120, 40, 100, 20);
		label_colortheme2.setForeground(Color.WHITE);
		label_colortheme2.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_colortheme2);

		JLabel label_resolution = new JLabel(string_label_resolution);
		label_resolution.setBounds(mx, 60, 100, 20);
		label_resolution.setForeground(Color.WHITE);
		label_resolution.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_resolution);

		JLabel label_resolution2 = new JLabel(string_resolution);
		label_resolution2.setBounds(mx + 120, 60, 100, 20);
		label_resolution2.setForeground(Color.WHITE);
		label_resolution2.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_resolution2);

		JLabel label_depth = new JLabel(string_label_depth);
		label_depth.setBounds(mx, 80, 100, 20);
		label_depth.setForeground(Color.WHITE);
		label_depth.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_depth);

		JLabel label_depth2 = new JLabel(string_depth);
		label_depth2.setBounds(mx + 120, 80, 100, 20);
		label_depth2.setForeground(Color.WHITE);
		label_depth2.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_depth2);

		JLabel label_zoom = new JLabel(string_label_zoom);
		label_zoom.setBounds(mx, 100, 100, 20);
		label_zoom.setForeground(Color.WHITE);
		label_zoom.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_zoom);

		JLabel label_zoom2 = new JLabel(string_zoom);
		label_zoom2.setBounds(mx + 120, 100, 100, 20);
		label_zoom2.setForeground(Color.WHITE);
		label_zoom2.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_zoom2);
		
		JLabel label_time = new JLabel(string_label_time);
		label_time.setBounds(mx, 130, 120, 20);
		label_time.setForeground(Color.WHITE);
		label_time.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_time);

		JLabel label_time2 = new JLabel(String.valueOf(time)+" ms");
		label_time2.setBounds(mx + 120, 130, 100, 20);
		label_time2.setForeground(Color.WHITE);
		label_time2.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(label_time2);
	}
	
	private void setMousePos(){
		mousePos = getMousePosition();
	}

}
