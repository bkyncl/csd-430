<%-- 
index.jsp
Module 8 assignment
Name: Brittany Kyncl
Date: 7.4.22
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
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Records Form</title>
<style>
    table {
        border-collapse: collapse;
        border: 2px solid #ddd;
        width: 100%;
    }
    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f2f2f2;
        text-align: center;
    }
    td.label-cell {
        text-align: right;
        font-weight: bold;
    }
    input[type="text"],
    input[type="number"],
    textarea,
    select {
        width: 100%;
        box-sizing: border-box;
    }
    #button-container {
        text-align: center;
        background-color: #f2f2f2;
    }
    #button {
        display: inline-block;
    }
    .result-table th, .result-table td {
        text-align: center;
        border-bottom: 1px solid #ddd;
    }
    .form-container {
        display: flex;
        flex-wrap: wrap;
        padding: 10px;
    }

    .form-container > div {
        flex: 1 1 100%;
        padding: 10px;
    }

    @media screen and (min-width: 768px) {
        .form-container > div {
            flex: 1 1 30%;
        }
    }
</style>
</head>
<body>
<div class="form-container">
	<div>
		<form action="<%=request.getContextPath()%>/plants?action=insert" method="POST">
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
		                <select name="categoryId" id="category">
		                    <option value="1">Fruits</option>
		                    <option value="2">Vegetables</option>
		                    <option value="3">Herbs</option>
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
		            <td colspan="3" id="button-container">
		            	<input type="hidden" name="action" value="result">
		                <input id="button" type="submit" value="Insert New Plant">
		            </td>
		        </tr>
		    </table>
		</form>
		<br>
		<a href="/kyncl_08/index.jsp">Return to Index</a>
	</div>
	<div>
            <%-- Check if the "categories" variable is not empty --%>
            <c:if test="${not empty categories}">
                <table class="result-table">
                    <tr>
                        <th colspan="3">Plant Categories</th>
                    </tr>
                    <tr>
                        <th>Category ID</th>
                        <th>Category Name</th>
                        <th>Plant Quantity</th>
                    </tr>
                    <c:forEach var="category" items="${categories}">
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.plantCount}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <div>
            <%-- Check if the "plants" variable is not empty --%>
            <c:if test="${not empty plants}">
                <table class="result-table">
                    <tr>
                        <th colspan="5">All Plants</th>
                    </tr>
                    <tr>
                        <th>Plant ID</th>
                        <th>Plant Name</th>
                        <th>Plant Description</th>
                        <th>Quantity</th>
                        <th>Plant Category</th>
                    </tr>
                    <c:forEach var="plant" items="${plants}">
                        <tr>
                            <td>${plant.plantId}</td>
                            <td>${plant.plantName}</td>
                            <td>${plant.plantDescription}</td>
                            <td>${plant.quantity}</td>
                            <td>
                                <c:forEach var="category" items="${categories}">
                                    <c:if test="${category.categoryId eq plant.categoryId}">
                                        ${category.categoryName}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
</div>
</body>
</html>