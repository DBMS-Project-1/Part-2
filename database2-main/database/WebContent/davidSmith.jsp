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
	
	<form action = "listTrees">
		<input type = "submit" value = "List Trees"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all Trees</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <>
                <th>Size</th>
                <th>Height</th>
                <th>Distance from House</th>

            </tr>
            <c:forEach var="tree" items="${listTree}">
                <tr style="text-align:center">
                    <td><c:out value="${users.size}" /></td>
                    <td><c:out value="${users.height}" /></td>
                    <td><c:out value="${users.distanceFromHouse}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>