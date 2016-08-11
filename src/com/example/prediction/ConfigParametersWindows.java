package com.example.prediction;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConfigParametersWindows {

	private JFrame frame;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigParametersWindows window = new ConfigParametersWindows();
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
	 * @wbp.parser.entryPoint
	 */
	public ConfigParametersWindows() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = WindowPreferences.mainFrame("/resources/background.jpg");
		
		frame.getContentPane().add(new AlphaContainer(configHeader()));
		frame.getContentPane().add(new AlphaContainer(configTitleUnderfit()) );
		frame.getContentPane().add(new AlphaContainer(configUnderfit()) );
		frame.getContentPane().add(new AlphaContainer(configTitleOverfit()) );
		frame.getContentPane().add(new AlphaContainer(configOverfit()) );
		frame.getContentPane().add(new AlphaContainer(WindowPreferences.itemsDegree()) );
		frame.getContentPane().add(new AlphaContainer(configAccept()) );
		frame.getContentPane().add(new AlphaContainer(configExit()) );
	}

	private JPanel configHeader() throws Exception{
		JPanel panel_header = WindowPreferences.defaultPanel(0,
															FlowLayout.LEFT, 
															WindowPreferences.DEFAULT_WIDTH_P, 
															WindowPreferences.DEFAULT_HEIGHT_P);
		panel_header.add(WindowPreferences.defaultButton("/resources/icon_home.png") );
		panel_header.add(WindowPreferences.mainTitle("Config Parameters"));
		return panel_header;
	}
	
	private JPanel configTitleUnderfit() throws Exception{
		JPanel panel_titleunder = WindowPreferences.defaultPanel(0, 
															FlowLayout.LEFT, 
															WindowPreferences.DEFAULT_WIDTH_P, 
															WindowPreferences.DEFAULT_HEIGHT_P);
		panel_titleunder.setBackground(new Color(0,0,0,50));
		panel_titleunder.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		panel_titleunder.add(WindowPreferences.textFormatConfigParams("In Underfitting"));
		return panel_titleunder;
	}
	
	private JPanel configTitleOverfit() {
		JPanel panel_titleover = WindowPreferences.defaultPanel(0, 
															FlowLayout.LEFT, 
															WindowPreferences.DEFAULT_WIDTH_P, 
															WindowPreferences.DEFAULT_HEIGHT_P);
		panel_titleover.setBackground(new Color(0,0,0,50));
		panel_titleover.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		panel_titleover.add(WindowPreferences.textFormatConfigParams("In Overfitting"));
		return panel_titleover;
	}
	
	private JPanel configUnderfit() throws Exception{
		JPanel panel_under = WindowPreferences.defaultPanel(0, 
															FlowLayout.LEFT, 
															WindowPreferences.DEFAULT_WIDTH_P, 
															WindowPreferences.DEFAULT_HEIGHT_P);
		panel_under.add(WindowPreferences.textFormatOptimizing("Add more examples"));
		panel_under.add(WindowPreferences.defaultButton("/resources/icon_mergefiles.png"));
		return panel_under;
	}

	private JPanel configOverfit(){
		JPanel panel_over = WindowPreferences.defaultPanel(0, 
															FlowLayout.LEFT, 
															WindowPreferences.DEFAULT_WIDTH_P, 
															WindowPreferences.ICON_SIZE);
		panel_over.add(WindowPreferences.textFormatOptimizing("Increase Polynomial Degree"));
		return panel_over;
	}
	
	private JPanel configAccept() throws Exception{
		JPanel panel_accept = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P); 
		panel_accept.add(WindowPreferences.defaultButton("/resources/icon_ok.png"));
		return panel_accept;
	}
	
	private JPanel configExit() throws Exception{
		JPanel panel_exit = WindowPreferences.defaultPanel(0, FlowLayout.RIGHT, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_exit.add(WindowPreferences.defaultButton("/resources/icon_exit.png"));
		
		return panel_exit;
	}
}
