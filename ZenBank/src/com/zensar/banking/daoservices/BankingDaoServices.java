package com.zensar.banking.daoservices;

import java.util.ArrayList;

import com.zensar.banking.beans.Account;
import com.zensar.banking.beans.Customer;
import com.zensar.banking.beans.Transaction;

public interface BankingDaoServices {
	int insertCustomer(Customer customer);
	int insertAccount(int customerId, Account account);
	boolean insertTransaction(int customerId, int accountNo, Transaction transaction);
	Customer getCustomer(int customerId);
	Account getAccount(int customerId,int accountNo);
	ArrayList<Customer> getCustomers();
	ArrayList<Account>getAccounts(int customerId);
	ArrayList<Transaction>getTransaction(int customerId,int accountNo);
	void deleteCustomer(int customerId);
	int generatePin(int custId, int accNo);
	boolean updateAccount(int customerId, Account account);
}
