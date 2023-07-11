<%-- 
Checkout.jsp
Module 5 assignment
Name: Brittany Kyncl
Date: 6.13.23
Course: CSD430
This JSP file represents the checkout page for the E-BookShop. It displays the selected books in the cart, along prices and quantities. 
It also calculates and displays the total amount. Uses JSP scriptlets to retrieve and display data from the session and request objects.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page session="true" import="ebookshop.Book, java.util.*"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>E-BookShop Checkout</title>
	<style type="text/css">
    body {
    	background-color:gray; 
    	font-size=10pt;
    }
    H1 {
    	font-size:20pt;
    }
    table {
    	background-color:white;
    	border-collapse: separate;
    }
    td {
    	padding: 5px; <%-- inclusion of css style to replace depreciated "cellpadding"--%>
    }
   	</style>
</head>
<body>
	<h1>Your Online BookShop - Checkout</h1>
	<hr/><p/>
	<%-- NOTICE OF CHANGEES FROM BOOKE EXAMPLE --%>
  	<%-- attribute 'cellpadding="2"' was obsolete in HTML5 use CSS instead to achieve desired padding --%>
	<table border="1">
		<tr>
	  		<td>TITLE</td>
	  		<td align="right">PRICE</td>
	  		<td align="right">QUANTITY</td>
  		</tr>
		<%
			// suppress warning for unchecked type-casting from a generic Object to Vector<Book>
			// certain of attribute ebookshop.cart being of type Vector<Book>
			@SuppressWarnings("unchecked")
		    Vector<Book> shoplist = (Vector<Book>) session.getAttribute("ebookshop.cart");
		    for (Book anOrder : shoplist) {
		%>
      	<tr>
	        <td><%=anOrder.getTitle()%></td>
	        <td align="right">$<%=anOrder.getPrice()%></td>
	        <td align="right"><%=anOrder.getQuantity()%></td>
        </tr>
		<%
		    }
		    session.invalidate();
		%>
	    <tr>
			<td>TOTALS</td>
			<td align="right">$<%=(String)request.getAttribute("dollars")%></td>
			<td align="right"><%=(String)request.getAttribute("books")%></td>
	    </tr>
    </table>
  	<p/>
  	<a href="/ebookshop/eshop">Buy more!</a>
</body>
</html>