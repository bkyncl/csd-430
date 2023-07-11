/* OderDetailsPeer.java
 * Module 7 Assignment
 * Name: Brittany Kyncl
 * Date: 6.30.23
 * Course: CSD430
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */
package eshop.model;

import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.SQLException;
import java.sql.Statement;
import eshop.beans.CartItem;

public class OrderDetailsPeer {

	public static void insertOrderDetails(Statement stmt, long orderId, Hashtable<String, CartItem> shoppingCart)
			throws SQLException {
		String sql;
		Enumeration<CartItem> enumList = shoppingCart.elements();
		while (enumList.hasMoreElements()) {
			CartItem item = enumList.nextElement();
			sql = "insert into order_details (order_id, book_id, quantity," + " price, title, author) values ('"
					+ orderId + "','" + item.getBookID() + "','" + item.getQuantity() + "','" + item.getPrice() + "','"
					+ item.getTitle() + "','" + item.getAuthor() + "')";
			stmt.executeUpdate(sql);
		}
	}
}