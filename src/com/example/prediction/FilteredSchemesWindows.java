package com.example.prediction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.example.prediction.logica.Config;
import com.example.prediction.logica.models.AbsModeler;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FilteredSchemesWindows {

	private Info info = Info.getInstance();
	private JFrame frame;

	private int loc=0;
	Vector<BufferedImage> vector=info.generateImagesSchemesComparator(info.getDatasetSelected());
	
	JLabel image;
	

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public FilteredSchemesWindows() throws Exception {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		frame = WindowPreferences.mainFrame("/resources/background.jpg", FlowLayout.CENTER);
		
		frame.getContentPane().add(configHeader());
		frame.getContentPane().add(configOptions());
		frame.getContentPane().add(configImage());
		frame.getContentPane().add(configSelectScheme());
		frame.getContentPane().add(configExit());
	}
	
	private JPanel configHeader() throws Exception{
		JPanel panel_header = WindowPreferences.defaultPanel(0, 0, FlowLayout.LEFT, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_header.add(WindowPreferences.defaultButton("/resources/icon_home.png") );
		panel_header.add(WindowPreferences.mainTitle("Step 1: Filtered Schemes"));
	
		return panel_header;
	}

	private JPanel configOptions() throws Exception {
		JPanel panel_options = WindowPreferences.defaultPanel(0, 0, FlowLayout.RIGHT,WindowPreferences.DEFAULT_HEIGHT_P);
		panel_options.add(WindowPreferences.defaultButton("/resources/icon_alert_save.png"));
		JButton nextBu=WindowPreferences.defaultButton("/resources/icon_next.png");
		nextBu.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//DE ESTA MANERA FUNCIONA EL HACER CLICK.  
				loc++;
				if (loc>=vector.size()){
					loc=0;
				}
				try {
					image.setIcon(new ImageIcon(vector.get(loc).getScaledInstance(WindowPreferences.DEFAULT_WIDTH_P,WindowPreferences.HEIGHT_I, Image.SCALE_SMOOTH)) );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_options.add(nextBu);
		return panel_options;
	}

	private JPanel configImage() throws Exception {
		JPanel panel = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.HEIGHT_I);		
		image = new JLabel(new ImageIcon(vector.get(loc).getScaledInstance(WindowPreferences.DEFAULT_WIDTH_P,WindowPreferences.HEIGHT_I, Image.SCALE_SMOOTH)) );
		panel.add(image);
		return panel;
	}

	private JPanel configSelectScheme() throws Exception{
		JPanel panel_select = WindowPreferences.defaultPanel(0, 0, FlowLayout.LEFT, WindowPreferences.DEFAULT_HEIGHT_P);
		
		JScrollPane panel_spinner = new JScrollPane();
		panel_spinner.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_spinner.setBackground(new Color(0,0,0,20));
		panel_spinner.setPreferredSize(new Dimension(WindowPreferences.LIST_FILT_WIDTH, WindowPreferences.DEFAULT_HEIGHT_P));
		
		final Choice choice = new Choice();
		choice.add("Select Scheme...");
		
		for(AbsModeler m:info.getBestSchemes())
			choice.add(m.getName());
		choice.setPreferredSize(new Dimension(WindowPreferences.LIST_FILT_WIDTH, WindowPreferences.DEFAULT_HEIGHT_P));
		choice.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				System.out.println("El item selecionado es: "+ choice.getSelectedIndex());
				AbsModeler m=info.getBestSchemes().get(choice.getSelectedIndex()-1);
				info.setBestScheme(m);
				try {
					ImagesLCWindows lc=new ImagesLCWindows();
					FileWriter writer = new FileWriter(Config.InitialSettings.getDirWorking()+m.getName()+".model");
					writer.write(m.toString());
					writer.close();
					frame.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//if(choice.getSelectedIndex() != 0)
					//info.setBestScheme(models.elementAt(choice.getSelectedIndex()));
			}
			
		});
		panel_select.add(choice);
		panel_select.add(WindowPreferences.defaultButton("/resources/icon_check.png"));
		
		return panel_select;
	}

	private JPanel configExit() throws Exception{
		JPanel panel_exit = WindowPreferences.defaultPanel(0, 0, FlowLayout.RIGHT, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_exit.add(WindowPreferences.defaultButton("/resources/icon_exit.png"));
		
		return panel_exit;
	}
}
