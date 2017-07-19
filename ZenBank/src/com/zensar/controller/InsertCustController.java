package com.zensar.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zensar.banking.beans.Customer;
import com.zensar.banking.exceptions.ServicesNotFoundException;
import com.zensar.banking.services.BankingServices;


@Controller
public class InsertCustController {

	@Autowired
	private BankingServices bankingServices;
	
	public BankingServices getBankingServices() {
		return bankingServices;
	}


	public void setBankingServices(BankingServices bankingServices) {
		this.bankingServices = bankingServices;
	}


	@ModelAttribute("customer")
	private Customer getCustomer(){
		return new Customer();
	}
	
	
	@RequestMapping(value = "/insertc", method = RequestMethod.POST)
	public ModelAndView executeLogin(@ModelAttribute("customer") Customer customer) {
		
		System.out.println(customer.getCustomerName());
		ModelAndView modelAndView = null;
		
		
			try {
				
				if (bankingServices.acceptCustomerDetails("vikas", "xyz", "24", "pune", "mh", 11, "mum", "mh", 33)!=0) 
					modelAndView = new ModelAndView("successPage", "customer",
							customer);
			} catch (ServicesNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		
		return modelAndView;
	}
	
	
}
