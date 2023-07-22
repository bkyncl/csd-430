/* PlantTableTag.java
 * Module 12 Assignment
 * Name: Brittany Kyncl
 * Date: 7.21.23
 * Course: CSD430
 * Custom JSP tag handler to generate HTML tables for displaying plant and categories table
 * Tag receives two lists of data: list of plant objects and list of category objects
 * Iterates through data and generates HTML <td> for each.
 * The resulting HTML tables are rendered on JSP page.
 */

package kyncl12_GardenManager.tags;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import kyncl12_GardenManager.bean.Category;
import kyncl12_GardenManager.bean.Plant;

public class PlantTableTag extends SimpleTagSupport {
	// Instance variables to hold the data
    private List<Plant> plants;
    private List<Category> categories;

    // Setters for the data
    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        // Generate HTML for the categories table
        if (categories != null && !categories.isEmpty()) {
            out.println("<div>");
            out.println("<table class=\"result-table\">");
            out.println("<tr>");
            out.println("<th colspan=\"3\">Plant Categories</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Category ID</th>");
            out.println("<th>Category Name</th>");
            out.println("<th>Plant Quantity</th>");
            out.println("</tr>");

            for (Category category : categories) {
                out.println("<tr>");
                out.println("<td>" + category.getCategoryId() + "</td>");
                out.println("<td>" + category.getCategoryName() + "</td>");
                out.println("<td>" + category.getPlantCount() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</div>");
        }

        // Generate HTML for the plants table
        if (plants != null && !plants.isEmpty()) {
            out.println("<div>");
            out.println("<table class=\"result-table\">");
            out.println("<tr>");
            out.println("<th colspan=\"9\">All Plants</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<th>Plant ID</th>");
            out.println("<th>Plant Name</th>");
            out.println("<th>Plant Description</th>");
            out.println("<th>Category</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Date Planted</th>");
            out.println("<th>Plot Number</th>");
            out.println("<th>Row</th>");
            out.println("<th>Climate</th>");
            out.println("</tr>");

            for (Plant plant : plants) {
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
        }
    }
}
