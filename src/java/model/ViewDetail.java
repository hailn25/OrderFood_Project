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
public class ViewDetail {
    int orderId;
   String name;
   String email;
   String phone;
   String address;
   String note;
   Date createDate;
   String paymentBy;
   String paymentStatus;

    public ViewDetail() {
    }

    public ViewDetail(int orderId, String name, String email, String phone, String address, String note, Date createDate, String paymentBy, String paymentStatus) {
        this.orderId = orderId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.createDate = createDate;
        this.paymentBy = paymentBy;
        this.paymentStatus = paymentStatus;
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

    @Override
    public String toString() {
        return "ViewDetail{" + "orderId=" + orderId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", note=" + note + ", createDate=" + createDate + ", paymentBy=" + paymentBy + ", paymentStatus=" + paymentStatus + '}';
    }
  

    
   
}
