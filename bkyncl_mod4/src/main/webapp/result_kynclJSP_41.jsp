<%-- 
result_kynclJSP_41.jsp
Module 4 assignment
Name: Brittany Kyncl
Date: 6.6.23
Course: CSD430
Results page to display requested content based on user input entered into character creation form,
 displaying all entered information and corresponding UserRole object attributes information in a table format
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.io.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Module 4 Assignment</title>
</head>
<body>
	<h2 id="top">Module 4 Assignment - Displaying User's Unique Character Profile Credentials</h2>
	<hr>
	<ul>
		<li><a href="kynclJSP_41.jsp">Back to Form - Create New Character</a></li>
	</ul>
	<hr>
	<table border="1">
		<tr>
			<td>User Name</td>
			<td>${userName}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${email}</td>
		</tr>
		<tr>
			<td>Character Name</td>
			<td>${characterName}</td>
		</tr>
		<tr>
			<td>Character Class</td>
			<td>${role}</td>
		</tr>
		<tr>
	        <td>Class Description</td>
	        <td>${description}</td>
   		</tr>
		<tr>
			<td>Character Attributes</td>
			<td>
				<ul>
				<%-- Iterate over the attributes array (if != null) retrieved from the request attributes 
					and display each attribute in a list item. --%>
		           <%
					    String[] attributes = (String[]) request.getAttribute("attributes");
					    if (attributes != null) {
					        for (String attribute : attributes) {
					%>
					            <li><%= attribute %></li>
					<%
					        }
					    }
					%>
        		</ul>
			</td>
		</tr>
		<tr>
			<td>Character Deficits</td>
			<td>
				<ul>
				<%-- Iterate over the deficits array (if != null) retrieved from the request deficits 
					and display each deficits in a list item. --%>
		           <%
					    String[] deficits = (String[]) request.getAttribute("deficits");
					    if (deficits != null) {
					        for (String deficit : deficits) {
					%>
					            <li><%= deficit %></li>
					<%
					        }
					    }
					%>
        		</ul>
			</td>
		</tr>
	</table>
	<hr>
</body>
</html>