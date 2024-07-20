/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class OrderDetailProfile {
    private int accountId;
    private int orderDetailId;
    private int orderId;
    private int productId;
    private String productName;
    private double price;
    private String imageURL;
    private int orderStatus;
    private int quantity;
    private double totalPrice;

    public OrderDetailProfile() {
    }

    public OrderDetailProfile(int accountId, int orderDetailId, int orderId, int productId, String productName, double price, String imageURL, int orderStatus, int quantity, double totalPrice) {
        this.accountId = accountId;
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.imageURL = imageURL;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailProfile{" + "accountId=" + accountId + ", orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productId=" + productId + ", productName=" + productName + ", price=" + price + ", imageURL=" + imageURL + ", orderStatus=" + orderStatus + ", quantity=" + quantity + ", totalPrice=" + totalPrice + '}';
    }

    
}
