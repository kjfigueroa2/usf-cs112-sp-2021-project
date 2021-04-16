package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Math;
import java.util.List;

public class KNNPredictor extends Predictor {
	private int k;
	public KNNPredictor(int k) {
		this.k = k;
	}
	
	
	private static int survived = 0;
	private static int died = 1;
	private static int age = 0;
	private static int fare = 0;
	private static int dataSurvival = 0;
	
	private int numSurvived = 0;
	private int numDied = 0;
	
	private ArrayList<DataPoint> dataSet = new ArrayList<DataPoint>();
	
	public ArrayList<DataPoint> readData(String filename) {
		ArrayList<DataPoint> result = new ArrayList<DataPoint>();
		
		File file = new File(filename);
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			return result;
		}
		
		int line = 0;
		int recordsNotUsed = 0;
		
		Random random = new Random();
		
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			line++;
			List<String> dataRecords = recordLine(sc.nextLine());
			
			if (dataRecords.size() <7) {
				recordsNotUsed++;
				continue;
			}
			
			String survivalStr = dataRecords.get(dataSurvival);
			String ageStr = dataRecords.get(age);
			String fareStr = dataRecords.get(fare);
			double age = 0.0;
			double fare = 0.0;
			int alive = 1;
			
			try {
				age = Double.parseDouble(ageStr);
			} catch (NumberFormatException ex) {
				recordsNotUsed++;
				continue;
			}
			
			try {
				fare =Double.parseDouble(fareStr);
			} catch (NumberFormatException ex) {
				recordsNotUsed++;
				continue;
			}
			
			try {
				survived = Integer.parseInt(survivalStr);
			} catch (NumberFormatException ex) {
				recordsNotUsed++;
				continue;
			}
			
			boolean isTrainingData = random.nextDouble() < 0.9;
			
			if(isTrainingData) {
				if(alive == survived) {
					numSurvived++;
				} else {
					numDied++;
				}
			}
			DataPoint datapoint = new DataPoint(age, fare, alive, isTrainingData);
			result.add(datapoint);
		}
		
		sc.close();
		
		System.out.println("Data Set " +"\nTotal Records: " + line +"\nRecords Not Used: " + recordsNotUsed + "\nTraining Data Total: " + (numSurvived-numDied) + 
				"\nTest Data: " + ((line-recordsNotUsed)-(numSurvived-numDied)) + "\nTraining Data \nNumber of survivors: " + numSurvived + "\nNumber of deaths: " +numDied);
		
		return result;
	}
	
		private List<String> recordLine(String lineNumber) {
		
		List<String> numbers = new ArrayList<String>();
		try(Scanner rowScanner = new Scanner(lineNumber))	{
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				numbers.add(rowScanner.next());
			}
		}
		return numbers;
}
	@Override
	public String test(DataPoint data) {
		if (!data.isTest()) {
			return "Data given is not a test data point.";
		}
		int trainingDataNum = numSurvived +numDied;
		Double[][] arr = new Double[trainingDataNum][2];
		for (int row = 0; row<trainingDataNum; row++) {
			DataPoint trainingData=dataSet.get(row);
			double distance = getDistance(data,trainingData);
			double label = trainingData.getLabel();
			arr[row][0] = distance;
			arr[row][1] = label;
		}
		
		java.util.Arrays.sort(arr, new java.util.Comparator<Double[]>(){
			public int compare(Double[] a, Double[] b) {
			return a[0].compareTo(b[0]);
		}
		});
		int survivalTotal = 0;
		int diedTotal = 0;
		for (int row = 0; row <k; row ++) {
			double survivalNum = arr[row][1];
			if (survivalNum == survived ) {
				survivalTotal++;
			} else {
				diedTotal = 0;
			}
		} 
		
		if(survivalTotal>diedTotal) {
			return String.valueOf(survived);
		} else {
			return String.valueOf(died);
		}
	}
	
		private double getDistance(DataPoint point1, DataPoint point2) {
			double distance = 0.0;
			double num = 0.0;
			double x1 = point1.getF1();
			double y1 = point1.getF2();
			double x2 = point2.getF1();
			double y2 = point2.getF2();
			num = Math.pow((x2-x1), 2) + Math.pow((y2-y1),2);
			distance = Math.sqrt(num);
			return distance;
		}
		private static class DataAnalysis {
			int truePositive = 0;
			int falsePositive = 0;
			int falseNegative = 0;
			int trueNegative = 0;
		}
		
		private DataAnalysis analyze(ArrayList<DataPoint> data) {
			DataAnalysis results = new DataAnalysis();
			for (DataPoint point : data) {
				if (!point.isTest()) {
					continue;
				}
				
				double actualLabel = point.getLabel();
				
				dataSet=data;
				double prediction = Double.parseDouble(test(point));
				
				if (actualLabel == 1.0) {
					if (prediction==1.0) {
						results.truePositive++;
					} else {
						results.falsePositive++;
					}
				} else {
					if (prediction ==1.0) {
						results.falseNegative++;
					} else {
						results.trueNegative++;
					}
				}
			}
			return results;
	}
		@Override
		public double getPrecision(ArrayList<DataPoint> data) {
			DataAnalysis dataAnalysis=analyze(data);
			double pr = dataAnalysis.truePositive + dataAnalysis.falseNegative;
			if (pr ==0) {
				throw new ArithmeticException("Error");
			}
			double precision = (double) (dataAnalysis.truePositive)/pr;
			return precision;
		}
		
		@Override
		public double getAccuracy(ArrayList<DataPoint> data) {
			DataAnalysis dataAnalysis=analyze(data);
			double completeResults = dataAnalysis.truePositive + dataAnalysis.trueNegative+ dataAnalysis.falsePositive + dataAnalysis.falseNegative;
			if (completeResults == 0) {
				throw new ArithmeticException("Error: Division by zero");
			}
			double accuracy = (double) (dataAnalysis.truePositive + dataAnalysis.trueNegative)/(completeResults);
			return accuracy;
		}
	}
