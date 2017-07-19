package com.zensar.banking.providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.zensar.banking.exceptions.ServicesNotFoundException;
import com.zensar.banking.services.BankingServices;
import com.zensar.banking.services.HDFCBankingServicesImpl;


public class BankingServicesProvider {

	public static BankingServices getBankingServices() throws ServicesNotFoundException{
	

		try {
			Properties p= new Properties();
			p.load(new FileInputStream(new File("D:\\Java Training\\Vikas\\ZenBank\\resources\\zensarbanking.properties")));
			return(BankingServices)Class.forName(p.getProperty("BankingServices")).newInstance();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			throw new ServicesNotFoundException("Service is down",e);
		} catch (IOException e) {

			e.printStackTrace();
			throw new ServicesNotFoundException("Service is down",e);
		} catch (InstantiationException e) {

			e.printStackTrace();
			throw new ServicesNotFoundException("Service is down",e);
		} catch (IllegalAccessException e) {

			e.printStackTrace();
			throw new ServicesNotFoundException("Service is down",e);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			throw new ServicesNotFoundException("Service is down",e);
		}
		


	}
	
}
