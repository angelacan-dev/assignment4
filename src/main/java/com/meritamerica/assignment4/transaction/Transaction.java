package com.meritamerica.assignment4.transaction;

import java.util.Date;

import com.meritamerica.assignment4.account.BankAccount;
import com.meritamerica.assignment4.exception.ExceedsAvailableBalanceException;
import com.meritamerica.assignment4.exception.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment4.exception.NegativeAmountException;

public abstract class Transaction {
	
	BankAccount sourceAccount;
	BankAccount targetAccount;
	double amount;
	Date date;
	
	
	
	//source account could be checking account 
	public BankAccount getSourceAccount() {
		return sourceAccount;
	}
	
	
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	//target account could be savings account
	public BankAccount getTargetAccount() {
		return targetAccount;
	}
	
	
	
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
		
	}
	
	public double getAmount() {
		return amount;
	}
	
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Date getTransactionDate() {
		return date;
	}
	
	public void setTransactionDate( Date date) {
		this.date = date;
	}
	
	public String writeToString() {
		return null;
	}
	
	public static Transaction readFromString(String transactionDataString) {
		return null;
	}
	
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	
	public boolean isProcessedByFraudTeam() {
		return false;
	}
	
	public void setProcessedByFraudTeam(boolean isProcessed) {
		
	}
	public String getRejectionReason() {
		return null;
	}
	public void setRejectionReason(String reason) {
		
	}
	
}
	