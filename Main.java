package project;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	private static double accuracy = 0;
	private static double precision = 0;
	
	public static void main(String[] args) {
		System.out.println("KNN Predictor");
		int k = input();
		Predictor knnPredictor = new KNNPredictor(k);
		
		ArrayList<DataPoint> dataArrayList = new ArrayList<DataPoint>();
		dataArrayList = knnPredictor.readData("titanic.csv");
		
		precision = knnPredictor.getPrecision(dataArrayList)*100;
		accuracy = knnPredictor.getAccuracy(dataArrayList)*100;
		SwingUtilities.invokeLater(
				new Runnable() {public void run() { initAndShowGUI(); }}
				);
	}
	
	public static int input() {
		boolean isOdd = false;
		int result = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter odd number: ");
			if (sc.hasNextInt()) {
				result = sc.nextInt();
			} else {
				System.out.println("Error. What was inputed was not acceptable. Try Again.");
				sc.nextLine();
				continue;
			}
			if(result%2==0) {
				System.out.println("Error. Input is even.");
			} else {
				isOdd = true;
			}
		} while (!isOdd);
		sc.close();
		return result;
	}
	private static void initAndShowGUI() {
		JFrame dataFrame = new JFrame();
		dataFrame.setSize(500,500);
		
		Container contentPane = dataFrame.getContentPane();
		contentPane.setLayout(new GridLayout(1,2));
		contentPane.setPreferredSize(new Dimension(600,600));
		
		JButton accuracyButton = new JButton("Accuracy: " + String.format("%.0f", accuracy) + "%");
		JButton precisionButton =new JButton("Precision: " + String.format("%.0f", precision) + "%");
		
		contentPane.add(accuracyButton);
		contentPane.add(precisionButton);
		
		dataFrame.pack();
		dataFrame.setVisible(true);
		dataFrame.setTitle("KNN Predictor");
	}

}
