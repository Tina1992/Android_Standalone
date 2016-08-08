package com.example.prediction.graphic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.example.prediction.logica.metrics.collection.MetricsCollection;
import com.example.prediction.logica.models.AbsModeler;

public abstract class AbsGraphics {
	
	protected double min;
	protected double max;
	protected DefaultCategoryDataset dataset;
	protected int height;
	protected int width;
	protected Vector<AbsModeler> series;
	protected MetricsCollection metricsEvaluation;
	
	private Vector<BufferedImage> images = new Vector<BufferedImage>();
	
	class Frame extends ApplicationFrame {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected Frame( String applicationTitle ) throws Exception {
			super(applicationTitle);
		}
	}
	
	protected JFreeChart getChart(Vector<AbsModeler> series, String chartTitle, String axisX, String axisY) throws Exception{
		JFreeChart chart = createChart(chartTitle,axisX,axisY);
		customizeChart(chart);
		return chart;	
	}
	
	protected void display(AbsModeler[] series, String chartTitle, String axisX, String axisY, String appTitle) throws Exception{
		JFreeChart chart = createChart(chartTitle,axisX,axisY);
		customizeChart(chart);
		
		Frame demo =  new Frame(appTitle);	 
		// add the chart to a panel...
	    ChartPanel chartPanel = new ChartPanel(chart);      
		chartPanel.setPreferredSize( new java.awt.Dimension( width , height ) );
		demo.setContentPane(chartPanel);
		
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);
	}
	
	/* ABSTRACT METHODS  */
		
	
	public void save(String nameFile) throws FileNotFoundException, IOException{
		String directory = System.getProperty("user.dir");
		String extension="png";
		int contador = 1;
		for(BufferedImage img: images){
			nameFile = nameFile + "_" + contador;									//VER QUE ONDA CON LAS PISADURAS, Y EN QUE SUBCARPETA VAN A ESTAR ESTAS IMAGENES. 
			contador++;
			File outputfile = new File(directory + nameFile + "." + extension);
			ImageIO.write(img, extension, outputfile);
		}		
	}
		
	public void setSeries(Vector<AbsModeler> series){	
		this.series = series; 
	}
	
	protected abstract JFreeChart createChart(String chartTitle, String axisX, String axisY) throws Exception;
	protected abstract void customizeChart(JFreeChart chart) throws Exception;

}
