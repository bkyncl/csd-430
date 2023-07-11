<%-- 
index.jsp
Module 7 assignment
Name: Brittany Kyncl
Date: 6.30.23
Course: CSD430
code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Welcome</title>
  <link rel="stylesheet" href="/eshop/css/eshop.css" type="text/css"/>
  </head>
<body>
<jsp:include page="TopMenu.jsp" flush="true"/>
<jsp:include page="LeftMenu.jsp" flush="true"/>
<div class="content">
  <h1>Welcome to e-Shop</h1>
  </div>
</body>
</html>
