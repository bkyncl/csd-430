/* CreateModel.java
 * Module 8 Assignment
 * Name: Brittany Kyncl
 * Date: 7.4.23
 * Course: CSD430
 * CreateModel, responsible for creating and populating the garden database tables,
 * as well as creating triggers for data manipulation.
 * The class provides methods to create the necessary tables, insert initial data,
 * and create triggers for maintaining the plant count in the plant_category table.
 */
package kyncl_08.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateModel {
	private DataManager dataManager;
	
	public CreateModel(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	// Method to drop garden and create garden database then create the necessary tables within garden database
	public void createTables() {
		Connection conn = null;
        try {
        	conn = dataManager.getConnection();
        	
        	// Drop and create the garden database
	        PreparedStatement dropGarden = conn.prepareStatement("DROP DATABASE garden");
	        PreparedStatement createGarden = conn.prepareStatement("CREATE DATABASE garden");
	        dropGarden.execute();
	        createGarden.execute();
	        PreparedStatement useGarden = conn.prepareStatement("USE garden");
	        useGarden.execute();
	        
        	// Create the plant_category table
            PreparedStatement categoryStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS plant_category ("
            		 + "category_id INT NOT NULL AUTO_INCREMENT,"
                     + "category_name VARCHAR(50) NOT NULL,"
                     + "plant_count INT NOT NULL DEFAULT 0,"
                     + "PRIMARY KEY (category_id)"
                     + ")");
            categoryStatement.execute();
            
            System.out.println("Table Created: plant_category");
            
            // Create plants table
            PreparedStatement plantsStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS plants ("
                    + "plant_id INT NOT NULL AUTO_INCREMENT,"
                    + "plant_name VARCHAR(50) NOT NULL,"
                    + "description VARCHAR(50) NOT NULL,"
                    + "quantity INT NOT NULL,"
                    + "category_id INT NOT NULL,"
                    + "FOREIGN KEY (category_id) REFERENCES plant_category(category_id),"
                    + "PRIMARY KEY (plant_id)"
                    + ")");
            plantsStatement.execute();

            System.out.println("Table Created: plants");
            
            // Create triggers for plants table
            createTriggers("plants");
       
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        } finally {
            dataManager.endConnection(conn);
        }
	}
	
	// Method to create triggers for data manipulation
	public void createTriggers(String tableName) {
		Connection conn = null;
	    try {
	        conn = dataManager.getConnection();
	        
	        // Drop insert trigger
            PreparedStatement dropInsertTriggerStatement = conn.prepareStatement("DROP TRIGGER IF EXISTS insert_trigger_" + tableName);
            dropInsertTriggerStatement.execute();

            // Drop delete trigger
            PreparedStatement dropDeleteTriggerStatement = conn.prepareStatement("DROP TRIGGER IF EXISTS delete_trigger_" + tableName);
            dropDeleteTriggerStatement.execute();

            // Drop update trigger
            PreparedStatement dropUpdateTriggerStatement = conn.prepareStatement("DROP TRIGGER IF EXISTS update_trigger_" + tableName);
            dropUpdateTriggerStatement.execute();

            System.out.println("Triggers deleted for table: " + tableName);

            // Create trigger for inserting new records
            PreparedStatement insertTriggerStatement = conn.prepareStatement("CREATE TRIGGER insert_trigger_" + tableName + " AFTER INSERT ON " + tableName + " "
                    + "FOR EACH ROW "
                    + "UPDATE plant_category "
                    + "SET plant_count = plant_count + NEW.quantity "
                    + "WHERE category_id = NEW.category_id");
            insertTriggerStatement.execute();

            // Create trigger for deleting records
            PreparedStatement deleteTriggerStatement = conn.prepareStatement("CREATE TRIGGER delete_trigger_" + tableName + " AFTER DELETE ON " + tableName + " "
                    + "FOR EACH ROW "
                    + "UPDATE plant_category "
                    + "SET plant_count = plant_count - - OLD.quantity  "
                    + "WHERE category_id = OLD.category_id");
            deleteTriggerStatement.execute();

            // Create trigger for updating records
            PreparedStatement updateTriggerStatement = conn.prepareStatement("CREATE TRIGGER update_trigger_" + tableName + " AFTER UPDATE ON " + tableName + " "
                    + "FOR EACH ROW "
                    + "IF OLD.category_id != NEW.category_id THEN "
                    + "UPDATE plant_category "
                    + "SET plant_count = plant_count - OLD.quantity "
                    + "WHERE category_id = OLD.category_id; "
                    + "UPDATE plant_category "
                    + "SET plant_count = plant_count + NEW.quantity "
                    + "WHERE category_id = NEW.category_id; "
                    + "ELSE "
                    + "UPDATE plant_category "
                    + "SET plant_count = plant_count - OLD.quantity + NEW.quantity "
                    + "WHERE category_id = NEW.category_id; "
                    + "END IF;");
            updateTriggerStatement.execute();

            System.out.println("Triggers created for table: " + tableName);
	        
	    } catch (SQLException e) {
	        System.out.println("Error creating triggers: " + e.getMessage());
	    } finally {
	        dataManager.endConnection(conn);
	    }
	}
	
	// method to insert records into necessary tables
	public void insertData() {
		Connection conn = null;
	    try {
	        conn = dataManager.getConnection();
	        
	        // Prepared statement for inserting into plant_category table
	        PreparedStatement categoryInsertStatement = conn.prepareStatement("INSERT INTO plant_category (category_name) VALUES (?)");
	        
	        // Prepared statement for inserting into plants table
            PreparedStatement plantsInsertStatement = conn.prepareStatement("INSERT INTO plants (plant_name, category_id, description, quantity) VALUES (?, ?, ?, ?)");
	        
	        // Use the prepared statements to insert data into the tables
	        
            // Insert records into plant_category table
	        categoryInsertStatement.setString(1, "Fruits"); // category_id 1
	        categoryInsertStatement.addBatch();

	        categoryInsertStatement.setString(1, "Vegetables"); // category_id 2
	        categoryInsertStatement.addBatch();

	        categoryInsertStatement.setString(1, "Herbs"); // category_id 3
	        categoryInsertStatement.addBatch();
	        
	        categoryInsertStatement.executeBatch();
	        System.out.println("Records Inserted Into: plant_category");
	        
	        // Insert records into plants table
	        plantsInsertStatement.setString(1, "Apple");
	        plantsInsertStatement.setInt(2, 1);
	        plantsInsertStatement.setString(3, "Water once a week");
	        plantsInsertStatement.setInt(4, 2);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.setString(1, "Pear");
	        plantsInsertStatement.setInt(2, 1);
	        plantsInsertStatement.setString(3, "Water once ever 2 weeks");
	        plantsInsertStatement.setInt(4, 5);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.setString(1, "Banana");
	        plantsInsertStatement.setInt(2, 1);
	        plantsInsertStatement.setString(3, "Water every 2 days");
	        plantsInsertStatement.setInt(4, 3);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.setString(1, "Carrot");
	        plantsInsertStatement.setInt(2, 2);
	        plantsInsertStatement.setString(3, "Water every 2 days");
	        plantsInsertStatement.setInt(4, 10);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.setString(1, "Peas");
	        plantsInsertStatement.setInt(2, 2);
	        plantsInsertStatement.setString(3, "Water every day");
	        plantsInsertStatement.setInt(4, 15);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.setString(1, "Asparagus");
	        plantsInsertStatement.setInt(2, 2);
	        plantsInsertStatement.setString(3, "Water twice a week");
	        plantsInsertStatement.setInt(4, 10);
	        plantsInsertStatement.addBatch();

	        plantsInsertStatement.setString(1, "Basil");
	        plantsInsertStatement.setInt(2, 3);
	        plantsInsertStatement.setString(3, "Water once a week");
	        plantsInsertStatement.setInt(4, 10);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.setString(1, "Thyme");
	        plantsInsertStatement.setInt(2, 3);
	        plantsInsertStatement.setString(3, "Water every other week");
	        plantsInsertStatement.setInt(4, 20);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.setString(1, "Cilantro");
	        plantsInsertStatement.setInt(2, 3);
	        plantsInsertStatement.setString(3, "Water once a week");
	        plantsInsertStatement.setInt(4, 15);
	        plantsInsertStatement.addBatch();
	        
	        plantsInsertStatement.executeBatch();
	        System.out.println("Records Inserted Into: plants");
	        
	        
	        
	    } catch (SQLException e) {
	        System.out.println("Error Inserting Records: " + e.getMessage());
	    } finally {
	        dataManager.endConnection(conn);
	    }
	}
}
