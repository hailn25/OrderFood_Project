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
public class RestaurantDTO {

    private int restaurantId;
    private String name;
    private String address;
    private double rateStar;
    private String imageAvatar;
    private Date createDate;
    private String email;
    private String phone;
    private int quantityOfProduct;

    public RestaurantDTO() {
    }

    public RestaurantDTO(int restaurantId, String name, String address, double rateStar, String imageAvatar, Date createDate, String email, String phone, int quantityOfProduct) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.rateStar = rateStar;
        this.imageAvatar = imageAvatar;
        this.createDate = createDate;
        this.email = email;
        this.phone = phone;
        this.quantityOfProduct = quantityOfProduct;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    
    @Override
    public String toString() {
        return "RestaurantDTO{" + "restaurantId=" + restaurantId + ", name=" + name + ", address=" + address + ", rateStar=" + rateStar + ", imageAvatar=" + imageAvatar + ", createDate=" + createDate + ", email=" + email + ", phone=" + phone + ", quantityOfProduct=" + quantityOfProduct + '}';
    }
    
    
}
