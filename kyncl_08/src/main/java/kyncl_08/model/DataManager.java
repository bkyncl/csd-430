/* DataManager.java
 * Module 8 Assignment
 * Name: Brittany Kyncl
 * Date: 7.4.23
 * Course: CSD430
 * Database connection model class provides methods for establishing database connection
 * inserting plant into plants table, retrieving all plants from the plants table,
 * and retrieving all categories from plant_category table
 */
package kyncl_08.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import kyncl_08.bean.*;

public class DataManager {
	private String dbURL = "jdbc:mysql://localhost:3306/garden";
	private String dbUserName = "student1";
	private String dbPassword = "pass";

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbPassword() {
		return dbPassword;
	}
	
	/**
	 * Establishes a connection to the database.
	 * 
	 * @return The database connection object.
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(getDbURL(), getDbUserName(), getDbPassword());
		} catch (SQLException e) {
			System.out.println("Could not connect to DB: " + e.getMessage());
		}
		return conn;
	}
	
	/**
	 * Closes the database connection.
	 * 
	 * @param conn The database connection object to be closed.
	 */
	public void endConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
	/**
	 * Inserts a new plant into the "plants" table and retrieves the auto-generated plantId.
	 * 
	 * @param plant The Plant object to be inserted.
	 */
	public void insertPlant(Plant plant) {
		Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conn = this.getConnection();

	        // SQL query to insert a new plant and retrieve the auto-generated plantId
	        String query = "INSERT INTO plants (plant_name, description, quantity, category_id) " +
	                "VALUES (?, ?, ?, ?)";

	        statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        statement.setString(1, plant.getPlantName());
	        statement.setString(2, plant.getPlantDescription());
	        statement.setInt(3, plant.getQuantity());
	        statement.setInt(4, plant.getCategoryId());

	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 1) {
	            // Retrieve the auto-generated plantId
	            resultSet = statement.getGeneratedKeys();

	            if (resultSet.next()) {
	                int generatedPlantId = resultSet.getInt(1);
	                plant.setPlantId(generatedPlantId);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error inserting plant: " + e.getMessage());
	    } finally {
	        // Close the resources
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	            	System.out.println("Error: " + e.getMessage());
	            }
	        }
	        this.endConnection(conn);
	    }
	}
	
	/**
	 * Retrieves all plants from the "plants" table.
	 * 
	 * @return A list of Plant objects representing all the plants in the table.
	 */
	public List<Plant> getAllPlants() {
	    List<Plant> plants = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conn = this.getConnection();

	        // SQL query to retrieve all plants from all tables
	        String query = "SELECT * FROM plants";

	        statement = conn.prepareStatement(query);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Plant plant = new Plant();
	            plant.setPlantId(resultSet.getInt("plant_id"));
	            plant.setPlantName(resultSet.getString("plant_name"));
	            plant.setPlantDescription(resultSet.getString("description"));
	            plant.setQuantity(resultSet.getInt("quantity"));
	            plant.setCategoryId(resultSet.getInt("category_id"));

	            plants.add(plant);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving plants: " + e.getMessage());
	    } finally {
	        // Close the resources
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	            	System.out.println("Error: " + e.getMessage());
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	            	System.out.println("Error: " + e.getMessage());
	            }
	        }
	        this.endConnection(conn);
	    }

	    return plants;
	}
	
	/**
	 * Retrieves all categories from the "plant_category" table.
	 * 
	 * @return A list of Category objects representing all the categories in the table.
	 */
	public List<Category> getAllCategories() {
	    List<Category> categories = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conn = this.getConnection();

	        // SQL query to retrieve all categories from the plant_category table
	        String query = "SELECT * FROM plant_category";

	        statement = conn.prepareStatement(query);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Category category = new Category();
	            category.setCategoryId(resultSet.getInt("category_id"));
	            category.setCategoryName(resultSet.getString("category_name"));
	            category.setPlantCount(resultSet.getInt("plant_count"));

	            categories.add(category);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error retrieving categories: " + e.getMessage());
	    } finally {
	        // Close the resources
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	            	System.out.println("Error: " + e.getMessage());
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	            	System.out.println("Error: " + e.getMessage());
	            }
	        }
	        this.endConnection(conn);
	    }

	    return categories;
	}
}
