package com.zensar.banking.gui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zensar.banking.exceptions.ServicesNotFoundException;
import com.zensar.banking.providers.BankingServicesProvider;
import com.zensar.banking.services.BankingServices;

/**
 * Servlet implementation class InsertCust
 */
public class InsertCust extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCust() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out=response.getWriter();
		String customername=	request.getParameter("customername");
	String customeremail=	request.getParameter("customeremail");
	String dateofbirth=	request.getParameter("dateofbirth");
	String localcity=	request.getParameter("localcity");
	String localstate=	request.getParameter("localstate");
	int localpin=	Integer.parseInt(request.getParameter("localpin"));
	String homecity=	request.getParameter("homecity");
	String homestate=	request.getParameter("homestate");
	int homepin=	Integer.parseInt(request.getParameter("homepin"));
	try {
		BankingServices bankingServices= BankingServicesProvider.getBankingServices();
		int custId=bankingServices.acceptCustomerDetails(customername, customeremail, dateofbirth, localcity, localstate, localpin, homecity, homestate, homepin);
		out.println("Customer Id obtained"+custId);
	} catch (ServicesNotFoundException e) {
		e.printStackTrace();
	}
	
	
	}

}
