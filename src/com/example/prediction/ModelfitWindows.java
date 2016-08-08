package com.example.prediction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

public class ModelfitWindows {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModelfitWindows window = new ModelfitWindows();
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
	public ModelfitWindows() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = WindowPreferences.mainFrame("/resources/background.jpg", FlowLayout.CENTER);
		frame.getContentPane().add(configHeader());
		frame.getContentPane().add(configScroll());
		frame.getContentPane().add(configOK());
	}
	
	private JPanel configHeader() throws Exception{
		JPanel panel_header = WindowPreferences.defaultPanel(0, FlowLayout.LEFT, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_header.add(WindowPreferences.defaultButton("/resources/icon_home.png") );
		panel_header.add(WindowPreferences.mainTitle("About learning curves"));
	
		return panel_header;
	}
	
	private JScrollPane configScroll() throws Exception{
		JPanel panel = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P ,1000);
		panel.add(configIntro());
		panel.add(configImage1());
		panel.add(configEpigraph());
		panel.add(configIntro2());
		panel.add(configImage2());
		JScrollPane scroll_panel = new JScrollPane();
		scroll_panel.setPreferredSize(new Dimension(WindowPreferences.DEFAULT_WIDTH_P,400));
		
		scroll_panel.setBackground(new Color(0,0,0,10));
		scroll_panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollBar vertical = scroll_panel.getVerticalScrollBar();
		scroll_panel.setViewportView(panel);
		
		/*vertical.setValue(0);
		
		JViewport jv = scroll_panel.getViewport();
		jv.setViewPosition(new Point(-100,0));
		System.out.println(jv.getViewPosition());*/
		
		
		return scroll_panel;
	}
	
	private JPanel configIntro(){
		String text = "When the quality of a model is evaluated, it is important to measure the error in the training set "
				+ "and the prediction. The exclusive use of error of the training set can lead to misleading results. These "
				+ "errors can lead to a phenomenon of overfitting, in which the model is very well suited to existing data but"
				+ " has poor performance to predict new results.";
		JPanel panel_intro = WindowPreferences.wordWrap(text);
		return panel_intro;
	}
	
	private JPanel configImage1() throws Exception{
		JPanel panel_fitdatacompare = WindowPreferences.defaultImage("/resources/fitdata_compare.png",WindowPreferences.IMG_FITCOMPARE_HEIGHT);
		return panel_fitdatacompare;
	}
	
	private JPanel configEpigraph(){
		String text = "Figure 1: The graph can be interpreted according to the degree of the polynomial model or model "
				+ "complexity. It can be understood that the model on the left is set to a linear order polynomial, which "
				+ "represents a case of poor quality adjustment (sub-optimal), the data are not well represented by a straight"
				+ " line. In cases of Overfitting, the data fit better if the hypothesis correspond to a higher degree "
				+ "polynomial.";
		JPanel panel_epigraph = WindowPreferences.wordWrap(text);
		return panel_epigraph;
	}
	
	private JPanel configIntro2(){
		String text = "On underfitting cases, accuracy on training and test data could be poor because the learning algorithm"
				+ " did not have enough data to learn from (the model is too simple). You could improve performance by increase"
				+ " the amount of training data examples.";
		JPanel panel_intro2 = WindowPreferences.wordWrap(text);
		return panel_intro2;
	}
	
	private JPanel configImage2() throws Exception{
		JPanel panel_fitdatadegree = WindowPreferences.defaultImage("/resources/fitdata_degree.png",WindowPreferences.IMG_FITDEGREE_HEIGHT);
		return panel_fitdatadegree;
	}
	
	private JPanel configOK() throws Exception{
		JPanel panel_ok = WindowPreferences.defaultPanel(0, FlowLayout.RIGHT, WindowPreferences.DEFAULT_WIDTH_P,150 );
		panel_ok.add(WindowPreferences.defaultButton("/resources/icon_ok.png"));
		return panel_ok;
	}
	
	

}
