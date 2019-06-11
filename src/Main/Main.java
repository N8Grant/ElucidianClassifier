package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Classifier.ElucidianClassifier;

public class Main {

	public static List<List<String>> records = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
		List<String> classes = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\NathanGrant\\eclipse-workspace\\ElucidianDistanceClassifier\\src\\Main\\housing.csv"))) {
		    String line;
		    int i =0;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",", 10);
		        records.add(Arrays.asList(values));
		        
		        if (!classes.contains(values[9]))
		        	classes.add(values[9]);
		    }
		}
		
		classes.remove(0);
		records.remove(0);
		System.out.println(classes);
		double accuracy = 0;
		int epochs;
		for (epochs = 0; epochs < 30; epochs++) {
			Collections.shuffle(records);
			
			
			ArrayList<double[]> data = transformData(classes);
			
			// Make training Set and Test Set
			int trainIndex = (int) (data.size()*.8);
			
			ElucidianClassifier classifier = new ElucidianClassifier(5);
			for (int i = 0; i < trainIndex; i++) {
				classifier.addDataPoint(data.get(i), classes.indexOf(records.get(i).get(9)));
			}
			
			accuracy += test(classifier, data.subList(trainIndex+1, data.size()-1), classes, trainIndex+1);
			//System.out.println(records.get(trainIndex+1));
			//System.out.println(classifier.predict(data.get(trainIndex+1)));
		}
		
		System.out.println("Accuracy: " + new DecimalFormat("##.###").format(accuracy/epochs));
	}
	
	private static double test(ElucidianClassifier classifier, List<double[]> subList, List<String> classes, int index) {
		int total = 0;
		int correct = 0;
	
		int guess;
		int actual;
		for (int i = 0; i < subList.size(); i ++) {
			guess = classifier.predict(subList.get(i));
			actual = classes.indexOf(records.get(index+i).get(9));
			total++;
			if (guess == actual) {
				correct++;
			}
			classifier.addDataPoint(subList.get(i), actual);
		}
		//System.out.println("Accuracy: " + new DecimalFormat("##.###").format((double) correct/total * 100)+ "%"); 
		return (double) correct/total * 100;
	}

	public static ArrayList<double[]> transformData(List<String> classes) {
		ArrayList<String> classList = new ArrayList<>();
		ArrayList<double[]> newList = new ArrayList<>();
		int faultyData = 0;
		int counter = 0;
		for (int i = 0; i < records.size(); i++) {
			List<String> temp = records.get(i);
			double[] data = new double[temp.size()-1];
			for (int j = 0; j < temp.size()-1; j++) {
				try {
					data[j] = Double.valueOf(temp.get(j));
				} catch(NumberFormatException e) {
					faultyData = 1;
					records.remove(temp);
					break;
				}
			}
			classList.add(temp.get(9));
			if (faultyData == 1) {
				faultyData = 0;
				counter++;
				continue;
			}
			newList.add(data);
		}
		//System.out.println(counter);
		return newList;
	}

}
