package com.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.LoanService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RequestDispatcher rd; 
    PrintWriter out;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(username.equals("Admin") && password.equals("Admin")) {
			HttpSession ses=request.getSession();
			rd=request.getRequestDispatcher("AdminAccess.jsp");
			rd.forward(request, response);
			
			
		} else {
			LoanService ls=new LoanService();
			String name=ls.loginvalidate(username, password);
			
			if(name != null) {
				HttpSession ses=request.getSession();
				ses.setAttribute("username", name);
				rd=request.getRequestDispatcher("UserAccess.jsp");
				rd.forward(request, response);
			}
			else {
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('Incorrect Username or Password.');");
			    out.println("location='Login.html';");
		        out.println("</script>");
			}
			
		}
		
	}

}
