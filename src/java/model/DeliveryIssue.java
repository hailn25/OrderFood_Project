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
public class DeliveryIssue {
   private int orderId;
   private String name;
   private String phone;
   private String address;
   private Date createDate;
   private double totalMoney;
   private String status;
   private String issueDescription;
   private Date issueDate;

    public DeliveryIssue() {
    }

    public DeliveryIssue(int orderId, String name, String phone, String address, Date createDate, double totalMoney, String status, String issueDescription, Date issueDate) {
        this.orderId = orderId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.totalMoney = totalMoney;
        this.status = status;
        this.issueDescription = issueDescription;
        this.issueDate = issueDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "DeliveryIssue{" + "orderId=" + orderId + ", name=" + name + ", phone=" + phone + ", address=" + address + ", createDate=" + createDate + ", totalMoney=" + totalMoney + ", status=" + status + ", issueDescription=" + issueDescription + ", issueDate=" + issueDate + '}';
    }

}