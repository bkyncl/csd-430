<%-- 
search.jsp
Module 12 assignment
Name: Brittany Kyncl
Date: 7.21.23
Course: CSD430
page to display search results of both keyword and category
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://kyncl12_GardenManager.com/taglibs/garden" prefix="garden" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Garden Database Search</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="form-container">
	<!-- Display the search results using the custom tag -->
     <garden:searchResults searchResults="${searchResults}" categories="${categories}" />
	</div>   
</body>
</html>