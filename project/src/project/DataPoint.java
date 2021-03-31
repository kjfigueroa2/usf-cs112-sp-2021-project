package project;

public class DataPoint {
	
	//instance variables
	public Double f1;
	public Double f2;
	public String label;
	public boolean isTest;
	
	//no arg constructor
	public DataPoint() {
		this.f1 = 0.0;
		this.f2 = 0.0;
		this.label = "";
		this.isTest = false;
	}
	
	//4 arg constructor
	public DataPoint(Double f1, Double f2, String label, Boolean isTest) {
		this.f1 = f1;
		this.f2 = f2;
		this.label = label;
		this.isTest = isTest;
	}
	
	//accessors 
	public double getF1() {
		return this.f1;
	}
	
	public double getF2() {
		return this.f2;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public boolean isTest() {
		return this.isTest;
	}
	
	//mutators
	public void setF1(Double val) {
		this.f1 = val;
	}
	
	public void setF2(Double val) {
		this.f2 = val;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setIsTest(boolean isTest) {
		this.isTest = isTest;
	}
	
	
}
