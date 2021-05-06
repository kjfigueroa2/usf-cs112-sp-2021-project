package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.List;

public class KNNPredictor extends Predictor {
	
	//instance variables
	private int k;
	private static int survived = 0;
	private int numSurvived = 0;
	private int numDied = 0;
	private int recordsNotUsed = 0;
	private int truePositive = 0;
	private int falsePositive = 0;
	private int falseNegative = 0;
	private int trueNegative = 0;
	private int lineNum = 0;
	private boolean isTest;
	private double pointLabel;
	private double testLabel;
	private double accuracy;
	private double precision;
	private String survivalStr;
	private String ageStr;
	private String fareStr;
	private double age = 0.0;
	private double fare = 0.0;
	private double total;
	
	
	//constructor that takes in a value for K and store K as a private int as a field
		public KNNPredictor(int k) {
			this.k = k;
		}
		
	private ArrayList<DataPoint> dataSet = new ArrayList<DataPoint>();
	
	//implementing readData
	private List<String> lineRecord(String line){
		List<String> values = new ArrayList<String>();
		try(Scanner row = new Scanner(line)) {
			row.useDelimiter(",");
			while(row.hasNext()) {
				values.add(row.next());
			}
		}
		return values;
	}
	
	@Override
	ArrayList<DataPoint> readData(String filename){
		File file = new File(filename);
		ArrayList<DataPoint> dataList = new ArrayList<DataPoint>();
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				lineNum++;
				List<String> passData = lineRecord(sc.nextLine());
				
				if(passData.size()<7) {
					recordsNotUsed++;
					continue;
				}
				//
				survivalStr = passData.get(1);
				ageStr = passData.get(5);
				fareStr = passData.get(6);
				
				try {
					age = Double.parseDouble(ageStr);
				} catch (NumberFormatException ex) {
					continue;
				}
				
				try {
					fare = Double.parseDouble(fareStr);
				} catch (NumberFormatException ex) {
					continue;
				}
				
				try {
					survived = Integer.parseInt(survivalStr);
				} catch (NumberFormatException ex) {
					continue;
				}
				
				if(splitToTestOrTrain()) {
					if (survived == 1 ) {
						numSurvived++;
					} else if (survived == 0){
						numDied++;
					}
				}
				DataPoint datapoint = new DataPoint(age, fare, survived, splitToTestOrTrain());
				dataList.add(datapoint);
			}
			sc.close();
			System.out.println("Data Set " +"\nTotal Records: " + lineNum +"\nRecords Not Used: " + recordsNotUsed + "\nTraining Data Total: " + (numSurvived-numDied) + 
					"\nTest Data: " + ((lineNum-recordsNotUsed)-(numSurvived-numDied)) + "\nTraining Data \nNumber of survivors: " + numSurvived + "\nNumber of deaths: " +numDied);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		return dataList;
	}
	
	public boolean splitToTestOrTrain() {
		Random random = new Random();
		double randomNumber = random.nextDouble();
		
		if (randomNumber<0.9) {
			isTest = true;
		} else {
			isTest = false;
		}
		return isTest;
	}
	
	//implement test method
	@Override
	public String test(DataPoint data) {
		int trainingDataNum = numSurvived + numDied;
		Double[][] arr = new Double[trainingDataNum][2];
		for (int row = 0; row < trainingDataNum; row++) {
			DataPoint trainingData = dataSet.get(row);
			double distance = getDistance(data, trainingData);
			double label = trainingData.getLabel();
			arr[row][0] = distance;
			arr[row][1] = label;
		}
		
	java.util.Arrays.sort(arr, new java.util.Comparator<Double[]>() {
		public int compare(Double[] a, Double[] b) {
			return a[0].compareTo(b[0]);
		}
	});
	
	double survivalTotal = 0.0; 
	double diedTotal = 0.0;
	for (int row = 0; row <k; row ++) {
		double survivalNum = arr[row][1];
		if (survivalNum == 1) {
			survivalTotal++;
		} else {
			diedTotal++;
		}
	}
	
	if (survivalTotal>diedTotal) {
		return "1";
	} else {
		return "0";
	}
}
	
	private double getDistance(DataPoint x, DataPoint y) {
		double distance = Math.sqrt(Math.pow(y.getF2() - x.getF2(), 2) + Math.pow(y.getF1() - x.getF1(), 2));
		return distance;
	}
		
	//implementing getPrecision and getAccuracy
	@Override
	double getAccuracy(ArrayList<DataPoint> data) {
		for (DataPoint dataPoint : data ) {
			pointLabel = dataPoint.getLabel();
			dataSet = data;
			testLabel = Double.parseDouble(test(dataPoint));
			
			if(testLabel == 1.0) {
				if(pointLabel == 1.0) {
					truePositive++;
				} else if (pointLabel == 0.0) {
					falsePositive++;
				}
			}
			
			if(testLabel == 0.0) {
				if(pointLabel == 0.0) {
					trueNegative++;
				} else if (pointLabel == 1.0) {
					falseNegative++;
				}
			}
		}
		total = truePositive + falsePositive + trueNegative + falseNegative;
		accuracy = (truePositive + trueNegative) / total;
		return accuracy;
	}
	
	@Override
	double getPrecision(ArrayList<DataPoint> data) {
		for (DataPoint dataPoint : data ) {
			pointLabel = dataPoint.getLabel();
			dataSet = data;
			testLabel = Double.parseDouble(test(dataPoint));
			
			if(testLabel == 1.0) {
				if(pointLabel == 1.0) {
					truePositive++;
				} else if (pointLabel == 0.0) {
					falsePositive++;
				}
			}
			
			if(testLabel == 0.0) {
				if(pointLabel == 0.0) {
					trueNegative++;
				} else if (pointLabel == 1.0) {
					falseNegative++;
				}
			}
		}
		total = truePositive+falseNegative;
		precision = truePositive/total;
		return precision;
	}
}