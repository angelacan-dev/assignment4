package com.meritamerica.assignment4.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.meritamerica.assignment4.CDOffering;


public class CDAccount extends BankAccount {
	
	private double balance;
	private CDOffering offering; 
	private Date accountOpened;
	private double futureValue;
	
	

	public CDAccount(CDOffering offering, double balance) {
		this.offering = offering;
		this.balance = balance;
	}
	
	
	CDAccount(CDOffering offering, double balance, long accountNumber, java.util.Date date) {
		this.offering = offering;
	}
	
	
	
	CDAccount(long accountNumber, double balance, double interest, Date date, int term) {
		this.balance = balance;
		offering = new CDOffering(term, interest);
		
	}
	
	public static CDAccount readFromString(String s) throws ParseException {
		String[] tokens = s.split(",",5);
		long accountNumber = Long.parseLong(tokens[0]);
		double balance = Double.parseDouble(tokens[1]);
		double interest = Double.parseDouble(tokens[2]);
		Date date = convertToDate(tokens[3]);
		int term = Integer.parseInt(tokens[4]);
		CDAccount account = new CDAccount(accountNumber, balance, interest, date, term);
		return account;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	public double getInterestRate() {
		return this.offering.getInterestRate();
	}
	
	public int getTerm() {
		return this.offering.getTerm();
	}
	
	
	
	public Date getStartDate() {
		return getStartDate();
	}
	
	public long getAccountNumber() {
		return getAccountNumber();
	}
	
	public double futureValue() {
		return futureValue();
	}
	
	public boolean withdraw(double x) {
		return false;
	}
	
	public boolean deposit(double x) {
		return false;
	}
	
	public CDOffering getOffering() {
		return offering;
	}
	
	public void setOffering(CDOffering offering) {
		this.offering = offering;
	}
	
	public Date getOpenedOn() {
		return accountOpened;
	}
	
	
}