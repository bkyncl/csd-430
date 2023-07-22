<%-- 
index.jsp
Module 12 assignment
Name: Brittany Kyncl
Date: 7.21.23
Course: CSD430
Index page for dynamic application display of garden database management options
to include:
search
insert
category selection
DB creation
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Garden Manager</title>
</head>
<body>
	<div>
		<%-- Include the top display --%>
    	<jsp:include page="topdisplay.jsp" />
	</div>
	<div>
		<%-- Include the left-hand menu --%>
    	<jsp:include page="leftmenu.jsp" />
	</div>	
  	<div>
  		<%-- Check if the JSP page content attribute is present, and if so, include it --%>
	    <c:if test="${not empty contentJsp}">
	        <jsp:include page="${contentJsp}" />
	    </c:if>
  	</div>     
</body>
</html>