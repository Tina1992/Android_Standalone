package com.example.prediction;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;

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

	private JFrame frame;
	private JButton button_configures_lock;
	private final int height = 475;
	private final int width = 375;

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
		
		configFrame();
		
		JPanel panel_title = new JPanel();
		panel_title.setBackground(new Color(0,0,0,0));
		panel_title.setPreferredSize(new Dimension(width,50));
		panel_title.add(configTitle());
		panel_title.add(this.configButtonSettings());
		frame.getContentPane().add(panel_title);
		
		final JPanel panel_library = createItem("/resources/icon_library.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[0]);
		panel_library.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//DE ESTA MANERA FUNCIONA EL HACER CLICK.  
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
		frame.getContentPane().add(new AlphaContainer(panel_library));
		JPanel panel_file = createItem("/resources/icon_file.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[1]);
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
		
        frame.getContentPane().add(new AlphaContainer(panel_file));
        JPanel panel_attribute = createItem("/resources/icon_attribute.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[2]);
        
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
        frame.getContentPane().add(new AlphaContainer(panel_attribute));
        JPanel panel_schemes = createItem("/resources/icon_schemes.png", Config.AppSettings.TITLE_CONFIGURES_ITEMS[3]);
        frame.getContentPane().add(new AlphaContainer(panel_schemes));
        
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
        
        configButtonLock();
        
        JPanel container_exit = new JPanel();
        ((FlowLayout)container_exit.getLayout()).setAlignment(FlowLayout.RIGHT);
        container_exit.setPreferredSize(new Dimension(width - 20,50));
        container_exit.setBackground(new Color(0,0,0,0));
        container_exit.add(configButtonExit());
        
        frame.getContentPane().add(container_exit);
	
	}
	
	private JPanel createItem(String iconID, String title) throws Exception {
		final JPanel panel_row = new JPanel();
		panel_row.setPreferredSize(new Dimension(330,55));
		
		/** CON ESTA LINEA HACEMOS TRANSPARENTE EL FONDO DEL PANEL. */
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
		panel_row.add(label_iconselect);	
		
		panel_row.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	panel_row.setBackground(new Color(0,0,0,40));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	panel_row.setBackground(new Color(0,0,0,20));
		    }
		});
		return panel_row;
	}
	
	private void configFrame() throws Exception{
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ConfiguresWindows.class.getResource("/resources/ic_launcher.png")));
		frame.setTitle("Prediction");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(width,height));
		
		Image image_background = ImageIO.read(ConfiguresWindows.class.getResource("/resources/background.jpg"));
		JLabel background = new JLabel(new ImageIcon(image_background.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		background.setPreferredSize(new Dimension(width,height));
		frame.setContentPane(background);
		frame.getContentPane().setLayout(new FlowLayout());	
	}
	
	private JLabel configTitle(){
		JLabel label_configures_title = new JLabel("Configures");
		label_configures_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_configures_title.setForeground(Color.WHITE);
		label_configures_title.setFont(new Font("Calibri", Font.BOLD, 25));
		return label_configures_title;
	}

	private JButton configButtonSettings() throws Exception{
		/** get the icon from resources */
		Image icon_settings = ImageIO.read(ConfiguresWindows.class.getResource("/resources/icon_configuration.png"));
		
		/** CON ESTA LINEA SE AJUSTA LA IMAGEN DEL ICONO, AL TAMAÑO DEL BOTON */
		JButton button_configures_setting = new JButton(new ImageIcon(icon_settings.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		button_configures_setting.setBorder(BorderFactory.createEmptyBorder());
		button_configures_setting.setPreferredSize(new Dimension(30,30));
		
		/** CON ESTA LINEA LE QUITAMOS EL FONDO AL BOTON. */
		button_configures_setting.setContentAreaFilled(false);
		button_configures_setting.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		return button_configures_setting;
	}

	private void configButtonLock() throws Exception{
		JPanel container_lock = new JPanel();
		container_lock.setPreferredSize(new Dimension(width,50));
		container_lock.setBackground(new Color(0,0,0,0));
		Image icon = ImageIO.read(ConfiguresWindows.class.getResource("/resources/icon_lockclose.png") );
		button_configures_lock = new JButton(new ImageIcon(icon.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		button_configures_lock.setBorder(BorderFactory.createEmptyBorder());
		button_configures_lock.setPreferredSize(new Dimension(30,30));
		button_configures_lock.setContentAreaFilled(false);
		container_lock.add(button_configures_lock);
		button_configures_lock.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				//DE ESTA MANERA FUNCIONA EL HACER CLICK.
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
		frame.getContentPane().add(new AlphaContainer(container_lock));
	}
	
	private JButton configButtonExit() throws Exception{
		Image icon = ImageIO.read(ConfiguresWindows.class.getResource("/resources/ic_launcher.png"));
		JButton button_configures_exit = new JButton(new ImageIcon(icon.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		button_configures_exit.setBorder(BorderFactory.createEmptyBorder());
		button_configures_exit.setPreferredSize(new Dimension(30,30));
		button_configures_exit.setContentAreaFilled(false);
		button_configures_exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("hizo click en exit");
			}
		});
		return button_configures_exit;
	}
}
