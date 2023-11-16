<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All Tree List</title>
</head>
<body>
   <div align="center">
   <center>
        <h1>Quote Management</h1>
    </center>
    <div align="center">
         <form action="insertQuote" method="post">

            <label for="product">ID: </label>
            <input type="text" id="product" name="clientId" required><br><br>

            <label for="product">Price: </label>
            <input type="number" id="quantity" name="price" required><br><br>

 			<label for="date">Schedule Start: </label>
    		<input type="date" id="scheduleStart" name="scheduleStart" required><br><br>

    		<label for="date">Schedule End: </label>
    		<input type="date" id="scheduleEnd" name="scheduleEnd" required><br><br>
            
            <input type="submit" value="Insert Quote">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>List of Trees</h2></caption>
            <tr>
            	<th>Quote ID</th>
				<th>Size</th>
                <th>Height</th>
                <th>Distance from House</th>

            </tr>
            <c:forEach var="tree" items="${listTree}">
                <tr style="text-align:center">
                	<td><c:out value="${tree.quoteId}" /></td>
                    <td><c:out value="${tree.size}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.distanceFromHouse}" /></td>

                </tr>
            </c:forEach>
        </table>
        
        <br>
        <br>
        
        
        
        
        
    </div>   
</body>
</html>