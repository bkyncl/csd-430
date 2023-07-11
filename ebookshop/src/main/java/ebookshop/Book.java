package ebookshop;

/* Book.java
 * Module 5 Assignment
 * Name: Brittany Kyncl
 * Date: 6.13.23
 * Course: CSD430
 * The Book class represents a book object in the ebook application. It contains information about the 
 * book, such as title(title & author), price, and quantity.
 */

/**
 * Constructs a Book object with the specified title (title string in this case also contains author name) , price, attributes, and quantity.
 *
 * @param tile    The title of the book and author.
 * @param price  The price of the book
 * @param quantity    The quantity of this.book.
 */

public class Book {
	String title;
	float  price;
	int    quantity;
	
	public Book(String t, float p, int q) {
	    title    = t;
	    price    = p;
	    quantity = q;
	}
	/**
	 * Retrieves the title of the book.
	 *
	 * @return The title of the book.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets the title of the book.
	 *
	 * @param title The title to be set for the book.
	 */
	public void setTitle(String t) {
		this.title = t;
	}
	/**
	 * Retrieves the price of the book.
	 *
	 * @return The price of the book.
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * Sets the price of the book.
	 *
	 * @param price The price to be set for the book.
	 */
	public void setPrice(float p) {
		this.price = p;
	}
	/**
	 * Retrieves the quantity of the book.
	 *
	 * @return The quantity of the book.
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Sets the quantity of the book.
	 *
	 * @param quantity The quantity to be set for the book.
	 */
	public void setQuantity(int q) {
		this.quantity = q;
	}
	
}