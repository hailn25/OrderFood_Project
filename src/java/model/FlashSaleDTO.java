/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author hailt
 */
public class FlashSaleDTO {
    private int productId;
    private String name;
    private int isFlashSale;
    private String imageURL;
    private int quantity;
    private double discount;
    private double salePrice;
    private double price;
    private int timeFrame;
    private Date date;
    private String description;

    public FlashSaleDTO() {
    }

    public FlashSaleDTO(int productId, String name, int isFlashSale, String imageURL, int quantity, double discount, double salePrice, double price, int timeFrame, Date date, String description) {
        this.productId = productId;
        this.name = name;
        this.isFlashSale = isFlashSale;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.discount = discount;
        this.salePrice = salePrice;
        this.price = price;
        this.timeFrame = timeFrame;
        this.date = date;
        this.description = description;
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

    public int getIsFlashSale() {
        return isFlashSale;
    }

    public void setIsFlashSale(int isFlashSale) {
        this.isFlashSale = isFlashSale;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String ImageURL) {
        this.imageURL = imageURL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FlashSaleDTO{" + "productId=" + productId + ", name=" + name + ", isFlashSale=" + isFlashSale + ", ImageURL=" + imageURL + ", quantity=" + quantity + ", discount=" + discount + ", salePrice=" + salePrice + ", price=" + price + ", timeFrame=" + timeFrame + ", date=" + date + ", description=" + description + '}';
    }

    
    
}
