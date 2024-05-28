/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Vu Huy
 */
public class Account {
    private int accountId;
    private String email;
    private String password;
    private String fullName;
    private boolean gender;
    private String phone;
    private String address;
    private String imageAvatar;
    private boolean status;
    private Date lastDateLogin;
    private Date createDate;
    private Date updateDate;
    private int roleId;
    
    public Account() {
    }
    
    public Account(int accountId, String email, String password, String fullName, boolean gender, String phone, String address, String imageAvatar, boolean status, Date lastDateLogin, Date createDate, Date updateDate, int roleId) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.imageAvatar = imageAvatar;
        this.status = status;
        this.lastDateLogin = lastDateLogin;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.roleId = roleId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getLastDateLogin() {
        return lastDateLogin;
    }

    public void setLastDateLogin(Date lastDateLogin) {
        this.lastDateLogin = lastDateLogin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", email=" + email + ", password=" + password + ", fullName=" + fullName + ", gender=" + gender + ", phone=" + phone + ", address=" + address + ", imageAvatar=" + imageAvatar + ", status=" + status + ", lastDateLogin=" + lastDateLogin + ", createDate=" + createDate + ", updateDate=" + updateDate + ", roleId=" + roleId + '}';
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
