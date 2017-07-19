package com.zensar.banking.services;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.zensar.banking.beans.Account;
import com.zensar.banking.beans.Address;
import com.zensar.banking.beans.Customer;
import com.zensar.banking.beans.Transaction;
import com.zensar.banking.daoservices.BankingDaoServices;
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
import com.zensar.banking.providers.BankingDaoServicesProvider;
public class HDFCBankingServicesImpl implements BankingServices{
	@Autowired
	private BankingDaoServices bankingDaoServices;
	public HDFCBankingServicesImpl() throws ServicesNotFoundException {
	//	bankingDaoServices= BankingDaoServicesProvider.getBankingDaoServices();
	}
	
	public BankingDaoServices getBankingDaoServices() {
		return bankingDaoServices;
	}

	public void setBankingDaoServices(BankingDaoServices bankingDaoServices) {
		this.bankingDaoServices = bankingDaoServices;
	}

	public int acceptCustomerDetails(String customerName,
			String customerEmailId, String dateOfBirth,
			String localAddressCity, String localAddressState,
			int localAddressPinCode, String homeAddressCity,
			String homeAddressState, int homeAddressPinCode)
					throws ServicesNotFoundException {
		return bankingDaoServices.insertCustomer( new Customer(customerName, customerEmailId, dateOfBirth, new Address(localAddressPinCode, localAddressCity, localAddressState), new Address(homeAddressPinCode, homeAddressCity, homeAddressState)));
	}
	public Account openAccount(int customerId, String accountType, float initBalance)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAmountException, InvalidAccountTypeException,
			ServicesNotFoundException, InvalidAccountNoException, AccountNotFoundException {
		if(initBalance<=0) throw new InvalidAmountException("Enter Valid Amount");
		getCustomerDetails(customerId);
		Account	accounts=new Account(accountType, initBalance);
		int accNo=bankingDaoServices.insertAccount(customerId, accounts);
		if(accNo!=0){
		accounts=getAccountDetails(customerId, accNo);
		}
		return accounts;
	}
	public boolean depositAmount(int customerId, int accountNo, float amount, int pin)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			InvalidAmountException, ServicesNotFoundException, InsufficientBalanceException, InvalidPinException, AccountBlockedException {
		pinVerify(customerId, accountNo, pin);
		if(amount<0)throw new InvalidAmountException("Enter Valid Amount");
		getAccountDetails(customerId, accountNo);
		bankingDaoServices.insertTransaction(customerId, accountNo, new Transaction(amount, "in"));
		Account account=bankingDaoServices.getAccount(customerId, accountNo);
		account.setAccountBalance(account.getAccountBalance()+amount);
		bankingDaoServices.updateAccount(customerId, account);

		return true;
	}
	public boolean withDrawAmount(int customerId, int accountNo, Float amount, int pin)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			InvalidAmountException, InsufficientBalanceException,
			FundNotTransferException, ServicesNotFoundException, InvalidPinException, AccountBlockedException {
		pinVerify(customerId, accountNo, pin);
		getAccountDetails(customerId, accountNo);
		if(getAccountDetails(customerId, accountNo).getAccountBalance()==0)
			throw new InsufficientBalanceException("Balance is 0, you can not withdraw");
		bankingDaoServices.insertTransaction(customerId, accountNo, new Transaction(amount, "out"));
		Account account=bankingDaoServices.getAccount(customerId, accountNo);
		account.setAccountBalance(account.getAccountBalance()-amount);
		bankingDaoServices.updateAccount(customerId, account);
		return true;
	}
	public boolean fundTransfer(int customerIdTo, int accountNoTo,
			int customerIdFrom, int accountNoFrom, float transferAmount , int pin)
					throws InvalidCustomerIdException, CustomerNotFoundException,
					InvalidAccountNoException, InsufficientBalanceException,
					FundNotTransferException, ServicesNotFoundException, AccountNotFoundException, InvalidAmountException, InvalidPinException, AccountBlockedException{
		pinVerify(customerIdFrom, accountNoFrom, pin);
		getCustomerDetails(customerIdTo);
		getCustomerDetails(customerIdFrom);
		getAccountDetails(customerIdTo, accountNoTo);
		getAccountDetails(customerIdFrom, accountNoFrom);
		bankingDaoServices.insertTransaction(customerIdFrom, accountNoFrom, new Transaction(transferAmount, "out"));
		bankingDaoServices.insertTransaction(customerIdTo, accountNoTo, new Transaction(transferAmount, "in"));	
		Account account=bankingDaoServices.getAccount(customerIdFrom, accountNoFrom);
		account.setAccountBalance(account.getAccountBalance()-transferAmount);
		bankingDaoServices.updateAccount(customerIdFrom, account);
		account=null;
		account=bankingDaoServices.getAccount(customerIdTo, accountNoTo);
		account.setAccountBalance(account.getAccountBalance()+transferAmount);
		bankingDaoServices.updateAccount(customerIdTo, account);
		return false;

	}
	public float getAccountBalance(int custId, int accNo , int pin)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,InsufficientBalanceException,
			ServicesNotFoundException, InvalidAmountException, InvalidPinException, AccountBlockedException {
		pinVerify(custId, accNo, pin);
		getCustomerDetails(custId);
		getAccountDetails(custId, accNo);
		return bankingDaoServices.getAccount(custId, accNo).getAccountBalance();
	}
	public Customer getCustomerDetails(int customerId)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			ServicesNotFoundException {
		if(customerId<100) throw new InvalidCustomerIdException("Enter Customer id >100");
		if(bankingDaoServices.getCustomer(customerId)==null)
			throw new CustomerNotFoundException("Customer not found");
		Customer c= bankingDaoServices.getCustomer(customerId);	
		return c;
	}
	public Account getAccountDetails(int customerId, int accountNo)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			InvalidAccountNoException, AccountNotFoundException,
			ServicesNotFoundException {
		if(accountNo<1000)throw new InvalidAccountNoException("Enter AccountNO >1000");
		Account account=bankingDaoServices.getAccount(customerId, accountNo);
		return account;
	}
	public ArrayList<Customer> getAllCustomerDetails() throws ServicesNotFoundException {
		return bankingDaoServices.getCustomers();
	}
	public ArrayList<Account> getCustomerAllAccountDetails(int customerId)
			throws InvalidCustomerIdException, CustomerNotFoundException,
			ServicesNotFoundException {
		getCustomerDetails(customerId);
		return bankingDaoServices.getAccounts(customerId);
	}
	public ArrayList<Transaction> getCustomerGetAccountGetAllTransactions(
			int customerId, int accountNo) throws InvalidCustomerIdException,
			CustomerNotFoundException, InvalidAccountNoException,
			AccountNotFoundException, ServicesNotFoundException {
		return bankingDaoServices.getTransaction(customerId, accountNo);}
	public void DeleteCustomer(int cust) throws InvalidCustomerIdException, CustomerNotFoundException, ServicesNotFoundException {
		getCustomerDetails(cust);
		bankingDaoServices.deleteCustomer(cust);
	}
	public int resetPin(int custId, int accNo)
			throws InvalidAccountNoException, InvalidCustomerIdException, CustomerNotFoundException, AccountNotFoundException, ServicesNotFoundException {
		getCustomerDetails(custId);
		getAccountDetails(custId, accNo);
		return bankingDaoServices.generatePin(custId, accNo);
	}
	public boolean pinVerify(int customerId, int accountNo, int pin)
			throws ServicesNotFoundException, InvalidCustomerIdException,
			CustomerNotFoundException, InvalidAmountException,
			InvalidAccountNoException, AccountNotFoundException,
			InsufficientBalanceException, InvalidPinException, AccountBlockedException {
		if(getAccountDetails(customerId, accountNo).getPinCounter()==2)
			throw new AccountBlockedException("Account has been blocked");
		if(getAccountDetails(customerId, accountNo).getPinNumber()==pin){
			getAccountDetails(customerId, accountNo).setPinCounter(0);
			return true;
		}
		else{
			int counter=getAccountDetails(customerId, accountNo).getPinCounter();
			getAccountDetails(customerId, accountNo).setPinCounter(++counter);
			throw new InvalidPinException("Entered pin is wrong");
		}
	}
	@Override
	public void exit() {
	//	bankingDaoServices.daoServiceClose();
	}
}
