package com.meritamerica.assignment4.transaction;

import com.meritamerica.assignment4.account.BankAccount;
import com.meritamerica.assignment4.exception.ExceedsAvailableBalanceException;
import com.meritamerica.assignment4.exception.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment4.exception.NegativeAmountException;

public class TransferTransaction extends Transaction {
	
	public TransferTransaction(BankAccount targetAccount, double amount) {
	
	}

	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}

}