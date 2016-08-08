package com.example.prediction.logica.metrics.simple_metrics;

import java.util.Vector;

import com.example.prediction.logica.metrics.abstracts.AbsSimpleMetric;
import com.example.prediction.logica.metrics.abstracts.Info;
import com.example.prediction.logica.metrics.abstracts.Representation;
import com.example.prediction.logica.metrics.abstracts.Required;
import com.example.prediction.logica.metrics.abstracts.Type;
import com.example.prediction.logica.metrics.collection.MetricsCollection;

public class SimpleCCMetric extends AbsSimpleMetric {
	
	public SimpleCCMetric(){
		super(MetricsCollection.CC,Required.MAX, Representation.NORMALIZED, Type.REGRESSION, Info.RELATION_DATA, "Correlation coefficient");
	}

	@Override
	protected Double calculate(Vector<Double> predicted, Vector<Double> reals) {
		// TODO Auto-generated method stub
		Double media=0.0;
		Double mediaPredic=0.0;
		for (int loc=0;loc<reals.size();loc++){
			media+=reals.get(loc);
			mediaPredic+=predicted.get(loc);
		}
		media/=reals.size();
		mediaPredic/=predicted.size();
		Double num=0.0;
		Double den1=0.0;
		Double den2=0.0;
		for (int loc=0; loc<reals.size(); loc++){
			num+=(reals.get(loc)-media)*(predicted.get(loc)-mediaPredic);
			den1+=Math.pow(reals.get(loc)-media, 2);
			den2+=Math.pow(predicted.get(loc)-mediaPredic, 2);
		}
		
		return num/(Math.sqrt(den1*den2));
	}

}
