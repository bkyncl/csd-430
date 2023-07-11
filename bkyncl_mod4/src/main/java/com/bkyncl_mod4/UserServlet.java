package com.bkyncl_mod4;

/* UserServlet.java
 * Module 4 Assignment
 * Name: Brittany Kyncl
 * Date: 6.6.23
 * Course: CSD430
 * This servlet handles user requests related to user roles and accessing their character attributes.
 * processes Character Creation form data, and forward the request to the result_kynclJSP_41.JSP page for displaying.
 * 
 * The servlet also initializes the user roles and character classes in the init() method,
 * which is called when the servlet is initialized. The user roles are stored in a list,
 * and the character classes of the user roles are stored in an array, which is set as a servlet context attribute.
 */

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(value = "/UserServlet", loadOnStartup =1)
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<UserRole> userRoles; // list to store user roles
	private static String[] characterClasses; // list to store role names
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }
    
    /**
     * Initializes the servlet by populating user roles and character classes.
     * It calls the methods to initialize user roles and character classes,
     * and sets the character classes as a servlet context attribute.
     */
    @Override
    public void init() throws ServletException {
    	initializeUserRoles();
    	initializeCharacterClasses();
    	getServletContext().setAttribute("characterClasses", characterClasses);
    }
    
    // Populates the userRoles list with predefined user roles and their attributes.
    private void initializeUserRoles() {
    	//Populate the userRoles list
    	userRoles = new ArrayList<>();
	    userRoles.add(new UserRole("Bard", "The playful trickster who using charm and charisma to to get out of trouble.", 
	    		new String[]{"Charisma", "Wisdom", "Music", "Crafting", "Dexterity"}, 
	    		new String[] {"Fragile", "Poor Melee"}));
	    userRoles.add(new UserRole("Necromancy", "The evil sorcerer who used dark magic to raise the dead and manipulate life force.", 
	    		new String[]{"Intelligence", "Dark Magic", "Life Force Manipulation", "Self-Healing", "Long Range Damage"}, 
	    		new String[] {"Poor Melee", "Vulnerable to Light Magic"}));
	    userRoles.add(new UserRole("Healer", "The empathetic team player who uses light magic to heal and manipulate surroundings.", 
	    		new String[]{"Wisdom", "Self-Healing", "Party-Healing", "Crafting", "Illusion"}, 
	    		new String[] {"Fragile", "Vulnerable to Dark Magic", "Limited Blocking"}));
	    userRoles.add(new UserRole("Stealth", "The rogue assassin dealing death from the shaddows.", 
	    		new String[]{"Agility", "Invisibility", "Speed", "Lockpicking", "Charisma", "Long Range"}, 
	    		new String[] {"Vulnerable to Magic", "Poor Melee", "Poor Strength"}));
	    userRoles.add(new UserRole("Warrior", "Specializing in close melee combat dealing extra damage.", 
	    		new String[] {"Strength", "Speed", "Crafting", "Melee", "Constitution", "Dual Weapons"}, 
	    		new String[] {"Poor Long Range", "Vulnerable to Stealth", "Limited Healing"}));

    }
    
    // Initializes the characterClasses array by retrieving role names from the userRoles list.
    private void initializeCharacterClasses() {
    	characterClasses = new String[userRoles.size()];
        for (int i = 0; i < userRoles.size(); i++) {
        	characterClasses[i] = userRoles.get(i).getRoleName();
        }
    }
    
	/**
	 * Handles HTTP GET requests by invoking the corresponding `doPost` method.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * Handles HTTP POST requests by retrieving form parameters, processing the request,
     * and forwarding the request and response to the result_bkynclJSP_41.JSP page.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// retrieve form parameters
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String characterName = request.getParameter("characterName");
		String role = request.getParameter("role");

		// retrieve selected role from userRoles list
	    UserRole selectedUserRole = null;
	    for (UserRole userRole : userRoles) {
	        if (userRole.getRoleName().equals(role)) {
	            selectedUserRole = userRole;
	            break;
	        }
	    }
		
		// set attributes to be accessed in JSP
		request.setAttribute("userName", userName);
		request.setAttribute("email", email);
		request.setAttribute("characterName", characterName);
		request.setAttribute("role", role);
		request.setAttribute("description", selectedUserRole.getDescription());
	    request.setAttribute("attributes", selectedUserRole.getAttributes());
	    request.setAttribute("deficits", selectedUserRole.getDeficits());
	    request.setAttribute("submitted", true);
	 
		// forward the request and response to entrance JSP
		request.getRequestDispatcher("result_kynclJSP_41.jsp").forward(request, response);
	}

}
