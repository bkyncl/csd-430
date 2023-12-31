<%-- 
buggy.jsp
Module 8 DB Post - Debug Program
Name: Brittany Kyncl
Date: 6.29.23
Course: CSD430
This JSP file contains 3 bugs to fix (2 syntax 1 logic)
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buggy Program</title>
</head>
<body>

	<%-- Create a new BookBean and set its properties --%>
	<jsp:useBean id="bookBean" class="mod8debug.BookBean" scope="page" />

	<%-- Set the properties of the BookBean --%>
	<jsp:setProperty name="bookBean" property="id" value="001" />
	<%-- NOTICE: original file contained improper name attribute for both set properties --%>
	<jsp:setProperty name="bookBean" property="title" value="My New Book" />
	<jsp:setProperty name="bookBean" property="author" value="George Steponapolis" />
    
    <%-- Display the properties of the BookBean --%>
	<h2>Displaying Value from Book Bean</h2>
	<table border="1">
		<tr>
			<td>ID</td>
			<td><jsp:getProperty name="bookBean" property="id" /></td>
		</tr>
		<tr>
			<%-- NOTICE: original file contained improper attribute name as value instead of property --%>
			<td>Title</td>
			<td><jsp:getProperty name="bookBean" property="title" /></td>
		</tr>
		<tr>
			<td>Author</td>
			<td><jsp:getProperty name="bookBean" property="author" /></td>
		</tr>
		
	</table>
</body>
</html>