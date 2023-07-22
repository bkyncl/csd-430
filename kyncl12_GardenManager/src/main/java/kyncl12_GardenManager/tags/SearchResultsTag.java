/* SearchResultsTag.java
 * Module 12 Assignment
 * Name: Brittany Kyncl
 * Date: 7.21.23
 * Course: CSD430
 * Custom JSP tag handler to generate HTML table for displaying search results of plants
 * tag receives two lists of data: a list of Plant objects representing search results 
 * and a list of Category objects.
 * If no matching plants are found, a message indicating that no results were found is displayed.
 */
package kyncl12_GardenManager.tags;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import kyncl12_GardenManager.bean.Category;
import kyncl12_GardenManager.bean.Plant;

public class SearchResultsTag extends SimpleTagSupport {
    // Instance variables to hold the data
    private List<Plant> searchResults;
    private List<Category> categories;

    // Setters for the data
    public void setSearchResults(List<Plant> searchResults) {
        this.searchResults = searchResults;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        // Generate HTML for the search results table
        if (searchResults != null && !searchResults.isEmpty()) {
        	out.println("<div class=\"search-container\">");
            out.println("<table class=\"result-table\">");
            out.println("<tr>");
            out.println("<th colspan=\"9\">Search Results</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Plant ID</th>");
            out.println("<th>Plant Name</th>");
            out.println("<th>Plant Description</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Plant Category</th>");
            out.println("<th>Date Planted</th>");
            out.println("<th>Plot Number</th>");
            out.println("<th>Row</th>");
            out.println("<th>Climate</th>");
            out.println("</tr>");

            for (Plant plant : searchResults) {
                out.println("<tr>");
                out.println("<td>" + plant.getPlantId() + "</td>");
                out.println("<td>" + plant.getPlantName() + "</td>");
                out.println("<td>" + plant.getPlantDescription() + "</td>");
                out.println("<td>" + plant.getQuantity() + "</td>");
                for (Category category : categories) {
                    if (category.getCategoryId() == plant.getCategoryId()) {
                        out.println("<td>" + category.getCategoryName() + "</td>");
                    }
                }
                out.println("<td>" + plant.getDatePlanted() + "</td>");
                out.println("<td>" + plant.getPlotNumber() + "</td>");
                out.println("<td>" + plant.getPlotRow() + "</td>");
                out.println("<td>" + plant.getNativeClimate() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div>");
        } else {
            out.println("<p>No matching plants found.</p>");
        }
    }
}