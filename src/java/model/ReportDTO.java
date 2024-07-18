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
public class ReportDTO {
        private int reportId;
    private String description;
    private String imageURL;
    private Date createDate;
    private int accountId;
    private int restaurantId;
    private String statusName;
    private String accountName;
    private String restaurantName;
    
    public ReportDTO() {
    }
    
    public ReportDTO(int reportId, String description, String imageURL, Date createDate, String accountName, String restaurantName, String statusName){
        this.reportId = reportId;
        this.description = description;
        this.imageURL = imageURL;
        this.createDate = createDate;
        this.accountName = accountName;
        this.restaurantName = restaurantName;
        this.statusName = statusName;
    }

    public ReportDTO(int reportId, String description, String imageURL, Date createDate, int accountId, int restaurantId, String statusName) {
        this.reportId = reportId;
        this.description = description;
        this.imageURL = imageURL;
        this.createDate = createDate;
        this.accountId = accountId;
        this.restaurantId = restaurantId;
        this.statusName = statusName;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return "ReportDTO{" + "reportId=" + reportId + ", description=" + description + ", imageURL=" + imageURL + ", createDate=" + createDate + ", accountId=" + accountId + ", restaurantId=" + restaurantId + ", statusName=" + statusName + '}';
    }
    
    
}
