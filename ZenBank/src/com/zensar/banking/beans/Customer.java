package com.zensar.banking.beans;
import java.io.Serializable;
import java.util.HashMap;
public class Customer implements Serializable{
	private int customerId;
	private String customerName,customerEmailId,dateOfBirth;
	private Address localAddress,homeAddress;
	private HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
	public Customer(){}
		public HashMap<Integer, Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(HashMap<Integer, Account> accounts) {
		this.accounts = accounts;
	}
	public Customer( String customerName,
			String customerEmailId, String dateOfBirth, Address localAddress,
			Address homeAddress) {
		super();
		this.customerName = customerName;
		this.customerEmailId = customerEmailId;
		this.dateOfBirth = dateOfBirth;
		this.localAddress = localAddress;
		this.homeAddress = homeAddress;
			}







	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(Address localAddress) {
		this.localAddress = localAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (customerEmailId == null) {
			if (other.customerEmailId != null)
				return false;
		} else if (!customerEmailId.equals(other.customerEmailId))
			return false;
		if (customerId != other.customerId)
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (localAddress == null) {
			if (other.localAddress != null)
				return false;
		} else if (!localAddress.equals(other.localAddress))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName="
				+ customerName + ", customerEmailId=" + customerEmailId
				+ ", dateOfBirth=" + dateOfBirth + ", localAddress="
				+ localAddress + ", homeAddress=" + homeAddress + ", accounts="
				+ accounts + "]";
	}


	
	
	


}
