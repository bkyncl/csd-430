<%-- 
TopMenu.jsp
Module 7 assignment
Name: Brittany Kyncl
Date: 6.30.23
Course: CSD430
code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
--%>
<%@page language="java" contentType="text/html"%>
<%
  String base = (String)application.getAttribute("base");
  String imageURL = (String)application.getAttribute("imageURL");
  %>
<div class="header">
  <div class="logo">
    <p>e-Shopping Center</p>
    </div>
  <div class="cart">
    <a class="link2" href="<%=base%>?action=showCart">Show Cart
      <img src="<%=imageURL%>cart.gif" border="0"/></a>
    </div>
  </div>