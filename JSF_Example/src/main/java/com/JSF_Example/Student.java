/* Student.java
 * Module 10 Assignment
 * Name: Brittany Kyncl
 * Date: 7.16.23
 * Course: CSD430
 * Student bean for student registration program serving as data container for storing and managing student data.
 * Student class properties first name, last name, ID, email, country, gender, and languages.
 */

package com.JSF_Example;

import java.io.*;
import javax.faces.bean.ManagedBean;


@SuppressWarnings("deprecation")
@ManagedBean
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private int studentId;
	private String email;
	private String country;
	private String gender;
	private String[] languages;
	
	// getters and setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String[] getLanguages() {
		return languages;
	}
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}
	
}
