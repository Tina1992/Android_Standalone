package com.example.prediction;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.prediction.logica.Config;

public class InitialConfigWindows {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialConfigWindows window = new InitialConfigWindows();
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
	public InitialConfigWindows() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = WindowPreferences.mainFrame("/resources/background_initial.jpg", FlowLayout.CENTER);
		frame.getContentPane().add(configHeader());
		frame.getContentPane().add(configOptions());
		frame.getContentPane().add(configOK());
	}

	private JPanel configHeader() throws Exception{
		JPanel panel_header = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_header.add(WindowPreferences.mainTitle("Initial Configuration"));
	
		return panel_header;
	}
	
	private JPanel configOptions(){
		int height = (WindowPreferences.DEFAULT_HEIGHT_P + WindowPreferences.UNIT_GAP) * 3;
		JPanel panel = WindowPreferences.defaultPanel(0, FlowLayout.LEFT, WindowPreferences.DEFAULT_WIDTH_P, height);
		
		final Choice choice_file = new Choice();
		choice_file.add("Select Format file");
		choice_file.setPreferredSize(new Dimension(WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P));
		for(String f:Config.InitialSettings.getOptionsDatasetFormat())
			choice_file.add(f);
		panel.add(choice_file);
		
		final Choice choice_typeprediction = new Choice();
		choice_typeprediction.add("Select Type Prediction");
		choice_typeprediction.setPreferredSize(new Dimension(WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P));
		for(String t:Config.InitialSettings.getOptionsTypePrediction() )
			choice_typeprediction.add(t);
		panel.add(choice_typeprediction);
		
		final Choice choice_storage = new Choice();
		choice_storage.add("Select Working Directory");
		choice_storage.setPreferredSize(new Dimension(WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P));
		for(String s:this.getDirectorys(Config.InitialSettings.getDirStorage()) )
			choice_storage.add(s);
		panel.add(choice_storage);
		
		return panel;
	}
	
	private JPanel configOK() throws Exception {
		JPanel panel_ok = WindowPreferences.defaultPanel(0, FlowLayout.RIGHT, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_ok.add(WindowPreferences.defaultButton("/resources/icon_check.png"));
		
		return panel_ok;
	}
	
	private List<String> getDirectorys(String root){
		List<String> directorys = new ArrayList<String>();
		File f = new File(root);
		if(f.isDirectory())
			directorys.add(root);
		
		File[] files = f.listFiles();
		for(File arch: files)
			if(arch.isDirectory())  {
				String dir = arch.getAbsolutePath();
				if(! dir.endsWith("/"))
					dir = dir.concat("/");
				directorys.add(dir);
			}
		return directorys;
	}
	
	
}
