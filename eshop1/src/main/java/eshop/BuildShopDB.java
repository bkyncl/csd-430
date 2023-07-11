package eshop;

/* BuildShopDB.java
 * Module 6 Assignment
 * Name: Brittany Kyncl
 * Date: 6.19.23
 * Course: CSD430
 * This class, BuildShopDB, is responsible for creating and populating a database called "shop"
 * with tables and records related to an e-commerce shop. It utilizes JDBC to establish a connection
 * to a MySQL database and executes SQL statements to create tables and insert records.
 * The class provides methods for creating the shop database and populating it with data.
 * The output of the operations is stored in a StringBuilder object, which can be retrieved
 * using the getOutput() method.
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */

import java.sql.*;

public class BuildShopDB {
	// Database connection variables
    Connection conn = null;
    Statement stmt = null;
    
    // Database connection details
    String url = null;
    String username = "student1";
    String password = "pass";
    
    // StringBuilder for storing output
    StringBuilder output = new StringBuilder(); 
    
    /*
     * Returns the output messages generated during the database creation and population process.
     *
     * @return The output messages as a string.
     */
    public String getOutput() {
    	return output.toString();
    }
    
    /*
     * Drops and creates the shop database by executing SQL statements.
     *
     * @throws SQLException if a database access error occurs.
     */
    public void createShop() throws SQLException {
        try {
            // Establish the database connection
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	url = "jdbc:mysql://localhost:3306";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            output.append("Connection Established: " + url).append("<br>");
            
            try {
            	// create shop database (drop if exists)
                output.append("&emsp;").append("Creating Database...").append("<br>");             
                String dropShopDB = "DROP DATABASE IF EXISTS shop";
                String createShopDB = "CREATE DATABASE shop";
                
                stmt.executeUpdate(dropShopDB);
                stmt.executeUpdate(createShopDB);
                output.append("&emsp;&emsp;").append("Database created: shop").append("<br>");
            } catch (Exception e) {
            	output.append("Error Creating Database").append("<br>");
            	output.append("Stack Trace: ").append(e.toString()).append("<br>");
            }
                      
        } catch (Exception e) {
            output.append("Connection Failed: ").append(url).append("<br>");
            output.append("Stack Trace: ").append(e.toString()).append("<br>");
            
        } finally {
            try {
            	// close all resources of db connection
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
                output.append("Connection Closed: ").append(url).append("<br><br>");
                
            } catch (Exception e) {
                e.printStackTrace();
                output.append("Error on close").append("<br>");
                output.append("Stack Trace: ").append(e.toString()).append("<br>");
            }
        }
    }
    
    /*
     * Populates the shop database with tables and records by executing SQL statements.
     *
     * @throws SQLException if a database access error occurs.
     */   
    public void populateShop() throws SQLException {
    	try {
            // Establish the database connection
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		url = "jdbc:mysql://localhost:3306/shop";
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            output.append("Connection Established: ").append(url).append("<br>");
            
            try {
            	// create tables, Categories, Books, Orders, Order_Details (if not exists)
                output.append("&emsp;").append("Creating Tables in shop database...").append("<br>");
                
                // creating table categories
                String createTableCategories = "CREATE TABLE IF NOT EXISTS shop.categories ("
    					+ "category_id int NOT NULL AUTO_INCREMENT,"
    					+ "category_name varchar(70) NOT NULL,"
    					+ "PRIMARY KEY (category_id),"
    					+ "KEY category_id_key (category_id))";
                stmt.executeUpdate(createTableCategories);
                output.append("&emsp;&emsp;").append("Table created: categories").append("<br>");
                
                // creating table books
                String createTableBooks = "CREATE TABLE IF NOT EXISTS shop.books ("
    					+ "book_id int NOT NULL AUTO_INCREMENT,"
    					+ "title varchar(70) NOT NULL,"
    					+ "author varchar(70) DEFAULT NULL,"
    					+ "price double NOT NULL,"
    					+ "category_id int NOT NULL,"
    					+ "PRIMARY KEY (book_id),"
    					+ "KEY book_id_key (book_id),"
    					+ "CONSTRAINT category_id FOREIGN KEY (category_id) REFERENCES categories (category_id))";
    			stmt.executeUpdate(createTableBooks);
    			output.append("&emsp;&emsp;").append("Table created: books").append("<br>");
    			
    			// creating table orders
                String createTableOrders = "CREATE TABLE IF NOT EXISTS shop.orders ("
    					+ "order_id bigint NOT NULL AUTO_INCREMENT,"
    					+ "delivery_name varchar(70) NOT NULL,"
    					+ "delivery_address varchar(70) NOT NULL,"
    					+ "cc_name varchar(70) NOT NULL,"
    					+ "cc_number varchar(32) NOT NULL,"
    					+ "cc_expiry varchar(20) NOT NULL,"
    					+ "PRIMARY KEY (order_id),"
    					+ "KEY order_id_key (order_id))";
    			stmt.executeUpdate(createTableOrders);
    			output.append("&emsp;&emsp;").append("Table created: orders").append("<br>");
    			
    			// creating table order_details
                String createTableOrder_Details = "CREATE TABLE IF NOT EXISTS shop.order_details ("
    					+ "id bigint NOT NULL AUTO_INCREMENT,"
    					+ "book_id int NOT NULL,"
    					+ "title varchar(70) NOT NULL,"
    					+ "author varchar(70) DEFAULT NULL,"
    					+ "quantity int NOT NULL,"
    					+ "price double NOT NULL,"
    					+ "order_id bigint NOT NULL,"
    					+ "PRIMARY KEY (id),"
    					+ "KEY order_details_id_key (id),"
    					+ "CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES books (book_id),"
    					+ "CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES orders (order_id))";
    			stmt.executeUpdate(createTableOrder_Details);
    			output.append("&emsp;&emsp;").append("Table created: order_details").append("<br>");
                
            } catch (Exception e) {
            	output.append("Error Creating Tables").append("<br>");
            	output.append("Stack Trace: ").append(e.toString()).append("<br>");
            }
            
            try {
            	// populate records into categories and books table
            	output.append("&emsp;").append("Populating records into shop database...").append("<br>");
                
                // insert records into categories table 
                String insertCategories = "INSERT INTO categories (category_id, category_name) VALUES "
                	    + "(1, 'Web Development'),"
                	    + "(2, 'Science Fiction'),"
                	    + "(3, 'Historical Mysteries')";
                stmt.executeUpdate(insertCategories);
                output.append("&emsp;&emsp;").append("Records inserted into: categories").append("<br>");
                
                // insert records into books table                
                String insertBooks = "INSERT INTO books (book_id, title, author, price, category_id) VALUES "
                	    + "(1, 'MYSQL 8 Query Performance Tuning', 'Jesper Wisborg Krogh', '34.31', '1'),"
                	    + "(2, 'JavaScript Next', 'Raju Ghandi', '36.70', '1'),"
                	    + "(3, 'The Complete Robot', 'Isaac Asimov', '12.13', '2'),"
                	    + "(4, 'Foundation and Earth', 'Isaac Asimov', '11.07', '2'),"
                	    + "(5, 'The Da Vinci Code', 'Dan Brown', '7.99', '3'),"
                	    + "(6, 'A Column of Fire', 'Ken Follett', '6.99', '3')";
                stmt.executeUpdate(insertBooks);
                output.append("&emsp;&emsp;").append("Records inserted into: books").append("<br>");
                
            } catch (Exception e) {
            	output.append("Error Populating Records").append("<br>");
            	output.append("Stack Trace: ").append(e.toString()).append("<br>");
            }
 
        } catch (Exception e) {
            e.printStackTrace();
            output.append("Connection Failed: ").append(url).append("<br>");
            output.append("Stack Trace: ").append(e.toString()).append("<br>");
            
        } finally {
            try {
            	// close all resources of db connection
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
                output.append("Connection Closed: ").append(url).append("<br>");
                
            } catch (Exception e) {
                e.printStackTrace();
                output.append("Error on close").append("<br>");
                output.append("Stack Trace: ").append(e.toString()).append("<br>");
            }
        }
    }
    
}
