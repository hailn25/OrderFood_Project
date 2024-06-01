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
public class ProductHome {
    private int id;
    private String name;
    private double price;
    private String decription;
    private String image;
    private String categoryName;
    private int categoryId;
    private String restaurantName;
    private int restaurantId;
    private boolean isSale;
    private int quantity;
    private Date createDate;
    private Date updateDate;
    private boolean status;
    private double rateStar;

    public ProductHome() {
    }

    public ProductHome(int id, String name, double price, String decription, String image, String categoryName, int categoryId, String restaurantName, int restaurantId, boolean isSale, int quantity, Date createDate, Date updateDate, boolean status, double rateStar) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.decription = decription;
        this.image = image;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.isSale = isSale;
        this.quantity = quantity;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.rateStar = rateStar;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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

    public double getRateStar() {
        return rateStar;
    }

    public void setRateStar(double rateStar) {
        this.rateStar = rateStar;
    }

    @Override
    public String toString() {
        return "ProductHome{" + "id=" + id + ", name=" + name + ", price=" + price + ", decription=" + decription + ", image=" + image + ", categoryName=" + categoryName + ", categoryId=" + categoryId + ", restaurantName=" + restaurantName + ", restaurantId=" + restaurantId + ", isSale=" + isSale + ", quantity=" + quantity + ", createDate=" + createDate + ", updateDate=" + updateDate + ", status=" + status + ", rateStar=" + rateStar + '}';
    }
}
