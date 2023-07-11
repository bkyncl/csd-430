<%-- 
exPractice.jsp
Module 2 assignment
Name: Brittany Kyncl
Date: 4.28.23
Course: CSD430
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.io.*, java.sql.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>JSP Demo Expanded</title>
</head>
<body>
	<%-- First Scriptlet Section - from book example --%>
	<h2>Hello World! : (Book Example)</h2>
	
	<%-- Second Scriptlet Section - Change Background Color --%>
	<h2>Change Background Color : (My Example)</h2>
    <%-- Form to select background color --%>
    <form method="post" action="">
        <label for="color">Select Color:</label>
        <select name="color" id="color">
            <option value="red">Red</option>
            <option value="green">Green</option>
            <option value="blue">Blue</option>
        </select>
        <input type="submit" value="Change">
    </form>
    
    <%-- Scriptlet to handle color form submission --%>
    <% 
    	String color = "";
        if (request.getMethod().equalsIgnoreCase("post")) {
        	// Retrieve the selected color from the request parameters
            color = request.getParameter("color");
        } else {
            color = request.getParameter("selectedColor");
        }
	%>
    <script>
		 // Set the background color of the body using the selected color
		 document.body.style.backgroundColor = '<%= color %>';
    </script>


	<%-- Third Scriptlet Section - User Log In --%>
	<h2>User Log In Form : (My Example)</h2>
	<form action="" method="post">
		<label>Enter User: "admin" Enter Password: "secret"</label><br>
		<label for="username">Username:</label>
		<input type="text" name="username" id="username" required><br>
		<label for="password">Password:</label>
		<input type="password" name="password" id="password" required><br>
		<input type="submit" value="Login">
	</form>
	
	<%-- Login Processing --%>
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username != null && password != null) {
		  if (username.equals("admin") && password.equals("secret")) {
		    // Successful login
		    session.setAttribute("username", username);
		    out.println("<p>Log In Successfull.</p>");
		  } else {
		    // Invalid login
		    out.println("<p>Invalid username or password.</p>");
		  }
		}
	%>
	<%-- Third Scriptlet Section - DB Connectivity Test --%>
	<h2>DB Connection Test : (My Example)</h2>
	<%-- Establish a database connection --%>
	<%
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/databasedb";
        String user = "student1";
        String pass = "pass";
        
        try {
            // Establish the database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);

            out.println("Connection Success<br>");
         	// Perform a create if not exists query
            stmt = conn.createStatement();
         	stmt.execute("DROP TABLE IF EXISTS sample");
         	stmt.execute("CREATE TABLE IF NOT EXISTS sample (id INT PRIMARY KEY AUTO_INCREMENT, firstname VARCHAR(50), lastname VARCHAR(50))");
         	
         	// Prepare the statement for inserting data
            String insertQuery = "INSERT INTO sample (firstname, lastname) VALUES (?, ?), (?, ?), (?, ?),(?, ?)";
            pstmt = conn.prepareStatement(insertQuery);

            // Set the parameter values in the prepared statement
            pstmt.setString(1, "Jane");
            pstmt.setString(2, "Doe");
            pstmt.setString(3, "John");
            pstmt.setString(4, "Doe");
            pstmt.setString(5, "Sam");
            pstmt.setString(6, "Smith");
            pstmt.setString(7, "Sarah");
            pstmt.setString(8, "Jones");

            // Execute the insert statement
            pstmt.executeUpdate();
         	
         	// Perform a select query to fetch all rows from the table
            String selectQuery = "SELECT * FROM sample";
            rs = stmt.executeQuery(selectQuery);

         	// Print the table data in HTML table format
            out.println("<br>Sample Table Data:<br>");
            if (rs != null && rs.next()) {
                // Only print the table if the result set is not null and contains at least one row
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th></tr>");
                do {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("firstname");
                    String lastName = rs.getString("lastname");
                    out.println("<tr><td>" + id + "</td><td>" + firstName + "</td><td>" + lastName + "</td></tr>");
                } while (rs.next());
                out.println("</table>");
            } else {
                // Display a message if the table is empty
                out.println("<br>No data in the table.<br>");
            }
            
        } catch (Exception e) {
            out.println(e.toString());
        } finally {
            // Close the database resources
            try {
            	if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                out.println(e.toString());
            }
        } 
	%>
	<br>
	<%-- Button to Secondary JSP page --%>
	<form method="get" action="extended.jsp">
		<button type="submit">To Example Page</button>
	</form>
	
	<%-- Button to Secondary JSP page --%>
	<form method="get" action="cookie.jsp">
		<button type="submit">To Cookie Page</button>
	</form>
</body>
</html>