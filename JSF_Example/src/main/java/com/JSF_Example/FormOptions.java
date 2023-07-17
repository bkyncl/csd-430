/* FormOptions.java
 * Module 10 Assignment
 * Name: Brittany Kyncl
 * Date: 7.16.23
 * Course: CSD430
 * FormOptions bean representing the list options in the student registration form. Responsible for providing options in select fields
 * (Gender, Country, Languages) options are read from XML file form_options.xml and stored in lists to be used in the user interface.
 * Provides getters/setters and private readOptionsFromFile() to read in XML data and return as list.
 */

package com.JSF_Example;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@SuppressWarnings("deprecation")
@ManagedBean
public class FormOptions implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<String> genderOptions;
	private List<String> countryOptions;
	private List<String> languageOptions;
	
	public FormOptions() {
		// Read gender, country, and language options from the XML file
		genderOptions = readOptionsFromFile("form_options.xml", "gender");
		countryOptions = readOptionsFromFile("form_options.xml", "country");
		languageOptions = readOptionsFromFile("form_options.xml", "language");
	}
	
	public List<String> getGenderOptions() {
		return genderOptions;
	}

	public void setGenderOptions(List<String> genderOptions) {
		this.genderOptions = genderOptions;
	}

	public List<String> getCountryOptions() {
		return countryOptions;
	}

	public void setCountryOptions(List<String> countryOptions) {
		this.countryOptions = countryOptions;
	}

	public List<String> getLanguageOptions() {
		return languageOptions;
	}

	public void setLanguageOptions(List<String> languageOptions) {
		this.languageOptions = languageOptions;
	}
	
	// Read options from an XML file based on the provided file name and element name
	private List<String> readOptionsFromFile(String fileName, String elementName) {
	    List<String> elements = new ArrayList<>();

	    try {
	    	// Get the real path of the XML file
	        String filePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(fileName);
	        // Create a File object from the XML file path
	        File xmlFile = new File(filePath);
	        
	        // Create a DocumentBuilderFactory to parse the XML file
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        // Create a DocumentBuilder to parse the XML file
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        
	        // Parse the XML file and normalize the document
	        Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();
	        
	        // Get a list of elements with the provided element name
	        NodeList nodeList = doc.getElementsByTagName(elementName);
	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Element element = (Element) nodeList.item(i);
	            String countryName = element.getTextContent();
	            elements.add(countryName);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return elements;
	}
}
