<%-- 
JSP_31.jsp
Module 3 assignment
Name: Brittany Kyncl
Date: 6.6.23
Course: CSD430
Declare and initialize int array, using iteration display each element and identify even/odd values
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Module 3 Assignment - Part One</title>
</head>
<body>
	<h2 id="top">Module 3 Assignment -  Part One</h2>
	<hr>
	<ul>
		<li><a href="JSP_31.jsp">Assignment Part 1</a></li>
		<li><a href="JSP_32.jsp">Assignment Part 2</a></li>
	</ul>
	<hr>
	<h3>An array is initialized with random integers 1-30. Then displayed in a table identifying even/odd values.</h3>
	<%
		//Declaring int array of size 20
		int[] myIntArray = new int[20];
		//Initializing array with random values int bound 1-30
		Random random = new Random();
		for (int i=0; i < myIntArray.length; i++) {
			myIntArray[i] = random.nextInt(30) + 1;
		}
		//Using foreach loop to print array elements
		out.println("My Int Array: [");
		for (int num:myIntArray) {
			out.print(num + ", ");
		}
		out.println("]");
	%>
	<br>
	<br>
	<%-- Table format to display array indices, their values, and identify even/odd --%>
	<table border="1">
		<tr>
			<%-- Table header with colspan of 3 --%>
			<th colspan="3">My Int Array</th>
		</tr>
		<tr>
			<th>Index</th>
			<th>Value</th>
			<th>Even/Odd</th>
		</tr>
		<%-- Iterate through each element of myIntArray, displaying value and generating table data --%>
		<% for (int i=0; i < myIntArray.length; i++) { %>
        <tr>
      		<td>Index: <%= i %></td>
      		<td><%= myIntArray[i] %></td>
      		<td>
      			<%-- Check if current index value is even or odd --%>
      			<%if(myIntArray[i] % 2 == 0) {
      				out.println("Even");
      			}else {
      				out.println("Odd");
      			}
      			%>
      		</td>
      		
        </tr>
		<% } %>
	</table>
	<br>
	<hr>
	<a href="#top">Back to Top</a>
</body>
</html>