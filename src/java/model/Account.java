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
    private String name;
    private boolean gender;
    private String phone;
    private String address;
    private int loginWith;
    private String imageAvatar;
    private boolean status;
    private Date lastDateLogin;
    private Date createDate;
    private Date updateDate;
    private int roleId;

    public Account() {
    }

    public Account(int accountId, String email, String password, String name, boolean gender, String phone, String address, String imageAvatar, int loginWith, boolean status, Date lastDateLogin, Date createDate, Date updateDate, int roleId) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.imageAvatar = imageAvatar;
        this.loginWith = loginWith;
        this.status = status;
        this.lastDateLogin = lastDateLogin;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.roleId = roleId;
    }
     public Account(String email, String password, String name, boolean gender, String phone, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getLoginWith() {
        return loginWith;
    }

    public void setLoginWith(int loginWith) {
        this.loginWith = loginWith;
    }
    

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
