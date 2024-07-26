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
public class ProductFlashSaleDTO {

    private int productId;
    private String productName;
    private int isFlashSale;
    private double salePrice;
    private String imageURL;
    private int quantity;
    private double discount;
    private int timeFrame;
    private Date date;

    public ProductFlashSaleDTO() {
    }

    public ProductFlashSaleDTO(int productId, String productName, int isFlashSale, double salePrice, String imageURL, int quantity, double discount, int timeFrame, Date date) {
        this.productId = productId;
        this.productName = productName;
        this.isFlashSale = isFlashSale;
        this.salePrice = salePrice;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.discount = discount;
        this.timeFrame = timeFrame;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
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

    public void setImageURL(String imageURL) {
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

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    @Override
    public String toString() {
        return "ProductFlashSaleDTO{" + "productId=" + productId + ", productName=" + productName + ", isFlashSale=" + isFlashSale + ", salePrice=" + salePrice + ", imageURL=" + imageURL + ", quantity=" + quantity + ", discount=" + discount + ", timeFrame=" + timeFrame + ", date=" + date + '}';
    }

}
