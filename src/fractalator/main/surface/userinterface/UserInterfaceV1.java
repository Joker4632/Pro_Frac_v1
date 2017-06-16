package fractalator.main.surface.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fractalator.converter.colors.Colortheme;
import fractalator.converter.colors.PredefinedColorthemes;
import fractalator.main.output.OutputManager;
import fractalator.main.surface.userinterface.components.ColorthemePreviewPanel;
import fractalator.processing.mandelbrot.MandelbrotProcessor;

/**
 * Main (graphical) Interface of the Fractalator Program
 * 
 * Structure:
 * 
 * - Declarations
 * 
 * - Settings & Definition of UI specific variables
 * 
 * - Definition UI Elements and Components
 * 
 * - External Component/Element Methods
 * 
 * - Methods
 * 
 * @author Johannes Scharrer
 *
 */
public class UserInterfaceV1 extends Abstract_UserInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Colortheme> colorthemes;
	private ArrayList<MandelbrotProcessor> MandelbrotProcessors = new ArrayList<MandelbrotProcessor>();
	private Color color_background;
	private Color color_bottombar;
	private Color color_titlebar;
	private Color color_foregroundUnfocused;
	private Color color_foregroundFocused;
	private ColorthemePreviewPanel colorthemePreviewPanel;
	private Dimension dimension_user_interface;
	private ExecutorService service;
	private JButton button_calculate;
	private JButton button_showLog;
	private JButton button_kill;
	private JButton button_fractaltype_information;
	private JButton button_fractaldepth_information;
	private JButton button_resolution_information;
	private JButton button_zoom_information;
	private JButton button_newcolortheme;
	private JCheckBox checkbox_renderonly;
	private JCheckBox checkbox_activateStartPoint;
	private JComboBox<String> combobox_fractaltype;
	private JComboBox<String> combobox_colortheme;
	private JLabel label_zoom_suggestion;
	private JPanel basePanel;
	private JPanel bottomPanel;
	private JPanel titlePanel;
	private PredefinedColorthemes colorthemes_predifined = new PredefinedColorthemes();
	private OutputManager output;
	private String string_title;
	private String string_fractaltype;
	private String string_fractaldepth;
	private String string_resolution;
	private String string_width;
	private String string_width_unit;
	private String string_height;
	private String string_height_unit;
	private String string_start;
	private String string_zoom;
	private String string_colortheme;
	private String string_moresettings;
	private String string_button_calculate;
	private String string_button_showLog;
	private String string_button_newcolortheme;
	private String string_button_information;
	private String string_checkbox_renderonly;
	private String string_checkbox_activateStartPoint;
	private String string_jop_info_types; // jop == JOptionPane
	private String string_jop_info_depth;
	private String string_jop_info_resolution;
	private String string_jop_info_zoom;
	private String[] fractaltypes;
	private String[] colortheme_names;
	private JTextField textfield_fractaldepth;
	private JTextField textfield_width;
	private JTextField textfield_height;
	private JTextField textfield_startx;
	private JTextField textfield_starty;
	private JTextField textfield_zoom;


	/**
	 * User Interface Constructor Class. Besides invoking required Methods,
	 * generates ExecutorService, OutputRunnable and gives Status Updates.
	 */
	public UserInterfaceV1() {

		service = Executors.newCachedThreadPool();
		output = new OutputManager();
		service.execute(output);

		try {
			output.print("  Adjust Settings . . .");
			adjustSettings();
			output.print("  Import predifined Colorthemes . . .");
			importColorthemes();
			output.print("  Initialize GUI . . .");
			initializeGUI();
			output.print("  Ready!");
			output.print("");
		} catch (InterruptedException ignored) {
		}

	}

	/**
	 * Appropriate Values for every Setting
	 */
	@Override
	void adjustSettings() {
		dimension_user_interface = new Dimension(700, 500);

		color_background = new Color(255, 255, 255);
		color_bottombar = new Color(50, 50, 50);
		color_titlebar = new Color(0, 0, 0);
		color_foregroundUnfocused = new Color(92, 184, 252);
		color_foregroundFocused = new Color(255, 255, 255);

		string_title = "Fractalator - User Interface";
		string_fractaltype = "Fractal Type:";
		string_fractaldepth = "Fractal Depth:";
		string_resolution = "Fractal Size:";
		string_width = "Width:";
		string_width_unit = "pixel";
		string_height = "Height:";
		string_height_unit = "pixel";
		string_start = "Start Point:";
		string_zoom = "Zoom:";
		string_colortheme = "Colortheme:";
		string_moresettings = "More Settings:";
		string_button_calculate = "Calculate";
		string_button_showLog = "Show Log";
		string_button_newcolortheme = "+";
		string_button_information = "i";
		string_checkbox_renderonly = "render only (without showing in internal Viewer)";
		string_checkbox_activateStartPoint = "Enhanced: Manual Start Postion";

		string_jop_info_types = "Mandelbrot - Set\n\nJulia - Set";
		string_jop_info_depth = "A higher depth (Iteration) requires more power/time to render!\nMaximal Value is 65535. Basic Setting is 100.";
		string_jop_info_resolution = "A higher resolution will show you more Details, but requires more power/time to render!\nNotice that you just have more space and need to increase the zoom for more Details!\nBasic Setting is 1000 x 1000";
		string_jop_info_zoom = "With a higher value you will zoom into the Fractal, so you will get a bigger Version.\nThe Zoom is absolut and not in dependence to Resolution! You need to increase if you want a bigger fractal!\n After changing the width, you will get an suggestion based on 360 Zoom for 1000 Pixels.";

		fractaltypes = new String[2];
		fractaltypes[0] = "Mandelbrot - Set";
		fractaltypes[1] = "Julia - Set";

	}

	/**
	 * Imports the predifined Colorthemes of an PredifinedColorthemes Class and
	 * invoke the updateColorthemeNames Method. After Importing the
	 * PredifinedColorthemes Class is set to Null for saving Memory
	 */
	private void importColorthemes() {
		colorthemes = colorthemes_predifined.importPredifined();
		colorthemes_predifined = null;

		updateColorthemeNames();
	}

	/**
	 * Builds the User Interface. 4 Parts: Default Operations, Base Elements,
	 * Action Elements, NonAction Elments.
	 * 
	 * Default Operations: Operations and Components related to the frame
	 * 
	 * Base Elements: Includes all Elements or Components which contains Action
	 * or NonAction Components
	 * 
	 * Action Elements: Includes all Elements or Components which could trigger
	 * an action (Combo- and CheckBoxes are listed as Action Elements)
	 * 
	 * NonAction Elements: Includes all Elements or Components which couldn't
	 * trigger an action or contain other Elements
	 */
	@Override
	void initializeGUI() {
		/*
		 * Default Operations
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				onExit();
			}
		});
		Image icon = new ImageIcon("./src/IncludedGraphics/fractalator_icon.png").getImage();
		setIconImage(icon);
		setResizable(false);
		setTitle(string_title);
		setSize(dimension_user_interface);
		/*
		 * Base Elements
		 */
		basePanel = new JPanel();
		basePanel.setLayout(null);
		basePanel.setBackground(color_background);
		setContentPane(basePanel);

		bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 365, dimension_user_interface.width, dimension_user_interface.height);
		bottomPanel.setLayout(null);
		bottomPanel.setBackground(color_bottombar);
		basePanel.add(bottomPanel);

		titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, dimension_user_interface.width, 35);
		titlePanel.setLayout(null);
		titlePanel.setBackground(color_titlebar);
		basePanel.add(titlePanel);
		/*
		 * Action Elements
		 */
		button_calculate = new JButton(string_button_calculate);
		button_calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionListner_calculate();
			}
		});
		button_calculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent enter) {
				button_calculate.setForeground(color_foregroundFocused);
			}

			@Override
			public void mouseExited(MouseEvent exit) {
				button_calculate.setForeground(color_foregroundUnfocused);
			}
		});
		button_calculate.setBounds(dimension_user_interface.width - 170, 0, 170, 35);
		button_calculate.setFont(new Font("Calibri Light", 1, 20));
		button_calculate.setForeground(color_foregroundUnfocused);
		button_calculate.setOpaque(false);
		button_calculate.setContentAreaFilled(false);
		button_calculate.setBorderPainted(false);
		button_calculate.setFocusable(false);
		titlePanel.add(button_calculate);
		
		button_showLog = new JButton(string_button_showLog);
		button_showLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.showPI();
			}
		});
		button_showLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent enter) {
				button_showLog.setForeground(color_foregroundFocused);
			}

			@Override
			public void mouseExited(MouseEvent exit) {
				button_showLog.setForeground(color_foregroundUnfocused);
			}
		});
		button_showLog.setBounds(0, 0, 100, 35);
		button_showLog.setFont(new Font("Calibri Light", 1, 15));
		button_showLog.setForeground(color_foregroundUnfocused);
		button_showLog.setOpaque(false);
		button_showLog.setContentAreaFilled(false);
		button_showLog.setBorderPainted(false);
		button_showLog.setFocusable(false);
		titlePanel.add(button_showLog);
		

		button_kill = new JButton();
		button_kill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(MandelbrotProcessor MP: MandelbrotProcessors){
					MP.kill();
				}
			}
		});
		button_kill.setEnabled(false);
		button_kill.setBackground(Color.GRAY);
		button_kill.setBounds(660, 50, 20, 20);
		button_kill.setMargin(new Insets(0, 0, 0, 0));
		button_kill.setFocusable(false);
		basePanel.add(button_kill);

		button_fractaltype_information = new JButton(string_button_information);
		button_fractaltype_information.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, string_jop_info_types);
			}
		});
		button_fractaltype_information.setBounds(290, 65, 20, 20);
		button_fractaltype_information.setMargin(new Insets(0, 0, 0, 0));
		button_fractaltype_information.setFocusable(false);
		basePanel.add(button_fractaltype_information);

		button_fractaldepth_information = new JButton(string_button_information);
		button_fractaldepth_information.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, string_jop_info_depth);
			}
		});
		button_fractaldepth_information.setBounds(240, 105, 20, 20);
		button_fractaldepth_information.setMargin(new Insets(0, 0, 0, 0));
		button_fractaldepth_information.setFocusable(false);
		basePanel.add(button_fractaldepth_information);

		button_resolution_information = new JButton(string_button_information);
		button_resolution_information.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, string_jop_info_resolution);
			}
		});
		button_resolution_information.setBounds(140, 145, 20, 20);
		button_resolution_information.setMargin(new Insets(0, 0, 0, 0));
		button_resolution_information.setFocusable(false);
		basePanel.add(button_resolution_information);

		button_zoom_information = new JButton(string_button_information);
		button_zoom_information.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, string_jop_info_zoom);
			}
		});
		button_zoom_information.setBounds(190, 285, 20, 20);
		button_zoom_information.setMargin(new Insets(0, 0, 0, 0));
		button_zoom_information.setFocusable(false);
		basePanel.add(button_zoom_information);

		button_newcolortheme = new JButton(string_button_newcolortheme);
		button_newcolortheme.setBounds(290, 325, 20, 20);
		button_newcolortheme.setMargin(new Insets(0, 0, 0, 0));
		button_newcolortheme.setFocusable(false);
		basePanel.add(button_newcolortheme);

		checkbox_renderonly = new JCheckBox(string_checkbox_renderonly);
		checkbox_renderonly.setBounds(60, 40, 400, 20);
		checkbox_renderonly.setForeground(Color.WHITE);
		checkbox_renderonly.setContentAreaFilled(false);
		checkbox_renderonly.setFont(new Font("Calibri Light", 0, 15));
		checkbox_renderonly.setFocusable(false);
		bottomPanel.add(checkbox_renderonly);

		checkbox_activateStartPoint = new JCheckBox(string_checkbox_activateStartPoint);
		checkbox_activateStartPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkbox_activateStartPoint.isSelected()){
					textfield_startx.setEnabled(true);
					textfield_starty.setEnabled(true);
				}else{
					textfield_startx.setEnabled(false);
					textfield_starty.setEnabled(false);
				}

			}
		});
		checkbox_activateStartPoint.setBounds(60, 70, 400, 20);
		checkbox_activateStartPoint.setForeground(Color.WHITE);
		checkbox_activateStartPoint.setContentAreaFilled(false);
		checkbox_activateStartPoint.setFont(new Font("Calibri Light", 0, 15));
		checkbox_activateStartPoint.setFocusable(false);
		bottomPanel.add(checkbox_activateStartPoint);

		combobox_fractaltype = new JComboBox<String>(fractaltypes);
		combobox_fractaltype.setBounds(140, 65, 150, 20);
		combobox_fractaltype.setFont(new Font("Calibri Light", 0, 13));
		combobox_fractaltype.setFocusable(false);
		basePanel.add(combobox_fractaltype);

		combobox_colortheme = new JComboBox<String>(colortheme_names);
		combobox_colortheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorthemePreviewPanel.update(colorthemes.get(combobox_colortheme.getSelectedIndex()));
			}
		});
		combobox_colortheme.setBounds(140, 325, 150, 20);
		combobox_colortheme.setFont(new Font("Calibri Light", 0, 13));
		combobox_colortheme.setFocusable(false);
		basePanel.add(combobox_colortheme);

		/*
		 * NonAction Elements
		 */
		JLabel jlabel_fractaltype = new JLabel(string_fractaltype);
		jlabel_fractaltype.setBounds(40, 65, 100, 20);
		jlabel_fractaltype.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_fractaltype);

		JLabel jlabel_fractaldepth = new JLabel(string_fractaldepth);
		jlabel_fractaldepth.setBounds(40, 105, 100, 20);
		jlabel_fractaldepth.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_fractaldepth);

		JLabel jlabel_resolution = new JLabel(string_resolution);
		jlabel_resolution.setBounds(40, 145, 100, 20);
		jlabel_resolution.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_resolution);

		JLabel jlabel_width = new JLabel(string_width);
		jlabel_width.setBounds(70, 175, 50, 20);
		jlabel_width.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_width);

		JLabel jlabel_width_unit = new JLabel(string_width_unit);
		jlabel_width_unit.setBounds(190, 175, 30, 20);
		jlabel_width_unit.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_width_unit);

		JLabel jlabel_height = new JLabel(string_height);
		jlabel_height.setBounds(70, 205, 50, 20);
		jlabel_height.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_height);

		JLabel jlabel_height_unit = new JLabel(string_height_unit);
		jlabel_height_unit.setBounds(190, 205, 30, 20);
		jlabel_height_unit.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_height_unit);

		JLabel jlabel_start = new JLabel(string_start);
		jlabel_start.setBounds(40, 245, 100, 20);
		jlabel_start.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_start);

		JLabel jlabel_startx = new JLabel("x:");
		jlabel_startx.setBounds(140, 245, 10, 20);
		jlabel_startx.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_startx);

		JLabel jlabel_starty = new JLabel("y:");
		jlabel_starty.setBounds(220, 245, 15, 20);
		jlabel_starty.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_starty);

		JLabel jlabel_zoom = new JLabel(string_zoom);
		jlabel_zoom.setBounds(40, 285, 50, 20);
		jlabel_zoom.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_zoom);

		JLabel jlabel_colortheme = new JLabel(string_colortheme);
		jlabel_colortheme.setBounds(40, 325, 100, 20);
		jlabel_colortheme.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_colortheme);

		JLabel jlabel_colortheme_caption1 = new JLabel("Fractal");
		jlabel_colortheme_caption1.setBounds(320, 325, 50, 20);
		jlabel_colortheme_caption1.setForeground(Color.GRAY);
		jlabel_colortheme_caption1.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_colortheme_caption1);

		JLabel jlabel_colortheme_caption2 = new JLabel("Background");
		jlabel_colortheme_caption2.setBounds(508, 325, 100, 20);
		jlabel_colortheme_caption2.setForeground(Color.GRAY);
		jlabel_colortheme_caption2.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_colortheme_caption2);

		JLabel jlabel_moresettings = new JLabel(string_moresettings);
		jlabel_moresettings.setBounds(40, 10, 100, 20);
		jlabel_moresettings.setForeground(Color.WHITE);
		jlabel_moresettings.setFont(new Font("Calibri Light", 0, 15));
		bottomPanel.add(jlabel_moresettings);

		label_zoom_suggestion = new JLabel("");
		label_zoom_suggestion.setBounds(230, 285, 200, 20);
		label_zoom_suggestion.setFont(new Font("Calibri Light", 0, 15));
		label_zoom_suggestion.setForeground(Color.GRAY);
		basePanel.add(label_zoom_suggestion);

		textfield_fractaldepth = new JTextField("100");
		textfield_fractaldepth.setBounds(140, 105, 102, 21);
		textfield_fractaldepth.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_fractaldepth);

		textfield_width = new JTextField("1000");
		textfield_width.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				textfield_height.setText(textfield_width.getText());
				try {
					int suggestion = (int) (Double.parseDouble(textfield_width.getText()) * 0.36);
					label_zoom_suggestion.setText("Suggested Zoom: " + suggestion);
				} catch (Exception irgnored) {
					// just invalid input.
				}

			}
		});
		textfield_width.setBounds(140, 175, 50, 21);
		textfield_width.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_width);

		textfield_height = new JTextField("1000");
		textfield_height.setBounds(140, 205, 50, 21);
		textfield_height.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_height);

		textfield_startx = new JTextField();
		textfield_startx.setEnabled(false);
		textfield_startx.setBounds(155, 245, 50, 21);
		textfield_startx.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_startx);

		textfield_starty = new JTextField();
		textfield_starty.setEnabled(false);
		textfield_starty.setBounds(235, 245, 50, 21);
		textfield_starty.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_starty);

		textfield_zoom = new JTextField("360");
		textfield_zoom.setBounds(140, 285, 50, 21);
		textfield_zoom.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_zoom);

		colorthemePreviewPanel = new ColorthemePreviewPanel(colorthemes.get(0));
		colorthemePreviewPanel.setBounds(370, 325, 128, 20);
		basePanel.add(colorthemePreviewPanel);

	}

	/**
	 * Method is used by button_calculate. If Inputs are validated, Method
	 * invoke a processing unit for the choosed Fractal Type.
	 * 
	 * Mandelbrot: For all Mandelbrot Fractals (Type 0) with up to an Limit of
	 * 65535 Iterations.
	 * 
	 * Julia: For Julia Frractals (Type 1).
	 */
	private void actionListner_calculate() {
		if (validateInputs()) {
			if (combobox_fractaltype.getSelectedItem().toString().equals(fractaltypes[0])) {
				Dimension res = new Dimension(Integer.parseInt(textfield_width.getText()),
						Integer.parseInt(textfield_height.getText()));
				Point start = new Point();
				if(checkbox_activateStartPoint.isSelected()){
					start.setLocation(Integer.parseInt(textfield_startx.getText()), Integer.parseInt(textfield_starty.getText()));
				}else{
				 start = new Point((res.width / 2)*-1, res.height / 2);
				}
				MandelbrotProcessor MP = new MandelbrotProcessor(Integer.parseInt(textfield_fractaldepth.getText()), start, res,
						Integer.parseInt(textfield_zoom.getText()),
						colorthemes.get(combobox_colortheme.getSelectedIndex()), service, output, this,
						checkbox_renderonly.isSelected());
				MandelbrotProcessors.add(MP);
				button_kill.setEnabled(true);
				button_kill.setBackground(Color.RED);
				service.execute(MP);
			} else if (combobox_fractaltype.getSelectedItem().toString().equals(fractaltypes[1])) {
				// TODO JULIA FRACTALE
			} else {
				JOptionPane.showMessageDialog(null, "No Fractal Type Selected");
			}
		}
	}

	/**
	 * Updates the String[] colortheme_names with all colorthemes in
	 * 'colorthemes'. If it is empty it will add '-' to symbolize this.
	 */
	private void updateColorthemeNames() {
		if (colorthemes.isEmpty()) {
			colortheme_names = new String[1];
			colortheme_names[0] = "-";
		} else {
			colortheme_names = new String[colorthemes.size()];
			for (int i = 0; i < colorthemes.size(); i++) {
				colortheme_names[i] = colorthemes.get(i).name();
			}
		}
	}

	/**
	 * Check all Inputs for validity. No Null, Negative or 0-values in
	 * TextFields. Gives Feedback.
	 * 
	 * @return true if inputs are valid
	 */
	private boolean validateInputs() {
		boolean check = true;
		if (textfield_fractaldepth.getText().equals("") || textfield_width.getText().equals("")
				|| textfield_height.getText().equals("") || textfield_zoom.getText().equals("")) {
			check = false;
			JOptionPane.showMessageDialog(null, "Something is missing!");
		}else if(textfield_startx.getText().equals("")&& checkbox_activateStartPoint.isSelected()||textfield_starty.getText().equals("")&&checkbox_activateStartPoint.isSelected()){
			check = false;
			JOptionPane.showMessageDialog(null, "Something is missing!");
		} else {
			try {
				if (Integer.parseInt(textfield_fractaldepth.getText()) <= 0) {
					check = false;
					JOptionPane.showMessageDialog(null, "Negativ or 0 Iteration is not permitted!");
				}
				if (Integer.parseInt(textfield_width.getText()) <= 0 || Integer.parseInt(textfield_height.getText()) <= 0
						|| Integer.parseInt(textfield_zoom.getText()) <= 0) {
					check = false;
					JOptionPane.showMessageDialog(null, "Negativ or 0 Width/Height/Zoom is not permitted!");
				}
				if (combobox_colortheme.getSelectedItem().toString().equals("-")) {
					check = false;
					JOptionPane.showMessageDialog(null, "No Colortheme selected!");
				}
				if(checkbox_activateStartPoint.isSelected()){
					Integer.parseInt(textfield_startx.getText());
					Integer.parseInt(textfield_starty.getText());
				}
			} catch (Exception e) {
				check = false;
				JOptionPane.showMessageDialog(null, "Invalid Input!");
			}
			
		}
		return check;
	}
	/**
	 * 
	 */
	@Override
	public void updateMPL(MandelbrotProcessor MP){
		MandelbrotProcessors.remove(MP);
		if(MandelbrotProcessors.isEmpty()){
			button_kill.setEnabled(false);
			button_kill.setBackground(Color.GRAY);
		}
	}

	/**
	 * Overwrites EXIT_ON_CLOSE Method. Shutting Down Output Service and
	 * ExecutorService. Exit Program.
	 */
	@Override
	void onExit() {
		try {
			output.print("Shuting down Fractalator ... Bye!");
		} catch (InterruptedException e) {
		} finally {
			output.shutdown();
			service.shutdown();
			while (true) {
				try {
					service.awaitTermination(2, TimeUnit.DAYS);
					break;
				} catch (InterruptedException ignored) {
				}
			}
			System.err.println("Shuting down Output and ExecutorServices was sucsessfully. Exit Fractalator . . .");
			System.exit(0);
		}

	}

}
