package com.example.prediction.graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.example.prediction.logica.Config;
import com.example.prediction.logica.database.AbsDatabase;
import com.example.prediction.logica.metrics.simple_metrics.SimpleERMetric;
import com.example.prediction.logica.models.AbsModeler;

public class LineGraphics extends AbsGraphics {
	private int bestPrediction = 0;
	private final static int limit = 300; 
	private final static int instancesLC = 20;
	private String image;
	
	
	public LineGraphics() throws Exception {
		// TODO Auto-generated constructor stub
		super();
		
	}
	
	@Override
	protected JFreeChart createChart(String chartTitle, String axisX, String axisY) throws Exception {
		// TODO Auto-generated method stub
		
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,			
				axisX,		
				axisY,
				dataset,
				PlotOrientation.VERTICAL,
				true,true,false );
		
		customizeChart(lineChart);
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( width , height ) );
		return lineChart;
	}

	protected  void customizeChart(JFreeChart chart) throws Exception{
		
		double factorRange = 5.0;
		height = 367;
		width = 560;
		
		chart.setBackgroundPaint(Color.white);
		
		String workingDir = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		
		//BufferedImage img = ImageIO.read(new File(workingDir + separator + image));
	
		CategoryPlot plot = chart.getCategoryPlot();
	    //plot.setBackgroundImage(img);
	    //plot.setBackgroundImageAlpha(0.3F);
	    //plot.setBackgroundPaint(Color.white);
	    plot.setOutlineVisible(false);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    
	    CategoryAxis categoryAxis = (CategoryAxis) plot.getDomainAxis();
	    categoryAxis.setVisible(false);
	  
	    CategoryItemRenderer renderer = plot.getRenderer();
		// FOR REAL VALUES:
		renderer.setSeriesPaint(0, Color.black);
		renderer.setSeriesStroke(0, new BasicStroke(3));
				
		//OTHERS:
		for(int s=1; s< dataset.getRowCount(); s++)
			renderer.setSeriesStroke(s, new BasicStroke(1.5F));
		
		//FOR BEST PREDICTED VALUES
		renderer.setSeriesStroke(bestPrediction, new BasicStroke(3));
		
		//RANGE VALUES:
		ValueAxis yAxis = plot.getRangeAxis();
		yAxis.setRange(min - factorRange, max + factorRange); 	//definir mejor el factor range
		
		
	}

	
	private void configureErrorPrediction(AbsDatabase trainingSet, int indexClass) throws Exception {
		// TODO Auto-generated method stub
		min = Double.MAX_VALUE;
		max = Double.MIN_VALUE;
		dataset = new DefaultCategoryDataset( );
		
		int limit = Config.Graphic.GRAPHIC_LINE_LIMIT_INSTANCES;
		
		Vector<Double> actual = trainingSet.getActualValues(indexClass);
		int instances = actual.size();
		if(instances > limit)
			instances = limit;
		Vector<Double> predicted = null;
		
		for(AbsModeler scheme: series){
			for(int v=0; v < instances; v++){
		    	  Integer index = v+1;
		    	  //REAL VALUE:
		    	  double actualValue = actual.elementAt(v);
		    	  dataset.addValue( actualValue , Config.Graphic.GRAPHIC_LINE_LABEL_REALVALUES , index);
		    	  max = Math.max(max,actualValue);
	  	    	  min = Math.min(min, actualValue);
			}
			scheme.calculateModeler(trainingSet);
			predicted = scheme.getPredictedValue(trainingSet);
	      	for(Integer v=0; v < instances; v++){
	      		Integer index = v+1;
	      			
	      		//PREDICTED VALUE:
	      		double predictedValue = predicted.elementAt(v);
	      		dataset.addValue( predictedValue , Config.Graphic.GRAPHIC_LINE_PREDICTION + " - " + scheme.getName() , index );
	  	    	  
	      		max = Math.max(max,predictedValue);
	      		min = Math.min(min, predictedValue);
	      	}
		}	
	}
	
	

	private void configureLearningCurve(AbsDatabase trainingSet) throws Exception {
		dataset = new DefaultCategoryDataset();
		int groupInstances =5;// Config.Graphic.GRAPHIC_LINE_INSTANCES_LEARNING_CURVE;
		//int last = trainingSet.numInstances();
		SimpleERMetric rae=new SimpleERMetric();
		int traininglimit=(int) Math.ceil(trainingSet.numInstances()*0.6);
		int CVlimit = (int) Math.ceil(trainingSet.numInstances()*0.8);
		AbsDatabase newTrainingSet = trainingSet.subDatabase(0, traininglimit);
		AbsDatabase CVSet = trainingSet.subDatabase(traininglimit, CVlimit);
		
		int trainingNum = (newTrainingSet.numInstances() / groupInstances);
		int cvNum = CVSet.numInstances() / groupInstances;
		int instances = trainingNum;
		if (trainingNum>cvNum){
			instances=cvNum;
		}
		
		
		for(AbsModeler scheme: series){
			for(int i=1; i<=instances; i++){
				Integer cantInstances = groupInstances * i; 
				//int first = cantInstances+1;
				//if(i == instances)
					//newTrainingSet =  trainingSet;
				//else
				
				/*Si se quiere hacer CVvalidation tenemos que:
				 * iterar k veces:
				 * 		Mezclar los instances
				 * 		Obtener training y CV
				 * 		scheme.calculatemodel(training.subDatabase(0, cantInstances));
				 * 		training : value+calculate(training.subDatabase(0, cantInstances), scheme);
				 * 		CV : value + calculate(CVSet, scheme);
				 * value/k;
				 * 
				 * 		
				 */
				
				
				AbsDatabase mTDataset =  newTrainingSet.subDatabase(0, cantInstances);
				//AbsDatabase mCVDatabase = CVSet.subDatabase(0, cantInstances);
				
				//Double value= rae.calculate(mTDataset, scheme,5);	//CV con fold=5
				
				scheme.calculateModeler(mTDataset);
				Double value = rae.calculate(mTDataset, scheme);
				
				dataset.addValue(value, Config.Graphic.GRAPHIC_LINE_LABEL_TRAINING, cantInstances);
				
				max = Math.max(max,value);
		    	min = Math.min(min, value);
		    	
		    	//value = rae.calculate(mCVDatabase, scheme,5);
		    	value=rae.calculate(CVSet, scheme);
				
				dataset.addValue(value, Config.Graphic.GRAPHIC_LINE_LABEL_CROSSVALIDATION, cantInstances);
				
				max = Math.max(max,value);
		    	min = Math.min(min, value);
			}	
		}
		
		
	}
	
	public JFreeChart graphedLearningCurve(AbsDatabase trainingSet, Vector<AbsModeler> schemes) throws Exception {
		setSeries(schemes);
		configureLearningCurve(trainingSet);
		return getChart(series, 
				Config.Graphic.GRAPHIC_LINE_TITLE_CHART_LC + " - " + schemes.elementAt(0).getName(),
				Config.Graphic.GRAPHIC_LINE_TITLE_AXISX,
				Config.Graphic.GRAPHIC_LINE_TITLE_AXISY );
	}
	
	public JFreeChart graphedErrorPrediction(AbsDatabase trainingSet, Vector<AbsModeler> schemes, int indexClass) throws Exception{
		setSeries(schemes);
		configureErrorPrediction(trainingSet, indexClass);
		return getChart(series, 
				Config.Graphic.GRAPHIC_LINE_TITLE_CHART_EP + " - " + schemes.elementAt(0).getName(),
				Config.Graphic.GRAPHIC_LINE_TITLE_AXISX,
				Config.Graphic.GRAPHIC_LINE_TITLE_AXISY );
	}
}

	
	


	
	
