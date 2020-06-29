package com.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Payment;
import com.service.LoanService;

/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;    
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses=request.getSession(false);
		String name=(String)ses.getAttribute("username");
		
		LoanService ls=new LoanService();
		List<Payment> payment = new ArrayList<Payment>();
		payment.add(ls.calculateemi(name));
		
		/*for(Payment p: payment) {
			System.out.println(p.getEmi());
			System.out.println(p.getFine());
			System.out.println(p.getTotalEmi());
		}*/
		
		request.setAttribute("list", payment);
		RequestDispatcher rd=request.getRequestDispatcher("Payment.jsp");
		rd.forward(request, response);
	}

}
