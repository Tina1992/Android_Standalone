package com.example.prediction.logica.metrics.simple_metrics;

import java.util.Vector;

import com.example.prediction.logica.metrics.abstracts.AbsSimpleMetric;
import com.example.prediction.logica.metrics.abstracts.Info;
import com.example.prediction.logica.metrics.abstracts.Representation;
import com.example.prediction.logica.metrics.abstracts.Required;
import com.example.prediction.logica.metrics.abstracts.Type;
import com.example.prediction.logica.metrics.collection.MetricsCollection;

public class SimpleRRSEMetric extends AbsSimpleMetric {
	
	public SimpleRRSEMetric(){
		super(MetricsCollection.RRSE,Required.MIN, Representation.PERCENTUAL, Type.REGRESSION, Info.ERROR_PREDICTION, "Root relative squared error");
		// TODO Auto-generated constructor stub
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
			num+=Math.pow(predicted.get(loc)-reals.get(loc),2);
			den+=Math.pow(media-reals.get(loc),2);
		}
		return Math.sqrt(num/den);
	}

}
