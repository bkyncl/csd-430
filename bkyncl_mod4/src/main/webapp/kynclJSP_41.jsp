<%-- 
kynclJSP_41.jsp
Module 4 assignment
Name: Brittany Kyncl
Date: 6.6.23
Course: CSD430
Page with character creation form for user to submit unique character information and retrieve character summary
based on their Unique User creation
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.bkyncl_mod4.UserRole, java.util.*, java.io.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Module 4 Assignment</title>
</head>
<body>
	<h2 id="top">Module 4 Assignment - Form Entry for User to Create
		Unique Character Profile</h2>
	<hr>
	<form action="UserServlet" method="POST">
		<%-- form inputs for username, character name, email --%>
		<label for="userName">User Name:</label> <input type="text"
			name="userName" required /> <br> <label for="email">Email:</label>
		<input type="text" name="email" required /> <br> <label
			for="characterName">Character Name:</label> <input type="text"
			name="characterName" required /> <br>
		<%-- user selection input for character role class type --%>
		<label for="role">Character Class:</label> 
		<select name="role" required>
			<option value="" >Select a class</option>
			<%-- Generating options for drop down menu retrieved from ServletContext attribute "characterClasses" --%>
			<%
		    String[] characterClassNames = (String[]) getServletContext().getAttribute("characterClasses");
		    if (characterClassNames != null) {
		        for (String characterClassName : characterClassNames) {
		    %>
		    <option value="<%= characterClassName %>"><%= characterClassName %></option>
		    <%
		        }
		    }
		    %>
		</select> 
		<br> 
		<input type="submit" value="Submit" />
	</form>
	<br>
</body>
</html>