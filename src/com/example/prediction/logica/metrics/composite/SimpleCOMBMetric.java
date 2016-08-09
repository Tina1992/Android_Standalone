package com.example.prediction.logica.metrics.composite;

import com.example.prediction.logica.Config;
import com.example.prediction.logica.metrics.abstracts.AbsCompMetric;
import com.example.prediction.logica.metrics.abstracts.Info;
import com.example.prediction.logica.metrics.abstracts.Representation;
import com.example.prediction.logica.metrics.abstracts.Required;
import com.example.prediction.logica.metrics.abstracts.Type;
import com.example.prediction.logica.metrics.collection.MetricsCollection;
import com.example.prediction.logica.metrics.simple_metrics.SimpleCCMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRAEMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRRSEMetric;

public class SimpleCOMBMetric extends AbsCompMetric {

	public SimpleCOMBMetric() {
		super(MetricsCollection.COMB, Required.MIN, Representation.NORMALIZED, Type.REGRESSION, Info.ERROR_PREDICTION,
				"Comb");
		addMetric(new SimpleCCMetric());
		addMetric(new SimpleRRSEMetric());
		addMetric(new SimpleRAEMetric());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double calculate(int mode) throws Exception {
		// TODO Auto-generated method stub
		double r = 0;
		r += 1 - Math.abs(getMetrics().get(0).calculate(mode));
		if (mode==Config.TrainingMode.TRAINING_MODE){
			r+=getMetrics().get(1).calculateNormalized(database, modeler);
			r+=getMetrics().get(2).calculateNormalized(database, modeler);
		}
		else
		{
			r+=getMetrics().get(1).calculateNormalized(database, modeler, 5);
			r+=getMetrics().get(2).calculateNormalized(database, modeler, 5);
		}
		return r;
	}

}