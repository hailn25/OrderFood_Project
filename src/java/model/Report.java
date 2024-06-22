/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author quoch
 */
public class Report {
    private int reportId;
    private String description;
    private String imageURL;
    private Date createDate;
    private int accountId;
    private int restaurantId;
    private int status;

    public Report() {
    }

    public Report(int reportId, String description, String imageURL, Date createDate, int accountId, int restaurantId, int status) {
        this.reportId = reportId;
        this.description = description;
        this.imageURL = imageURL;
        this.createDate = createDate;
        this.accountId = accountId;
        this.restaurantId = restaurantId;
        this.status = status;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", description=" + description + ", imageURL=" + imageURL + ", createDate=" + createDate + ", accountId=" + accountId + ", restaurantId=" + restaurantId + ", status=" + status + '}';
    }
    
    
}
