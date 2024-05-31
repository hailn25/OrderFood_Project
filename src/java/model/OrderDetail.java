/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class OrderDetail {
    private int oderDetailId;
    private int oderId;
    private int productId;
    private int quantity;
    private double totalMoney;
    private String paymentBy;
    private boolean paymentStatus;
    private Date updateDate;

    public OrderDetail() {
    }

    public int getOderDetailId() {
        return oderDetailId;
    }

    public void setOderDetailId(int oderDetailId) {
        this.oderDetailId = oderDetailId;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getPaymentBy() {
        return paymentBy;
    }

    public void setPaymentBy(String paymentBy) {
        this.paymentBy = paymentBy;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public OrderDetail(int oderDetailId, int oderId, int productId, int quantity, double totalMoney, String paymentBy, boolean paymentStatus, Date updateDate) {
        this.oderDetailId = oderDetailId;
        this.oderId = oderId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
        this.paymentBy = paymentBy;
        this.paymentStatus = paymentStatus;
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "OderDetail{" + "oderDetailId=" + oderDetailId + ", oderId=" + oderId + ", productId=" + productId + ", quantity=" + quantity + ", totalMoney=" + totalMoney + ", paymentBy=" + paymentBy + ", paymentStatus=" + paymentStatus + ", updateDate=" + updateDate + '}';
    }

   
}
