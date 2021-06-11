package com.meritamerica.assignment4.account;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.meritamerica.assignment4.MeritBank;
import com.meritamerica.assignment4.transaction.Transaction;

public abstract class BankAccount {
	static SimpleDateFormat SDF = new SimpleDateFormat ("MM/dd/yyyy");

	protected double balance;
	protected double interestRate;
	protected long accountNumber;
	protected double futureValue;
	protected double accountTotal;
	protected Date accountOpenedOn;
	protected Date date;
	private List <Transaction> transactions;

	public BankAccount() {
		
	}
	
	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
		accountOpenedOn = accountOpenedOn();
		accountNumber = getAccountNumber();
	}
	public BankAccount(long accountNumber,double balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		accountOpenedOn = accountOpenedOn();
	}
	
	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = accountOpenedOn;
		accountNumber = getAccountNumber();
	}

	public BankAccount(long accountNumber, double balance, double interestRate, Date date) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountOpenedOn = date;
		this.accountNumber = accountNumber;
	}

	public java.util.Date accountOpenedOn() {
		java.util.Date date = new java.util.Date();
		return date;
	}

	public java.util.Date getOpenedOn() {
		return accountOpenedOn;
	}

	public long getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	


	public boolean withdraw(double amount) {
		
		if ((balance - amount) >= 0) {
			balance = balance - amount;
			return true;
		} else
			return false;
	}
	
	public boolean deposit( double amount) {
		if (amount < 0) {
			return false;
		} else if ((this.balance + amount) <= 250000) {
			System.out.println("Deposit bank: " + amount);
			this.balance = this.balance + amount;
			return true;
		} else
			System.out.println("more than 250000");
		return false;

	}
	public double futureValue(int years) {
		//double futureValue = balance * Math.pow((1 + interestRate), years);
		//futureValue = futureValue;
		futureValue = MeritBank.recursiveFutureValue(balance, years, interestRate);
		return futureValue;
	}
	public String toString() {
		return "";
	}

	public Date dateAccountOpened(String string) {
		Date date = convertToDate(string);
		this.date = date;
		return this.date;
	}
	
	public void addTransaction(Transaction transaction) {
		
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	static Date convertToDate (String d) {
		try {
			return SDF.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new Date();
		}
	}
	
}	