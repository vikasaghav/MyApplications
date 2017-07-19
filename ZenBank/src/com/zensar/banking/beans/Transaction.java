package com.zensar.banking.beans;

import java.io.Serializable;

public class Transaction implements Serializable{
	private int transactionId;
	private float amount;
	private String transactionType;
	public Transaction(int transactionId){}




	public Transaction(float amount, String transactionType) {
		super();
		this.amount = amount;
		this.transactionType = transactionType;

	}




	public Transaction(int transactionId, float amount, String transactionType) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionType = transactionType;
	}




	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}




	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount
				+ ", transactionType=" + transactionType + "]";
	}







	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}





}
