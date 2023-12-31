<%-- 
ShoppingCart.jsp
Module 7 assignment
Name: Brittany Kyncl
Date: 6.30.23
Course: CSD430
NOTICE OF REVISION:
To adhere more to MCV the controller logic responsible for manipulating the shopping cart has been extracted
into a separate CartController class. This promotes a clearer separation of concerns, with the JSP file focusing
on presentation and user interaction, while the controller class handles the business logic.
code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
--%>

<%@page language="java" contentType="text/html"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="eshop.beans.Book"%>
<%@page import="eshop.beans.CartItem"%>
<%@page import="eshop.controller.CartController"%>
<jsp:useBean id="dataManager" scope="application"
  class="eshop.model.DataManager"/>
<%
	//Get the base URL from the application context
    String base = (String) application.getAttribute("base");
	//Retrieve the shopping cart from the session
    @SuppressWarnings("unchecked")
    Hashtable<String, CartItem> shoppingCart =
            (Hashtable<String, CartItem>) session.getAttribute("shoppingCart");
 	// If the shopping cart doesn't exist, create a new one and set it in the session
    if (shoppingCart == null) {
        shoppingCart = new Hashtable<String, CartItem>(10);
        session.setAttribute("shoppingCart", shoppingCart);
    }
 	// Retrieve the action parameter from the request
    String action = request.getParameter("action");
 	// Instantiate the CartController and pass the DataManager as a dependency
    CartController cartController = new CartController(dataManager);

 	// Process the action based on its value
    if (action != null && action.equals("addItem")) {
        // Call the addItem method of the CartController
        cartController.addItem(request);
    } else if (action != null && action.equals("updateItem")) {
        // Call the updateItem method of the CartController
        cartController.updateItem(request);
    } else if (action != null && action.equals("deleteItem")) {
        // Call the deleteItem method of the CartController
        cartController.deleteItem(request);
    }
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Shopping Cart</title>
  <link rel="stylesheet" href="/eshop/css/eshop.css" type="text/css"/>
  </head>
<body>
<jsp:include page="TopMenu.jsp" flush="true"/>
<jsp:include page="LeftMenu.jsp" flush="true"/>
<%
  if (!shoppingCart.isEmpty()) {
  %>
    <div class="content">
      <h2>Shopping Cart</h2>
      <table>
        <tr>
          <th>Title</th>
          <th>Author</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Subtotal</th>
          <th>Delete</th>
          </tr>
<%
        Enumeration<CartItem> enumList = shoppingCart.elements();
        double itemPrice = 0;
        double totalPrice = 0;
        while (enumList.hasMoreElements()) {
          CartItem item = enumList.nextElement();
          itemPrice = Math.round(
        		    Integer.parseInt(item.getQuantity())
        		    * item.getPrice() * 100.) / 100.
                    ;
          totalPrice += itemPrice;
  %>
          <tr>
            <td><%=item.getTitle()%></td>
            <td><%=item.getAuthor()%></td>
            <td><%=item.getPrice()%></td>
            <td><form>
              <input type="hidden" name="action" value="updateItem"/>
              <input type="hidden" name="bookId"
                value="<%=item.getBookID()%>"/>
              <input type="text" size="2" name="quantity" 
                value="<%=item.getQuantity()%>"/>
              <input type="submit" value="Update"/>
              </form></td>
            <td>
              <%=itemPrice%>
                </td>
            <td><form>
              <input type="hidden" name="action" value="deleteItem"/>
              <input type="hidden" name="bookId" 
                value="<%=item.getBookID()%>"/>
              <input type="submit" value="Delete"/>
              </form></td>
            </tr>
<%
          }
  %>
        <tr>
          <td colspan="5" id="total">Total: <%=totalPrice%></td>
          <td class="total">&nbsp;</td>
          </tr>
        <tr>
          <td colspan="6" class="center"><a class="link1"
            href="<%=base%>?action=checkOut">Check Out</a></td>
          </tr>
        </table>
      </div>
<%
    }
  else {
    %><p class="info">The Shopping cart is empty.</p><%
    }
  %>
</body>
</html>
