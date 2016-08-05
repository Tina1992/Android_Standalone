package com.example.prediction;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
