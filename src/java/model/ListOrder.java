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
    private int accountId;
    private String name;
    private String email;
    private String phonel;
    private String address;
    private String paymentBy;
    private String paymentStatus;
    private Date createDate;
    private String note;
    private String nameP;
    private Double price;
    private int quantity;
    private Double totalMoney;

    public ListOrder() {
    }

    public ListOrder(int accountId, String name, String email, String phonel, String address, String paymentBy, String paymentStatus, Date createDate, String note, String nameP, Double price, int quantity, Double totalMoney) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.phonel = phonel;
        this.address = address;
        this.paymentBy = paymentBy;
        this.paymentStatus = paymentStatus;
        this.createDate = createDate;
        this.note = note;
        this.nameP = nameP;
        this.price = price;
        this.quantity = quantity;
        this.totalMoney = totalMoney;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public String getPhonel() {
        return phonel;
    }

    public void setPhonel(String phonel) {
        this.phonel = phonel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentBy() {
        return paymentBy;
    }

    public void setPaymentBy(String paymentBy) {
        this.paymentBy = paymentBy;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
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

    @Override
    public String toString() {
        return "ListOrder{" + "accountId=" + accountId + ", name=" + name + ", email=" + email + ", phonel=" + phonel + ", address=" + address + ", paymentBy=" + paymentBy + ", paymentStatus=" + paymentStatus + ", createDate=" + createDate + ", note=" + note + ", nameP=" + nameP + ", price=" + price + ", quantity=" + quantity + ", totalMoney=" + totalMoney + '}';
    }
    
    
}