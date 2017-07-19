package com.zensar.banking.gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zensar.banking.beans.Account;
import com.zensar.banking.exceptions.AccountNotFoundException;
import com.zensar.banking.exceptions.CustomerNotFoundException;
import com.zensar.banking.exceptions.InvalidAccountNoException;
import com.zensar.banking.exceptions.InvalidAccountTypeException;
import com.zensar.banking.exceptions.InvalidAmountException;
import com.zensar.banking.exceptions.InvalidCustomerIdException;
import com.zensar.banking.exceptions.ServicesNotFoundException;
import com.zensar.banking.providers.BankingServicesProvider;
import com.zensar.banking.services.BankingServices;

/**
 * Servlet implementation class OpenAcc
 */
public class OpenAcc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenAcc() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		String accType=request.getParameter("account");
		int balance=Integer.parseInt(request.getParameter("balance"));
		
		try {
			BankingServices bankingServices= BankingServicesProvider.getBankingServices();
			Account account=bankingServices.openAccount(id, accType, balance);
			out.println("<html>");
			out.println("<body>");
			out.println("Account Number Obtained ="+account.getAccountNo()+" Account Pin Number ="+account.getPinNumber());
			out.println("</html>");
			out.println("</body>");
			
			
			
		} catch (ServicesNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidCustomerIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAccountTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAccountNoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
