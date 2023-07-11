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
	<meta charset="ISO-8859-1">
	<title>JSP Demo Expanded Part Two</title>
</head>
<body>
	<jsp:include page="my-header.html" />
	<h2>JSP Practice Examples Continued...</h2>
	
	<p>The time on the server is <%= new java.util.Date()%></p>
	<%-- JSP Expression : is a small java expression
		Syntax: <%= some Java expression %>
	 --%>
	
	<p>JSP Expression Examples:<br>
		Converting a string to upper case: <%= new String("hello world").toUpperCase() %> <br>
		25 multiplied by 4 = <%= 25*4 %> <br>
		Is 75 less than 69? <%= 75 < 69 %> <br>
	</p>
	
	<%-- JSP Scriptlet : to insert one to many lines of java code
		Syntax: <% some Java code: 1 - many lines %>
	--%>
	
	<p>JSP Scriptlet Examples:<br>
		Hello World! Using for loop to print X 5...<br>
		<%
			for(int i=0; i<5; i++) {
				out.println("Hello again! i : " + i + "<br>");
			}
		%>
		<br>
	</p>
	
	<%-- JSP Declaration : for defining java variables or methods
		Syntax: <%! variable or method declaration %>
	--%>
	
	<p>JSP Declaration Examples:<br>
		Using a JSP method declaration to convert a string to lower case<br>
		<%!
			String makeItLower(String words) {
				return words.toLowerCase();
			}
		%>
		<%! String words = "Hello World"; 
			String words2 = "goodbye world";
		%>
		Convert <% out.println(words); %> to lower = <%= makeItLower(words) %> <br>
		
	</p>
	
	<%-- Call Java Class from JSP example by calling the "packagename.classname.methodname"
		alternatively you can clean up the reference statement my using a package import statement at
		the top of the jsp file
	--%>
	
	<p>Calling a Java Class from JSP example:<br>
		<%-- This first reference uses the full statement --%>
		Convert <% out.println(words); %> to upper = <%= com.bkyncl_mod2.jsp.FunUtils.makeItUpper(words) %> <br>
		<%-- This second reference uses a shortened statement using the above import as a reference --%>
		Convert <% out.println(words2); %> to upper = <%= FunUtils.makeItUpper(words2) %> <br>
		
	</p>
	
	<h2>Practice with JSP Objects...</h2>
	<p>Commonly used JSP objects..</p>
		<ul>
			<li>Request: Contains information about the HTTP request headers and form data</li>
			<li>Response: Provides HTTP support for sending response</li>
			<li>Out: JspWriter for including content in HTML page "out.prinln()"</li>
			<li>Session: Unique session for each user of the web application</li>
			<li>Application: Shared data for all users of the web application</li>
		</ul>
	<p>Request user agent: <%= request.getHeader("User-Agent") %> <br>
		Request user agent language: <%= request.getLocale() %> <br>
	</p>
	<%-- Example of forms with JSP--%>
	<h2>Handling HTML forms with JSP...</h2>
	<form action="extended.jsp" method="post">
		<label>First name: </label>
		<input type="text" name="firstname" /><br>
		<label>Last name: </label>
		<input type="text" name="lastname" /><br>
		<label>Language: </label>
		<input type="radio" name="lang" value="English"/>English
		<input type="radio" name="lang" value="Spanish"/>Spanish
		<input type="radio" name="lang" value="French"/>French<br>
		<label>Favorite Colors: </label>
		<input type="checkbox" name="color" value="Blue"/>Blue
		<input type="checkbox" name="color" value="Red"/>Red
		<input type="checkbox" name="color" value="Green"/>Green<br>
		<label>Major: </label>
		<select name="major">
			<option>Business</option>
			<option>Science</option>
			<option>English</option>
			<option>Arts</option>
		</select><br>
		<input type="submit" value="Submit" />
	</form>
	<br>
	<%-- using scriptlet to request parameter name --%>
	<% out.println("Student Name: " + request.getParameter("firstname") + " " + request.getParameter("lastname")); %>
	<% out.println("<br>Student Major:" + request.getParameter("major")); %>
	<% out.println("<br>Student Language:" + request.getParameter("lang")); %>
	<% 
		String[] colors;
		if(request.getParameterValues("color") != null) {
			colors = request.getParameterValues("color");
		} else {
			colors = new String[]{"red", "blue", "green"};
		}
		out.println("<br>Student colors: ");
		for (String color : colors ) {
			out.println("<li>" + color + "</li>");
		}
	%>
	<%-- or alternative syntax ${param.formFieldName} --%>
	<p>Student Name: ${param.firstname} ${param.lastname}<br>
		Student Major: ${param.major}<br>
		Student Language: ${param.lang}<br>
		Student Colors: 
		<%
		for (String color : colors ) {
			out.println("<li>" + color + "</li>");
		}
		%>
	</p>
	<%-- Example of Creating cookie and sending it to the browser--%>
	<%
		// read sutdent language from form data
		String studentlang;
		if( request.getParameter("lang") != null){
			studentlang = request.getParameter("lang");
		} else {
			studentlang = "english";
		}
		studentlang = studentlang.trim(); // Trim leading and trailing spaces
		
		//crete the cookie which consists of a key value pair (name:value)
		Cookie theCookie = new Cookie("student.lang", studentlang);
		
		//set the lifespan of the cookie (total number in seconds)
		theCookie.setMaxAge(60*60*24*365);  //one year
		
		// send cookie to broswer by loading it up into response object that goes back to browser
		response.addCookie(theCookie);
	%>
	<%-- Example of reading student major cookie--%>
	<%
		String cookieLang = null;
		Cookie[] theCookies = request.getCookies();
		
		if(theCookies != null) {
			for(Cookie tempCookie : theCookies) {
				if("student.lang".equals(tempCookie.getName())) {
					cookieLang = tempCookie.getValue();
					break;
				}
			}
		}
		out.println("student language cookie value: " + cookieLang);
	%>

	<%-- Example of including files with JSP --%>
	<p>In this example we have used the inclusion of external files to display both the header and footer</p>
	
	<br>
	<%-- Button to Secondary JSP page --%>
	<form method="get" action="index.jsp">
		<button type="submit">Return to Home</button>
	</form>
	
	<%-- Button to Secondary JSP page --%>
	<form method="get" action="cookie.jsp">
		<button type="submit">To Cookie Page</button>
	</form>
	
	<jsp:include page="my-footer.jsp" />
</body>
</html>