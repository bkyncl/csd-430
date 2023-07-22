/* MyServlet.java
 * Module 12 Assignment
 * Name: Brittany Kyncl
 * Date: 7.21.23
 * Course: CSD430
 * MyServlet, which serves as the controller for managing plants in "garden" database management web app. 
 * The servlet handles HTTP requests related to the management of the garden database, 
 * such as creating and populating database tables, inserting new plants, and retrieving plant and category information.  
 */
package kyncl12_GardenManager.controller;

import java.io.IOException;
import java.util.*;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kyncl12_GardenManager.bean.*;
import kyncl12_GardenManager.model.*;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name = "MyServlet", urlPatterns = { "/plants/*" })
public class MyServlet extends HttpServlet implements javax.servlet.Servlet{
	private static final long serialVersionUID = 1L;
	private DataManager dataManager;
	private CreateModel createModel;
	private List<Plant> plants;
    private List<Category> categories;
       
    
    public MyServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
		System.out.println("*** initializing controller servlet.");
		super.init(config);
		
		// Initialize DataManager and set the database connection parameters
		this.dataManager = new DataManager();
		dataManager.setDbURL(config.getInitParameter("dbURL"));
		dataManager.setDbUserName(config.getInitParameter("dbUserName"));
		dataManager.setDbPassword(config.getInitParameter("dbPassword"));
		System.out.println("*** dataManager set parameters.");
		
		// Set the servlet context attributes
		ServletContext context = config.getServletContext();
		context.setAttribute("base", config.getInitParameter("base"));
		context.setAttribute("dataManager", dataManager);
		System.out.println("*** set servlet context.");

		try { // load the database JDBC driver
			Class.forName(config.getInitParameter("jdbcDriver"));
			System.out.println("*** JDBC driver loaded.");
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action != null) {
			switch (action) {
            case "CategorySearch":
                // Get the selected category from the request parameters (query parameter)
                String selectedCategory = request.getParameter("category");

                // Call the searchPlantsByKeyword method in DataManager to get the search results
                List<Plant> searchResults = dataManager.searchPlantsByKeyword(selectedCategory);
                categories = dataManager.getAllCategories();

                // Set the search results and categories as attributes in the request object
                request.setAttribute("searchResults", searchResults);
                request.setAttribute("categories", categories);

                // Set the "contentJsp" attribute to the JSP page that displays the search results
                request.setAttribute("contentJsp", "search.jsp");
                break;
         
	        case "EnterPlant":
	        	// Retrieve the list of plants and categories
	        	plants = dataManager.getAllPlants();
	        	categories = dataManager.getAllCategories();
	        	
	            // Set the attributes in the request object
	            request.setAttribute("plants", plants);  
	            request.setAttribute("categories", categories);
	            
	            // Set the "contentJsp" attribute to the JSP page that displays the form
                request.setAttribute("contentJsp", "form.jsp");
                break;
	        
	        default:
	            // Invalid action or no action specified
	            // Redirect to an error page or display an error message
	            response.sendRedirect("index.jsp");
	            break;
			}
		}
		// Forward the request and response objects to the index.jsp page
	    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "KeySearch":
                String keyword = request.getParameter("keyword");

                // Call the searchPlantsByKeyword method in DataManager to get the search results
                List<Plant> searchResults = dataManager.searchPlantsByKeyword(keyword);
                categories = dataManager.getAllCategories();

                // Set the search results as an attribute in the request object
                request.setAttribute("searchResults", searchResults);
                request.setAttribute("categories", categories);
                
                // Set the "contentJsp" attribute to the JSP page that displays the search results
                request.setAttribute("contentJsp", "search.jsp");
                break;
                
			case "CreateAndPop":
	        	// Create and populate the database tables
                boolean populationSuccess = createAndPopulateTables();
                
                if (populationSuccess) {
                    // Set an attribute to indicate successful creation
                    request.setAttribute("message", "Database Created: garden<br>"
                            + "Table Created: plant_category<br>"
                            + "Table Created: plants<br>"
                            + "Records Inserted Into: plant_category<br>"
                            + "Records Inserted Into: plants<br>"
                            + "Database Population Successful<br>");
                } else {
                    // Set an attribute to indicate failure
                    request.setAttribute("message", "Database Population Failed");
                }
                
                // Set the "contentJsp" attribute to the JSP page that displays the DB initialization results
                request.setAttribute("contentJsp", "createandpop.jsp");
                break;

	        case "Insert":
	        	// Call the handleInsertPlant() method and pass the request object
	            handleInsertPlant(request);
	            
	            System.out.println("New plant inserted");
	            
	            // Retrieve the list of plants and categories	
	        	plants = dataManager.getAllPlants();
	        	categories = dataManager.getAllCategories();
	            // Set the attributes in the request object
	            request.setAttribute("plants", plants);  
	            request.setAttribute("categories", categories);
	            
	            // Set the "contentJsp" attribute to the JSP page that displays the new plant insertion
                request.setAttribute("contentJsp", "form.jsp");
                break;
                
	        default:
	            // Invalid action or no action specified
	            // Redirect to an error page or display an error message
	            response.sendRedirect("index.jsp");
	            break;
			}
		
		}
		// Forward the request and response objects to the index.jsp page
	    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	    dispatcher.forward(request, response);
	    
    }
	
	public boolean createAndPopulateTables() {
        // Create a new CreateModel object with the dataManager instance
        this.createModel = new CreateModel(this.dataManager);

        try {
            // Create the database tables
            createModel.createTables();
            // Populate the tables with initial data
            createModel.insertData();
            
            return true; // Population successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Population failed
        }
    }
	
	public void handleInsertPlant(HttpServletRequest request) {
		// Create a new Plant object and set its properties based on the request parameters
		Plant newPlant = new Plant();
	    newPlant.setPlantName(request.getParameter("plantName"));
	    newPlant.setPlantDescription(request.getParameter("plantDescription"));
	    newPlant.setQuantity(Integer.parseInt(request.getParameter("quantity")));
	    newPlant.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));	   
	    newPlant.setDatePlanted(Date.valueOf(request.getParameter("datePlanted")));
	    newPlant.setPlotNumber(Integer.parseInt(request.getParameter("plotNumber")));
	    newPlant.setPlotRow(request.getParameter("plotRow"));
	    newPlant.setNativeClimate(request.getParameter("nativeClimate"));
		
		// Insert the new plant into the database using the dataManager instance
		this.dataManager.insertPlant(newPlant);
	}

}
