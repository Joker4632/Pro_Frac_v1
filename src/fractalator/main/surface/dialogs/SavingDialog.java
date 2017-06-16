package fractalator.main.surface.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fractalator.converter.ImageToConverter;

public class SavingDialog extends JFrame {

	private BufferedImage image;
	private JButton button_save;
	private JComboBox<String> combobox_fileextension;
	private JPanel basePanel;
	private JTextField textfield_filename;
	private JTextField textfield_path;
	private String string_title;
	private String string_label_filename;
	private String string_label_path;
	private String string_label_fileextension;
	private String string_button_save;
	private String[] extensions = {"png","jpg","gif","bmp"};

	
	public SavingDialog(BufferedImage image) {
		this.image = image;
		adjustSettings();
		initializeGUI();
	}
	
	private void adjustSettings() {
		
		string_title = "Fractalator - Saving Image as ...";
		string_label_filename ="Filename:";
		string_label_path = "Path:";
		string_label_fileextension = "Save as:";
		string_button_save = "Save";
		
		
	}
	
	private void initializeGUI(){
		/*
		 * Default Operations
		 */
		Image icon = new ImageIcon("./src/IncludedGraphics/fractalator_icon.png").getImage();
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(string_title);
		setSize(500, 200);
		/*
		 * Base Elements
		 */
		basePanel = new JPanel();
		basePanel.setBackground(Color.WHITE);
		basePanel.setLayout(null);
		setContentPane(basePanel);
		/*
		 * Action Elements
		 */
		button_save = new JButton(string_button_save);
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textfield_filename.getText().equals("")&&!textfield_path.getText().equals("")){
					new ImageToConverter(textfield_filename.getText(), textfield_path.getText(), image, extensions[combobox_fileextension.getSelectedIndex()]);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Something is missing!");
				}
			}
		});
		button_save.setBounds(200, 115, 100, 30);
		button_save.setFont(new Font("Calibri Light", 1, 22));
		button_save.setFocusable(false);
		basePanel.add(button_save);
		
		combobox_fileextension = new JComboBox<String>(extensions);
		combobox_fileextension.setBounds(120, 70, 100, 20);
		combobox_fileextension.setFont(new Font("Calibri Light", 0, 13));
		combobox_fileextension.setFocusable(false);
		basePanel.add(combobox_fileextension);
		
		/*
		 * NonAction Elements
		 */
		JLabel jlabel_filename = new JLabel(string_label_filename);
		jlabel_filename.setBounds(20, 20, 100, 20);
		jlabel_filename.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_filename);
		
		JLabel jlabel_path = new JLabel(string_label_path);
		jlabel_path.setBounds(20, 45, 100, 20);
		jlabel_path.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_path);
		
		JLabel jlabel_fileextension = new JLabel(string_label_fileextension);
		jlabel_fileextension.setBounds(20, 70, 100, 20);
		jlabel_fileextension.setFont(new Font("Calibri Light", 0, 15));
		basePanel.add(jlabel_fileextension);
		
		textfield_filename = new JTextField();
		textfield_filename.setBounds(120, 20, 340, 21);
		textfield_filename.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_filename);
		
		textfield_path = new JTextField("./RenderedImages");
		textfield_path.setBounds(120, 45, 340, 21);
		textfield_path.setFont(new Font("Calibri Light", 0, 13));
		basePanel.add(textfield_path);
	}	

}
