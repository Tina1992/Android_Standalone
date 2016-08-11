package com.example.prediction;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagesLCWindows {

	private JFrame frame;
	
	private Info info=Info.getInstance();
	
	private int loc=0;
	Vector<BufferedImage> vector=new Vector<BufferedImage>();
	
	JLabel image;

	private BufferedImage image_learningcurve;

	private BufferedImage image_errorprediction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImagesLCWindows window = new ImagesLCWindows();
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
	public ImagesLCWindows() throws Exception {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = WindowPreferences.mainFrame("/resources/background.jpg");
		generateImages();
		frame.getContentPane().add(new AlphaContainer(configHeader()) );
		frame.getContentPane().add(new AlphaContainer(configReturn()) );
		frame.getContentPane().add(new AlphaContainer(configImage()) );
		frame.getContentPane().add(new AlphaContainer(configChangeImage()) );
		
	}
	
	private JPanel configImage() throws Exception {
		JPanel panel = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.HEIGHT_I);		
		image = new JLabel(new ImageIcon(vector.get(loc).getScaledInstance(WindowPreferences.DEFAULT_WIDTH_P,WindowPreferences.HEIGHT_I, Image.SCALE_SMOOTH)) );
		panel.add(image);
		return panel;
	}
	
	private JPanel configHeader() throws Exception{
		JPanel panel_header = WindowPreferences.defaultPanel(0, FlowLayout.LEFT, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		panel_header.add(WindowPreferences.defaultButton("/resources/icon_home.png") );
		panel_header.add(WindowPreferences.mainTitle("All Learning Curve Images"));
	
		return panel_header;
	}
	
	private JPanel configReturn() throws Exception{
		JPanel panel_return = WindowPreferences.defaultPanel(	0, 
																FlowLayout.RIGHT, 
																WindowPreferences.ICON_SIZE + WindowPreferences.UNIT_GAP, 
																WindowPreferences.DEFAULT_HEIGHT_P);
		panel_return.add(WindowPreferences.defaultButton("/resources/icon_return.png") );
		return panel_return;
	}
	
	private JPanel configChangeImage() throws Exception{
		JPanel panel_change = WindowPreferences.defaultPanel(	0, 
															FlowLayout.CENTER, 
															WindowPreferences.DEFAULT_WIDTH_P,
															WindowPreferences.DEFAULT_HEIGHT_P);
		JButton prev=WindowPreferences.defaultButton("/resources/icon_prevbutton.png");
		prev.addMouseListener(new MouseAdapter(){
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
		panel_change.add(new AlphaContainer(prev));
		
		JButton next=WindowPreferences.defaultButton("/resources/icon_nextbutton.png");
		next.addMouseListener(new MouseAdapter(){
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
		panel_change.add(new AlphaContainer(next));
		
		return panel_change;
	}
	
	private void generateImages(){
		try {
		    addImageError(info.generateImageErrorPrediction(info.getDatasetSelected()));
		    addImageLearningCurve(info.generateImageLearningCurve(info.getDatasetSelected()));
	       
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }

	public void addImageLearningCurve(BufferedImage image){
		image_learningcurve=image;
		vector.add(0, image);
	}
	
	public void addImageError(BufferedImage image){
		image_errorprediction=image;
		vector.add(0, image);
	}
	
}
