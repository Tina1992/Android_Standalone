package com.example.prediction;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class WindowPreferences {
	static Dimension DIMENSION_SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	static int WIDTH_W = 400;
	static int HEIGHT_W = 600;
	
	static int PADDING = 20;
	static int DEFAULT_WIDTH_P = WIDTH_W - (PADDING * 2);
	static int DEFAULT_HEIGHT_P = 40;
	
 
	static int ICON_SIZE = 30;
	
	static int HEIGHT_I = 250;
	static int TEXT_OPT_WIDTH = 255;
	static int LIST_FILT_WIDTH = 250; 
	static int ITEM_DEGREE_WIDTH = 250;
	static int UNIT_GAP = 5;
	static int CHOICE_INITIAL_WIDTH = 330;
	static int TEXT_MODEL_HEIGHT = 300;
	static int IMG_FITCOMPARE_HEIGHT = 250;
	static int IMG_FITDEGREE_HEIGHT = 150;
	static int STARTW_X = (DIMENSION_SCREEN.width /2) - (WIDTH_W /2) ;
	static int STARTW_Y = (DIMENSION_SCREEN.height /2) - (HEIGHT_W /2);
	
	public static JFrame mainFrame(int LayoutAlign) throws Exception{
		JFrame frame = new JFrame();
		frame.setBounds(STARTW_X, STARTW_Y, WIDTH_W,HEIGHT_W);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ConfiguresWindows.class.getResource("/resources/ic_launcher.png")));
		frame.setTitle("Prediction");
		//frame.getContentPane().setLayout(new FlowLayout(LayoutAlign));	
		return frame;
	}
	
	public static JFrame mainFrame(String ResourceID) throws Exception{
		JFrame frame = new JFrame();
		frame.setBounds(STARTW_X, STARTW_Y, WIDTH_W,HEIGHT_W);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ConfiguresWindows.class.getResource("/resources/ic_launcher.png")));
		frame.setTitle("Prediction");
		Image image_background = ImageIO.read(ConfiguresWindows.class.getResource(ResourceID));
		JLabel background = new JLabel(new ImageIcon(image_background.getScaledInstance(WIDTH_W, HEIGHT_W, Image.SCALE_SMOOTH)));
		background.setPreferredSize(new Dimension(WIDTH_W,HEIGHT_W));
		frame.setContentPane(background);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		return frame;
	}

	
	public static JPanel mainPanel(BufferedImage background){
		JPanel panel = new JPanel();
		Graphics g = background.createGraphics();
		g.drawImage(background, 0, 0, WIDTH_W,HEIGHT_W,null);
		panel.paintComponents(g);
		panel.setLayout(new BorderLayout(0, 0));
		return panel;
	}
	
	public static JPanel defaultPanel(int alpha, int LayoutAlign, int width, int height){
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,alpha));
		panel.setPreferredSize(new Dimension(DEFAULT_WIDTH_P, height));
		panel.setLayout(new FlowLayout(LayoutAlign));
		
		return panel;
	}
	
	public static JButton defaultButton(String ImageID) throws Exception{
		Image img = ImageIO.read(ConfiguresWindows.class.getResource(ImageID));
		ImageIcon icon = new ImageIcon(img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
		JButton button = new JButton();
		button.setIcon(icon);
		button.setBorder(null);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setOpaque(false);
		button.setBackground(new Color(0,0,0,10));
		return button;
		
	}
	
	public static JPanel defaultImage(BufferedImage image, int height) throws Exception{
		JPanel panel = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, DEFAULT_WIDTH_P, height);		
		JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(DEFAULT_WIDTH_P,HEIGHT_I, Image.SCALE_SMOOTH)) );
		panel.add(label);
		
		return panel;
	}
	
	public static JLabel mainTitle(String title){
		int width = (title.length() * 11) + 4;
		JLabel label = new JLabel(title);
		label.setPreferredSize(new Dimension(width,DEFAULT_HEIGHT_P));					 
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Calibri", Font.BOLD, 25));
		return label;
	}
	
	public static JLabel textFormatOptimizing(String text){
		JLabel label = new JLabel(text);
		label.setPreferredSize(new Dimension(TEXT_OPT_WIDTH, ICON_SIZE));
		label.setBackground(new Color(0,0,0,10));
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Calibri", Font.ITALIC, 16));
		return label;
	}
	
	public static JLabel textFormatConfigParams(String text) {
		JLabel label = new JLabel(text);
		label.setPreferredSize(new Dimension(TEXT_OPT_WIDTH, ICON_SIZE));
		label.setBackground(new Color(0,0,0,0));
		//label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Calibri", Font.PLAIN, 20));
		return label;
	}
	
	public static JPanel itemsDegree(){
		int height = (WindowPreferences.DEFAULT_HEIGHT_P + WindowPreferences.UNIT_GAP ) *4; 
		JPanel panel_list = WindowPreferences.defaultPanel(	0, 
															FlowLayout.LEFT, 
															WindowPreferences.DEFAULT_WIDTH_P,
															height);
		ButtonGroup group = new ButtonGroup();
		for(int i=1; i<5; i++){
			JRadioButton degree = new JRadioButton("Degree 1");
			degree.setPreferredSize(new Dimension(ITEM_DEGREE_WIDTH,DEFAULT_HEIGHT_P));
			degree.setBackground(new Color(0,0,0,15));
			if(i==1)
				degree.setSelected(true);
			degree.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}	
			});
			group.add(degree);
			panel_list.add(degree);
		}
		return panel_list;
		
	}
	
	

	
	
	public static Choice groupItemConfig(String[] strings, String title){
		final Choice choice = new Choice();
		choice.add(title);
		choice.setPreferredSize(new Dimension(CHOICE_INITIAL_WIDTH, DEFAULT_HEIGHT_P));
		for(String i:strings)
			choice.add(i);
		
		
		return choice;
	}

	public static JPanel wordWrap(String text) {
		// TODO Auto-generated method stub
		JTextArea area = new JTextArea();
		area.setText(text);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setEditable(false);
		area.setBackground(new Color(0,0,0,15));
		area.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		area.setFont(new Font("Calibri", Font.PLAIN, 18));
		area.setForeground(Color.BLACK);
		area.setPreferredSize(new Dimension(DEFAULT_WIDTH_P,TEXT_MODEL_HEIGHT));
			
		JPanel panel = defaultPanel(0,FlowLayout.CENTER,DEFAULT_WIDTH_P, TEXT_MODEL_HEIGHT + UNIT_GAP );
		panel.add(area);
		return panel;
	}
	
}
