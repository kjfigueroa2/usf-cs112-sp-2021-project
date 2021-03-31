package project;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class DriverClass {
	private static double accuracy = 0.0;
	private static double precision = 0.0;

	public static void main(String[] args) {
		String tData = "points.txt";
				
		DataPoint dataPointOne = new DataPoint (2.2, 4.4, "Bad", false);
		DataPoint dataPointTwo = new DataPoint (1.1, 3.3, "Good", true);
		
		ArrayList<DataPoint> data = new ArrayList<DataPoint>();
		data.add(dataPointOne);
		data.add(dataPointTwo);
		
		DummyPredictor dPredictor = new DummyPredictor();
		
		dPredictor.readData(tData);
		dPredictor.test(dataPointOne);
		dPredictor.test(dataPointTwo);
		accuracy = dPredictor.getAccuracy(data);
		precision = dPredictor.getPrecision(data);
		
		SwingUtilities.invokeLater(
				new Runnable() { public void run() {initAndShowGUI(); } }
				);
	}
	
	private static void initAndShowGUI() {
		JFrame frame = new JFrame("Data Point Tester");
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(5,5));
		contentPane.add(new JButton("Accuracy is " + accuracy));
		contentPane.add(new JButton("Precision is " + precision));
		
		frame.pack();
		frame.setVisible(true);
	
}
}