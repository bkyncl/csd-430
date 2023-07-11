<%-- 
JSP_32.jsp
Module 3 assignment
Name: Brittany Kyncl
Date: 6.6.23
Course: CSD430
Declare and initialize String array, using for each loop to display array.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Module 3 Assignment - Part Two </title>
</head>
<body>
	<h2 id="top">Module 3 Assignment - Part Two</h2>
	<hr>
	<ul>
		<li><a href="JSP_31.jsp">Assignment Part 1</a></li>
		<li><a href="JSP_32.jsp">Assignment Part 2</a></li>
	</ul>
	<hr>
	<h3>Using foreach loop to display contents of String array.</h3>
	<%
		//Declaring and initializing string array with values
		String[] myStringArray = {"Igneous", "Sedimentary", "Metamorphic", "Volcanic", "Granite", "Marble", "Fossiliferous", "Limestone", "Basalt", "Quartzite", "Travertine"};
	
		//Using foreach loop to print array elements
		out.println("My String Array: {");
		for (String word:myStringArray) {
			out.print(word + ", ");
		}
		out.println("}");
	%>
	<br>
	<br>
	<%-- Table format to display array indices and values--%>
	<table border="1">
		<tr>
			<%-- Table header with colspan of 2 --%>
			<th colspan="2">My String Array</th>
		</tr>
		<tr>
			<th>Index</th>
			<th>Value</th>
		</tr>
		<%-- Iterate through each element of myArray, displaying value, index counter var, and generating table data --%>
		<% 	int index = 0;
			for (String word:myStringArray) { %>
        <tr>
      		<td>Index: <%= index %></td>
      		<td><%= word %></td>
        </tr>
		<% 	index++;
			} 
		%>
	</table>
	<br>
	<hr>
	<a href="#top">Back to Top</a>
</body>
</html>