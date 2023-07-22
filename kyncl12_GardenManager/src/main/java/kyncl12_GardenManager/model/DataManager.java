/* DataManager.java
 * Module 12 Assignment
 * Name: Brittany Kyncl
 * Date: 7.21.23
 * Course: CSD430
 * Database connection model class provides methods for establishing database connection
 * inserting plant into plants table, retrieving all plants from the plants table,
 * searching for plants by keyword, and retrieving all categories from plant_category table
 */
package kyncl12_GardenManager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import kyncl12_GardenManager.bean.*;

public class DataManager {
	private String dbURL;
	private String dbUserName;
	private String dbPassword;

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
	        String query = "INSERT INTO plants (plant_name, description, quantity, date_planted, plot_number, plot_row, native_climate, category_id) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        statement.setString(1, plant.getPlantName());
	        statement.setString(2, plant.getPlantDescription());
	        statement.setInt(3, plant.getQuantity());
	        statement.setDate(4, new java.sql.Date(plant.getDatePlanted().getTime()));
	        statement.setInt(5, plant.getPlotNumber());
	        statement.setString(6, plant.getPlotRow());
	        statement.setString(7, plant.getNativeClimate());
	        statement.setInt(8, plant.getCategoryId());

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
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                System.out.println("Error closing resultSet: " + e.getMessage());
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                System.out.println("Error closing statement: " + e.getMessage());
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
	            plant.setDatePlanted(resultSet.getDate("date_planted"));
	            plant.setPlotNumber(resultSet.getInt("plot_number"));
	            plant.setPlotRow(resultSet.getString("plot_row"));
	            plant.setNativeClimate(resultSet.getString("native_climate"));
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
	/**
	 * Retrieves plants based on a keyword search.
	 * 
	 * @param keyword The keyword used to search for plants in the database.
	 * @return A list of Plant objects representing the search results.	
	 */
	public List<Plant> searchPlantsByKeyword(String keyword) {
	    List<Plant> searchResults = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conn = this.getConnection();
	        // Perform the database query to search for plants based on the keyword
	        String sql = "SELECT p.* FROM plants p " +
                    "INNER JOIN plant_category c ON p.category_id = c.category_id " +
                    "WHERE p.plant_name LIKE ? OR c.category_name LIKE ? OR p.description LIKE"
                    + " ? OR p.date_planted LIKE ? OR p.plot_number LIKE ? OR p.plot_row LIKE ? OR"
                    + " p.native_climate LIKE ?";
	        statement = conn.prepareStatement(sql);
	        String likeKeyword = "%" + keyword + "%";
	        statement.setString(1, likeKeyword);
	        statement.setString(2, likeKeyword);
	        statement.setString(3, likeKeyword);
	        statement.setString(4, likeKeyword);
	        statement.setString(5, likeKeyword);
	        statement.setString(6, likeKeyword);
	        statement.setString(7, likeKeyword);
	        resultSet = statement.executeQuery();

	        // Loop through the result set and create Plant objects for each row
	        while (resultSet.next()) {
	            Plant plant = new Plant();
	            plant.setPlantId(resultSet.getInt("plant_id"));
	            plant.setPlantName(resultSet.getString("plant_name"));
	            plant.setPlantDescription(resultSet.getString("description"));
	            plant.setQuantity(resultSet.getInt("quantity"));
	            plant.setDatePlanted(resultSet.getDate("date_planted"));
	            plant.setPlotNumber(resultSet.getInt("plot_number"));
	            plant.setPlotRow(resultSet.getString("plot_row"));
	            plant.setNativeClimate(resultSet.getString("native_climate"));
	            plant.setCategoryId(resultSet.getInt("category_id"));

	            // Add the Plant object to the search results list
	            searchResults.add(plant);
	        }

	        // Close the result set and statement
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return searchResults;
	}
}
