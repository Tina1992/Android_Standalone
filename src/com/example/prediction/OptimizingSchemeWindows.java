package com.example.prediction;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptimizingSchemeWindows {

	private JFrame frame;
	
	private Info info=Info.getInstance();
	
	private JLabel image;
	private int loc=0;
	Vector<BufferedImage> vector=info.generateImagesSchemesComparator(info.getDatasetSelected());
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptimizingSchemeWindows window = new OptimizingSchemeWindows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public OptimizingSchemeWindows() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = WindowPreferences.mainFrame("/resources/background.jpg");
		
		frame.getContentPane().add(configHeader());
		frame.getContentPane().add(configOptions());
		frame.getContentPane().add(configImage());
		frame.getContentPane().add(configChangeImage());
		frame.getContentPane().add(configParams());
		frame.getContentPane().add(configModel());
	}

	private JPanel configHeader() throws Exception{
		JPanel panel_header = WindowPreferences.defaultPanel(0, FlowLayout.LEFT, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_header.add(WindowPreferences.defaultButton("/resources/icon_home.png") );
		panel_header.add(WindowPreferences.mainTitle("Step 2: Optimizing Scheme"));
	
		return panel_header;
	}

	private JPanel configOptions() throws Exception{
		// TODO Auto-generated method stub
		JPanel panel_options = WindowPreferences.defaultPanel(0, FlowLayout.RIGHT,WindowPreferences.DEFAULT_WIDTH_P,WindowPreferences.DEFAULT_HEIGHT_P);
		panel_options.add(WindowPreferences.defaultButton("/resources/icon_alert_save.png"));
		panel_options.add(WindowPreferences.defaultButton("/resources/icon_historial.png"));
		
		return panel_options;
	}

	private JPanel configImage() throws Exception {
		JPanel panel = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.HEIGHT_I);		
		image = new JLabel(new ImageIcon(vector.get(loc).getScaledInstance(WindowPreferences.DEFAULT_WIDTH_P,WindowPreferences.HEIGHT_I, Image.SCALE_SMOOTH)) );
		panel.add(image);
		return panel;
	}
	
	private JPanel configChangeImage() throws Exception{
		JPanel panel_change = WindowPreferences.defaultPanel(	0, 
															FlowLayout.CENTER, 
															WindowPreferences.DEFAULT_WIDTH_P,
															WindowPreferences.DEFAULT_HEIGHT_P);
		panel_change.add(WindowPreferences.defaultButton("/resources/icon_prevbutton.png"));
		panel_change.add(WindowPreferences.defaultButton("/resources/icon_nextbutton.png"));
		return panel_change;
	}

	private JPanel configParams() throws Exception {
		JPanel panel_params = WindowPreferences.defaultPanel(	0, 
																FlowLayout.LEFT,
																WindowPreferences.DEFAULT_WIDTH_P,
																WindowPreferences.DEFAULT_HEIGHT_P);
		panel_params.add(WindowPreferences.textFormatOptimizing("Select the button to config parameters"));
		panel_params.add(WindowPreferences.defaultButton("/resources/icon_configparams.png"));
		panel_params.add(WindowPreferences.defaultButton("/resources/icon_help.png"));
		
		return panel_params;
	}
	
	private JPanel configModel() throws Exception{
		JPanel panel_params = WindowPreferences.defaultPanel(	0, 
																FlowLayout.LEFT,
																WindowPreferences.DEFAULT_WIDTH_P,
																WindowPreferences.DEFAULT_HEIGHT_P);
		panel_params.add(WindowPreferences.textFormatOptimizing("Select the button to generate model"));
		JButton button_model = WindowPreferences.defaultButton("/resources/icon_model.png");
		button_model.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ImagesLCWindows LCWindows=new ImagesLCWindows();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		panel_params.add(button_model);

		return panel_params;
	}
}
