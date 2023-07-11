/* ShopController.java
 * Module 7 Assignment
 * Name: Brittany Kyncl
 * Date: 6.30.23
 * Course: CSD430
 * NOTICE OF REVISION:
 * Controller class responsible for handling user input, updating the model,
 * and selecting the appropriate view to render the response. The controller
 * logic has been moved into this separate class to align more with MVC pattern. By encapsulating
 * the controller logic in its own class, it becomes more modular, maintainable,
 * and independent of the servlet implementation.
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */

package eshop.controller;

import javax.servlet.http.HttpServletRequest;

public class ShopController {
	
	/**
	 * Determines the appropriate URL based on the user's action.
	 * 
	 * @param request The HTTP servlet request containing the necessary parameters.
	 * @return The URL of the view to render.
	 */
	public String determineURL(HttpServletRequest request) {
        String base = "/jsp/";
        String action = request.getParameter("action");
        String url = base + "index.jsp";

        if (action != null) {
            switch (action) {
                case "search":
                    url = base + "SearchOutcome.jsp";
                    break;
                case "selectCatalog":
                    url = base + "SelectCatalog.jsp";
                    break;
                case "bookDetails":
                    url = base + "BookDetails.jsp";
                    break;
                case "checkOut":
                    url = base + "Checkout.jsp";
                    break;
                case "orderConfirmation":
                    url = base + "OrderConfirmation.jsp";
                    break;
                default:
                	// For shopping cart related actions
                    if (action.matches("(showCart|(add|update|delete)Item)"))
                        url = base + "ShoppingCart.jsp";
                    break;
            }
        }

        return url;
    }
}
