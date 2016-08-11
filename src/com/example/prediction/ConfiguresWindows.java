package com.example.prediction;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

import com.example.prediction.logica.Config;
import com.example.prediction.logica.models.AbsModeler;

import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

public class ConfiguresWindows {
	
	private class BackgroundPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Image img;

		@SuppressWarnings("static-access")
		public BackgroundPanel(String resourceID) throws Exception {
			img = ImageIO.read(getClass().getClassLoader().getSystemResource(resourceID));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, WindowPreferences.WIDTH_W,WindowPreferences.HEIGHT_W , null);
		}
	}

	private static JFrame frame;
	private JButton button_configures_lock;
	private JLabel[] labelSelected= new JLabel[4];
	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public ConfiguresWindows() throws Exception {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		frame = WindowPreferences.mainFrame(FlowLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel = new BackgroundPanel("resources/background.jpg");
		frame.getContentPane().add(panel);
		panel.add(new AlphaContainer(configHeader()));
		
		final JPanel panel_library = createItem("/resources/icon_library.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[0], 0);
		panel_library.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){  
				final Info info=Info.getInstance();
				info.setLibraries();
				final CharSequence[] lc=info.getListLibraries();
				final SimpleMenuFrame smp;
				try {
					smp = new SimpleMenuFrame(Config.AppSettings.TITLE_CONFIGURES_ITEMS[0], lc);
					smp.setLocationByPlatform(false);
					smp.addConfirmListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				             int selected=smp.getValue();
				             info.setLibrarySelected(lc[selected].toString());
				             smp.setVisible(false);
				          }
				       });
					smp.setLocationRelativeTo(frame);
					smp.setLocation(80, 100);
					smp.pack();
					smp.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//frame.getContentPane().add(new AlphaContainer(panel_library));
		panel.add(new AlphaContainer(panel_library));
		
		JPanel panel_file = createItem("/resources/icon_file.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[1],1);
		panel_file.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//DE ESTA MANERA FUNCIONA EL HACER CLICK.  
				final Info info=Info.getInstance();
				info.setListFilesDataset();
				final CharSequence[] lc=info.getListFilesDataset();
				final SimpleMenuFrame smp;
				try {
					smp = new SimpleMenuFrame(Config.AppSettings.TITLE_CONFIGURES_ITEMS[1], lc);
					smp.addConfirmListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				             int selected=smp.getValue();
				             try {
								info.setFileDatasetSelected(lc[selected].toString());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				             smp.setVisible(false);
				          }
				       });

					smp.setLocationRelativeTo(frame);
					smp.setLocation(80, 100);
					smp.pack();
					smp.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
       // frame.getContentPane().add(new AlphaContainer(panel_file));
        panel.add(new AlphaContainer(panel_file));
		
        JPanel panel_attribute = createItem("/resources/icon_attribute.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[2],2);
        
        panel_attribute.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//DE ESTA MANERA FUNCIONA EL HACER CLICK.  
				final Info info=Info.getInstance();
				info.setListAttributes(info.getDatasetSelected());
				CharSequence[] lc=info.getListAttributes();
				final SimpleMenuFrame smp;
				try {
					smp = new SimpleMenuFrame(Config.AppSettings.TITLE_CONFIGURES_ITEMS[2], lc);
					smp.addConfirmListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				             int selected=smp.getValue();
				             info.setAttributeSelected(selected);
				             smp.setVisible(false);
				          }
				       });

					smp.setLocationRelativeTo(frame);
					smp.setLocation(80, 100);
					smp.pack();
					smp.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        panel.add(new AlphaContainer(panel_attribute));
        
        JPanel panel_schemes = createItem("/resources/icon_schemes.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[3],3);
       // frame.getContentPane().add(new AlphaContainer(panel_schemes));
        panel.add(new AlphaContainer(panel_schemes));
        
        panel_schemes.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//DE ESTA MANERA FUNCIONA EL HACER CLICK.  
				final Info info=Info.getInstance();
				info.setListSchemes();
				CharSequence[] lc=info.getListSchemes();
				final MultipleMenuFrame mmp;
				try {
					mmp = new MultipleMenuFrame(Config.AppSettings.TITLE_CONFIGURES_ITEMS[3], lc);
					mmp.addConfirmListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				             Vector<Integer> sels=mmp.getSelected();
				             info.setSchemesSelected(sels);
				             mmp.setVisible(false);
				          }
				       });
					mmp.setLocationRelativeTo(frame);
					mmp.setLocation(80, 100);
					mmp.pack();
					mmp.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
       panel.add( new AlphaContainer(configButtonLock() ));
       panel.add(new AlphaContainer(this.configButtonExit()));
	}
	
	public static void changeCloseOperation(int OP){
		frame.setDefaultCloseOperation(OP);
	}
	
	
	private JPanel configHeader() throws Exception{
		int height = WindowPreferences.DEFAULT_HEIGHT_P * 2;
		int width = WindowPreferences.ICON_SIZE * 3;
		JPanel panel_header = WindowPreferences.defaultPanel(0, FlowLayout.LEFT, WindowPreferences.DEFAULT_WIDTH_P,height );
		JLabel label_title = WindowPreferences.mainTitle("Configures");
		JButton button_settings = WindowPreferences.defaultButton("/resources/icon_config.png");
		button_settings.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				try {
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					@SuppressWarnings("unused")
					InitialConfigWindows icw = new InitialConfigWindows();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JButton button_home = WindowPreferences.defaultButton("/resources/icon_home.png");
		button_home.setPreferredSize(new Dimension(width,WindowPreferences.ICON_SIZE));
		button_home.setHorizontalAlignment(SwingConstants.LEFT);
		panel_header.add(button_home);
		panel_header.add(label_title);
		panel_header.add(button_settings);
		return panel_header;
	}
	
	private JPanel createItem(String iconID, String title, int ID) throws Exception {
		final JPanel panel_row = new JPanel();
		panel_row.setPreferredSize(new Dimension(330,55));
		panel_row.setBackground(new Color(0,0,0,20));
		
		Image icon_main = ImageIO.read(ConfiguresWindows.class.getResource(iconID));
		JLabel label_iconmain = new JLabel(new ImageIcon(icon_main.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		panel_row.add(label_iconmain);
		
		JLabel label_title = new JLabel(title);
		label_title.setPreferredSize(new Dimension(250,50));
		label_title.setForeground(Color.white);
		label_title.setFont(new Font("Calibri", Font.BOLD, 20));
		panel_row.add(label_title);
		
		JLabel label_iconselect = new JLabel();
		label_iconselect.setPreferredSize(new Dimension(40,40));
		labelSelected[ID]= label_iconselect;
		panel_row.add(label_iconselect);	
		
		panel_row.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	panel_row.setBackground(new Color(0,0,0,40));
		    }
			@Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	panel_row.setBackground(new Color(0,0,0,20));
		    }
		});
		return panel_row;
	}
	
	private JPanel configButtonLock() throws Exception{
		JPanel container_lock = WindowPreferences.defaultPanel(0, FlowLayout.CENTER, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_HEIGHT_P);
		button_configures_lock = WindowPreferences.defaultButton("/resources/icon_lockclose.png");
		button_configures_lock.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				Info info=Info.getInstance();
				Vector<AbsModeler> models=info.getListSchemesSelected();
                //info.reset();
                for (AbsModeler m:models){
                	
                	if (!m.calculateModeler(info.getDatasetSelected())){
                		info.addNotComputedSchemes(m);
                	}
                	else
                	{

                		m.saveModel(Config.InitialSettings.getDirWorking()+m.getName()+".txt");
                	}
                }
                info.setFilteredBestSchemes();
                try {
					FilteredSchemesWindows fs=new FilteredSchemesWindows();
	                frame.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		container_lock.add(button_configures_lock);	
		return container_lock;
	}
	
	private JPanel configButtonExit() throws Exception{
	JPanel panel_exit = WindowPreferences.defaultPanel(0, FlowLayout.RIGHT, WindowPreferences.DEFAULT_WIDTH_P, WindowPreferences.DEFAULT_WIDTH_P);
	JButton button_configures_exit = WindowPreferences.defaultButton("/resources/icon_exit.png");
		button_configures_exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
		panel_exit.add(button_configures_exit);
		return panel_exit;
	}
}
