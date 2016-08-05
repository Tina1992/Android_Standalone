package com.example.prediction.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import com.example.prediction.logica.Config;
import com.example.prediction.logica.database.AbsDatabase;
import com.example.prediction.logica.metrics.abstracts.AbsMetric;
import com.example.prediction.logica.metrics.abstracts.Required;
import com.example.prediction.logica.metrics.collection.MetricsCollection;
import com.example.prediction.logica.models.AbsModeler;

public class BarGraphics extends AbsGraphics {
	
	public BarGraphics() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Vector<Integer> bestResults = new Vector<Integer>();
	private double maxBarWidth = 0.05;
	private AbsMetric[] categorys;
	
	
	class CustomRenderer extends BarRenderer
	{
		public CustomRenderer() {}
		
		public Paint getItemPaint(final int row, final int column) {
			int index1 = column * 2;									//ROW: SERIE (BARRA)
			  int index2 = (column * 2) + 1;							//COLUMN: CATEGORY (METRIC)
			  if(column < 4){
				  if(row == bestResults.elementAt(index1)){
					   return new Color(133,47,4);
				   }
				   if(row == bestResults.elementAt(index2)){
					   return new Color(174,60,4);
					   
				   }  
			  }
				return Color.gray;																					  
		   }
		
		//SHOW VALUES ON BAR WHICH RESULTS ARE BETTER!!
		public CategoryItemLabelGenerator getItemLabelGenerator(int i, int j){ 				//J = COLUMN = METRIC
			int index1 = j * 2;																//I = ROW = SCHEME
			int index2 = (j * 2) + 1;
			if(bestResults.elementAt(index1) == i || bestResults.elementAt(index2) == i)	
				return new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("##.###"));
			return null;
		} 
	}

	//DEFINE EL ANCHO DE LAS BARRAS 
	private double itemMargin(int categorys, int series){
		if (categorys == 1 || (categorys == 2 && series == 2) )
			return -1.2;
		else
			if(categorys == 2 && (series == 2 || series == 3))
				return -0.5;
			else
				if( (categorys == 2 && series == 5) || 
						(categorys == 3 && (series == 2 || series == 3)) ||
							(categorys == 4 && series == 2) )
					return 0;
		return 0.2;
	}

	private void settings(){
		min = Double.MAX_VALUE;
		max = Double.MIN_VALUE;
		double value=0;
		Vector<Double> values = new Vector<Double>();
		Vector<Double> auxValues = new Vector<Double>();
																							//row = serie
		for(int column=0; column < dataset.getColumnCount(); column++){						//column = category
			System.out.println("iteracion columna...");
			Required req = categorys[column].getRequired();
			for(int row=0; row < dataset.getRowCount(); row++) {
				value = dataset.getValue(row, column).doubleValue();
				values.add(value);
				auxValues.add(value);
				if(value < min)
					min = value;
				if(value > max)
					max = value;	
			}
			Collections.sort(auxValues);
			if(req.equals(Required.MAX)){
				bestResults.add(values.indexOf(auxValues.elementAt(auxValues.size()-1)));	//PLANTEAR EXCEPCION SI SOLO TENGO UN SCHEME
				bestResults.add(values.indexOf(auxValues.elementAt(auxValues.size()-2)));
			}
			else {
				bestResults.add(values.indexOf(auxValues.elementAt(0)));
				bestResults.add(values.indexOf(auxValues.elementAt(1)));
			}
			auxValues.removeAllElements();
			values.removeAllElements();
			
			
		}
		for(Integer i:bestResults)
			System.out.print("" + i + ", ");
		System.out.println();
		max += max/10;				//VER COMO PONER POR DEFECTO
		min = 0;
		System.out.println("min: "+min +", max: "+ max);
	}
	
	protected JFreeChart generateChart(String chartTitle, String axisX, String axisY) throws Exception{
		
		// create the chart... 
		final JFreeChart chart = ChartFactory.createBarChart(
				chartTitle,       
				axisX,               
				axisY,                  
	            dataset,                 
	            PlotOrientation.VERTICAL,
	            true, true, false );
	   
        ChartPanel chartPanel = new ChartPanel( chart );
	    chartPanel.setPreferredSize( new java.awt.Dimension( width , height ) );
	    return chart;
	}
	 
	protected  void configureDataset(AbsDatabase trainingSet) throws Exception{
		dataset = new DefaultCategoryDataset();
		Vector<AbsModeler> axisY = series;										
		  double value=0;
		  for(int y=0; y< axisY.size(); y++){	
			  for(int x=0; x< categorys.length; x++){
				  value = categorys[x].calculateNormalized(trainingSet, axisY.elementAt(y));			//Training without CV					
				  dataset.addValue(value, axisY.elementAt(y).getName(), categorys[x].getID()); 
			  }
		  }
	}
	
	protected void customizeChart(JFreeChart chart) throws Exception{
		height = 400;
		width = 500;
		settings();
		
		String workingDir = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		BufferedImage img = ImageIO.read(new File(workingDir + separator + "ML.png"));
		
	    chart.setBackgroundImage(img);
	    chart.setBackgroundImageAlpha(0.1F);
	    chart.setBackgroundPaint(Color.white);
	    CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundImage(img);
	    plot.setOutlineVisible(false);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    BarRenderer render = (BarRenderer)plot.getRenderer();
	    
	    CategoryItemRenderer renderer = new CustomRenderer();
	    ((BarRenderer) renderer).setMaximumBarWidth(maxBarWidth);
		((BarRenderer) renderer).setItemMargin(itemMargin(dataset.getColumnCount(),dataset.getRowCount()));
	    
	    if(dataset.getRowCount() < 5) {
	    	chart.getLegend().setFrame(BlockBorder.NONE);	
			//TOP-LEFT-BOTTOM-RIGHT
			chart.getLegend().setItemLabelPadding(new RectangleInsets(5.0,2.0,3.0,width));
		}
	    
	    for(int i=0;i<dataset.getRowCount();i++){
		    renderer.setSeriesPaint(i, Color.GRAY);			//BUSCAR MEJOR COLOR!!
		}
	    
	    plot.setRenderer(renderer);
	    renderer.setItemLabelsVisible(true);
	    renderer.setBaseItemLabelsVisible(true);
	    renderer.setItemLabelFont(new Font("SansSerif", Font.ITALIC, 15));
	
	  
	    //  chart.getCategoryPlot().setRenderer(render);
	    //SET RANGE AXIS
	    ValueAxis yAxis = plot.getRangeAxis();
	    System.out.println("min: "+min +", max: "+ max);
	    yAxis.setRange(min, max);  	    
	}


	
	public JFreeChart graphedErrorPredictionNormalized(AbsDatabase trainingSet, MetricsCollection metricsEvaluation) throws Exception{
		Vector<AbsMetric> metrics = metricsEvaluation.ErrorPredictionNormalizedMetrics();
		return graphed(metrics, trainingSet);	
	}
	
	public JFreeChart graphedErrorPredictionScale(AbsDatabase trainingSet, MetricsCollection metricsEvaluation) throws Exception{
		Vector<AbsMetric> metrics = metricsEvaluation.ErrorPredictionScaleMetrics();
		return graphed(metrics, trainingSet);	
	}
	
	public JFreeChart graphedRelationData(AbsDatabase trainingSet, MetricsCollection metricsEvaluation) throws Exception{
		Vector<AbsMetric> metrics = metricsEvaluation.RelationDataMetrics();
		return graphed(metrics, trainingSet);
	}
	
	private JFreeChart graphed(Vector<AbsMetric> metrics, AbsDatabase trainingSet) throws Exception{
		int index=0;
		categorys = new AbsMetric[metrics.size()];
		for(AbsMetric m:metrics){	
			categorys[index]=m;
			index++;
		}

		configureDataset(trainingSet);
		return getChart(series, 
				Config.Graphic.GRAPHIC_BAR_TITLE_CHART,
				Config.Graphic.GRAPHIC_BAR_TITLE_AXISX,  
				Config.Graphic.GRAPHIC_BAR_TITLE_AXISY );	
	}

	@Override
	protected JFreeChart createChart(String chartTitle, String axisX, String axisY) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
