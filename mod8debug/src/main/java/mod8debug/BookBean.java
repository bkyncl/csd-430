/*
BookBean.java
Module 8 DB Post - Debug Program
Name: Brittany Kyncl
Date: 6.29.23
Course: CSD430
This Bean class contains 1 bug (logical)
*/
package mod8debug;

public class BookBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String author;
	

	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getId() {
		return id;
	}
	public String gettitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	//NOTICE: original program contained missing "getter" as bug since removed for solution purposes

}
