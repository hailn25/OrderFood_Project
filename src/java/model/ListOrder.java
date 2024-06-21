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

    int accountId;
    String accountName;
    String email;
    String phone;
    String address;
    String paymentBy;
    String paymantStatus;
    Date createDate;
    String note;
    String ProductName;
    double price;
    int quantity;
    double totalMoney;

    public ListOrder() {
    }

    public ListOrder(int accountId, String accountName, String email, String phone, String address, String paymentBy, String paymantStatus, Date createDate, String note, String ProductName, double price, int quantity, double totalMoney) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.paymentBy = paymentBy;
        this.paymantStatus = paymantStatus;
        this.createDate = createDate;
        this.note = note;
        this.ProductName = ProductName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getPaymentBy() {
        return paymentBy;
    }

    public void setPaymentBy(String paymentBy) {
        this.paymentBy = paymentBy;
    }

    public String getPaymantStatus() {
        return paymantStatus;
    }

    public void setPaymantStatus(String paymantStatus) {
        this.paymantStatus = paymantStatus;
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

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "ListOrder{" + "accountId=" + accountId + ", accountName=" + accountName + ", email=" + email + ", phone=" + phone + ", address=" + address + ", paymentBy=" + paymentBy + ", paymantStatus=" + paymantStatus + ", createDate=" + createDate + ", note=" + note + ", ProductName=" + ProductName + ", price=" + price + ", quantity=" + quantity + ", totalMoney=" + totalMoney + '}';
    }
    
    

}
