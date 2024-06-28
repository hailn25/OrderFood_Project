/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quoch
 */
public class RestaurantDTO {

    private int restaurantId;
    private String name;
    private String address;
    private double rateStar;
    private String imageAvatar;

    public RestaurantDTO() {
    }

    public RestaurantDTO(int restaurantId, String name, String address, double rateStar, String imageAvatar) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.rateStar = rateStar;
        this.imageAvatar = imageAvatar;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRateStar() {
        return rateStar;
    }

    public void setRateStar(double rateStar) {
        this.rateStar = rateStar;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" + "restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", rateStar=" + rateStar + ", imageAvatar=" + imageAvatar + '}';
    }

    
}
