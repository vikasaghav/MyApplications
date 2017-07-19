package com.zensar.banking.services;

import java.util.ArrayList;

import com.zensar.banking.beans.Account;
import com.zensar.banking.beans.Customer;
import com.zensar.banking.beans.Transaction;
import com.zensar.banking.exceptions.AccountBlockedException;
import com.zensar.banking.exceptions.AccountNotFoundException;
import com.zensar.banking.exceptions.CustomerNotFoundException;
import com.zensar.banking.exceptions.FundNotTransferException;
import com.zensar.banking.exceptions.InsufficientBalanceException;
import com.zensar.banking.exceptions.InvalidAccountNoException;
import com.zensar.banking.exceptions.InvalidAccountTypeException;
import com.zensar.banking.exceptions.InvalidAmountException;
import com.zensar.banking.exceptions.InvalidCustomerIdException;
import com.zensar.banking.exceptions.InvalidPinException;
import com.zensar.banking.exceptions.ServicesNotFoundException;

public interface BankingServices {
	int acceptCustomerDetails(String customerName, String customerEmailId, String dateOfBirth, String localAddressCity,
			String localAddressState, int localAddressPinCode, String homeAddressCity, String HomeAddressState, 
			int homeAddressPinCode
			) throws ServicesNotFoundException;

	Account openAccount(int customerId, String accountType, float initBalance) throws InvalidCustomerIdException,
	CustomerNotFoundException, InvalidAmountException, InvalidAccountTypeException, ServicesNotFoundException, InvalidAccountNoException, AccountNotFoundException;

	boolean depositAmount(int customerId, int accountNo, float amount, int pin) throws InvalidCustomerIdException, CustomerNotFoundException,
	InvalidAccountNoException, AccountNotFoundException, InvalidAmountException,ServicesNotFoundException, InsufficientBalanceException, InvalidPinException, AccountBlockedException;


	boolean withDrawAmount(int customerId, int AccountNo, Float amount, int pin)
			throws InvalidCustomerIdException, CustomerNotFoundException,InvalidAccountNoException,
			AccountNotFoundException,InvalidAmountException,InsufficientBalanceException,FundNotTransferException,ServicesNotFoundException, InvalidPinException, AccountBlockedException;

	boolean fundTransfer(int customerIdTo, int accountNoTo,int customerIdFrom, int accountNoFrom, float transferAmount, int pin) throws InvalidCustomerIdException,
	CustomerNotFoundException,InvalidAccountNoException,InsufficientBalanceException,FundNotTransferException,ServicesNotFoundException, AccountNotFoundException, InvalidAmountException, InvalidPinException, AccountBlockedException;




	Customer getCustomerDetails(int customerId) throws InvalidCustomerIdException,CustomerNotFoundException,ServicesNotFoundException;

	Account getAccountDetails(int customerId, int accountNo) throws InvalidCustomerIdException,CustomerNotFoundException,InvalidAccountNoException,
	AccountNotFoundException,ServicesNotFoundException;

	ArrayList <Customer>getAllCustomerDetails() throws ServicesNotFoundException;

	ArrayList <Account>getCustomerAllAccountDetails(int customerId) throws InvalidCustomerIdException,CustomerNotFoundException,ServicesNotFoundException;

	ArrayList <Transaction>getCustomerGetAccountGetAllTransactions(int customerId, int accountNo) throws InvalidCustomerIdException,CustomerNotFoundException,
	InvalidAccountNoException,AccountNotFoundException,ServicesNotFoundException;

	void DeleteCustomer(int cust) throws InvalidCustomerIdException, CustomerNotFoundException, ServicesNotFoundException;
	public boolean pinVerify(int customerId,int accountNo,int pin) throws InvalidPinException, ServicesNotFoundException, InvalidCustomerIdException, CustomerNotFoundException, InvalidAmountException, InvalidAccountNoException, AccountNotFoundException, InsufficientBalanceException, AccountBlockedException;
	float getAccountBalance(int custId, int accNo, int pin)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			ServicesNotFoundException, InsufficientBalanceException, InvalidAmountException, InvalidPinException, AccountBlockedException;
	 int resetPin(int custId, int accNo ) throws InvalidAccountNoException, InvalidCustomerIdException, CustomerNotFoundException, AccountNotFoundException, ServicesNotFoundException;
	
	 void exit();
}
