package com.zensar.banking.daoservices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.zensar.banking.beans.Account;
import com.zensar.banking.beans.Customer;


public class HibernateDaoServicesImpl extends HibernateDaoSupport implements BankingDaoServices{
	Configuration  configuration;
	SessionFactory factory;
	Session session;
	Transaction tx;


	public HibernateDaoServicesImpl() {
		configuration=new Configuration();
		factory=configuration.configure().buildSessionFactory();
		session=factory.openSession();
	}

	@Override
	public int insertCustomer(Customer customer) {
		
		getHibernateTemplate().update(customer);
		
		return 1;
		
		/*Query q=session.createQuery("select max(customerId) from Customer");
		List<Integer> list=q.list();  
		tx=session.beginTransaction();
		session.save(customer);
		tx.commit();

		if(list.get(0)!=null){
			return (list.get(0)+1);
		}
		return 1;
			*/
	}

	@Override
	public int insertAccount(int customerId, Account account) {


		Customer customer=getCustomer(customerId);
		account.setPinNumber((int) (Math.random()*8000)+2000);
		Query q=session.createQuery("select max(accountNo) from Account");
		List<Integer> list=q.list();  
		int accno;

		if(list.get(0)==null){
			customer.getAccounts().put(1000, account);
		accno=1000;
		}
			else{
				accno=list.get(0)+1;
			customer.getAccounts().put(accno, account);    
			}
			System.out.println(customer);
		tx=session.beginTransaction();
		session.save(customer);
		tx.commit();
		return accno;
	}



	@Override
	public boolean insertTransaction(int customerId, int accountNo,
			com.zensar.banking.beans.Transaction transaction) {
		Account account=getAccount(customerId, accountNo);

		Query query=session.createQuery("select max(transactionId) from Transaction");
		List<Integer> list=query.list();
		if(list.get(0)==null){
			account.getTransaction().put(1000, transaction);
		}	
		else
		account.getTransaction().put(list.get(0)+1, transaction);
		System.out.println(transaction);
		tx=session.beginTransaction();
		session.save(account);
		tx.commit();
		return true;
	}



	@Override
	public Customer getCustomer(int customerId) {
		tx=session.beginTransaction();

		Customer c = (Customer)session.get(Customer.class, customerId);

		tx.commit();
		return c;
	}

	@Override
	public Account getAccount(int customerId, int accountNo) {
		return (Account) session.get(Account.class, accountNo);
	}

	@Override
	public ArrayList<Customer> getCustomers() {
		tx=session.beginTransaction();
		Query query=session.createQuery("from Customer");
		List list=query.list();
		tx.commit();
		
		return (ArrayList<Customer>) list;
	}

	@Override
	public ArrayList<Account> getAccounts(int customerId) {
		tx=session.beginTransaction();
		Query query=session.createQuery("from Account where customerId="+customerId);
		List list=query.list();
		tx.commit();
		return (ArrayList<Account>) list;
		
	}

	@Override
	public ArrayList<com.zensar.banking.beans.Transaction> getTransaction(int customerId, int accountNo) {
		tx=session.beginTransaction();
		Query query=session.createQuery("from Transaction where accountNo="+accountNo);
		List list=query.list();
		tx.commit();
		return  (ArrayList<com.zensar.banking.beans.Transaction>) list;
	}

	@Override
	public void deleteCustomer(int customerId) {
tx=session.beginTransaction();
Customer customer=getCustomer(customerId);
session.delete(customer);
tx.commit();
		
	}

	@Override
	public int generatePin(int custId, int accNo) {
	/*	tx=session.beginTransaction();
	Account account=getAccount(custId, accNo);
	account.setPinNumber((int)Math.random()+1);
	session.save(account);
		tx.commit();*/
		return 0;
	}

	@Override
	public boolean updateAccount(int customerId, Account account) {
		tx=session.beginTransaction();
		session.save(account);
		tx.commit();
		
		return true;
	}



}
