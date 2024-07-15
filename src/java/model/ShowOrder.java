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
public class ShowOrder {
    private int accountId;
    private int orderId;
    private String name;
    private String address;
    private String note;
    private Date createDate;
    private Double totalMoney;
    private String status;

    public ShowOrder() {
    }

    public ShowOrder(int accountId, int orderId, String name, String address, String note, Date createDate, Double totalMoney, String status) {
        this.accountId = accountId;
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.note = note;
        this.createDate = createDate;
        this.totalMoney = totalMoney;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShowOrder{" + "accountId=" + accountId + ", orderId=" + orderId + ", name=" + name + ", address=" + address + ", note=" + note + ", createDate=" + createDate + ", totalMoney=" + totalMoney + ", status=" + status + '}';
    }

}
