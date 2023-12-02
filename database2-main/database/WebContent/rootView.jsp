<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>Credit Card Numbers</th>

            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.creditCardNum}" /></td>
            </c:forEach>
        </table>
	</div>
	
	<!-- list all quotes made by users -->
	<h1>List all Quotes</h1> 
	
	<!-- List all trees added by users -->
	<h1>List all Trees</h1>
	
	
	<!-- part 3 information -->
	<h1>User Statistics and data</h1>
	
	<h2>Big Clients</h2>
	<h2>Easy Clients</h2>
	<h2>One Tree Quotes</h2>
	<h2>Prospective Clients</h2>
	<h2>Highest Tree</h2>
	<h2>Overdue Bills</h2>
	<h2>Bad Clients</h2>
	<h2>Good Clients</h2>
	<h2>Client Statistics</h2>
	
	</div>

</body>
</html>