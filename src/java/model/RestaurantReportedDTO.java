/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vu Huy
 */
public class RestaurantReportedDTO {
    
    private int restaurantId;
    private String restaurantName;
    private String imageAvatar;
    private double rateStar;
    private boolean status;

    public RestaurantReportedDTO() {
    }

    public RestaurantReportedDTO(int restaurantId, String restaurantName, String imageAvatar, double rateStar, boolean status) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.imageAvatar = imageAvatar;
        this.rateStar = rateStar;
        this.status = status;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public double getRateStar() {
        return rateStar;
    }

    public void setRateStar(double rateStar) {
        this.rateStar = rateStar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RestaurantReportedDTO{" + "restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", imageAvatar=" + imageAvatar + ", rateStar=" + rateStar + ", status=" + status + '}';
    }

}
