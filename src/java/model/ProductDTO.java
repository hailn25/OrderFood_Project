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
public class ProductDTO {
    private int producId;
    private String name;
    private double price;
    private String description;
    private String imageURL;
    private int categoryId;
    private String restaurantImage;
    private boolean isSale;
    private int quantity;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(int producId, String name, double price, String description, String imageURL, int categoryId, String restaurantImage, boolean isSale, int quantity, Date createDate, Date updateDate, boolean status) {
        this.producId = producId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
        this.categoryId = categoryId;
        this.restaurantImage = restaurantImage;
        this.isSale = isSale;
        this.quantity = quantity;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public int getProducId() {
        return producId;
    }

    public void setProducId(int producId) {
        this.producId = producId;
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

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
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
        return "ProductDTO{" + "producId=" + producId + ", name=" + name + ", price=" + price + ", description=" + description + ", imageURL=" + imageURL + ", categoryId=" + categoryId + ", restaurantImage=" + restaurantImage + ", isSale=" + isSale + ", quantity=" + quantity + ", createDate=" + createDate + ", updateDate=" + updateDate + ", status=" + status + '}';
    }
    
    
    
}
