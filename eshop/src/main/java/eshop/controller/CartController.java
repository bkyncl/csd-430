/* CartController.java
 * Module 7 Assignment
 * Name: Brittany Kyncl
 * Date: 6.30.23
 * Course: CSD430
 * NOTICE OF REVISION:
 * Extracted shopping cart operations from ShoppingCart.jsp and Checkout.jsp into a separate controller class to align more with MCV
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */

package eshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import eshop.beans.Book;
import eshop.beans.CartItem;
import eshop.model.DataManager;


public class CartController {
	private DataManager dataManager;
	
	// Constructor to inject DataManager dependency
	public CartController(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	/**
	 * Retrieves the shopping cart stored in the HttpSession.
	 * 
	 * @param request The HTTP servlet request containing the current session.
	 * @return The shopping cart as a Hashtable with book IDs as keys and CartItem objects as values.
	 */
	@SuppressWarnings("unchecked")
	public Hashtable<String, CartItem> getShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Hashtable<String, CartItem>) session.getAttribute("shoppingCart");
    }
	
	/**
	 * Adds an item to the shopping cart.
	 * 
	 * @param request The HTTP servlet request containing the necessary parameters.
	 */
	public void addItem(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
		Hashtable<String, CartItem> shoppingCart = (Hashtable<String, CartItem>) session.getAttribute("shoppingCart");
	    if (shoppingCart == null) {
	    	// If shopping cart doesn't exist in session, create a new one
	    	shoppingCart = new Hashtable<>(10);
	    	session.setAttribute("shoppingCart", shoppingCart);
	    }

	    String bookId = request.getParameter("bookId");
	    Book book = dataManager.getBookDetails(bookId);
	    if (book != null) {
	    	CartItem item = new CartItem(book, 1);
	    	// Replace any existing item with the same bookId
	    	shoppingCart.remove(bookId);
	    	shoppingCart.put(bookId, item);
	    }
	}
	
	/**
	 * Updates the quantity of an item in the shopping cart.
	 * 
	 * @param request The HTTP servlet request containing the necessary parameters.
	 */
	public void updateItem(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
	    Hashtable<String, CartItem> shoppingCart = (Hashtable<String, CartItem>) session.getAttribute("shoppingCart");
	    if (shoppingCart == null) {
	    	return;// Shopping cart doesn't exist, no action needed
	    }

	    String bookId = request.getParameter("bookId");
	    String quantity = request.getParameter("quantity");
	    CartItem item = shoppingCart.get(bookId);
	    if (item != null) {
	    	item.setQuantity(quantity);
	    }
	}
	
	/**
	 * Deletes an item from the shopping cart.
	 * 
	 * @param request The HTTP servlet request containing the necessary parameters.
	 */
	public void deleteItem(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    @SuppressWarnings("unchecked")
	    Hashtable<String, CartItem> shoppingCart = (Hashtable<String, CartItem>) session.getAttribute("shoppingCart");
	    if (shoppingCart == null) {
	    	return; // Shopping cart doesn't exist, no action needed
	    }

	    String bookId = request.getParameter("bookId");
	    shoppingCart.remove(bookId);
	}
}
