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
public class Restaurant {
    private int restaurantId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private double rateStar;
    private Date dateMenuUpdate;
    private int accountId;

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String name, String email, String phone, String address, double rateStar, Date dateMenuUpdate, int accountId) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.rateStar = rateStar;
        this.dateMenuUpdate = dateMenuUpdate;
        this.accountId = accountId;
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

    public Date getDateMenuUpdate() {
        return dateMenuUpdate;
    }

    public void setDateMenuUpdate(Date dateMenuUpdate) {
        this.dateMenuUpdate = dateMenuUpdate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "restaurantId=" + restaurantId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", rateStar=" + rateStar + ", dateMenuUpdate=" + dateMenuUpdate + ", accountId=" + accountId + '}';
    }
    
    
}
