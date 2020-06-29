package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.LoanApplication;

/**
 * Servlet implementation class EMIServlet
 */
public class EMIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd; 
	private SessionFactory factory;
	private Configuration conf;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession ses=request.getSession(false);
		String name = (String)ses.getAttribute("username");
		
		try {
			conf = new Configuration();
			factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
					.buildSessionFactory();

			Session ses1 = factory.openSession();
			Transaction tx = ses1.beginTransaction();
			
			Query q=ses1.createQuery("from LoanApplication la where la.name=:name");
			q.setParameter("name", name);
			@SuppressWarnings("unchecked")
			List<LoanApplication> list=q.getResultList();
		
			for(LoanApplication l : list) {
				if(l.getDecision().equals("Approve")) {
					rd=request.getRequestDispatcher("UserEMI.jsp");
					rd.forward(request, response);
				}
				else if(l.getDecision().equals("Pending")) {
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('Your Loan is Not Approved yet..! Sorry');");
				    out.println("location='UserAccess.jsp';");
			        out.println("</script>");				
				}
				else if(l.getDecision().equals("Deny")) {
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('Your Loan is Not Approved yet..! Sorry');");
				    out.println("location='UserAccess.jsp';");
			        out.println("</script>");
				}
			}
			tx.commit();
			ses1.close();
			
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
		
}
