<%-- 
jdbc.jsp
Module 6 assignment
Name: Brittany Kyncl
Date: 6.19.23
Course: CSD430
This JSP file demonstrates the usage of JDBC to create a shop database, populate it with records, and query data from the 'books' table.
The file starts by instantiating the BuildShopDB class, which handles the database creation and record insertion. 
The output of these operations is stored in the 'output' variable.
Next, a database connection is established using initialization parameters stored in web.xml. 
A query is executed to retrieve all records from the 'books' table, and the results are displayed in an HTML table.
code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="eshop.BuildShopDB, java.sql.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>JDBC Test</title>
	
	<%-- Internal CSS styling --%>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #D9F9F2;
            margin: 0;
            padding: 0px 20px 20px 20px;
        }        
        h2 {
            color: #130439;
            text-transform: uppercase;
            text-decoration: underline;

        }       
        table {
            width: 100%;
            border-collapse: collapse;
            border: 3px solid #130439;
            margin: 10px 0px;
        }    
        td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        tr {
        background-color: #F7F5D3; /* Set the table rows fill color */
	    }
	    
	    tr:nth-child(even) {
	        background-color: #FBFBE8; /* Set the even rows fill color */
	    }
	    
	    tr:hover {
	        background-color: #EBEEED; /* Set the hover fill color */
	    }
    </style>
</head>
<body>
	<%-- Instantiate the BuildShopDB class to create shop database, and populate database with records --%>
	<%
		BuildShopDB shop = new BuildShopDB();
	    
	    try {
	    	// create shop database 
	    	shop.createShop();	    	
	    } catch (SQLException e) {
	    	out.println("<br>Error");
			out.println("<br>Stack Trace: " + e.toString() + "<br>");
	    }
	    
	    try {
	    	// populate shop with tables and records
	    	shop.populateShop();	    	
	    } catch (SQLException e) {
	    	out.println("<br>Error");
			out.println("<br>Stack Trace: " + e.toString() + "<br>");
	    }
	    
	    // get the output from the database operations within BuildShopDB to append to HTML
	    String output = shop.getOutput();
	%>
	
	<%-- Display initialization and record insertion progress to HTML --%>
	<h2>Shop Database Initialization and Record Insertion</h2>
	<%= output %>
	
	
	<%-- Perform query on books table and display results --%>
	<h2>Shop Database 'books' Table Query Results</h2>
	
	<%
		// database connection variables
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsMeta = null;
		
		// database connection parameters as defined in web.xml
		String url = application.getInitParameter("db-url");
		String username = application.getInitParameter("db-username");
		String password = application.getInitParameter("db-password");
    
		try {
			// Establish the database connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			out.println("Connection Established: " + url);
			
			// execute a query to retrieve all records from books table
			rs = stmt.executeQuery("SELECT * FROM books");
			out.println("<br>Displaying all from table: books");
			
			// display results formatted in HTML table
			%><table border="1"><%
			rsMeta = rs.getMetaData();
			int nCols = rsMeta.getColumnCount();
			%><tr><%
			for (int kCol = 1; kCol <= nCols; kCol++) {
				out.print("<td><b>" + rsMeta.getColumnName(kCol) + "</b></td>");
			}
			%></tr><%
			while (rs.next()) {
				%><tr><%
				for (int kCol = 1; kCol <= nCols; kCol++) {
					out.print("<td>" + rs.getString(kCol) + "</td>");
				}
				%></tr><%
			}
			%></table><%
		} catch (Exception e) {
			out.println("<br>Error");
			out.println("<br>Stack Trace: " + e.toString() + "<br>");
		} finally {
			try {
				// close all resources of db connection
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				out.println("Connection closed: " + url);
			} catch (Exception e) {
				out.println("<br>Error on close");
				out.println("<br>Stack Trace: " + e.toString() + "<br>");
			}
		}
	%>
	
	<%-- Perform query on books table and display results --%>
	<h2>Shop Database 'categories' Table Query Results</h2>
	
	<%

    
		try {
			// Establish the database connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			out.println("Connection Established: " + url);
			
			// execute a query to retrieve all records from categories table
			rs = stmt.executeQuery("SELECT * FROM categories");
			out.println("<br>Displaying all from table: categories");
			
			// display results formatted in HTML table
			%><table border="1"><%
			rsMeta = rs.getMetaData();
			int nCols = rsMeta.getColumnCount();
			%><tr><%
			for (int kCol = 1; kCol <= nCols; kCol++) {
				out.print("<td><b>" + rsMeta.getColumnName(kCol) + "</b></td>");
			}
			%></tr><%
			while (rs.next()) {
				%><tr><%
				for (int kCol = 1; kCol <= nCols; kCol++) {
					out.print("<td>" + rs.getString(kCol) + "</td>");
				}
				%></tr><%
			}
			%></table><%
		} catch (Exception e) {
			out.println("<br>Error");
			out.println("<br>Stack Trace: " + e.toString() + "<br>");
		} finally {
			try {
				// close all resources of db connection
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				out.println("Connection closed: " + url);
			} catch (Exception e) {
				out.println("<br>Error on close");
				out.println("<br>Stack Trace: " + e.toString() + "<br>");
			}
		}
	%>

</body>
</html>