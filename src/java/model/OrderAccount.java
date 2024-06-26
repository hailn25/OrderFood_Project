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
public class OrderAccount {
    private int accountId;
    private int orderId;
    private String name;
    private String phone;
    private String address;
    private String note;
    private Date createDate;
    private double totalMoney;
    private int status;

    public OrderAccount(int accountId, int orderId, String name, String phone, String address, String note, Date createDate, double totalMoney, int status) {
        this.accountId = accountId;
        this.orderId = orderId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.createDate = createDate;
        this.totalMoney = totalMoney;
        this.status = status;
    }

    public OrderAccount() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderAccount{" + "accountId=" + accountId + ", orderId=" + orderId + ", name=" + name + ", phone=" + phone + ", address=" + address + ", note=" + note + ", createDate=" + createDate + ", totalMoney=" + totalMoney + ", status=" + status + '}';
    }
    
    
}
