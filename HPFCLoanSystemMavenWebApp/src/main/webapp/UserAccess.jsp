<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="StyleAdmin.css">
<%@page import="com.bean.LoanApplication" %>
<%@page import="org.hibernate.Session" %>
<%@page import="java.util.*" %>
<%@page import="javax.persistence.Query" %>
<%@page import="org.hibernate.SessionFactory" %>
<%@page import="org.hibernate.Transaction" %>
<%@page import="org.hibernate.cfg.Configuration" %>

<title>Insert title here</title>
</head>
<body>
	<form action="EMIServlet" method="post">
	<% 
	HttpSession ses=request.getSession(false);
	String name = (String)ses.getAttribute("username");
	%>
	<h1 class="heading"> Welcome <%= name %></h1>
	<hr>
	<p class="para">Details about Your Loan</p>
		<table class="content">
			
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Age</th>
				<th>Address</th>
				<th>Contact Number</th>
				<th>Loan Amount</th>
				<th>Total Payable Amount</th>
				<th>Duration(Years)</th>
				<th>Decision</th>
			</tr>
			<%
			SessionFactory factory;
			Configuration conf;
			try {
				
				conf = new Configuration();
				factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
						.buildSessionFactory();

				Session ses1 = factory.openSession();
				Transaction tx = ses1.beginTransaction();			
		
				Query q=ses1.createQuery("from LoanApplication la where la.name=:name");
				q.setParameter("name", name);
				List<LoanApplication> loanlist = (List<LoanApplication>)q.getResultList();
				for(LoanApplication la : loanlist)
				{
			%>
			<tr>
				<td><%= la.getId() %></td>
				<td><%= la.getName() %></td>
				<td><%= la.getAge() %></td>
				<td><%= la.getAddress() %></td>
				<td><%= la.getContactnumber() %></td>
				<td><%= la.getLoanamount() %></td>
				<td><%= la.getTotalpayableamount() %></td>
				<td><%= la.getDuration() %></td>
				<td><%= la.getDecision() %></td>
			</tr>
			<% } %>
			<tr>
				<td class="btn"><input type="submit" value="Check EMI"></td>
			</tr>
			<%
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			%>
		</table>
	</form>
	
	
	

</body>
</html>