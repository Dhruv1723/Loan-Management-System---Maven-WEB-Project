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
	<form action="EditDecision.html" method="post">
	
	<h1 class="heading"> Welcome Dhruv</h1>
	<hr>
	<p class="para">Loan Application List:</p>
		<table class="content" id="table">
			
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
				HttpSession ses=request.getSession(false);
				SessionFactory factory;
				Configuration conf;
				try {
					
					conf = new Configuration();
					factory = conf.configure("hibernate.cfg.xml").addAnnotatedClass(LoanApplication.class)
							.buildSessionFactory();

					Session ses1 = factory.openSession();
					Transaction tx = ses1.beginTransaction();			
			
					Query q=ses1.createQuery("from LoanApplication");
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
				<td class="btn"><input type="submit" value="Update List"></td>
			</tr>
			<%
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			%>
		</table>
	</form>
	
	<!--  <script>
highlight_row();
function highlight_row() {
    var table = document.getElementById('table');
    var cells = table.getElementsByTagName('td');

    for (var i = 0; i < cells.length; i++) {
        var cell = cells[i];
        cell.onclick = function () {
            var rowId = this.parentNode.rowIndex;

            
            var rowSelected = table.getElementsByTagName('tr')[rowId];
            rowSelected.className += " selected";

            msg = rowSelected.cells[0].innerHTML;
          
            alert(msg);
 			       
        }
    }

}
</script>	-->
	
	<!--  <script type="text/javascript">
		function GetSelected(){
			var grid=document.getElementById("table");
			var checkboxes=document.getElementsByName("decision");
			
			for (var i = 0; i < checkboxes.length; i++) {
	            if (checkboxes[i].checked) {
	               	console.log("yes");
	            	var row = checkBoxes[i].parentNode.parentNode;
	                var text = row.cells[1].innerHTML;
	                
	            }
	        }
			alert(text);
		}
	</script>	-->
</body>
</html>