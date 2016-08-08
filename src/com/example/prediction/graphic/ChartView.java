/*package com.example.prediction.graphic;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.jfree.chart.JFreeChart;

import com.example.prediction.logica.Config;

import javafx.scene.canvas.Canvas;
import javafx.scene.chart.AreaChart;
import javafx.scene.image.ImageView;


public class ChartView extends ImageView {
	
	
	private BufferedImage              bitmap;
    private Rectangle           rectArea;
    private Graphics2D              canvas;
    private JFreeChart          chart;

	public ChartView(int type, JFreeChart chart2) {
		// TODO Auto-generated constructor stub
		super();
		
		switch(type){
		case Config.Graphic.GRAPHIC_TYPE_BAR:
			bitmap = Bitmap.createBitmap(Config.Graphic.GRAPHIC_BAR_WIDTH, Config.Graphic.GRAPHIC_BAR_HEIGHT, Bitmap.Config.ARGB_8888);
	        rectArea = new Rectangle(Config.Graphic.GRAPHIC_BAR_WIDTH, Config.Graphic.GRAPHIC_BAR_HEIGHT);
	        break;
	        
		case Config.Graphic.GRAPHIC_TYPE_LINE:
			bitmap = Bitmap.createBitmap(Config.Graphic.GRAPHIC_LINE_WIDTH, Config.Graphic.GRAPHIC_LINE_HEIGHT, Bitmap.Config.ARGB_8888);
	        rectArea = new Rectangle(Config.Graphic.GRAPHIC_LINE_WIDTH, Config.Graphic.GRAPHIC_LINE_HEIGHT);
	        break;
		}
		
	}
	
	public void drawChart( JFreeChart chart2 )
    {
        canvas = new Graphics2D();
        this.chart = chart2;   
        this.chart.draw(canvas, rectArea);
        setImage(bitmap);
    }
	


                  
    
}*/
