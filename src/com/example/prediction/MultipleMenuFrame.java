package com.example.prediction;

import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MultipleMenuFrame extends JFrame {
	
	private int width=200;
	private int height=300;
	
	public static Boolean[] selected;
	

	private JButton acceptButton = new JButton("Accept");
	
	
	private class BackgroundPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * Create the panel.
		 */
		private Image img = null;

		/**
		 * 
		 */
		@SuppressWarnings("static-access")
		public BackgroundPanel(String resourceID) throws Exception {
			img = ImageIO.read(getClass().getClassLoader().getSystemResource(resourceID));
		}

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, this);
            }
        }
	}

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public MultipleMenuFrame(String name, CharSequence[] list) throws IOException {
		selected=new Boolean[list.length];
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setType(Type.UTILITY);
		this.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
		this.getContentPane().setLayout(new FlowLayout());	
		//BackgroundPanel panel;
		try {
			BackgroundPanel panel = new BackgroundPanel("resources/background.jpg");
			

			panel.setPreferredSize(new Dimension(width,300));
			JScrollPane panel_scroll = new JScrollPane();
			panel_scroll.setViewportView(panel);
			panel_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_scroll.setPreferredSize(new Dimension(width,150));
			
			setContentPane(panel_scroll);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setFont(new Font("Calibri", Font.BOLD, 17));
			textPane.setForeground(Color.white);
			textPane.setText(name);
			textPane.setOpaque(false);
			textPane.setMinimumSize(new Dimension(this.getWidth(), 50));
			panel.add(textPane);
			for (int i=0; i<list.length; i++){
				JRadioButtonMenuItem jrb=new JRadioButtonMenuItem();
				jrb.setText(list[i].toString());
				final int count=i;
				jrb.setMinimumSize(new Dimension(this.getWidth(), 50));
				jrb.setFont(new Font("Calibri", Font.BOLD, 17));
				jrb.setForeground(Color.white);
				jrb.setOpaque(false);
				jrb.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e){
						try {
							MultipleMenuFrame.selected[count]=!MultipleMenuFrame.selected[count];
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
				});
				selected[i]=false;
				panel.add(jrb);
			}
			
			JPanel panel2 = new JPanel();
			panel.add(panel2);
			panel2.setOpaque(false);
			
			acceptButton.setBackground(new Color(0,0,0,0));
			
			acceptButton.setOpaque(false);
			acceptButton.setFont(new Font("Calibri", Font.BOLD, 17));
			acceptButton.setForeground(Color.white);
			acceptButton.setBorderPainted(false);
			acceptButton.setFocusable(false);
			acceptButton.setContentAreaFilled(false);
			
			acceptButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	acceptButton.setBackground(new Color(0,0,0,20));
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	acceptButton.setBackground(new Color(0,0,0,0));
			    }
			});
			
			final JButton cancelButton = new JButton("Cancel");
			cancelButton.setFont(new Font("Calibri", Font.BOLD, 17));
			cancelButton.setForeground(Color.white);
			cancelButton.setBorderPainted(false);
			cancelButton.setFocusable(false);
			cancelButton.setContentAreaFilled(false);
			cancelButton.setRolloverEnabled(true);
			
			cancelButton.setBackground(new Color(0,0,0,0));
			
			cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	cancelButton.setBackground(new Color(0,0,0,20));
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	cancelButton.setBackground(new Color(0,0,0,0));
			    }
			});
			
			AlphaContainer aca= new AlphaContainer(acceptButton);
			AlphaContainer acc=new AlphaContainer(cancelButton);
			
			GroupLayout gl_panel2 = new GroupLayout(panel2);
			gl_panel2.setHorizontalGroup(
				gl_panel2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel2.createSequentialGroup()
						.addComponent(aca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(acc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(2))
			);
			gl_panel2.setVerticalGroup(
				gl_panel2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel2.createParallelGroup(Alignment.BASELINE)
						.addComponent(aca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(acc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			);
			panel2.setLayout(gl_panel2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Vector<Integer> getSelected(){
		Vector<Integer> ret=new Vector<Integer>();
		for (int i=0;i<selected.length;i++){
			if (selected[i]){
				ret.add(i);
				System.out.println(i);
			}
		}
		return ret;
	}
	
	public void addConfirmListener(ActionListener listener) {
		  acceptButton.addActionListener(listener);
	}
	
	

}
