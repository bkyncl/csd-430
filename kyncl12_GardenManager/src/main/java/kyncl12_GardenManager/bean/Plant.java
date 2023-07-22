/* Plant.java
 * Module 12 Assignment
 * Name: Brittany Kyncl
 * Date: 7.21.23
 * Course: CSD430
 * Java Bean class represents plant object of the the plants table data model of the applications database
 * used within this applications context as a data structure
 * with which to store and manipulate information related to a plant from the garden database
 */
package kyncl12_GardenManager.bean;

import java.sql.Date;

public class Plant implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int plantId;
    private String plantName;
    private String plantDescription;
    private int quantity;
    private Date datePlanted;
    private int plotNumber;
    private String plotRow;
    private String nativeClimate;
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

    public Date getDatePlanted() {
		return datePlanted;
	}

	public void setDatePlanted(Date datePlanted) {
		this.datePlanted = datePlanted;
	}

	public int getPlotNumber() {
		return plotNumber;
	}

	public void setPlotNumber(int plotNumber) {
		this.plotNumber = plotNumber;
	}

	public String getPlotRow() {
		return plotRow;
	}

	public void setPlotRow(String plotRow) {
		this.plotRow = plotRow;
	}

	public String getNativeClimate() {
		return nativeClimate;
	}

	public void setNativeClimate(String nativeClimate) {
		this.nativeClimate = nativeClimate;
	}

	public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
