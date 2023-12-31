/* Category.java
 * Module 12 Assignment
 * Name: Brittany Kyncl
 * Date: 7.21.23
 * Course: CSD430
 * Java Bean class represents category object of the the plant_categogry table data model of the applications database
 * used within this applications context as a data structure
 * with which to store and manipulate information related to plant categories from the garden database
 */
package kyncl12_GardenManager.bean;

public class Category implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int categoryId;
    private String categoryName;
    private int plantCount;

    // Getters and setters
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public int getPlantCount() {
        return plantCount;
    }
    
    public void setPlantCount(int plantCount) {
        this.plantCount = plantCount;
    }

}
