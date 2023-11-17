<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>David Smith</title>
</head>

<div align = "center">
<h1>List all Trees</h1>
	<form action = "listTrees" method="post">
		<input type = "submit" value = "List Trees"/>
	</form>
</div>


<div align = "center">
<h1>List all Quotes</h1>
	<form action = "listUserQuotes" method="post">
		<input type = "submit" value = "List Quotes"/>
	</form>
	
	
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

	

</body>
</html>