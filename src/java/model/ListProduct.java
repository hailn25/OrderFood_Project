/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ListProduct {
    private int id;
    private String name;
    private String decription;
    private double price;
    private String image;
    private String cateName;
    private int restaurantld;
    private boolean isSale;
    private int quantity;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public ListProduct() {
    }

    public ListProduct(int id, String name, String decription, double price, String image, String cateName, int restaurantld, boolean isSale, int quantity, Date createDate, Date updateDate, boolean status) {
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.price = price;
        this.image = image;
        this.cateName = cateName;
        this.restaurantld = restaurantld;
        this.isSale = isSale;
        this.quantity = quantity;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getRestaurantld() {
        return restaurantld;
    }

    public void setRestaurantld(int restaurantld) {
        this.restaurantld = restaurantld;
    }

    public boolean isIsSale() {
        return isSale;
    }

    public void setIsSale(boolean isSale) {
        this.isSale = isSale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ListProduct{" + "id=" + id + ", name=" + name + ", decription=" + decription + ", price=" + price + ", image=" + image + ", cateName=" + cateName + ", restaurantld=" + restaurantld + ", isSale=" + isSale + ", quantity=" + quantity + ", createDate=" + createDate + ", updateDate=" + updateDate + ", status=" + status + '}';
    }

    
}
