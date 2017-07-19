package com.zensar.banking.client;
import java.util.ArrayList;
import java.util.Scanner;

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
import com.zensar.banking.providers.BankingServicesProvider;
import com.zensar.banking.services.BankingServices;
public class MainClass {
	public static void main(String[] args) throws AccountNotFoundException, ServicesNotFoundException, InvalidCustomerIdException, InvalidAmountException, InvalidAccountNoException, InsufficientBalanceException, FundNotTransferException, InvalidAccountTypeException {
		String customerName,customerEmailId,dateOfBirth,localAddressCity,localAddressState,homeAddressCity,homeAddressState;
		int localAddressPinCode,homeAddressPinCode;
		int choice=0;
		BankingServices bankingServices= BankingServicesProvider.getBankingServices();
		Scanner in = new Scanner(System.in);
		do{
			System.out.println("Enter your choice\n1. Add Customer\n2. Open Account\n3. Get Customer Details based on customerId \n4. Get Account Details based on account number \n5. Deposit Amount \n6. Withdraw Amount \n7. Check account balance\n8. Fund Transfer\n9. Get all customer details\n10. Get all account details of a particular customer \n11.Get Transaction Details of particular account\n12. Delete Customer \n13. Chnage existing Pin\n14. Reset pin of blocked account\n15. Exit and save data");
			try{
				choice=in.nextInt();
				switch(choice){
				case 1:
					System.out.println("Enter Customer Details");
					System.out.println("Enter customer name");
					customerName=in.next();
					System.out.println("Enter customer email");
					customerEmailId=in.next();
					System.out.println("Enter customer date of birth");
					dateOfBirth=in.next();
					System.out.println("Enter customer local city");
					localAddressCity=in.next();
					System.out.println("Enter customer local state");
					localAddressState=in.next();
					System.out.println("Enter customer local pin");
					localAddressPinCode=in.nextInt();
					System.out.println("Enter customer home city");
					homeAddressCity=in.next();
					System.out.println("Enter customer home state");
					homeAddressState=in.next();
					System.out.println("Enter customer home pin");
					homeAddressPinCode=in.nextInt();
					int custId= bankingServices.acceptCustomerDetails(customerName, customerEmailId, dateOfBirth, localAddressCity, localAddressState, localAddressPinCode, homeAddressCity, homeAddressState, homeAddressPinCode);
					System.out.println("Custome Id Obtained "+ custId);
					break;
				case 2:
					System.out.println("To open an account Enter Customer Id ");
					int customerId=in.nextInt(); 
					System.out.println("Enter Type of Account ");
					String accountType=in.next(); 
					System.out.println("Initial Balance ");
					float initBalance=in.nextFloat();
					Account account=bankingServices.openAccount(customerId, accountType, initBalance);
					System.out.println("Account Number obtained "+account.getAccountNo());
					System.out.println("Account pin obtained "+account.getPinNumber());
					break;
				case 3:
					System.out.println("Enter customer Id to view customer details");
					custId=in.nextInt();
					Customer c=bankingServices.getCustomerDetails(custId);
					System.out.println(c);
					break;
				case 4:
					System.out.println("Enter customer id an");
					custId=in.nextInt();
					System.out.println("account number ");
					int accNo=in.nextInt();
					account=bankingServices.getAccountDetails(custId, accNo);
					System.out.println(account);
					break;
				case 5:
					System.out.println("Enter customer id ");
					custId=in.nextInt();
					System.out.println("Enter account number");
					accNo=in.nextInt();
					System.out.println("Amount to deposit");
					float amt=in.nextFloat();
					System.out.println("Enter pin");
					int pin=in.nextInt();
					bankingServices.depositAmount(custId, accNo, amt, pin);
					break;
				case 6:
					System.out.println("Enter customer id ");
					custId=in.nextInt();
					System.out.println("Enter account number");
					accNo=in.nextInt();
					System.out.println("Amount to withdraw");
					amt=in.nextFloat();
					System.out.println("Enter pin");
					pin=in.nextInt();
					bankingServices.withDrawAmount(custId, accNo, amt, pin);
					break;
				case 7:
					System.out.println("Enter customer id  ");
					custId=in.nextInt();
					System.out.println("Enter account number ");
					accNo=in.nextInt();
					System.out.println("Enter pin number  ");
					pin=in.nextInt();
					amt=bankingServices.getAccountBalance(custId, accNo, pin);
					System.out.println(" Balance "+ amt);
					break;
				case 8:
					System.out.println("Enter customer id and account no of a customer to which you want to transfer fund ");
					System.out.println("Enter customer id");
					int cust=in.nextInt();
					System.out.println("Enter account number");
					int acc=in.nextInt();
					System.out.println("Enter customer id and account no of a customer from which you want to transfer fund ");
					System.out.println("Enter customer id");
					custId=in.nextInt();
					System.out.println("Enter account number");
					int acc2=in.nextInt();
					System.out.println("Enter the amount ");
					amt=in.nextFloat();
					System.out.println("Enter the your pin ");
					pin=in.nextInt();
					bankingServices.fundTransfer(cust, acc, custId, acc2, amt, pin);
					break;
				case 9:
					ArrayList<Customer> cArr;
					cArr=bankingServices.getAllCustomerDetails();
					for (Customer customer : cArr) {
						System.out.println(customer);
					}
					break;
				case 10:
					System.out.println("Enter customerid to find account details");
					custId=in.nextInt();
					System.out.println(" Accounts are ");
					ArrayList<Account> account1=bankingServices.getCustomerAllAccountDetails(custId);
					for (Account account2 : account1) {
						System.out.println(account2);
					}
					break;
				case 11:
					System.out.println("Enter customer id");
					custId=in.nextInt();
					System.out.println("Enter account number");
					accNo=in.nextInt();
					ArrayList<Transaction> transaction=bankingServices.getCustomerGetAccountGetAllTransactions(custId, accNo);
					for (Transaction transaction2 : transaction) {
						System.out.println(transaction2.getTransactionId()+" "+transaction2.getTransactionType());
					}
					break;
				case 12:
					System.out.println("Enter customer id to delete all details");
					custId=in.nextInt();
					bankingServices.DeleteCustomer(custId);
					break;
				case 13:
					System.out.println("Enter customer id ");
					custId=in.nextInt();
					System.out.println("Enter account number ");
					accNo=in.nextInt();
					System.out.println("Enter old pin ");
					pin=in.nextInt();
					if(bankingServices.pinVerify(custId, accNo, pin))
						System.out.println(" Newly generated pin " +bankingServices.resetPin(custId, accNo) );
					break;
				case 14:
					System.out.println("Enter customer id ");
					custId=in.nextInt();
					System.out.println("Enter account number ");
					accNo=in.nextInt();
					pin=bankingServices.resetPin(custId, accNo);
					System.out.println(" Newly obtained pin"+pin);
					break;
				case 15:
					bankingServices.exit();

					break;
				default:
					System.out.println("Wrong choice");
				}
			}
			catch(CustomerNotFoundException e){e.printStackTrace();}
			catch (AccountNotFoundException e) {e.printStackTrace();}
			catch(InvalidAmountException e){e.printStackTrace();}
			catch(InvalidAccountNoException e){e.printStackTrace();}
			catch(InsufficientBalanceException e){e.printStackTrace();}
			catch(InvalidCustomerIdException e){e.printStackTrace();}
			catch(ServicesNotFoundException e){e.printStackTrace();}
			catch(InvalidPinException e){e.printStackTrace();}
			catch(AccountBlockedException e){ e.printStackTrace();}
		}while(choice!=15);
	}
}
