package ebookshop;

/* ShoppingServlet.java
 * Module 5 Assignment
 * Name: Brittany Kyncl
 * Date: 6.13.23
 * Course: CSD430
 * The servlet allows users to browse and purchase books from an online ebook shop. It manages the shopping cart
 * and provides functionality for adding, removing, and checking out books. The servlet interacts with the session
 * to store and retrieve the shopping cart and book list. The servlet also forwards requests to appropriate pages
 * for display and processing, such as the home page and the checkout page.
 * 
 * The servlet uses the `Book` class to represent book objects, which encapsulate information such as the title, price, and quantity
 * of a book. The `getBook` method is used to retrieve book information from request parameters and create `Book` objects.
 * 
 */

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/eshop")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Constructor for the ShoppingServlet class.
     */
    public ShoppingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("**** INITIALIZING CONTROLLER SREVLET ****");
    	super.init(config);
    	
    }

    /**
	 * Handles HTTP GET requests.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// forward call to doPost()
		doPost(request,response);
	}

	/**
	 * Handles HTTP POST requests.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the current session
		HttpSession session = request.getSession(true);
		
		// suppress warning for unchecked type-casting from a generic Object to Vector<Book>
		// certain of attribute ebookshop.cart being of type Vector<Book>
	    @SuppressWarnings("unchecked")
	    // Retrieve the shopping cart from the session
	    Vector<Book> shoplist = (Vector<Book>)session.getAttribute("ebookshop.cart");
	    
	    // Retrieve the do_this parameter from the request
	    String do_this = request.getParameter("do_this");

	    // If it is the first time, initialize the list of books, which in
	    // real life would be stored in a database on disk
	    if (do_this == null) {
	    	
	    	// Create a vector to hold the book titles
	    	Vector<String> blist = new Vector<String>();
	    	blist.addElement("Learn HTML5 and JavaScript for iOS. Scott Preston $39.99");
	    	blist.addElement("Java 7 for Abosulte Beginners. Jay Bryant $39.99");
	    	blist.addElement("Beginning Android 4. Livingston $39.99");
	    	blist.addElement("Pro Spatial with SQL Server 2012. Alastair Aitchison $59.99");
	    	blist.addElement("Beginning Database Design. Clare Churner $34.99");
	    	
	    	// Set the book list attribute in the session
	    	session.setAttribute("ebookshop.list", blist);
	    	
	    	// Forward the request and response to the home page
	    	ServletContext    sc = request.getSession().getServletContext();
	    	RequestDispatcher rd = sc.getRequestDispatcher("/");
	    	rd.forward(request, response);
	    }
	    else {
	    	
	    	// If it is not the first request, it can only be a checkout request
	    	// or a request to manipulate the list of books being ordered
	    	if (do_this.equals("checkout"))  {
		        float dollars = 0;
		        int   books = 0;
		        
		        // Calculate the total price and quantity of the books in the shopping cart
		        for (Book aBook : shoplist) {
		        	float price = aBook.getPrice();
		        	int   qty = aBook.getQuantity();
		        	dollars += price * qty;
		        	books += qty;
		        }
		        
		        // Set the dollars and books attributes in the request
		        request.setAttribute("dollars", String.valueOf(dollars));
		        request.setAttribute("books", String.valueOf(books));
		        
		        // Forward the request and response to the checkout page
		        ServletContext    sc = request.getServletContext();
		        RequestDispatcher rd = sc.getRequestDispatcher("/Checkout.jsp");
		        rd.forward(request, response);
		        
	    	} // if (..checkout..
	    	else { 
	    		// Not a checkout request - Manipulate the list of books
	    		if (do_this.equals("remove")) {
					String pos = request.getParameter("position");
					shoplist.removeElementAt(Integer.parseInt(pos));
	    		}
	    		else if (do_this.equals("add")) {
		    		boolean  found = false;
		    		Book aBook = getBook(request);
		    		
			        if (shoplist == null) {  // the shopping cart is empty
			        	shoplist = new Vector<Book>();
			        	shoplist.addElement(aBook);
			        } else {  // update the #copies if the book is already there
			        	for (int i = 0; i < shoplist.size() && !found; i++) {
			        		Book b = (Book)shoplist.elementAt(i);
			        		if (b.getTitle().equals(aBook.getTitle())) {
			        			b.setQuantity(b.getQuantity() + aBook.getQuantity());
			        			shoplist.setElementAt(b, i);
			        			found = true;
			        		}
			        	} // for (i..
			        	
			        	if (!found) {  // if it is a new book => Add it to the shoplist
			        		shoplist.addElement(aBook);
			        	}
			        } // if (shoplist == null) .. else ..
	    		} // if (..add..

				// Save the updated list of books and return to the home page
				session.setAttribute("ebookshop.cart", shoplist);
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/");
				rd.forward(request, response);
	    	} // if (..checkout..else
	    } // if (do_this..
	} // doPost
	
	/**
	 * Retrieves the book information from the request parameters.
	 *
	 * @param req The HTTP servlet request.
	 * @return A Book object containing the book title, price, and quantity.
	 */
	private Book getBook(HttpServletRequest req) {
	  	String myBook = req.getParameter("book");
	    int    n = myBook.indexOf('$');
	    String title = myBook.substring(0, n);
	    String price = myBook.substring(n+1);
	    String qty = req.getParameter("qty");
	    return new Book(title, Float.parseFloat(price), Integer.parseInt(qty));
	} // getBook
}

