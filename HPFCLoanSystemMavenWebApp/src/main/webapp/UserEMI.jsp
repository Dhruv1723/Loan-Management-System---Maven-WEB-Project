<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@page import="com.bean.LoanApplication" %>
<%@page import="org.hibernate.Session" %>
<%@page import="java.util.*" %>
<%@page import="javax.persistence.Query" %>
<%@page import="org.hibernate.SessionFactory" %>
<%@page import="org.hibernate.Transaction" %>
<%@page import="org.hibernate.cfg.Configuration" %>
<title>Insert title here</title>
<link rel="stylesheet" href="styleuseremi.css">

</head>
<body>

<form action="PaymentServlet" method="post" class="wrapper">
	<% 
	HttpSession ses=request.getSession(false);
	String name = (String)ses.getAttribute("username");
	%>
	<h1 class="heading">Congratulations <%= name %> on your Approval</h1>
	<hr>
	<p class="para">Details of your Loan EMI</p>
	<div class="form">	
		<div class="label">
			<label> Name: <%= name %> </label><br>
		
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
					double totalamount=la.getTotalpayableamount();
					double year=la.getDuration();
					
					double emi= totalamount/(12*year);
			%>
			
			<label> Loan Amount: <%= la.getLoanamount() %> </label><br>
			<label> Total Payable Amount: <%= la.getTotalpayableamount() %> </label><br>
			<label> Interest Rate: <%= 7.0 %>% </label><br>
			<label> Duration: <%= la.getDuration() %> years </label><br>
			<label> EMI:  <%= emi %>  </label><br>
		</div>
		
	</div>
	<input type="submit" value="Pay EMI">	
			<% } %>
				
			<%
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			%>
		
	</form>

</body>
</html>