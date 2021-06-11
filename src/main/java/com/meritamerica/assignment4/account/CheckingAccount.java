package com.meritamerica.assignment4.account;

import java.util.Date;

import com.meritamerica.assignment4.MeritBank;


public class CheckingAccount extends BankAccount {
	
	private double balance;
	double INTEREST_RATE = 0.0001;
	private double accountNumber;
	private double futureValue;
	private double interestRate;
	private Date accountOpened;


	public CheckingAccount() {
		super(); //super means call your constructor in the parent class
		// TODO Auto-generated constructor stub
		
	}
	
	public CheckingAccount(long accountNumber, double balance, double interestRate) {
		super (accountNumber, balance, interestRate);
	}
	
	public CheckingAccount(long accountNumber, double balance, double interestRate, Date openedOn) {
		super (accountNumber, balance, interestRate, openedOn);
	}
	
	
	public CheckingAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}
	
	public CheckingAccount(double balance) {
		super(balance, .0001);
	}
	

	// this is the method for withdraw for funds
	//the amount withdraw has to be less than the balance in the account
	public boolean withdraw(double amount)

	 {
		if (amount < this.balance && amount > 0) {
			balance -= amount;
			return true;
		}  
		return false;
		
	}
	//this method is for depositing funds.If amount is >0 retn True
	//balance is less than 0 it will print you cant deposit
	
	public boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			return true;
		}else {
			System.out.println(" You canot deposit a negative amount");
		}
		
		return false;
	}
	
	public double futureValue(int years) {
		
		futureValue = MeritBank.recursiveFutureValue(balance, years, interestRate);
		return futureValue;
		
	}

	//the to string method will print the hidden values
	@Override
	public String toString() {
		return "CheckingAccount [balance=" + balance + ", interestRate=" + interestRate + ", futureVal=" + futureValue
				+ "]";
	}
	
	public static CheckingAccount readFromString(String accountData) {
		String[] ad = accountData.split(",");	
		Date openedOn = convertToDate(ad[3]);
		CheckingAccount ca = new CheckingAccount(Integer.parseInt(ad[0]),Double.parseDouble(ad[1]), Double.parseDouble(ad[2]), openedOn);
		return ca;

	
	}
}