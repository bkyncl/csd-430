/* OrderPeer.java
 * Module 7 Assignment
 * Name: Brittany Kyncl
 * Date: 6.30.23
 * Course: CSD430
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */
package eshop.model;

import java.sql.SQLException;
import java.sql.Statement;
import eshop.beans.Customer;

public class OrderPeer {

	public static void insertOrder(Statement stmt, long orderId, Customer customer) throws SQLException {
		String sql = "insert into orders (order_id, delivery_name,"
				+ " delivery_address, cc_name, cc_number, cc_expiry) values ('" + orderId + "','"
				+ customer.getContactName() + "','" + customer.getDeliveryAddress() + "','" + customer.getCcName()
				+ "','" + customer.getCcNumber() + "','" + customer.getCcExpiryDate() + "')";
		stmt.executeUpdate(sql);
	}
}
