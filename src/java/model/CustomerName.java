/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author hailt
 */
public class CustomerName {

    private String customerName;
    private String imageCustomer;
    private int userId;

    public CustomerName() {
    }

    public CustomerName(String customerName, String imageCustomer, int userId) {
        this.customerName = customerName;
        this.imageCustomer = imageCustomer;
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getImageCustomer() {
        return imageCustomer;
    }

    public void setImageCustomer(String imageCustomer) {
        this.imageCustomer = imageCustomer;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CustomerName{" + "customerName=" + customerName + ", imageCustomer=" + imageCustomer + ", userId=" + userId + '}';
    }
    

}
