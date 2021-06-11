package com.meritamerica.assignment4.account;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.meritamerica.assignment4.MeritBank;


public class SavingsAccount extends BankAccount  {
	
		public SavingsAccount() {
			super();
		}
		
		public SavingsAccount(long accountNumber, double balance, double interestRate, Date openedOn) {
			super (accountNumber, balance, interestRate, openedOn);
		}
		
		
		public SavingsAccount(long accountNumber, double balance, double interestRate) {
			super(accountNumber, balance, interestRate);
		}
		
		public SavingsAccount(double balance, double interestRate) {
			super(balance, interestRate);
		}
		
		public SavingsAccount(double balance) {
			super(balance, .01);
		}

		
		public boolean withdraw(double amount) {
			if (amount < this.balance && amount > 0) {
				balance -= amount;
				return true;
			}  
			return false;
			
		}
		public boolean deposit(double amount) {
			if (amount > 0) {
				balance += amount;
				return true;
			}
			return false;
		}
		
		public double futureValue(int years) {
			futureValue = MeritBank.recursiveFutureValue(balance, years, interestRate);
			return futureValue;
			
		}
		@Override
		public String toString() {
			return "SavingsAccount [balance=" + balance + ", interestRate=" + interestRate + ", futureVal=" + futureValue
					+ "]";
		}
		
		public static SavingsAccount readFromString(String accountData) {
			String[] ad = accountData.split(",");	
			Date openedOn = convertToDate(ad[3]);
			SavingsAccount ca = new SavingsAccount(Integer.parseInt(ad[0]),Double.parseDouble(ad[1]), Double.parseDouble(ad[2]), openedOn);
			return ca;

		
		}
		

		
	}