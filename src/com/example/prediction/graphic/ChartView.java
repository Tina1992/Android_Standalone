package com.example.prediction.graphic;


import com.example.prediction.logica.Config;

import javafx.scene.canvas.Canvas;
import javafx.scene.chart.AreaChart;
import javafx.scene.image.ImageView;


public class ChartView extends ImageView {
	
	
	//private Bitmap              bitmap;
    private RectangularShape           rectArea;
    private Canvas              canvas;
    private AreaChart          chart;

	public ChartView(int type, AreaChart chart) {
		// TODO Auto-generated constructor stub
		super();
		
		switch(type){
		case Config.Graphic.GRAPHIC_TYPE_BAR:
			bitmap = Bitmap.createBitmap(Config.Graphic.GRAPHIC_BAR_WIDTH, Config.Graphic.GRAPHIC_BAR_HEIGHT, Bitmap.Config.ARGB_8888);
	        rectArea = new RectangularShape(0.0, 0.0, Config.Graphic.GRAPHIC_BAR_WIDTH, Config.Graphic.GRAPHIC_BAR_HEIGHT);
	        break;
	        
		case Config.Graphic.GRAPHIC_TYPE_LINE:
			bitmap = Bitmap.createBitmap(Config.Graphic.GRAPHIC_LINE_WIDTH, Config.Graphic.GRAPHIC_LINE_HEIGHT, Bitmap.Config.ARGB_8888);
	        rectArea = new RectangularShape(0.0, 0.0, Config.Graphic.GRAPHIC_LINE_WIDTH, Config.Graphic.GRAPHIC_LINE_HEIGHT);
	        break;
		}
		
	}
	
	public void drawChart( AreaChart chart )
    {
        canvas = new Canvas();
        this.chart = chart;   
        this.chart.draw(canvas, rectArea);
        setImageBitmap(bitmap);
    }
	
    @Override
    protected void onDraw( Canvas canvas )
    {
        super.onDraw(canvas);               
    }


                  
    
}
