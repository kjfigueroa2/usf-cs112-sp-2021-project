package project;

import java.util.ArrayList;

public class DummyPredictor extends Predictor {
	
		@Override
		public ArrayList<DataPoint> readData(String filename) {
			System.out.println("Reading data from " + filename);
			return null;
		}
		
		@Override
		public String test(DataPoint data) {
			if(data.isTest()) {
				System.out.println("Test data point accepted.");
			} else {
				System.out.println("This is not a test data point.");
			}
			return null;
		}
		
		@Override
		public double getAccuracy(ArrayList<DataPoint> data) {
			System.out.println("Testing accuracy.");
			return 50.00;
		}
		
		@Override
		public double getPrecision(ArrayList<DataPoint> data) {
			System.out.println("Testing precision.");
			return 50.00;
		}
}
