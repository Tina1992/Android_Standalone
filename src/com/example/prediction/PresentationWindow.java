package com.example.prediction;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.GridLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.Box;
import java.awt.Button;
import javax.swing.SwingConstants;

public class PresentationWindow {

	private JFrame frame;
	
	public class BackgroundPanel extends JPanel {

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
		BackgroundPanel panel = new BackgroundPanel("resources/background_initial.jpg");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		toolBar.setOpaque(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		panel.add(toolBar, BorderLayout.WEST);
		
		JButton button = new JButton("Select Library");
		button.setOpaque(false);
		toolBar.add(button);
		
		JButton button_1 = new JButton("Select Database");
		toolBar.add(button_1);
		
		JButton button_2 = new JButton("New button");
		toolBar.add(button_2);
		
		JButton button_3 = new JButton("New button");
		toolBar.add(button_3);
		frame.getContentPane().setLayout(groupLayout);
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
