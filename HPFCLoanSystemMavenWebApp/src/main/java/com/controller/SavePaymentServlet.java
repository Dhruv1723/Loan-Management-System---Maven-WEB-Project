package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Payment;
import com.dao.LoanDAO;

/**
 * Servlet implementation class SavePaymentServlet
 */
public class SavePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter out;   
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out=response.getWriter();
		HttpSession ses=request.getSession(false);
		String name = (String)ses.getAttribute("username");
		
		int userid=0;
		double fine=0,totalemi=0;
		
		List<Payment> paymentlist=(List<Payment>)request.getAttribute("list");
		for(Payment p1:paymentlist) {
			fine=p1.getFine();
			totalemi=p1.getTotalEmi();
		}
		
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date d = new Date();
			Date today = format.parse(format.format(d));
			System.out.println(today);
			
			LoanDAO ld=new LoanDAO();
			userid=ld.getUserid(name);
			
			int i=ld.newEMIPayment(userid,totalemi,fine,today);
			if(i>0) {
				out.println("<font size=5 color=blue>EMI Paid...Thank You "+ name +"</font>");
			}else {
				out.println("<font size=5 color=red>Error</font>");
			}
		}
		catch(Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}
