<%-- 
index.jsp
Module 8 assignment
Name: Brittany Kyncl
Date: 7.4.23
Course: CSD430
Index page for initial navigation to database creation and record population or to the form entry page
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Garden Database Index</title>
</head>
<body>
	<h2>Please click the "Create and Populate Garden DB" link to create the necessary database</h2>
	<h2>Or navigate to the "Plant Entry Form" if you have previously done so</h2>
	<a href="<%=request.getContextPath()%>/plants?action=CreateAndPop">Create and Populate Garden DB, Tables, and Records</a>
	<br>
	<a href="/kyncl_08/form.jsp">To Plant Entry From</a>
</body>
</html>
