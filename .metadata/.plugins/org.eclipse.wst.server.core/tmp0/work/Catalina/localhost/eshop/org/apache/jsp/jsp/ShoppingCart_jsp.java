/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.75
 * Generated at: 2023-07-11 20:50:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Enumeration;
import java.util.Hashtable;
import eshop.beans.Book;
import eshop.beans.CartItem;
import eshop.controller.CartController;

public final class ShoppingCart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Enumeration");
    _jspx_imports_classes.add("eshop.controller.CartController");
    _jspx_imports_classes.add("eshop.beans.CartItem");
    _jspx_imports_classes.add("java.util.Hashtable");
    _jspx_imports_classes.add("eshop.beans.Book");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      eshop.model.DataManager dataManager = null;
      synchronized (application) {
        dataManager = (eshop.model.DataManager) _jspx_page_context.getAttribute("dataManager", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (dataManager == null){
          dataManager = new eshop.model.DataManager();
          _jspx_page_context.setAttribute("dataManager", dataManager, javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');

	//Get the base URL from the application context
    String base = (String) application.getAttribute("base");
	//Retrieve the shopping cart from the session
    @SuppressWarnings("unchecked")
    Hashtable<String, CartItem> shoppingCart =
            (Hashtable<String, CartItem>) session.getAttribute("shoppingCart");
 	// If the shopping cart doesn't exist, create a new one and set it in the session
    if (shoppingCart == null) {
        shoppingCart = new Hashtable<String, CartItem>(10);
        session.setAttribute("shoppingCart", shoppingCart);
    }
 	// Retrieve the action parameter from the request
    String action = request.getParameter("action");
 	// Instantiate the CartController and pass the DataManager as a dependency
    CartController cartController = new CartController(dataManager);

 	// Process the action based on its value
    if (action != null && action.equals("addItem")) {
        // Call the addItem method of the CartController
        cartController.addItem(request);
    } else if (action != null && action.equals("updateItem")) {
        // Call the updateItem method of the CartController
        cartController.updateItem(request);
    } else if (action != null && action.equals("deleteItem")) {
        // Call the deleteItem method of the CartController
        cartController.deleteItem(request);
    }

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
      out.write("  <title>Shopping Cart</title>\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"/eshop/css/eshop.css\" type=\"text/css\"/>\r\n");
      out.write("  </head>\r\n");
      out.write("<body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "TopMenu.jsp", out, true);
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "LeftMenu.jsp", out, true);
      out.write('\r');
      out.write('\n');

  if (!shoppingCart.isEmpty()) {
  
      out.write("\r\n");
      out.write("    <div class=\"content\">\r\n");
      out.write("      <h2>Shopping Cart</h2>\r\n");
      out.write("      <table>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th>Title</th>\r\n");
      out.write("          <th>Author</th>\r\n");
      out.write("          <th>Price</th>\r\n");
      out.write("          <th>Quantity</th>\r\n");
      out.write("          <th>Subtotal</th>\r\n");
      out.write("          <th>Delete</th>\r\n");
      out.write("          </tr>\r\n");

        Enumeration<CartItem> enumList = shoppingCart.elements();
        double itemPrice = 0;
        double totalPrice = 0;
        while (enumList.hasMoreElements()) {
          CartItem item = enumList.nextElement();
          itemPrice = Math.round(
        		    Integer.parseInt(item.getQuantity())
        		    * item.getPrice() * 100.) / 100.
                    ;
          totalPrice += itemPrice;
  
      out.write("\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td>");
      out.print(item.getTitle());
      out.write("</td>\r\n");
      out.write("            <td>");
      out.print(item.getAuthor());
      out.write("</td>\r\n");
      out.write("            <td>");
      out.print(item.getPrice());
      out.write("</td>\r\n");
      out.write("            <td><form>\r\n");
      out.write("              <input type=\"hidden\" name=\"action\" value=\"updateItem\"/>\r\n");
      out.write("              <input type=\"hidden\" name=\"bookId\"\r\n");
      out.write("                value=\"");
      out.print(item.getBookID());
      out.write("\"/>\r\n");
      out.write("              <input type=\"text\" size=\"2\" name=\"quantity\" \r\n");
      out.write("                value=\"");
      out.print(item.getQuantity());
      out.write("\"/>\r\n");
      out.write("              <input type=\"submit\" value=\"Update\"/>\r\n");
      out.write("              </form></td>\r\n");
      out.write("            <td>\r\n");
      out.write("              ");
      out.print(itemPrice);
      out.write("\r\n");
      out.write("                </td>\r\n");
      out.write("            <td><form>\r\n");
      out.write("              <input type=\"hidden\" name=\"action\" value=\"deleteItem\"/>\r\n");
      out.write("              <input type=\"hidden\" name=\"bookId\" \r\n");
      out.write("                value=\"");
      out.print(item.getBookID());
      out.write("\"/>\r\n");
      out.write("              <input type=\"submit\" value=\"Delete\"/>\r\n");
      out.write("              </form></td>\r\n");
      out.write("            </tr>\r\n");

          }
  
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"5\" id=\"total\">Total: ");
      out.print(totalPrice);
      out.write("</td>\r\n");
      out.write("          <td class=\"total\">&nbsp;</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"6\" class=\"center\"><a class=\"link1\"\r\n");
      out.write("            href=\"");
      out.print(base);
      out.write("?action=checkOut\">Check Out</a></td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("      </div>\r\n");

    }
  else {
    
      out.write("<p class=\"info\">The Shopping cart is empty.</p>");

    }
  
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}