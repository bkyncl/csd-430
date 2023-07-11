/* Plant.java
 * Module 8 Assignment
 * Name: Brittany Kyncl
 * Date: 7.4.22
 * Course: CSD430
 * Java Bean class represents plant object of the the plants table data model of the applications database
 * used within this applications context as a data structure
 * with which to store and manipulate information related to a plant from the garden database
 */
package kyncl_08.bean;

public class Plant implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int plantId;
    private String plantName;
    private String plantDescription;
    private int quantity;
    private int categoryId;
    

    // Getters and setters
    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
