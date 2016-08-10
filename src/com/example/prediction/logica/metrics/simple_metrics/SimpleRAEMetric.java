package com.example.prediction.logica.metrics.simple_metrics;

import java.util.Vector;

import com.example.prediction.logica.metrics.abstracts.AbsSimpleMetric;
import com.example.prediction.logica.metrics.abstracts.Info;
import com.example.prediction.logica.metrics.abstracts.Representation;
import com.example.prediction.logica.metrics.abstracts.Required;
import com.example.prediction.logica.metrics.abstracts.Type;
import com.example.prediction.logica.metrics.collection.MetricsCollection;

public class SimpleRAEMetric extends AbsSimpleMetric {
	
	public SimpleRAEMetric(){
		super(MetricsCollection.RAE, Required.MIN, Representation.NORMALIZED, Type.REGRESSION, Info.ERROR_PREDICTION,
				"Relative absolute error");
	}

	@Override
	protected Double calculate(Vector<Double> predicted, Vector<Double> reals)  {
		// TODO Auto-generated method stub
		Double media=0.0;
		for (int loc=0;loc<reals.size();loc++){
			media+=reals.get(loc);
		}
		media/=reals.size();
		Double num=0.0;
		Double den=0.0;
		for (int loc=0; loc<reals.size();loc++){
			num+=Math.abs(predicted.get(loc)-reals.get(loc));
			den+=Math.abs(media-reals.get(loc));
		}
		return num/den;
	}
}
