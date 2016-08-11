package com.example.prediction.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

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
	private double umbral = 0.1;
	
	
	class CustomRenderer extends BarRenderer
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public CustomRenderer() {}
		
		public Paint getItemPaint(final int row, final int column) {			//ROW: SERIE (SCHEME) //COLUMN: CATEGORY (METRIC)
			Integer index1 = 0;
			Integer index2=0;
			if(dataset.getRowCount() <= 1){
				index1 = column;									
				index2 = null;	
			}
			else{
				index1 = column * 2;									
				index2 = (column * 2) + 1;
			}
			if(column < 4){
				  if(row == bestResults.elementAt(index1)){
					   return Config.Graphic.GRAPHIC_BAR_COLOR_BESTRESULT1;
				   }
				   if(index2 !=null && row == bestResults.elementAt(index2)){
					   return Config.Graphic.GRAPHIC_BAR_COLOR_BESTRESULT2;
					   
				   }  
			  }
				return Config.Graphic.GRAPHIC_BAR_COLOR;																					  
		   }
		
		//SHOW VALUES ON BAR WHICH RESULTS ARE BETTER!!
		public CategoryItemLabelGenerator getItemLabelGenerator(int i, int j){ 				//J = COLUMN = METRIC
			int index1 = 0;
			if(dataset.getRowCount() > 1){ 
				index1 = j * 2;	
			}
			else
				index1=j;
			if(bestResults.elementAt(index1) == i || (dataset.getValue(j, j)).doubleValue() < umbral )	
				return new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("##.#####"));
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
				bestResults.add(values.indexOf(auxValues.elementAt(auxValues.size()-1)));	
				if (auxValues.size()>1){
					bestResults.add(values.indexOf(auxValues.elementAt(auxValues.size()-2)));}
			}
			else {
				bestResults.add(values.indexOf(auxValues.elementAt(0)));
				if (values.size()>1){
					bestResults.add(values.indexOf(auxValues.elementAt(1)));}
			}
			auxValues.removeAllElements();
			values.removeAllElements();
		}
		max += max/10;				
		min = 0;
	}
	
	protected JFreeChart createChart(String chartTitle, String axisX, String axisY) throws Exception{
		
		// create the chart... 
		final JFreeChart chart = ChartFactory.createBarChart(
				chartTitle,       
				axisX,               
				axisY,                  
	            dataset,                 
	            PlotOrientation.VERTICAL,
	            true, true, false );
	   TextTitle title = new TextTitle(chartTitle);
	   title.setFont(new Font("Calibri", Font.ITALIC,18));
	   chart.setTitle(title);
		
		
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
	
	@SuppressWarnings("deprecation")
	protected void customizeChart(JFreeChart chart) throws Exception{
		height = Config.Graphic.GRAPHIC_BAR_HEIGHT;
		width =  Config.Graphic.GRAPHIC_BAR_WIDTH;
		settings();
		
		Image img = ImageIO.read(BarGraphics.class.getResource("/resources/machine_learning.png"));
		chart.setBackgroundImage(img);
		chart.setBackgroundImageAlpha(0.2F);
		
		CategoryPlot plot = chart.getCategoryPlot();
	    plot.setOutlineVisible(false);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    plot.setBackgroundImage(img);
	    plot.setBackgroundAlpha(0.2F);
	    
	    
	    
	    CategoryItemRenderer renderer = new CustomRenderer();
	    ((BarRenderer) renderer).setMaximumBarWidth(maxBarWidth);
		((BarRenderer) renderer).setItemMargin(itemMargin(dataset.getColumnCount(),dataset.getRowCount()));
	    
	    if(dataset.getRowCount() < 5) {
	    	chart.getLegend().setFrame(BlockBorder.NONE);	
			//TOP-LEFT-BOTTOM-RIGHT
			//chart.getLegend().setItemLabelPadding(new RectangleInsets(5.0,2.0,3.0,width));
		}
	    
	    for(int i=0;i<dataset.getRowCount();i++){
		    renderer.setSeriesPaint(i, Color.GRAY);			//BUSCAR MEJOR COLOR!!
		}
	    
	    
	    renderer.setItemLabelsVisible(true);
	    renderer.setBaseItemLabelsVisible(true);
	    renderer.setItemLabelFont(new Font("Calibri", Font.ITALIC, 18));
	    plot.setRenderer(renderer);
	  
	    //  chart.getCategoryPlot().setRenderer(render);
	    //SET RANGE AXIS
	    ValueAxis yAxis = plot.getRangeAxis();
	    yAxis.setLabelFont(new Font("Calibri", Font.PLAIN,16));
	    yAxis.setLabelPaint(Color.GRAY);
	    max = max*1.10;
	    yAxis.setRange(min, max); 
	    
	    CategoryAxis xAxis = plot.getDomainAxis();
	    xAxis.setLabelFont(new Font("Calibri", Font.PLAIN,16));
	    xAxis.setLabelPaint(Color.GRAY);
	    
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
		bestResults.removeAllElements();
		return getChart(series, 
				Config.Graphic.GRAPHIC_BAR_TITLE_CHART,
				Config.Graphic.GRAPHIC_BAR_TITLE_AXISX,  
				Config.Graphic.GRAPHIC_BAR_TITLE_AXISY );	
	}
	
	
}
