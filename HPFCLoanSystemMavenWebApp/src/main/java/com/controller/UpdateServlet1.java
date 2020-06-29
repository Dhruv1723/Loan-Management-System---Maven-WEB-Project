package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LoanDAO;

/**
 * Servlet implementation class UpdateServlet1
 */
public class UpdateServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	//RequestDispatcher rd;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//boolean b=false;
		out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		String decision=request.getParameter("decision");
		
		try {
			LoanDAO ld=new LoanDAO();
			ld.updateDecision(id, decision);
			
			if(true) {
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('Data Updated Successfully.!');");
			    out.println("location='AdminAccess.jsp';");
		        out.println("</script>");
				
			//	rd=request.getRequestDispatcher("AdminAccess.jsp");
			//	rd.forward(request, response);
			}else {
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('Error in Updating Data.');");
			    out.println("location='AdminAccess.jsp';");
		        out.println("</script>");
			}
		}catch(Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
