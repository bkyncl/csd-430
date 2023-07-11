package com.bkyncl_mod4;

/* UserRole.java
 * Module 4 Assignment
 * Name: Brittany Kyncl
 * Date: 6.6.23
 * Course: CSD430
 * The UserRole class represents a user role in a system. Each user role has a role name,
 * description, attributes, and deficits. It provides methods to retrieve the role name,
 * description, attributes, and deficits associated with a user role.
 */

/**
 * Constructs a UserRole object with the specified role name, description, attributes, and deficits.
 *
 * @param roleName    The name of the user role.
 * @param description The description of the user role.
 * @param attributes  The attributes associated with the user role.
 * @param deficits    The deficits associated with the user role.
 */
public class UserRole {
	private String roleName;
	private String description;
    private String[] attributes;
    private String[] deficits;

    public UserRole(String roleName, String description, String[] attributes, String[] deficits) {
        this.roleName = roleName;
        this.description = description;
        this.attributes = attributes;
        this.deficits = deficits;
    }
    
    // get role name of user role
    public String getRoleName() {
        return roleName;
    }
    // get role description of user role
    public String getDescription() {
        return description;
    }
    // get list of attributes associated with user role
    public String[] getAttributes() {
        return attributes;
    }
    // get list of deficits associated with user role
    public String[] getDeficits() {
        return deficits;
    }

}


