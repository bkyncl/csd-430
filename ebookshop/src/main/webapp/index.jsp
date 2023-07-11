<%-- 
index.jsp
Module 5 assignment
Name: Brittany Kyncl
Date: 6.13.23
Course: CSD430
This JSP file represents the index page for the E-BookShop. Displays a form to add books to the shopping cart
and a table to display the books in the cart. The page includes CSS styling for formatting and uses JSP scriptlets to retrieve and display data from the session and request objects.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@page session="true" import="ebookshop.Book, java.util.Vector"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>E-BookShop</title>
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
    	border-colapse: separate; 
    }
    td {
    	padding: 5px; <%-- inclusion of css style to replace depreciated "cellpadding"--%>
    }
   	</style>
</head>
<body>
	<h1>Your Online BookShop</h1>
	<hr/>
	<p/>
	
	<%-- Scriptlet 1: check whether the book list is ready --%>
	<%-- NOTICE OF CHANGEES FROM BOOKE EXAMPLE --%>
  	<%-- getValue() method for retrieving attributes from the session object outdated, using getAttribute() method instead --%>
	<%  
		// suppress warning for unchecked type-casting from a generic Object to Vector<Book>
		// certain of attribute ebookshop.cart being of type Vector<Book>
		@SuppressWarnings("unchecked")
		Vector<ebookshop.Book> booklist = (Vector<ebookshop.Book>)session.getAttribute("ebookshop.list");
		if (booklist == null) {
		  response.sendRedirect("/ebookshop/eshop");
		  }
		else {
	%>
    <form name="addForm" action="eshop" method="POST">
    	<input type="hidden" name="do_this" value="add">
		Book:
      	<select name=book>
      		<%-- Scriptlet 2: copy the book list to the selection control --%>
      		<%-- NOTICE OF CHANGEES FROM BOOKE EXAMPLE --%>
  			<%-- removed (String) cast to fix Error "cannot cast from Book to String" at (String)booklist.elemetAt(i) --%>
			<%  
				for (int i = 0; i < booklist.size(); i++) {
		        	out.println("<option>" + booklist.elementAt(i) + "</option>");
		        }
	  		%>
	  	</select>
	    Quantity: 
	    <input type="text" name="qty" size="3" value="1">
		<input type="submit" value="Add to Cart">
	</form>
	<p/>
	<%-- Scriptlet 3: check whether the shopping cart is empty --%>
	<%  
		// suppress warning for unchecked type-casting from a generic Object to Vector<Book>
		// certain of attribute ebookshop.cart being of type Vector<Book>
		@SuppressWarnings("unchecked")
		Vector<ebookshop.Book> shoplist = (Vector<ebookshop.Book>)session.getAttribute("ebookshop.cart");
		if (shoplist != null  &&  shoplist.size() > 0) {
	%>
  	<%-- NOTICE OF CHANGEES FROM BOOKE EXAMPLE --%>
  	<%-- attribute 'cellpadding="2"' was obsolete in HTML5 use CSS instead to achieve desired padding --%>
	<table border="1" >
	    <tr>
		    <td>TITLE</td>
		    <td>PRICE</td>
		    <td>QUANTITY</td>
		    <td></td>
	    </tr>
	    <%-- Scriptlet 4: display the books in the shopping cart --%>
		<%  
			for (int i = 0; i < shoplist.size(); i++) {
				Book aBook = shoplist.elementAt(i);
		%>
		<%-- NOTICE OF CHANGEES FROM BOOKE EXAMPLE --%>
  		<%-- Invalid location of <form> tag within <tr> tag. Fixed by placing the <form> tag within the <td> element --%>
        <tr>
			<td><%= aBook.getTitle() %></td>
	        <td align="right">$<%= aBook.getPrice() %></td>
	        <td align="right"><%= aBook.getQuantity() %></td>
	        <td>
	            <form name="removeForm" action="eshop" method="POST">
	                <input type="hidden" name="position" value="<%=i%>">
	                <input type="hidden" name="do_this" value="remove">
	                <input type="submit" value="Remove from Cart">
	            </form>
	        </td>
        </tr>
        <%
	     	} // for (int i..
	    %>
	</table>
    <p/>
	<form name="checkoutForm" action="eshop" method="POST">
		<input type="hidden" name="do_this" value="checkout">
		<input type="submit" value="Checkout">
	</form>
    <%
    	} // if (shoplist..
    } // if (booklist..else..
    %>

</body>
</html>