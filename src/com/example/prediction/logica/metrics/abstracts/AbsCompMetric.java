package com.example.prediction.logica.metrics.abstracts;

import java.util.Vector;

import com.example.prediction.logica.database.AbsDatabase;
import com.example.prediction.logica.metrics.abstracts.Type;
import com.example.prediction.logica.models.AbsModeler;

public abstract class AbsCompMetric extends AbsMetric{
	
	protected Vector<AbsMetric> metrics;

	protected AbsCompMetric(int ID, Required req, Representation rep, Type t, Info i, String name) {
		super(ID, req, rep, t, i, name);
		metrics = new Vector<AbsMetric>();
		// TODO Auto-generated constructor stub
	}
	
	public void addMetric(AbsMetric metric){
		metrics.add(metric);
	}
	
	public Vector<AbsMetric> getMetrics(){
		return metrics;
	}

	@Override
	protected void configurateTraining(AbsDatabase database, AbsModeler modeler) {
		for (AbsMetric m : getMetrics()) {
			m.configurateTrainingMode(database, modeler);
		}

	}

	@Override
	public void configurateCV(AbsDatabase database, AbsModeler modeler, int folds) {
		// TODO Auto-generated method stub
		for (AbsMetric m : getMetrics()) {
			m.configurateCVMode(database, modeler, folds);
		}
	}
	
	@Override
	public abstract Double calculate(int mode) throws Exception;
	

}
