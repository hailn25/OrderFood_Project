/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalTime;

/**
 *
 * @author quoch
 */
public class RestaurantName {
    private String restaurantName;
    private String restaurantImageURL;
    private int restaurantId;

    public RestaurantName() {
    }

    public RestaurantName(String restaurantName, String restaurantImageURL, int restaurantId) {
        this.restaurantName = restaurantName;
        this.restaurantImageURL = restaurantImageURL;
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantImageURL() {
        return restaurantImageURL;
    }

    public void setRestaurantImageURL(String restaurantImageURL) {
        this.restaurantImageURL = restaurantImageURL;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "RestaurantName{" + "restaurantName=" + restaurantName + ", restaurantImageURL=" + restaurantImageURL + ", restaurantId=" + restaurantId + '}';
    }

    
    
}
