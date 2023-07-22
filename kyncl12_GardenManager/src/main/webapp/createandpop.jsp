<%-- 
createandpop.jsp
Module 12 assignment
Name: Brittany Kyncl
Date: 7.21.23
Course: CSD430
Page to display successfull database creation and record insertion 
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Populate Garden Database</title>
</head>
<body>
	<div class="form-container">
		<div class="creation-container">
			<h2>Creating Garden Database and Tables...</h2>
	
		    <%-- Check if the "message" attribute is set --%>
		    <c:if test="${not empty message}">
		        <h3>${message}</h3>
		    </c:if>
		</div>
	</div>	
</body>
</html>