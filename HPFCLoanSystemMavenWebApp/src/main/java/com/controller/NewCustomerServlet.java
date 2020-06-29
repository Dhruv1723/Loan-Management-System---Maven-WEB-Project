package com.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.LoanApplication;
import com.dao.LoanDAO;

/**
 * Servlet implementation class NewCustomerServlet
 */
public class NewCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
	//private RequestDispatcher rd;
	
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out=response.getWriter();
		
		LoanApplication la=new LoanApplication();
		la.setName(request.getParameter("name"));
		la.setUsername(request.getParameter("username"));
		la.setPassword(request.getParameter("password"));
		la.setAge(Integer.parseInt(request.getParameter("age")));
		la.setAddress(request.getParameter("address"));
		la.setContactnumber(request.getParameter("number"));
		
		double amount = Double.parseDouble(request.getParameter("amount"));
		double year = Double.parseDouble(request.getParameter("year"));
		double totalamount=(amount + (0.07*amount*year));
		
		la.setLoanamount(amount);
		la.setTotalpayableamount(totalamount);
		la.setDuration(year);
		la.setDecision("Pending");
				
		try {
			LoanDAO ld=new LoanDAO();
			int status=ld.newLoan(la);
			
			if(status>0) {
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('Loan Applied Successfully.!');");
			    out.println("alert('Please Login after 24 hours to check your Loan Status.');");
			    out.println("location='Login.html';");
		        out.println("</script>");
				
				
			}else {
				out.println("<font size=5 color=red>Error in Application Submition</font>");
			}
			
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
