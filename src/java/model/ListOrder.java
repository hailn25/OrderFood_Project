/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author ADMIN
 */
public class ListOrder {
    private int accountId;
    private int orderId;
    private int productId;
    private String productName;
    private double price;
    private String imageURL;
    private String restaurant;
    private String accountName;
    private String phone;
    private String address;
    private String note;
    private int quantity;
    private double totalMoney;
    private int orderStatusId;
    private String status;

    public ListOrder() {
    }

    public ListOrder(int accountId, int orderId, int productId, String productName, double price, String imageURL, String restaurant, String accountName, String phone, String address, String note, int quantity, double totalMoney, int orderStatusId, String status) {
        this.accountId = accountId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.imageURL = imageURL;
        this.restaurant = restaurant;
        this.accountName = accountName;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
        this.orderStatusId = orderStatusId;
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ListOrder{" + "accountId=" + accountId + ", orderId=" + orderId + ", productId=" + productId + ", productName=" + productName + ", price=" + price + ", imageURL=" + imageURL + ", restaurant=" + restaurant + ", accountName=" + accountName + ", phone=" + phone + ", address=" + address + ", note=" + note + ", quantity=" + quantity + ", totalMoney=" + totalMoney + ", orderStatusId=" + orderStatusId + ", status=" + status + '}';
    }
    
}
