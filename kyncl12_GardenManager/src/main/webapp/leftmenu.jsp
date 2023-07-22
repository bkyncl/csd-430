<%-- 
leftmneu.jsp
Module 12 assignment
Name: Brittany Kyncl
Date: 7.21.23
Course: CSD430
page for menu options
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Garden Manager</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="menu">
		<h3>Manage Garden Database</h3>
        <ul>       	
            <li>
            	<form action="<%= request.getContextPath() %>/plants?action=CreateAndPop" method="POST">
		            <input type="hidden" name="action" value="CreateAndPop">
		            <button type="submit">Create Garden DB</button>
		        </form>
            </li> 
            <li> 
            	<form action="<%= request.getContextPath() %>/plants" method="GET">
		            <button type="submit" name="action" value="EnterPlant">Enter New Plant</button>
		        </form>
            </li>       
        </ul>
        <h3>Search Plants</h3>
        <ul>
        	<li>Enter Keyword:</li>
            <li>         	
		        <form action="<%= request.getContextPath() %>/plants?action=KeySearch" method="POST">
		            <input type="text" name="keyword" id="keyword" required><br>
		            <input type="submit" value="Search">
		        </form>
            </li>
        </ul>
        <h3>Plant Categories</h3>
        <ul>
        	<li><a href="<%= request.getContextPath() %>/plants?action=CategorySearch&category=Fruits">Fruits</a></li>
		    <li><a href="<%= request.getContextPath() %>/plants?action=CategorySearch&category=Vegetables">Vegetables</a></li>
		    <li><a href="<%= request.getContextPath() %>/plants?action=CategorySearch&category=Herbs">Herbs</a></li>
        </ul>
    </div>
</body>
</html>