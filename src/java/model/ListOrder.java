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
public class ListOrder {
    private int orderId;
    private int orderStatusId;
    private int accountId;
    private String shipperName;
    private double totalMoney;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String note;
    private Date createDate;
    private Date finishDate;

    public ListOrder() {
    }

    public ListOrder(int orderId, int orderStatusId, int accountId, String shipperName, double totalMoney, String name, String email, String phone, String address, String note, Date createDate, Date finishDate) {
        this.orderId = orderId;
        this.orderStatusId = orderStatusId;
        this.accountId = accountId;
        this.shipperName = shipperName;
        this.totalMoney = totalMoney;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.createDate = createDate;
        this.finishDate = finishDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "ListOrder{" + "orderId=" + orderId + ", orderStatusId=" + orderStatusId + ", accountId=" + accountId + ", shipperName=" + shipperName + ", totalMoney=" + totalMoney + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", note=" + note + ", createDate=" + createDate + ", finishDate=" + finishDate + '}';
    }

    
}
