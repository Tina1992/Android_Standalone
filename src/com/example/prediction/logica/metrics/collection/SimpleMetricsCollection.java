package com.example.prediction.logica.metrics.collection;

import com.example.prediction.logica.metrics.composite.SimpleCOMBMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleCCMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleMAEMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRAEMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRMSEMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRRSEMetric;
import com.example.prediction.logica.models.AbsModeler;

public class SimpleMetricsCollection extends MetricsCollection{
	
	public SimpleMetricsCollection(){
		acceptMetric(new SimpleMAEMetric());
		acceptMetric(new SimpleCCMetric());
		acceptMetric(new SimpleRMSEMetric());
		acceptMetric(new SimpleRRSEMetric());
		acceptMetric(new SimpleCOMBMetric());
		acceptMetric(new SimpleRAEMetric());
	}

	@Override
	public boolean aceptModel(AbsModeler model) {
		// TODO Auto-generated method stub
		return true;
	}

}
