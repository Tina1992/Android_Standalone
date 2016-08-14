package com.example.prediction;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
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
	private JLabel[] labelSelected;
	private JLabel label_text;
	private Image icon_check;
	private boolean[] enabledItems = new boolean[4];
	private boolean[] selectedItems;
	private Integer[] lastSelectedItems;
	private Info info = Info.getInstance();
	
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
		JPanel panel = new JPanel();
		panel = new BackgroundPanel("resources/background.jpg");
		
		frame = WindowPreferences.mainFrame(FlowLayout.CENTER);
		frame.getContentPane().add(panel);
		
		icon_check = ImageIO.read(ConfiguresWindows.class.getResource("/resources/icon_checked.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		int length = Config.AppSettings.TITLE_CONFIGURES_ITEMS.length;
		enabledItems = new boolean[length];
		selectedItems = new boolean[length];
		lastSelectedItems = new Integer[length];
		labelSelected = new JLabel[length];
		panel.add(new AlphaContainer(configHeader()));
		panel.add(new AlphaContainer(createItem("/resources/icon_library.png", Config.AppSettings.ITEM_CODE_SELECT_LIBRARY)));
		panel.add(new AlphaContainer(createItem("/resources/icon_file.png", Config.AppSettings.ITEM_CODE_SELECT_FILE_DATASET)));
		panel.add(new AlphaContainer(createItem("/resources/icon_attribute.png", Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT)));
		panel.add(new AlphaContainer(createItem("/resources/icon_schemes.png", Config.AppSettings.ITEM_CODE_SELECT_SCHEMES)));
		panel.add(new AlphaContainer(configButtonLock() ));
		panel.add(new AlphaContainer(configButtonExit()));
		panel.add(new AlphaContainer(configPanelInfo()));
	}
	
	private JComponent configPanelInfo() throws Exception {
		// TODO Auto-generated method stub
		JPanel panel_info = WindowPreferences.defaultPanel(	0,
															FlowLayout.LEFT,
															WindowPreferences.DEFAULT_WIDTH_P,
															125);
		Image img = ImageIO.read(ConfiguresWindows.class.getResource("/resources/icon_alert_exception.png"));
		label_text = new JLabel();
		label_text.setIcon(new ImageIcon(img.getScaledInstance(WindowPreferences.ICON_SIZE, WindowPreferences.ICON_SIZE, Image.SCALE_SMOOTH)));
		label_text.setFont(new Font("Calibri",Font.ITALIC, 17));
		label_text.setBackground(new Color(0,0,0,0));
		label_text.setOpaque(false);
		label_text.setVisible(false);
		panel_info.add(new AlphaContainer(label_text));	
		return panel_info;
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
	
	private JPanel createItem(String iconID, final int ID) throws Exception {
		final JPanel panel_row = new JPanel();
		panel_row.setPreferredSize(new Dimension(330,55));
		panel_row.setBackground(new Color(0,0,0,20));
		panel_row.setLayout(new FlowLayout());
		
		Image icon_main = ImageIO.read(ConfiguresWindows.class.getResource(iconID));
		JLabel label_iconmain = new JLabel(new ImageIcon(icon_main.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		panel_row.add(label_iconmain);
		
		JLabel label_title = new JLabel(Config.AppSettings.TITLE_CONFIGURES_ITEMS[ID]);
		label_title.setPreferredSize(new Dimension(220,50));
		label_title.setForeground(Color.white);
		label_title.setFont(new Font("Calibri", Font.BOLD, 19));
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
		panel_row.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){ 
				label_text.setVisible(false);
				final Info info=Info.getInstance();
				CharSequence[] aux = null;
				boolean multi = false;
				boolean show = false;
				switch(ID){
				case Config.AppSettings.ITEM_CODE_SELECT_LIBRARY:
					show=true;
					info.setLibraries();
					aux=info.getListLibraries();
					break;
				case Config.AppSettings.ITEM_CODE_SELECT_FILE_DATASET:
					if(enabledItems[ID]){
						info.setListFilesDataset(); 
						aux=info.getListFilesDataset();	
						show = true;
					}
					else { 
						label_text.setVisible(true);
						label_text.setText("Please, select the library before");
					}
					break;
				case Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT:
					if(enabledItems[ID]){
						info.setListAttributes(info.getDatasetSelected());
						aux=info.getListAttributes();
						show = true;
					}
					else {
						label_text.setVisible(true);
						label_text.setText("Please, select the file dataset before");
					}	
					break;
				case Config.AppSettings.ITEM_CODE_SELECT_SCHEMES:
					if(enabledItems[ID]){
						info.setListSchemes();
						aux=info.getListSchemes();
						show = true;
						multi=true;
					}
					else {
						label_text.setVisible(true);
						label_text.setText("Please, select the predicted attribute before");
					}	
				}
				final CharSequence[] list = aux;
				try {
					if(show) {
					final SMFrame smp = new SMFrame(Config.AppSettings.TITLE_CONFIGURES_ITEMS[ID], list,multi);
					smp.setLocation(frame.getLocation().x + frame.getWidth() + 10, frame.getLocation().y + 100);
					smp.pack();
					smp.setVisible(true);
					smp.getAcceptButton().addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent event) {
							// TODO Auto-generated method stub
							switch(ID){
							case Config.AppSettings.ITEM_CODE_SELECT_LIBRARY:
								try {
									controlSelectLibrary(smp.getValue(), list[smp.getValue()].toString());	
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								smp.setVisible(false);
								break;
							case Config.AppSettings.ITEM_CODE_SELECT_FILE_DATASET:
								try {
									controlPickFile(smp.getValue(), list[smp.getValue()].toString());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								smp.setVisible(false);
								break;
							case Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT:
								try {
									controlSelectPredAtt(smp.getValue());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					             smp.setVisible(false);
								break;
							case Config.AppSettings.ITEM_CODE_SELECT_SCHEMES:
								try {
									controlSelectSchemes(smp.getValues());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							labelSelected[ID].setIcon(new ImageIcon(icon_check));
						}
						
					});
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();}
				
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
				label_text.setVisible(true);
				label_text.setText("Processing...");
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
					@SuppressWarnings("unused")
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
	JPanel panel_exit = WindowPreferences.defaultPanel(0, FlowLayout.RIGHT, WindowPreferences.DEFAULT_WIDTH_P, 40);
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

	
	
	private void controlSelectLibrary(int itemSelect, String item) throws Exception{
		int index = Config.AppSettings.ITEM_CODE_SELECT_LIBRARY;
		if(lastSelectedItems[index] == null || lastSelectedItems[index] != itemSelect) {					
			info.setLibrarySelected(item);
			info.setListFilesDataset();
			info.setListSchemes();
			lastSelectedItems[index]=itemSelect;
			info.getLibrarySelected().setNumericalTypes();
			info.getLibrarySelected().setCategoricalTypes();	
			selectedItems[index]=true;
			controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_FILE_DATASET, true);
			controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT, false);
			controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_SCHEMES, false);
		}	

	}
	
	private void controlPickFile(int itemSelect, String item) throws Exception{
		int index = Config.AppSettings.ITEM_CODE_SELECT_FILE_DATASET;
		if(lastSelectedItems[index] == null || lastSelectedItems[index] != itemSelect) {					
			try {
				info.setFileDatasetSelected(item);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lastSelectedItems[index]=itemSelect;
			selectedItems[index]=true;
			controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT, true);
			controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_SCHEMES, false);
		}			
	}
	
	private void controlSelectPredAtt(int itemSelect) throws Exception{
		int index = Config.AppSettings.ITEM_CODE_SELECT_PREDICTED_ATT;
		if(lastSelectedItems[index] == null || lastSelectedItems[index] != itemSelect) {
			info.setAttributeSelected(itemSelect);
			lastSelectedItems[index]=itemSelect;	 
			selectedItems[index]=true;
			controlReSelect(Config.AppSettings.ITEM_CODE_SELECT_SCHEMES, true);
		}	
		
	}
	
	private void controlSelectSchemes(Vector<Integer> schemes) throws Exception {
		int index = Config.AppSettings.ITEM_CODE_SELECT_SCHEMES;
		info.setSchemesSelected(schemes);
		if(info.getListSchemesSelected().size() != 0) {
			selectedItems[index]=true;
			labelSelected[index].setIcon(new ImageIcon(icon_check));
			Image icon = ImageIO.read(ConfiguresWindows.class.getResource("/resources/icon_lockopen.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			button_configures_lock.setIcon(new ImageIcon(icon));
		}
				
	}

	private void controlReSelect(int index, boolean enabled) throws Exception {
		enabledItems[index]=enabled;
		if(selectedItems[index]){
			selectedItems[index] = false;
			lastSelectedItems[index]=null;
			labelSelected[index].setIcon(null);
			Image icon = ImageIO.read(ConfiguresWindows.class.getResource("/resources/icon_lockclose.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			button_configures_lock.setIcon(new ImageIcon(icon));
		}

	}

}
