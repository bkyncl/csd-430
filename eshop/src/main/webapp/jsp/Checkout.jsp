<%-- 
Checkout.jsp
Module 7 assignment
Name: Brittany Kyncl
Date: 6.30.23
Course: CSD430
NOTICE OF REVISION:
To adhere more to MCV the checkout operation responsible for retrieving the shopping cart items was moved to the CartController class
code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
--%>

<%@page language="java" contentType="text/html"%>
<%@page import="java.util.Hashtable"%>
<%@page import="eshop.beans.CartItem"%>
<%@page import="eshop.controller.CartController"%>
<jsp:useBean id="dataManager" scope="application"
  class="eshop.model.DataManager"/>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Check Out</title>
  <link rel="stylesheet" href="/eshop/css/eshop.css" type="text/css"/>
  </head>
<body>
<jsp:include page="TopMenu.jsp" flush="true"/>
<jsp:include page="LeftMenu.jsp" flush="true"/>
<div class="content">
  <h2>CheckOut</h2>
	<%
		// Retrieve the shopping cart from the CartController using the current request
	    CartController cartController = new CartController(dataManager);
	    Hashtable<String, CartItem> shoppingCart = cartController.getShoppingCart(request);
	 	// Check if the shopping cart exists and is not empty
	    if (shoppingCart != null && !shoppingCart.isEmpty()) {
	%>
    <form action="">
      <input type="hidden" name="action" value="orderConfirmation"/>
      <table class="checkout">
        <tr>
          <th colspan="2">Delivery Details</th>
          </tr>
        <tr>
          <td>Contact Name:</td>
          <td><input type="text" name="contactName"/></td>
          </tr>
        <tr>
          <td>Delivery Address:</td>
          <td><input type="text" name="deliveryAddress"/></td>
          </tr>
        <tr>
          <th colspan="2">Credit Card Details</th>
          </tr>
        <tr>
          <td>Name on Credit Card:</td>
          <td><input type="text" name="ccName"/></td>
          </tr>
        <tr>
          <td>Credit Card Number:</td>
          <td><input type="text" name="ccNumber"/></td>
          </tr>
        <tr>
          <td>Credit Card Expiry Date:</td>
          <td><input type="text" name="ccExpiryDate"/></td>
          </tr>
        <tr>
          <td>&nbsp;</td>
          <td><input type="submit" value="Confirm Order"/></td>
          </tr>
        </table>
      </form>
<%
    }
  else {
    %><p class="error">ERROR: You can't check out an empty shopping cart!</p><% 
    }
  %>
  </div>
</body>
</html>
