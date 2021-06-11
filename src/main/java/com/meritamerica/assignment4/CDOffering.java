package com.meritamerica.assignment4;


public class CDOffering {
	
	private int term;
	private double interestRate;

	public CDOffering() {
	}

	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}

	public int getTerm() {
		return term;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public static CDOffering readFromString(String line) {
		String[] termoffers = line.split(",");
		int term = Integer.parseInt(termoffers[0]);
		double interestRate = Double.parseDouble(termoffers[1]);
		CDOffering offer = new CDOffering(term, interestRate);
		return offer;
	
	}
}