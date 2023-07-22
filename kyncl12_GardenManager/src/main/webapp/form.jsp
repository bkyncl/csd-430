<%-- 
form.jsp
Module 12 assignment
Name: Brittany Kyncl
Date: 7.21.23
Course: CSD430
Form for inserting new plant record into garden database. The form data is sent to
servlet with "insert" action which handles the form submission and uses a Java Bean 'Plant.java' to insert
a new plant record into the plants table. The servlet then retrieves a list of plants and categories from the DataManage
and sets the retrieved data as attributes in the request object. It then forwards the request and response objects back to form.jsp
When from.jsp receives the plants and categories list after form submission it then displays the updated table results
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://kyncl12_GardenManager.com/taglibs/garden" prefix="garden" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert Records Form</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="form-container">
	<div>
		<form action="<%=request.getContextPath()%>/plants?action=Insert" method="POST">
		    <table>
		    	<tr>
		            <th colspan="3">Insert New Plant into Garden Database</th>
		        </tr>
		        <tr>
		            <th colspan="3">Enter Plant Information</th>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="categoryId">Plant Category:</label>
		            </td>
		            <td colspan="2">
		                <select name="categoryId" id="category" required>
		                	<option value="" selected disabled>Select a Category</option>
		                    <c:forEach var="category" items="${categories}">
				                <option value="${category.categoryId}">${category.categoryName}</option>
				            </c:forEach>
		                </select>
		            </td>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="name">Plant Name:</label>
		            </td>
		            <td colspan="2">
		                <input type="text" name="plantName" id="name" required maxlength="50">
		            </td>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="description">Description:</label>
		            </td>
		            <td colspan="2">
		                <textarea name=plantDescription id="description" maxlength="200"></textarea>
		            </td>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="quantity">Quantity:</label>
		            </td>
		            <td colspan="2">
		                <input type="number" name="quantity" id="quantity" required pattern="[0-9]+">
		            </td>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="datePlanted">Date Planted:</label>
		            </td>
		            <td colspan="2">
		                <input type="date" name="datePlanted" id="datePlanted" required>
		            </td>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="plotNumber">Plot Number:</label>
		            </td>
		            <td colspan="2">
		                <select name="plotNumber" id="plotNumber" required>
					        <% for (int i = 1; i <= 10; i++) { %>
					            <option value="<%= i %>"><%= i %></option>
					        <% } %>
					    </select>
		            </td>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="plotRow">Row:</label>
		            </td>
		            <td colspan="2">
		                <label>A<input type="radio" name="plotRow" value="A" required></label><br>
					    <label>B<input type="radio" name="plotRow" value="B" required></label><br>
					    <label>C<input type="radio" name="plotRow" value="C" required></label><br>
					    <label>D<input type="radio" name="plotRow" value="D" required></label><br>
		            </td>
		        </tr>
		        <tr>
		            <td class="label-cell">
		                <label for="nativeClimate">Native Climate:</label>
		            </td>
		            <td colspan="2">
		                <select name="nativeClimate" id="nativeClimate" required>
						  <option value="" selected disabled>Select a climate</option>
						  <option value="Tropical">Tropical</option>
						  <option value="Dry">Dry</option>
						  <option value="Temperate">Temperate</option>
						  <option value="Continental">Continental</option>
						  <option value="Polar">Polar</option>
						</select>
		            </td>
		        </tr>
		        <tr>
		            <td colspan="3" id="button-container">
		                <input id="button" type="submit" value="Insert New Plant">
		            </td>
		        </tr>
		    </table>
		</form>
	</div>
	<!-- Display the tables  using the garden custom tag -->
    <garden:plantTable categories="${categories}" plants="${plants}" />     
    
</div>
</body>
</html>