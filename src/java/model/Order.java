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
public class Order {
    private int oderId;
    private int oderStatusId;
    private int accountId;
    private int shipperId;
    private double totalMoney;  
    private String name;
    private String email;
    private String phone;
    private String address;
    private String note;
    private Date createDate;
    private Date finishDate;

    public Order() {
    }

    public Order(int oderId, int oderStatusId, int accountId, int shipperId, double totalMoney, String name, String email, String phone, String address, String note, Date createDate, Date finishDate) {
        this.oderId = oderId;
        this.oderStatusId = oderStatusId;
        this.accountId = accountId;
        this.shipperId = shipperId;
        this.totalMoney = totalMoney;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.createDate = createDate;
        this.finishDate = finishDate;
    }

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public int getOderStatusId() {
        return oderStatusId;
    }

    public void setOderStatusId(int oderStatusId) {
        this.oderStatusId = oderStatusId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
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
        return "Oder{" + "oderId=" + oderId + ", oderStatusId=" + oderStatusId + ", accountId=" + accountId + ", shipperId=" + shipperId + ", totalMoney=" + totalMoney + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", note=" + note + ", createDate=" + createDate + ", finishDate=" + finishDate + '}';
    }

    public String getPaymentBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isPaymentStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
  
}
