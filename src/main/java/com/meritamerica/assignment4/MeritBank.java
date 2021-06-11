package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import com.meritamerica.assignment4.account.BankAccount;
import com.meritamerica.assignment4.account.CDAccount;
import com.meritamerica.assignment4.account.CheckingAccount;
import com.meritamerica.assignment4.account.SavingsAccount;
import com.meritamerica.assignment4.exception.ExceedsAvailableBalanceException;
import com.meritamerica.assignment4.exception.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment4.exception.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment4.exception.NegativeAmountException;
import com.meritamerica.assignment4.transaction.DepositTransaction;
import com.meritamerica.assignment4.transaction.Transaction;
import com.meritamerica.assignment4.transaction.TransferTransaction;
import com.meritamerica.assignment4.transaction.WithdrawTransaction;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class MeritBank {
	

	private static AccountHolder[] accounts = new AccountHolder[0];
	private static FraudQueue fraudQueue = new FraudQueue();
	private static CDOffering[] cdOfferings;
	private static long nextAccountNumber;
	private static CDOffering bestCDOffering;
	private static CDOffering secondBestCDOffering;
	private static int counterA = 0;
	private static int counterCD = 0;
	private static double totalBalance = 0;
	
	
	public static void addAccountHolder(AccountHolder accountHolder) {

		if (counterA == accounts.length) {
			AccountHolder[] newAccounts = new AccountHolder[counterA + 1];
			for (int i = 0; i < counterA; i++) {
				newAccounts[i] = accounts[i];
			}
			accounts = newAccounts;
		}
		accounts[counterA] = accountHolder;
		counterA++;
	}
	
	
	//Existing futureValue methods that used to call Math.pow() should now call this method
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		return interestRate;
		
	}
	
	
	
	//If transaction does not violate any constraints, deposit/withdraw values from the relevant BankAccounts and add the transaction to the relevant BankAccounts
	
	//If the transaction violates any of the basic constraints(negative amount, exceeds available balance) the relevant exception should be thrown 
	//and the processing should terminate
	
	//If the transaction violates the $1,000 suspicion limit, it should simply be added to the FraudQueu for future processing 
	public static boolean processTranscation(Transaction transaction) 
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
				
		try {
			transaction.process();
		}catch (Exception ex) {
			
			if (ex instanceof ExceedsFraudSuspicionLimitException) {
				// TODO: Add the transaction into the FraudQueue instance
			}
		
			throw ex;
		}
		
		return true;
}


	public static FraudQueue getFraudQueue() {
		return fraudQueue;
		
	}
	
	
	//return null if account not found 
	public static BankAccount getBankAccount(long accountId) {
		
		for(int i = 0; i < accounts.length; i++) {
			AccountHolder accountHolder = accounts[i];
			for (int z = 0; z < accountHolder.getCheckingAccounts().length; z++) {
				BankAccount bA = accountHolder.getCheckingAccounts()[z];
				if (accountId == bA.getAccountNumber())
					return bA;
			}
			
			for (int z = 0; z < accountHolder.getSavingsAccounts().length; z++) {
				BankAccount bA = accountHolder.getSavingsAccounts()[z];
				if (accountId == bA.getAccountNumber())
					return bA;
			}
			
			for (int z = 0; z < accountHolder.getCdAccounts().length; z++) {
				BankAccount bA = accountHolder.getCdAccounts()[z];
				if (accountId == bA.getAccountNumber())
					return bA;
			}
		}
			
		
		return null;
		
	}
	
	public static AccountHolder[] getAccountHolders() {
		return accounts;
	}

	public static CDOffering[] getCDOfferings() {
		return cdOfferings;
	}

	public static CDOffering getSecondBestCDOfferings(double depositAmount) {
		return secondBestCDOffering;
	}

	public static void clearCDOfferings() {
		cdOfferings = null;
	}

	public static void setCDOfferings(CDOffering[] offerings) {
		cdOfferings = offerings;
	}
	
	public static long getNextAccountNumber() {
		return nextAccountNumber;
	}
	
	public static double totalBalance() {
		double tB = 0;
		System.out.println("Method Total Balance is" + tB);
		return tB;
	}
	
	


	public static boolean readFromFile(String fileName) {
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			line = reader.readLine();
			long n = Long.parseLong(line);
			nextAccountNumber = n;
			
			
			System.out.println(nextAccountNumber);
			
			line = reader.readLine();
			int numCDOff = Integer.parseInt(line);
			CDOffering[] cdOffers = new CDOffering[numCDOff];
			cdOfferings = cdOffers;
			for (int i = 0; i < cdOfferings.length; i++) {
				line = reader.readLine();
				cdOfferings[i] = CDOffering.readFromString(line);
			}
			
			line = reader.readLine();
			int numAcc = Integer.parseInt(line);
			accounts = new AccountHolder[numAcc];
			for (int i = 0; i < accounts.length; i++) {
		
				line = reader.readLine();
				accounts[i] = AccountHolder.readFromString(line);
			
				line = reader.readLine();
				int chAc = Integer.parseInt(line);
				if (chAc != 0) {
					for (int j = 0; j < chAc; j++) {
						line = reader.readLine();
						CheckingAccount acc = CheckingAccount.readFromString(line);
						try {
							accounts[i].addCheckingAccount(acc);
						} catch (ExceedsCombinedBalanceLimitException e) {
							e.printStackTrace();
						}
						
						
						line = reader.readLine();
						int transactionNumber = Integer.parseInt(line);
						for (int z = 0; z < transactionNumber; z++) {
							line = reader.readLine();
			
							String data[] = line.split(",");
							Transaction transa = null;
							double amount = Double.parseDouble(data[2]);
							if ("-1".equals(data[0])) {
								if (amount<0) {
									transa = new WithdrawTransaction(acc, amount);
								} else {
									transa = new DepositTransaction(acc, amount);
								}
							} else {
								transa = new TransferTransaction(acc, amount);
							}
							
							if (transa != null) {
								acc.addTransaction(transa);
							}
						}
						
					}
				}
				
				line = reader.readLine();
				int svAc = Integer.parseInt(line);
				if (svAc != 0) {
					for (int k = 0; k < svAc; k++) {
						line = reader.readLine();
						SavingsAccount acc = SavingsAccount.readFromString(line);
						try {
							accounts[i].addSavingsAccount(acc);
						} catch (ExceedsCombinedBalanceLimitException e) {
							e.printStackTrace();
						}
						line = reader.readLine();
						int transactionNumber = Integer.parseInt(line);
						for (int z = 0; z < transactionNumber; z++) {
							line = reader.readLine();
			
							String data[] = line.split(",");
							Transaction transa = null;
							double amount = Double.parseDouble(data[2]);
							if ("-1".equals(data[0])) {
								if (amount<0) {
									transa = new WithdrawTransaction(acc, amount);
								} else {
									transa = new DepositTransaction(acc, amount);
								}
							} else {
								transa = new TransferTransaction(acc, amount);
							}
							
							if (transa != null) {
								acc.addTransaction(transa);
							}
						}
					}
				}
				
				line = reader.readLine();
				int cdAc = Integer.parseInt(line);
				if (cdAc != 0) {
					for (int l = 0; l < cdAc; l++) {
						line = reader.readLine();
						CDAccount acc = CDAccount.readFromString(line);
						try {
							accounts[i].addCDAccount(acc);
						} catch (ExceedsCombinedBalanceLimitException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						line = reader.readLine();
						int transactionNumber = Integer.parseInt(line);
						for (int z = 0; z < transactionNumber; z++) {
							line = reader.readLine();
			
							String data[] = line.split(",");
							Transaction transa = null;
							double amount = Double.parseDouble(data[2]);
							if ("-1".equals(data[0])) {
								if (amount<0) {
									transa = new WithdrawTransaction(acc, amount);
								} else {
									transa = new DepositTransaction(acc, amount);
								}
							} else {
								transa = new TransferTransaction(acc, amount);
							}
							
							if (transa != null) {
								acc.addTransaction(transa);
							}
						}
					}
				}
			}
			
			// TODO:
			line = reader.readLine();
			int fraudNumber = Integer.parseInt(line);
			for (int i = 0; i < fraudNumber; i++) {
				line = reader.readLine();
				
				String data[] = line.split(",");
				Transaction transa = null;
				double amount = Double.parseDouble(data[2]);
				long accountId = Long.parseLong(data[1]);
				BankAccount ba = getBankAccount(accountId);
				if ("-1".equals(data[0])) {
					if (amount<0) {
						transa = new WithdrawTransaction(ba, amount);
					} else {
						transa = new DepositTransaction(ba, amount);
					}
				} else {
					transa = new TransferTransaction(ba, amount);
				}
				
				fraudQueue.addTransaction(transa);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			return false;
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static AccountHolder[] sortAccountHolders() {
		Arrays.sort(accounts);
		return accounts;
	}


public static void writeToFile(String fileName) {
	try { 
		BufferedWriter bw = new BufferedWriter (new FileWriter(fileName));
		bw.close();
	}catch (IOException e) {
		
	}
	
	
	
	
	}



	//Transfer Transaction
	public static void processTransaction(Transaction transferTransaction) {
		// TODO Auto-generated method stub
		
	}


}
