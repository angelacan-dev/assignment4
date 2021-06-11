package com.meritamerica.assignment4;

import com.meritamerica.assignment4.account.CDAccount;
import com.meritamerica.assignment4.account.CheckingAccount;
import com.meritamerica.assignment4.account.SavingsAccount;
import com.meritamerica.assignment4.exception.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment4.transaction.DepositTransaction;

public class AccountHolder implements Comparable<AccountHolder> { 
	
	private static final double MAX_BALANCE_AMOUNT = 250000;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String SSN;
	private CheckingAccount[] checkingAccounts;
	private SavingsAccount[] savingsAccounts;
	private CDAccount[] cdAccounts;
	private double cdBalance = 0;
	private double checkingBalance = 0;
	
	private CDAccount[] amountCDAccounts = new CDAccount[0];
	private CheckingAccount[] amountCheckingAccounts = new CheckingAccount[0];
	private SavingsAccount[] amountSavingsAccounts = new SavingsAccount[0];
	
	

	
	private double totalB = 0;  

	private double combinedBalance;
	
	
	
	// Constructor for an account holder
		public AccountHolder(String first, String middle, String last, String ssn) {
			this.firstName = first;
			this.middleName = middle;
			this.lastName = last;
			this.SSN = ssn;
		}
		
		

		
		
	//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
	//Should also add a deposit transaction with the opening balance
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		CheckingAccount clientCheckingAccount = new CheckingAccount(openingBalance);
		addCheckingAccount(clientCheckingAccount);
		return clientCheckingAccount;
	}
	
	
	
	
	
	//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
	//Should also add a deposit transaction with the opening balance 
	
	CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
		double combined = getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance();
		System.out.println("new Balance :" + checkingAccount.getBalance());
		
		if (combined > 250000) {
			throw new ExceedsCombinedBalanceLimitException();
		}
		
		CheckingAccount[] temp = new CheckingAccount[checkingAccounts.length + 1];
		for (int i = 0; i < checkingAccounts.length; i++) {
			temp[i] = checkingAccounts[i];
		}
		temp[temp.length - 1] = checkingAccount;
		checkingAccounts = temp;
		DepositTransaction deposit = new DepositTransaction(checkingAccount, checkingAccount.getBalance());
		MeritBank.processTransaction(deposit);
		return checkingAccount;	
	}
	
	
	
	
	// Returns the combined Balance between all checkingAccounts
	public double getCheckingBalance() {
		double total = 0; //start with sum being 0, look into every single CA in 
		//our CA variable, increase total by balance of that and return total
		
		for (int i = 0; i < checkingAccounts.length; i++) {
			total += checkingAccounts[i].getBalance();
		}
		return total;
	}
		

	
	public double getSavingsBalance() {
		double total = 0; 
		for (int i = 0; i <savingsAccounts.length; i++) {
			total += savingsAccounts[i].getBalance();
		}
		return total;
	}

	
	


	
	
	
	//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
	//Should also add a deposit transaction with the opening balance
	SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
			return addSavingsAccount(new SavingsAccount(openingBalance));
		}

		

	
	
	
	//If combined balance limit is exceeded, throw ExceedsCombinedBalanceLimitException
	//Should also add a deposit transaction with the opening balance
	SavingsAccount addSavingsAccount(SavingsAccount savingAccount) throws ExceedsCombinedBalanceLimitException {
		double combined = getCheckingBalance() + getCDBalance() + getSavingsBalance() +savingAccount.getBalance();
		System.out.println("new Balance :" + savingAccount.getBalance());
		
		if (combined > 250000) {
			throw new ExceedsCombinedBalanceLimitException();
		}
				
		SavingsAccount[] temp = new SavingsAccount[savingsAccounts.length + 1];
		for (int i = 0; i < savingsAccounts.length; i++) {
			temp[i] = savingsAccounts[i];
		}
		
		temp[temp.length - 1] = savingAccount;
		savingsAccounts = temp;
		
		return savingAccount;
	}
	
	

	public CDAccount addCDAccount(CDAccount cdAccount) throws ExceedsCombinedBalanceLimitException {
		double combined = getCheckingBalance() + getSavingsBalance() + cdAccount.getBalance();
		System.out.println("new Balance :" + cdAccount.getBalance());
		
		if (combined > 250000) {
			throw new ExceedsCombinedBalanceLimitException();
		}
		
		CDAccount[] temp = new CDAccount[cdAccounts.length + 1];
		for (int i = 0; i < cdAccounts.length; i++) {
			temp[i] = cdAccounts[i];
		}
		temp[temp.length - 1] = cdAccount;
		cdAccounts = temp;
		
		return cdAccount;
	}

	
	
	
	//Should also add a deposit transaction with the opening balance
	CDAccount addCDAccount(CDOffering offering, double openingBalance) throws ExceedsCombinedBalanceLimitException {
		return addCDAccount(new CDAccount(offering, openingBalance));
	}
	
	public static AccountHolder readFromString(String line) {
		String[] tokens = line.split(",", 4);
		String last = tokens[0];
		String middle = tokens[1];
		String first = tokens[2];
		String number = tokens[3];
		AccountHolder account = new AccountHolder(first, middle, last, number);
		return account;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getMiddleName() {
		return middleName;
	}



	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getSSN() {
		return SSN;
	}



	


	public CheckingAccount[] getCheckingAccounts() {
		return checkingAccounts;
	}



	public void setCheckingAccounts(CheckingAccount[] checkingAccounts) {
		this.checkingAccounts = checkingAccounts;
	}



	public SavingsAccount[] getSavingsAccounts() {
		return savingsAccounts;
	}



	public void setSavingsAccounts(SavingsAccount[] savingsAccounts) {
		this.savingsAccounts = savingsAccounts;
	}



	public CDAccount[] getCdAccounts() {
		return cdAccounts;
	}



	public int getNumberOfCDAccounts() {
		return this.amountCDAccounts.length;

	}
	
	//The following method returns the total amount of checkingAccounts
	public int getNumberOfCheckingAccounts() {
		return this.amountCheckingAccounts.length;
	}
	
	// The following method returns the total amount of savingsAccounts
	public int getNumberOfSavingsAccounts() {
		return this.amountSavingsAccounts.length;
	}



	@Override
	public int compareTo(AccountHolder o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	// This returns the combined Balance of all CD Accounts
	public double getCDBalance() {
		double cdB = 0;

		for (int i = 0; i < this.amountCDAccounts.length; i++) {
			cdB = this.amountCDAccounts[i].getBalance() + cdB;
		}

		this.cdBalance = cdB;
		return this.cdBalance;
	}
	
	
	public double getCombinedBalance() {
		this.combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		return combinedBalance;
	}

	

	

	
	
}