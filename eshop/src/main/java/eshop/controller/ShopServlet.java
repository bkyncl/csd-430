/* ShopServlet.java
 * Module 7 Assignment
 * Name: Brittany Kyncl
 * Date: 6.30.23
 * Course: CSD430
 * NOTICE OF REVISION:
 * The controller logic has been moved into this separate class to align more with MVC pattern. By encapsulating
 * the controller logic in its own class, it becomes more modular, maintainable,
 * and independent of the servlet implementation.
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */

package eshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eshop.model.DataManager;

@WebServlet(name = "ShopServlet", urlPatterns = { "/shop/*" })
public class ShopServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
	private ShopController shopController;

	public ShopServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("*** initializing controller servlet.");
		super.init(config);

		DataManager dataManager = new DataManager();
		dataManager.setDbURL(config.getInitParameter("dbURL"));
		dataManager.setDbUserName(config.getInitParameter("dbUserName"));
		dataManager.setDbPassword(config.getInitParameter("dbPassword"));

		ServletContext context = config.getServletContext();
		context.setAttribute("base", config.getInitParameter("base"));
		context.setAttribute("imageURL", config.getInitParameter("imageURL"));
		context.setAttribute("dataManager", dataManager);

		try { // load the database JDBC driver
			Class.forName(config.getInitParameter("jdbcDriver"));
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		// NOTICE OF REVISION : Controller logic moved to ShopController.java
		// Create an instance of the ShopController
        shopController = new ShopController();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// NOTICE OF REVISION : Controller logic moved to ShopController.java
		// Determine the URL based on the action parameter using the ShopController
        String url = shopController.determineURL(request);

        // Forward the request to the determined URL
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
	}
}
