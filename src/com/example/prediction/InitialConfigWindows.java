package com.example.prediction;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.example.prediction.logica.Config;

public class InitialConfigWindows {
	private JFrame frame;
	private Info info = new Info();
	private int[] lastOptionSelect = {0,0,0};


	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public InitialConfigWindows() throws Exception {
		initialize();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = WindowPreferences.mainFrame( "/resources/background_initial.jpg");
		frame.setBounds(WindowPreferences.STARTW_X + 40,
						WindowPreferences.STARTW_Y + 40,
						WindowPreferences.WIDTH_W,
						WindowPreferences.HEIGHT_W);
		
		frame.getContentPane().add(configHeader());
		frame.getContentPane().add(configOptions());
		frame.getContentPane().add(new AlphaContainer(configOK()));
	}

	private JPanel configHeader() throws Exception{
		JPanel panel_header = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_header.add(WindowPreferences.mainTitle("Initial Configuration"));
	
		return panel_header;
	}
	
	private JPanel configOptions(){
		int height = (WindowPreferences.DEFAULT_HEIGHT_P + WindowPreferences.UNIT_GAP) * 3;
		JPanel panel = WindowPreferences.defaultPanel(0, FlowLayout.LEFT, WindowPreferences.DEFAULT_WIDTH_P, height);
		
		String[] items_f = Config.InitialSettings.getOptionsDatasetFormat(); 
		Choice choice_file = WindowPreferences.groupItemConfig(items_f,"Select Format file");
				
		choice_file.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				// TODO Auto-generated method stub
				String itemSelected = (String) event.getItem();
				int position = 0;										//CAMBIARRRRRRRRRRRRRRRRRRRRRRRR
				if(lastOptionSelect[Config.InitialSettings.ITEM_CODE_FORMATFILE] == position){
					Config.InitialSettings.setFormatDataset(Config.InitialSettings.getOptionsDatasetFormat()[position]);
					configFileDatasetOptions();
				}
			}	
		});
		panel.add(choice_file);
		
		String[] items_t = Config.InitialSettings.getOptionsTypePrediction();
		Choice choice_typeprediction = WindowPreferences.groupItemConfig(items_t, "Select Type Prediction");
		choice_typeprediction.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				// TODO Auto-generated method stub
				int position = 0;										//CAMBIARRRRRRRRRRRRRRRRRRRRRRRR;
				if(lastOptionSelect[Config.InitialSettings.ITEM_CODE_TYPEPREDICTION] == position){
					Config.InitialSettings.setTypePrediction(Config.InitialSettings.getOptionsTypePrediction()[position]);
					info.setTypePrediction(position);
					configAttOptions();
				}
			}
		});
		
		panel.add(choice_typeprediction);
		
		
		String[] items_s = this.getDirectorys(Config.InitialSettings.getDirStorage());
		Choice choice_storage = WindowPreferences.groupItemConfig(items_s, "Select working directory");
		choice_storage.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String itemSelected = (String) e.getItem();
					moveDirectory(itemSelected);
					Config.InitialSettings.setWorkingDir(itemSelected);
					configFileDatasetOptions();	
			}
			
		});
		panel.add(choice_storage);
		
		return panel;
	}
	
	private JPanel configOK() throws Exception {
		JPanel panel_ok = WindowPreferences.defaultPanel(0, FlowLayout.RIGHT, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		JButton button_ok = WindowPreferences.defaultButton("/resources/icon_check.png");
		button_ok.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				try {
					frame.setVisible(false);
	                ConfiguresWindows.changeCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		panel_ok.add(button_ok);
		return panel_ok;
	}
	
	private void moveDirectory(String itemSelected){
		String oldPath = Config.InitialSettings.getDirWorking() + Config.InitialSettings.SUBDIR_APP;
		File directorySource = new File(oldPath);
		if(directorySource.exists()){
			File destination = new File(itemSelected + "/" + Config.InitialSettings.SUBDIR_APP );
			destination.mkdir();
			File[] files = directorySource.listFiles();
			for(File f:files){
				File newFile = new File(destination,f.getName());
				f.renameTo(newFile);
			}
			directorySource.delete();
		}	
	}
	
	private void configFileDatasetOptions(){
		try {
			info.setListFilesDataset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	ConfiguresActivity.controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_FILE_DATASET, true);
		ConfiguresActivity.controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT, false);
		ConfiguresActivity.controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_SCHEMES, false);*/
	}
	
	private void configAttOptions() {
		// TODO Auto-generated method stub
		/*ConfiguresActivity.controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT, true);
		ConfiguresActivity.controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_SCHEMES, false);*/
	}
	
	private String[] getDirectorys(String root){
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
		String[] result = new String[directorys.size()];
		int index=0;
		for(String s: directorys){
			result[index]=s;
			index++;
		}		
		return result;
	}
	
	
}
