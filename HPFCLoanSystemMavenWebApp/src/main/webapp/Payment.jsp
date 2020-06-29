<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@page import="java.sql.*" %>
<%@page import="java.util.List" %>
<%@page import="com.bean.Payment" %>
<title>Insert title here</title>
<link rel="stylesheet" href="stylepay.css">

</head>
<body>

<form class="wrapper" action="SavePaymentServlet" method="post">
	
	<% 
	HttpSession ses=request.getSession(false);
	String name = (String)ses.getAttribute("username");
	Payment p=new Payment();
	
	List<Payment> paylist=(List<Payment>)request.getAttribute("list");
	
	double totalemi=0,emi=0,fine=0;
	for(Payment p1: paylist){
		

	%>
	
	<h1 class="heading">Welcome To Online EMI Payment Portal</h1>
	<hr>	
	<p class="para">Please Provide some details to Pay your EMI</p>
	<div class="form">
		<table class="table">
			<tr>
				<td><strong>Name:</strong></td>
				<td><input type="text" name="name1"></td> 
			</tr>
			<tr>
				<td><strong>Card Number:</strong></td>
				<td><input type="text" name="cardnumber"></td> 
			</tr>
			<tr>
				<td><strong>CVV code:</strong></td>
				<td><input type="text" name="cvv"></td> 
			</tr>
			<tr>
				<td><strong>Regular EMI:</strong></td>
				<td><strong><%= p1.getEmi() %></strong></td>
			</tr>
			<tr>
				<td><strong>Fine:</strong></td>
				<td><strong><%= p1.getFine() %></strong></td>
			</tr>
			<tr>
				<td><strong>Total EMI:</strong></td>
				<td><strong><%= p1.getTotalEmi() %></strong></td>
			</tr>
			<% } %>
			<tr>
				<td></td>
				<td><input type="submit" value="Pay Now"></td>
			</tr>
			
		</table>
	
	</div>
	

</form>

</body>
</html>