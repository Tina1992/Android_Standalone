package com.example.prediction;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Color;
import javax.swing.JTextPane;

public class PresentationWindow {

	private JFrame frame;
	
	private class BackgroundPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Create the panel.
		 */
		private Image img;

		/**
		 * 
		 */
		@SuppressWarnings("static-access")
		public BackgroundPanel(String resourceID) throws Exception {
			img = ImageIO.read(getClass().getClassLoader().getSystemResource(resourceID));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, 450, 300, this);
		}
	}
	
	public class MyButton extends JButton {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyButton(String string) {
	    	super(string);
			// TODO Auto-generated constructor stub
		}
		@Override
	    public Dimension getMaximumSize() {
	        return new Dimension(120,frame.getHeight()/4);

	    }
	    @Override
	    public Dimension getMinimumSize() {
	        return new Dimension(120,frame.getHeight()/4);
	    }
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PresentationWindow window = new PresentationWindow();
					window.frame.setVisible(true);
					//window.panel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PresentationWindow() {
		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PresentationWindow.class.getResource("/resources/ic_launcher.png")));
		frame.setBounds(100, 100, 450, 300);
		
		//JPanel panel = new JPanel();
		BackgroundPanel panel = new BackgroundPanel("resources/background_presentation.jpg");
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					@SuppressWarnings("unused")
					ConfiguresWindows cw=new ConfiguresWindows();
					frame.setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setOpaque(false);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBorderPainted(true);
		btnNewButton.setFocusable(false);
		btnNewButton.setContentAreaFilled(false);
		panel.add(btnNewButton, BorderLayout.SOUTH);
		
		JTextPane txtpnTuecnicasDeAprendizaje = new JTextPane();
		txtpnTuecnicasDeAprendizaje.setOpaque(false);
		txtpnTuecnicasDeAprendizaje.setEditable(false);
		txtpnTuecnicasDeAprendizaje.setFocusable(false);
		txtpnTuecnicasDeAprendizaje.setFont(new Font("Calibri", Font.BOLD, 20));
		txtpnTuecnicasDeAprendizaje.setForeground(Color.white);
		txtpnTuecnicasDeAprendizaje.setText("Técnicas de aprendizaje para predecir atributos no funcionales en componentes de aplicaciones Android");
		panel.add(txtpnTuecnicasDeAprendizaje, BorderLayout.NORTH);
		
	}
}
