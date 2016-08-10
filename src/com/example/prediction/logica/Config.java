package com.example.prediction.logica;

import java.awt.Color;

public class Config {

	public static class InitialSettings {
		private static String DIR_STORAGE = "C:\\Users\\Tina\\Desktop\\";//System.getProperty("user.dir");// Environment.getExternalStorageDirectory().toString()
																			// +
																			// "/";
		private static String DIR_WORKING = "C:\\Users\\Tina\\Desktop\\";//System.getProperty("user.dir");// Environment.getExternalStorageDirectory().toString()
																			// +
																			// "/"
																			// ;

		public static String SUBDIR_APP = "Prediction/";
		public static String SUBDIR_MODELS = "Models/";
		public static String SUBDIR_OPT_SCHEMES = "Optimization Schemes/";
		public static String SUBDIR_OPT_PARAMS = "Parameter Optimization/";

		public static String[] optionsFormatDataset = { "csv" };
		public static String[] optionsTypePrediction = { "Regression" };

		public static String EXTENSION_DATASET = optionsFormatDataset[0];
		public static String TYPE_PREDICTION = optionsTypePrediction[0];

		public static final int ITEM_CODE_FORMATFILE = 0;
		public static final int ITEM_CODE_TYPEPREDICTION = 1;
		public static final int ITEM_CODE_STORAGE = 2;

		public static final int CODE_REGRESSION = 0;
		public static final int CODE_CLASSIFICATION = 1;
		public static final int CODE_CLUSTERING = 2;

		public final static int ITEM_CODE_FILE_SELECT = 0;

		public static final String[] TITLE_INITIAL_ITEMS = { "Format file", "Type Prediction", "Storage" };

		/* GETTERS */
		public static String getFormatDataset() {
			return EXTENSION_DATASET;
		}

		public static String getTypePrediction() {
			return TYPE_PREDICTION;
		}

		public static String getDirStorage() {
			return DIR_STORAGE;
		}

		public static String getDirWorking() {
			return DIR_WORKING;
		}

		public static String[] getOptionsDatasetFormat() {
			return optionsFormatDataset;
		}

		public static String[] getOptionsTypePrediction() {
			return optionsTypePrediction;
		}

		/* SETTERS */
		public static void setFormatDataset(String format) {
			EXTENSION_DATASET = format;
		}

		public static void setTypePrediction(String TypePrediction) {
			TYPE_PREDICTION = TypePrediction;
		}

		public static void setWorkingDir(String workingDir) {
			DIR_WORKING = workingDir;
		}
	}

	public static class AppSettings {
		public static final int ITEM_CODE_SELECT_LIBRARY = 0;
		public final static int ITEM_CODE_SELECT_FILE_DATASET = 1;
		public final static int ITEM_CODE_SELECT_PREDICTED_ATT = 2;
		public final static int ITEM_CODE_SELECT_SCHEMES = 3;

		public static final String[] TITLE_CONFIGURES_ITEMS = { "Select library", "Select File Dataset",
				"Select Predicted Attribute", "Select Schemes" };

		/*
		 * public static final int[] ICON_CONFIGURES_ITEMS = {
		 * R.drawable.icon_library, R.drawable.icon_file,
		 * R.drawable.icon_attribute, R.drawable.icon_schemes };
		 */
		public static final String[] TITLE_POLYNOMIAL_DEGREE = { "Degree 1",
				"Degree 2", "Degree 3", "Degree 4" };
	}

	public static class Graphic {

		public static final int GRAPHIC_TYPE_LINE = 0;
		public static final int GRAPHIC_TYPE_BAR = 1;

		public static final int GRAPHIC_LINE_HEIGHT = 367;
		public static final int GRAPHIC_LINE_WIDTH = 560;
		public static final int GRAPHIC_LINE_LIMIT_INSTANCES = 3;
		public static final int GRAPHIC_LINE_INSTANCES_LEARNING_CURVE = 5;

		public static final float GRAPHIC_LINE_STROKE_REALVALUES = 3F;
		public static final float GRAPHIC_LINE_STROKE_BESTPRED = 3F;
		public static final float GRAPHIC_LINE_STROKE_PREDVALUES = 1.5F;

		public static final String GRAPHIC_LINE_LABEL_REALVALUES = "Real values";
		public static final String GRAPHIC_LINE_TITLE_CHART_EP = "Error Prediction";
		public static final String GRAPHIC_LINE_TITLE_CHART_LC = "Learning Curve";
		public static final String GRAPHIC_LINE_TITLE_AXISX = "Instances";
		public static final String GRAPHIC_LINE_TITLE_AXISY = "Measure";
		public static final String GRAPHIC_LINE_LABEL_TRAINING = "TrainingSet";
		public static final String GRAPHIC_LINE_LABEL_CROSSVALIDATION = "Cross Validation";
		public static final String GRAPHIC_LINE_PREDICTION = "Prediction";

		public static final Color GRAPHIC_LINE_COLOR_REALVALUES = Color.BLACK;

		public static final int GRAPHIC_BAR_HEIGHT = 400;
		public static final int GRAPHIC_BAR_WIDTH = 500;
		public static final int GRAPHIC_BAR_MAXLABELSHORIZONTAL = 5;

		public static final float GRAPHIC_BAR_MAXBARWIDTH = 0.05F;

		public static final String GRAPHIC_BAR_TITLE_CHART = "Schemes Comparator";
		public static final String GRAPHIC_BAR_TITLE_AXISX = "Metrics";
		public static final String GRAPHIC_BAR_TITLE_AXISY = "Measure";

		public static final Color GRAPHIC_BAR_COLOR = Color.GRAY;
		public static final Color GRAPHIC_BAR_COLOR_BESTRESULT1 = new Color(133, 47, 4);
		public static final Color GRAPHIC_BAR_COLOR_BESTRESULT2 = new Color (174, 60, 4);

	}

	public static class Message {

		public final static String MESSAGE_PROGRESSDIALOG_TITLE = "Please wait ...";
		public final static String MESSAGE_PROGRESSDIALOG_DETAIL = "Task in progress...";

		public final static String MESSAGE_DIALOG_SAVE_DETAIL = "The images will be storage in working directory";
		public final static String MESSAGE_DIALOG_YES = "YES";
		public final static String MESSAGE_DIALOG_NO = "NO";
		public final static String MESSAGE_DIALOG_CONFIRMSAVE_TITLE = "Confirm";
		public final static String MESSAGE_DIALOG_CONFIRMSAVE_DETAIL = "Task completed successfully";

		public final static String MESSAGE_DIALOG_OK = "Ok";
		public final static String MESSAGE_DIALOG_CANCEL = "Cancel";

		public final static String MESSAGE_DIALOG_GENERATEMODEL_DETAIL = "To generate the prediction model enter the file name";
		public final static String MESSAGE_DIALOG_GENERATEMODEL_TITLE = "Generate model";
		public final static String MESSAGE_DIALOG_GENERATEMODEL_ACCEPT = "Generate";
		public final static String MESSAGE_DIALOG_GENERATEMODEL_CANCEL = "Cancel";
		public final static String MESSAGE_DIALOG_GENERATEMODEL_INTROTEXT = "Enter name file here";

		public final static String MESSAGE_SELECT_FORMATFILE = "Format file selected: ";
		public final static String MESSAGE_SELECT_TYPEPREDICTION = "Type of prediction selected: ";
		public final static String MESSAGE_SELECT_STORAGE = "Directory to work: ";

		public final static String MESSAGE_DIALOG_HANDLESSCHEMES_DETAIL = "the following schemes could not be executed: ";
		public final static String MESSAGE_DIALOG_HANDLESSCHEMES_ALERT = "The selected schemes could not be run, please select others.";

	}

	public class Exception {

		public final static String EXCEPTION_MISSING_LIBRARY = "Please, select the library before";
		public final static String EXCEPTION_MISSING_FILEDATASET = "Please, select the file dataset before";
		public final static String EXCEPTION_MISSING_PREDATT = "Please, select the predicted attribute before";
		public final static String EXCEPTION_MISSING_SCHEME = "Please, select a scheme to continue";

		public final static String EXCEPTION_ALREADY_SAVED = "Files are already saved";

	}

	public class Modeler {
		public final static int LINEAR_REGRESSION = 0;
		public final static int NEURAL_NETWORK_REGRESSION = 1;
		public final static int STOCHASTIC_GRADIENT_DESCENT_REGRESSION = 2;
		public final static int SIMPLE_LINEAR_REGRESSION = 3;
		public final static int SUPPORT_VECTOR_MACHINE_REGRESSION = 4;
		public final static int SIMPLE_K_CLUSTERER = 5;

	}

	public class TrainingMode {
		public final static int TRAINING_MODE = 0;
		public final static int CROSS_VALIDATION_MODE = 1;
	}

}
