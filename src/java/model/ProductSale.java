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
public class ProductSale {
    private Product product;
    private Date startTime;
    private Date endTime;
    private double salePrice;
    private double discount;
    private boolean isFlashSale;
    private int quantity;
    private int timeFrame;
    private int updateBy;
    private Date createDate;

    public ProductSale() {
    }

    public ProductSale(Product product, Date startTime, Date endTime, double salePrice, double discount, boolean isFlashSale, int quantity, int timeFrame, int updateBy, Date createDate) {
        this.product = product;
 
        this.startTime = startTime;
        this.endTime = endTime;
        this.salePrice = salePrice;
        this.discount = discount;
        this.isFlashSale = isFlashSale;
        this.quantity = quantity;
        this.timeFrame = timeFrame;
        this.updateBy = updateBy;
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

   

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isIsFlashSale() {
        return isFlashSale;
    }

    public void setIsFlashSale(boolean isFlashSale) {
        this.isFlashSale = isFlashSale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ProductSale{" + "product=" + product + ", startTime=" + startTime + ", endTime=" + endTime + ", salePrice=" + salePrice + ", discount=" + discount + ", isFlashSale=" + isFlashSale + ", quantity=" + quantity + ", timeFrame=" + timeFrame + ", updateBy=" + updateBy + ", createDate=" + createDate + '}';
    }

   

    
    
}
