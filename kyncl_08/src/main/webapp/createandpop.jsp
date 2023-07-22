<%-- 
createandpop.jsp
Module 8 assignment
Name: Brittany Kyncl
Date: 7.4.23
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

 	<h2>Creating Garden Database and Tables...</h2>

    <%-- Check if the "message" attribute is set --%>
    <c:if test="${not empty message}">
        <h3>${message}</h3>
    </c:if>


    <a href="index.jsp">Return to Index</a>
</body>
</html>
