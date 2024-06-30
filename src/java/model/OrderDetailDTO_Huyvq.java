/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vu Huy
 */
public class OrderDetailDTO_Huyvq {
    private int orderDetailId;
    private int orderId;
    private String name;
    private int quantity;
    private double totalMoney;
    private int orderStatusId;
    private String imageURL;

    public OrderDetailDTO_Huyvq() {
    }

    public OrderDetailDTO_Huyvq(int orderDetailId, String name, int quantity, double totalMoney, int orderStatusId, String imageURL) {
        this.orderDetailId = orderDetailId;
        this.name = name;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
        this.orderStatusId = orderStatusId;
        this.imageURL = imageURL;
    }
    
    public OrderDetailDTO_Huyvq(int orderId, String name, double totalMoney, int orderStatusId) {
        this.orderId = orderId;
        this.name = name;
        this.totalMoney = totalMoney;
        this.orderStatusId = orderStatusId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    
    
}
