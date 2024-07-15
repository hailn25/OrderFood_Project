/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author quoch
 */
public class ReportDTO_1 {
    private String restaurantName;
    private String description;
    private String imageURL;
    private Date createDate;

    public ReportDTO_1() {
    }

    public ReportDTO_1(String restaurantName, String description, String ImageURL, Date createDate) {
        this.restaurantName = restaurantName;
        this.description = description;
        this.imageURL = ImageURL;
        this.createDate = createDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String ImageURL) {
        this.imageURL = ImageURL;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ReportDTO{" + "restaurantName=" + restaurantName + ", description=" + description + ", ImageURL=" + imageURL + ", createDate=" + createDate + '}';
    }
    
}
