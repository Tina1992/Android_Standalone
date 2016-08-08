package com.example.prediction.logica.metrics.collection;

import com.example.prediction.logica.metrics.composite.SimpleCOMBMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleCCMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleERMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleMAEMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRAEMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRMSEMetric;
import com.example.prediction.logica.metrics.simple_metrics.SimpleRRSEMetric;
import com.example.prediction.logica.models.AbsModeler;

public class SimpleMetricsCollection extends MetricsCollection{
	
	public SimpleMetricsCollection(){
		acceptMetric(new SimpleERMetric());
		acceptMetric(new SimpleMAEMetric());
		acceptMetric(new SimpleCCMetric());
		acceptMetric(new SimpleRAEMetric());
		acceptMetric(new SimpleRMSEMetric());
		acceptMetric(new SimpleRRSEMetric());
		acceptMetric(new SimpleCOMBMetric());
	}

	@Override
	public boolean aceptModel(AbsModeler model) {
		// TODO Auto-generated method stub
		return true;
	}

}
