<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All David Quote</title>
</head>
<body>
	<div align="center">
   <center>
        <h1>Quote Management</h1>
    </center>
    <div align="center">
         <form action="insertDavidReply" method="post">

			<label for="product">ID: </label>
            <input type="text" id="product" name="id" required><br><br>
			
			<input type="radio" name="acceptOrDeny" value="accept" id="davidAccept" checked>
    		<label for="userRole">Accept</label>
    
    		<input type="radio" name="acceptOrDeny" value="deny" id="davidDeny">
    		<label for="rootRole">Deny</label>
    		<br>
			<label for="product">Reply</label>
            <input type="text" id="product" name="davidReply"><br><br>
            
            <input type="submit" value="Send">
        </form>
        
   <div align="center">
   <center>
        <h1>All David Quote</h1>
    </center>
    
        <table border="1" cellpadding="5">
            <caption><h2>All User Quote</h2></caption>
            <tr>
            	<th>id</th>
            	<th>clientId</th>
				<th>price</th>
				<th>schedulestart</th>
				<th>scheduleend</th>
				<th>userAccept</th>
				<th>davidAccept</th>
				<th>userResponse</th>
				<th>davidResponse</th>

            </tr>
            <c:forEach var="quote" items="${listQuote}">
                <tr style="text-align:center">

                    <td><c:out value="${quote.id}" /></td>
                    <td><c:out value="${quote.clientId}" /></td>
                    <td><c:out value="${quote.price}" /></td>
                    <td><c:out value="${quote.scheduleStart}" /></td>
                    <td><c:out value="${quote.scheduleEnd}" /></td>
                    <td><c:out value="${quote.userAccept}" /></td>
                    <td><c:out value="${quote.davidAccept}" /></td>
                    <td><c:out value="${quote.userResponse}" /></td>
                    <td><c:out value="${quote.davidResponse}" /></td>

                </tr>
            </c:forEach>
        </table>
        
    </div>   
</body>
</html>
