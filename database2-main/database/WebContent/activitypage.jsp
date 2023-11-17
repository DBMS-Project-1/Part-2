<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Request a Quote</title>
</head>
<body>
	<div align = "center">	
<h1>List all Replies</h1>
	<form action = "listDavidQuote" method="post">
		<input type = "submit" value = "List David Reply"/>
	</form>

	<a href="login.jsp"target ="_self" > logout</a><br><br> 
</div>	
	
	
    <center>
        <h1>Request a Quote</h1>
        <form action="submitQuote" method="post">

            <label for="product">Size: </label>
            <input type="text" id="product" name="size" required><br><br>

            <label for="product">Height: </label>
            <input type="number" id="quantity" name="height" required><br><br>

			<label for="product">Distance from House: </label>
            <input type="number" id="quantity" name="distanceFromHouse" required><br><br>
            
            <input type="submit" value="Submit Quote">
        </form>
    </center>
</body>
</html>