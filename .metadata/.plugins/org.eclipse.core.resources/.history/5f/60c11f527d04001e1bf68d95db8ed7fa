<%-- 
exPractice.jsp
Module 2 assignment
Name: Brittany Kyncl
Date: 4.28.23
Course: CSD430
--%>
<%@page import="com.bkyncl_mod2.jsp.FunUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.io.*, java.sql.*"%>
<%@ page import="com.bkyncl_mod2.jsp.FunUtils" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Cookie Customization Example</title>
</head>
<body>
	<jsp:include page="my-header.html" />
	<h2>Example Heading: Phrase</h2>
	<ul>
		<li>List Item</li>
		<li>List Item</li>
		<li>List Item</li>
	</ul>
	<form action="cookie.jsp" method="post">
		<label>Select your favorite color: </label>
		<select name="favoritecolor">
			<option value="red">Red</option>
			<option value="blue">Blue</option>
			<option value="green"> Green</option>
		</select><br>
		<input type="submit" value="Submit">
	</form>
	<br>
	<form action="cookie.jsp">
		<label>Select your favorite language: </label>
		<select name="favoritlang">
			<option value="Java">Java</option>
			<option value="C++">C++</option>
			<option value="Python"> Python</option>
		</select><br>
		<input type="submit" value="Submit">
	</form>
	
	<%-- Example of Creating cookie and sending it to the browser--%>
	<%
		
		String color = "";
	    if (request.getMethod().equalsIgnoreCase("post")) {
	    	// Retrieve the selected color from the request parameters
	        color = request.getParameter("favoritecolor");
	    	
	      	//crete the cookie which consists of a key value pair (name:value)
			Cookie theCookie = new Cookie("user.color", color);
			
			//set the lifespan of the cookie (total number in seconds)
			theCookie.setMaxAge(60*60*24*365);  //one year
			
			// send cookie to broswer by loading it up into response object that goes back to browser
			response.addCookie(theCookie);
	    	
	    } else {
			String cookieColor = null;
			// store cookies in array
			Cookie[] theCookies = request.getCookies();
			
			if(theCookies != null) {
				for(Cookie tempCookie : theCookies) {
					if("user.color".equals(tempCookie.getName())) {
						cookieColor = tempCookie.getValue();
						break;
					}
				}
			}
	        color = cookieColor;
	    }
	    
	    out.println("<br>" + color);
	%>
    <script>
		 // Set the background color of the body using the selected color
		 document.body.style.backgroundColor = '<%= color %>';
    </script>
	
	
	
	<br>
	<%-- Button to Secondary JSP page --%>
	<form method="get" action="index.jsp">
		<button type="submit">Return to Home</button>
	</form>
	
	<%-- Button to Secondary JSP page --%>
	<form method="get" action="extended.jsp">
		<button type="submit">To Example Page</button>
	</form>
	
	<jsp:include page="my-footer.jsp" />
</body>
</html>