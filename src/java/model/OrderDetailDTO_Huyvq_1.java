/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Vu Huy
 */
public class OrderDetailDTO_Huyvq_1 {

    int orderDetail;
    int orderId;
    int productId;
    String productName;
    Double price;
    int quantity;
    Double totalMoney;

    public OrderDetailDTO_Huyvq_1() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(int orderDetail) {
        this.orderDetail = orderDetail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public OrderDetailDTO_Huyvq_1(int orderDetail, int orderId, int productId, String productName, Double price, int quantity, Double totalMoney) {
        this.orderDetail = orderDetail;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
    }

    

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderDetail=" + orderDetail + ", orderId=" + orderId + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + ", totalMoney=" + totalMoney + '}';
    }

}
