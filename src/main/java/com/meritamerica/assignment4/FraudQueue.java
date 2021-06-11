package com.meritamerica.assignment4;

import com.meritamerica.assignment4.transaction.Transaction;

public class FraudQueue {
	
	private Transaction[] transactions = new Transaction[0];
	
	public FraudQueue() {
		
	}
	
	public void addTransaction(Transaction transaction) {
	
		Transaction[] temp = new Transaction[transactions.length + 1];
		for (int i = 0; i < transactions.length; i++) {
			temp[i] = transactions[i];
		}
		
		transactions = temp;
		
		transactions[transactions.length - 1] = transaction;
	}
	
	public Transaction getTransaction() {
		Transaction t = null;
		if (transactions.length>0) {
			 t = transactions[0];
			
			Transaction[] temp = new Transaction[transactions.length - 1];
			for (int i = 0; i < transactions.length - 1; i++) {
				temp[i] = transactions[i+1];
			}
			
			transactions = temp;
			
		}
		return t;
	}
}
