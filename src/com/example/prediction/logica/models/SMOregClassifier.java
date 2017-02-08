package com.example.prediction.logica.models;

import com.example.prediction.logica.optimization.WekaKernelOptimizer;
import com.example.prediction.logica.parameters.WekaKernelParameter;
import com.example.prediction.logica.parameters.WekaSimpleParameter;

import weka.classifiers.functions.SMOreg;
import weka.classifiers.functions.supportVector.*;

public class SMOregClassifier extends AbsWekaClassifier {
	
	private static final double DEFAULT_C = 0;
	private static final double DEFAULT_E = 3;
	private static final Kernel DEFAULT_KERNEL=new NormalizedPolyKernel();
	
	private static final double MIN_VALUE=0;
	private static final double MAX_VALUE=8;
	/**/
	public SMOregClassifier(int index){
		super(new SMOreg(), new WekaKernelOptimizer(MIN_VALUE,MAX_VALUE), index);
		WekaSimpleParameter c = new WekaSimpleParameter('C', DEFAULT_C, "C"/*"Complexy"*/);
		c.setMinValor(0);
		c.setMaxValue((float) 25.0);
		addParameter(c);
		WekaSimpleParameter exponent=new WekaSimpleParameter('E',DEFAULT_E,"E"/*"exponent"*/);
		exponent.setMinValor(2);
		WekaKernelParameter kernelParam = new WekaKernelParameter(DEFAULT_KERNEL);
		kernelParam.addKernelOption(exponent);
		addParameter(kernelParam);
	}
	
	public String getName(){
		return "SMOreg";
	}
	
}
