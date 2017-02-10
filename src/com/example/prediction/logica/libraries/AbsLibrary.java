package com.example.prediction.logica.libraries;

import java.util.Vector;

import com.example.prediction.logica.database.AbsDatabase;
import com.example.prediction.logica.evaluation.AbsEvaluation;
import com.example.prediction.logica.metrics.collection.MetricsCollection;
import com.example.prediction.logica.models.AbsModeler;

public abstract class AbsLibrary {
	
	private String ID;											
	
	protected AbsDatabase trainingSet;				
	protected MetricsCollection metricsEvaluation;	
	protected Vector<Integer> acceptedModelers = new Vector<Integer>();	

	protected Vector<String> numericalTypes;
	protected Vector<String> categoricalTypes;
	
	public AbsLibrary(String ID){
		createMetricsEvaluation();
		createDatabase();
		setAcceptedModelers();
		this.ID =ID;
	}
	
	public String getID(){
		return ID;
	}
	
	public AbsDatabase getDatasetObject(){
		return trainingSet;
	}
	
	public MetricsCollection getMetricsEvaluationObject(){
		return metricsEvaluation;
	}
	
	public Vector<Integer> getAcceptedModelers(){
		return acceptedModelers;
	}
	
	public Vector<String> getNumericalTypes(){
			return numericalTypes;
		}
		
		public Vector<String> getCategoricalTypes(){
			return categoricalTypes;
		}

	
	public abstract void createDatabase();
	public abstract void createMetricsEvaluation();
	public abstract void setAcceptedModelers();
	public abstract void setNumericalTypes();
	public abstract void setCategoricalTypes();
	
	public abstract Vector<AbsModeler> createModelers(Vector<Integer> selectedModels, int index);

}
