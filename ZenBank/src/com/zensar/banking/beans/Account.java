package com.zensar.banking.beans;
import java.io.Serializable;
import java.util.HashMap;
public class Account implements Serializable{
	private int accountNo,pinCounter=0,pinNumber;
	private boolean flag=true;
	private String accountType;
	private float accountBalance;
	private HashMap<Integer, Transaction> transaction= new HashMap<Integer, Transaction>();
	public Account(){}
	public Account(String accountType, float accountBalance) {
		super();
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int getPinCounter() {
		return pinCounter;
	}
	public void setPinCounter(int pinCounter) {
		this.pinCounter = pinCounter;
	}
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public HashMap<Integer, Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(HashMap<Integer, Transaction> transaction) {
		this.transaction = transaction;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Float.floatToIntBits(accountBalance) != Float
				.floatToIntBits(other.accountBalance))
			return false;
		if (accountNo != other.accountNo)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", pinCounter=" + pinCounter
				+ ", pinNumber=" + pinNumber + ", flag=" + flag
				+ ", accountType=" + accountType + ", accountBalance="
				+ accountBalance + ", transaction=" + transaction + "]";
	}
	
}
