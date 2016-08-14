package com.example.prediction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;


public class SMFrame extends JDialog {
	
	private class BackgroundPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private Image img = null;
		
		@SuppressWarnings("static-access")
		public BackgroundPanel(String resourceID) throws Exception {
			Image aux = ImageIO.read(getClass().getClassLoader().getSystemResource(resourceID));
			img = aux.getScaledInstance(WIDTH_W, HEIGHT_W, Image.SCALE_SMOOTH);
		}

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, this);
            }
        }
	}

	private static final long serialVersionUID = 1L;
	private int WIDTH_W = 225;
	private int HEIGHT_W = 300;
	private int HEIGHT_Title = 35;
	private int HEIGHT_Resp = 45; 
	private int HEIGHT_Scroll = HEIGHT_W - HEIGHT_Title - HEIGHT_Resp - 40;
	private int HEIGHT_ITEM = 30;
	
	private static JButton button_accept;
	private static JButton button_cancel;
	private int selected;
	private Vector<Integer> schemesSelected;
	
	public SMFrame(String name,CharSequence[] list, boolean multi) throws Exception{
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setType(Type.UTILITY);
		setPreferredSize(new Dimension(WIDTH_W,HEIGHT_W));
		this.setBounds(	WindowPreferences.STARTW_X + 25,
						WindowPreferences.STARTW_Y + 25,
						WIDTH_W,
						HEIGHT_W);
		
		BackgroundPanel panel = new BackgroundPanel("resources/background.jpg");
		panel.setPreferredSize(new Dimension(WIDTH_W,HEIGHT_W));
		BorderLayout bl = new BorderLayout();
		bl.setVgap(1);
		JPanel panel_title = new JPanel();
		panel_title.setPreferredSize(new Dimension(WIDTH_W,HEIGHT_Title));
		panel_title.setBackground(new Color(0,0,0,0));
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Calibri", Font.BOLD, 17));
		textPane.setForeground(Color.white);
		textPane.setText(name);
		textPane.setBackground(new Color(0,0,0,0));
		textPane.setOpaque(false);
		textPane.setFocusable(false);
		textPane.setPreferredSize(new Dimension(WIDTH_W,HEIGHT_Title));
		//textPane.setMinimumSize(new Dimension(this.getWidth(), 50));
		panel_title.add(new AlphaContainer(textPane));
		panel.add(new AlphaContainer(panel_title));
		
		panel.add(new AlphaContainer(configScroll(list,multi )));
		
		final JDialog dialog = this;
		button_accept = configButton("accept"); 
		button_accept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
			}
			
		});
		button_cancel = configButton("cancel");
		button_cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
			}
			
		});
		JPanel panel_buttons = new JPanel();
		panel_buttons.setPreferredSize(new Dimension(WIDTH_W,HEIGHT_Resp));
		panel_buttons.setBackground(new Color(0,0,0,0));
		panel_buttons.add(new AlphaContainer(button_accept));
		panel_buttons.add(new AlphaContainer(button_cancel));
		panel.add(new AlphaContainer(panel_buttons));
		
		add(new AlphaContainer(panel));
		
	}

	private JButton configButton(String text){
		final JButton button = new JButton(text);
		button.setFont(new Font("Calibri", Font.BOLD, 17));
		button.setForeground(Color.white);
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setBackground(new Color(0,0,0,0));
		button.setRolloverEnabled(true);
		button.setOpaque(false);
		button.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button.setBackground(new Color(0,0,0,20));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button.setBackground(new Color(0,0,0,0));
		    }
		});
		return button;
	}
	
	private JScrollPane configScroll(CharSequence[] list, boolean multi){
		int height = HEIGHT_Scroll;
		JScrollPane panel_spinner = new JScrollPane();
		panel_spinner.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_spinner.setPreferredSize(new Dimension(
				WIDTH_W, height));
		panel_spinner.getViewport().setBackground(new Color(0,0,0,50));
		panel_spinner.getViewport().setOpaque(false);		
		panel_spinner.setBackground(new Color(0,0,0,0));
		panel_spinner.setOpaque(false);
		panel_spinner.setBorder(null);
		if(multi)
			panel_spinner.setViewportView(new AlphaContainer(configMultiItemSelect(list)));
		else
			panel_spinner.setViewportView(new AlphaContainer(configSingleItemSelect(list)));
		return panel_spinner;
	}
	
	private JPanel configSingleItemSelect(CharSequence[] list){
		int height = list.length * (HEIGHT_ITEM + WindowPreferences.UNIT_GAP);
		int width = 150;
		JPanel panel_list = WindowPreferences.defaultPanel(	0, 
															FlowLayout.LEFT, 
															width,
															height);
		panel_list.setBackground(new Color(0,0,0,0));
		panel_list.setOpaque(false);
		
		ButtonGroup group = new ButtonGroup();
		for(int i=0; i<list.length; i++){
			final int index=i;
			JRadioButton degree = new JRadioButton(list[i].toString());
			degree.setPreferredSize(new Dimension(WIDTH_W,HEIGHT_ITEM));
			degree.setBackground(new Color(0,0,0,0));
			if(i==0)
				degree.setSelected(true);
			
			degree.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					selected = index;
				}
				
			});
			group.add(degree);
			panel_list.add(new AlphaContainer(degree));
		}
		
		return panel_list;
	}
	
	private JPanel configMultiItemSelect(CharSequence[] list){
		int height = list.length * (HEIGHT_ITEM + WindowPreferences.UNIT_GAP);
		int width = 150;
		JPanel panel_list = WindowPreferences.defaultPanel(	0, 
															FlowLayout.LEFT, 
															width,
															height);
		panel_list.setBackground(new Color(0,0,0,0));
		panel_list.setOpaque(false);
		schemesSelected = new Vector<Integer>();
		for(int i=0; i<list.length; i++){
			final int index=i;
			JRadioButtonMenuItem degree = new JRadioButtonMenuItem(list[i].toString());
			degree.setPreferredSize(new Dimension(WIDTH_W,HEIGHT_ITEM));
			degree.setBackground(new Color(0,0,0,0));
			degree.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(schemesSelected.contains(index))
						schemesSelected.remove(index);
					else
						schemesSelected.add(index);
				}
				
			});
			panel_list.add(new AlphaContainer(degree));
		}
		
		return panel_list;
		
	}
	
	public int getValue(){
		return selected;
	}
	
	public Vector<Integer> getValues(){
		return schemesSelected;
	}
	
	public JButton getAcceptButton(){
		return button_accept;
	}
	
	
}
