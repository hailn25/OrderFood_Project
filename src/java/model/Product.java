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
public class Product {

    private int productId;
    private String name;
    private double price;
    private String description;
    private String imageURL;
    private int categoryId;
    private int restaurantId;
    private boolean isSale;
    private int quantity;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public Product() {
    }

    public Product(int productId, String name, double price, String description, String imageURL, int categoryId, int restaurantId, boolean isSale, int quantity, Date createDate, Date updateDate, boolean status) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
        this.isSale = isSale;
        this.quantity = quantity;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
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

}
