<%-- 
index.jsp
Module 9 assignment
Name: Brittany Kyncl
Date: 7.5.23
Course: CSD430
File to example the usage the usage of JSTL and XPath to parse XML data, 
iterate over XML elements, apply conditional logic, and display the data in with customized styling.
Context purpose is to parse the XML file 'employees.xml' containing 
employees data, and display it in tabular format. The file incorporates JSTL tags and functions
to manipulate the XML data and apply conditional logic for displaying the data based on various criteria.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<html>
<head>
	<title>Parsing employees.xml</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<c:import url="employees.xml" var="employeeData"/>
	<x:parse doc="${employeeData}" var="dom"/>
<div class="container">
	<div class="left">
		<h2>Displaying All Employees</h2>
		<table border="1">
			<tr>
				<th colspan="4">All Employees in 'employees.xml' File</th>
			</tr>
			<tr>
				 <th>Name</th>
				 <th>Job Title</th>
				 <th>Department</th>
				 <th>Salary</th>
			</tr>
			<x:forEach var="employee" select="$dom/employees/employee">
			<%-- Displaying the details of each employee --%>
			<tr>
			  	<td><x:out select="$employee/name"/></td>
				<td><x:out select="$employee/jobTitle"/></td>
				<td><x:out select="$employee/department"/></td>
				<td><x:out select="$employee/salary"/></td>
			</x:forEach>
		</table>
	</div>
	<div class="right">
		<h2>Applying Conditional Logic with JSTL Functions to Display XML Data</h2>
		<table border="1">
		    <tr>
		        <th colspan="4">Employees Salary >50000 in Green or Else in Red</th>
		    </tr>
		    <tr>
		        <th>Name</th>
		        <th>Job Title</th>
		        <th>Department</th>
		        <th>Salary</th>
		    </tr>
		    <x:forEach var="employee" select="$dom/employees/employee">
		        <x:choose>
		        	<%-- Applying styling to employees with salary > 50000 --%>
		            <x:when select="$employee/salary>50000">
		                <tr style="background-color: #BDFCB1;">
		                    <td><x:out select="$employee/name"/></td>
		                    <td><x:out select="$employee/jobTitle"/></td>
		                    <td><x:out select="$employee/department"/></td>
		                    <td><x:out select="$employee/salary"/></td>
		                </tr>
		            </x:when>
		            <%-- Applying styling to employees with salary <= 50000 --%>
		            <x:when select="$employee/salary<=50000">
		                <tr style="background-color: #FFC0C0;">
		                    <td><x:out select="$employee/name"/></td>
		                    <td><x:out select="$employee/jobTitle"/></td>
		                    <td><x:out select="$employee/department"/></td>
		                    <td><x:out select="$employee/salary"/></td>
		                </tr>
		            </x:when>
		            <%-- Displaying employees without any styling for otherwise clause (just in case conditions not met) --%>
		            <x:otherwise>
		                <tr>
		                    <td><x:out select="$employee/name"/></td>
		                    <td><x:out select="$employee/jobTitle"/></td>
		                    <td><x:out select="$employee/department"/></td>
		                    <td><x:out select="$employee/salary"/></td>
		                </tr>
		            </x:otherwise>
		        </x:choose>
		    </x:forEach>
		</table>
	    <br>
	    <table border="1">
		    <tr>
		        <th colspan="4">Employees with Department = 'Engineering'</th>
		    </tr>
		    <tr>
		        <th>Name</th>
		        <th>Job Title</th>
		        <th>Department</th>
		        <th>Salary</th>
		    </tr>
		    <x:forEach var="employee" select="$dom/employees/employee">
		        <x:choose>
		        	<%-- Filtering and displaying employees with the department value 'Engineering' --%>
		            <x:when select="$employee/department='Engineering'">
		                <tr>
		                    <td><x:out select="$employee/name"/></td>
		                    <td><x:out select="$employee/jobTitle"/></td>
		                    <td><x:out select="$employee/department"/></td>
		                    <td><x:out select="$employee/salary"/></td>
		                </tr>
		            </x:when>
		        </x:choose>
		    </x:forEach>
		</table>
		<br>
		<table border="1">
		    <tr>
		        <th colspan="4">Employees with Department = 'Finance' or 'Marketing'</th>
		    </tr>
		    <tr>
		        <th>Name</th>
		        <th>Job Title</th>
		        <th>Department</th>
		        <th>Salary</th>
		    </tr>
		    <x:forEach var="employee" select="$dom/employees/employee">
		        <x:choose>
		        	<%-- Filtering and displaying employees with the department values 'Finance' or 'Marketing' --%>
		            <x:when select="$employee/department='Finance' or $employee/department='Marketing'">
		                <tr>
		                    <td><x:out select="$employee/name"/></td>
		                    <td><x:out select="$employee/jobTitle"/></td>
		                    <td><x:out select="$employee/department"/></td>
		                    <td><x:out select="$employee/salary"/></td>
		                </tr>
		            </x:when>
		        </x:choose>
		    </x:forEach>
		</table>
	</div>
</div>

</body>
</html>
