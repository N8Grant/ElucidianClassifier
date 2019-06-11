package Classifier;

import java.util.ArrayList;

public class ElucidianClassifier {
	private int[] classCounter;		// Used to keep track of how many of each class are in the training set
	private ArrayList<Integer> classVector;		//
	private ArrayList<double[]> poolMatrix;
	private double[] outputClasses;
	
	public ElucidianClassifier(int numClasses) {
		this.classCounter = new int[numClasses];
		this.poolMatrix = new ArrayList<>();
		this.classVector = new ArrayList<>();
		this.outputClasses = new double[numClasses];
	} 
	
	public ElucidianClassifier(ArrayList<double[]> matrix, int numClasses, int[] classCounter) {
		poolMatrix = matrix;
		this.classCounter=classCounter;
	}
	
	// Adds a data point to the classifier
	public void addDataPoint(double[] data, int numClass) {
		classCounter[numClass]++;
		poolMatrix.add(data);
		classVector.add(numClass);
	}
	
	public int predict(double[] testPoint) {
		// Go through all elements and calculate distance between point and add to respective outputClass matrix
		for (int i = 0; i < poolMatrix.size(); i++) {
			for (int j = 0; j < testPoint.length; j++) {
				outputClasses[classVector.get(i)] += calculateDistance(testPoint, poolMatrix.get(i));
			} 
		}
		
		// Average out all distances
		for(int i = 0; i < outputClasses.length; i++) {
			outputClasses[i] = outputClasses[i]/classCounter[i];
		}

		double min = Double.MAX_VALUE; 
		int prediction = 0;
		
		// Find lowest average
		for (int i = 0; i < outputClasses.length; i++) {
			if (outputClasses[i] < min) {
				min = outputClasses[i];
				prediction = i;
			}
		}
		
		return prediction;
	}
	
	// Calculates the euclidian distance between two points in any dimension
	double calculateDistance(double[] first, double[] second) {
		double sum = 0;
		for (int i = 0; i < first.length; i++) {
			sum += Math.pow(first[i]-second[i], 2);
		}
		
		return Math.sqrt(sum);
	}
}
