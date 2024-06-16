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
public class Feedback {
    private int productId;
    private String name;
    private double price;
    private String description;
    private String imageURL;
    private int categoryId;
    private int restaurantId;
    private int feedbackId;
    private String feedback;
    private Date date;
    private double rateStar;
    private String imageAvatar;
    private String nameAccount;

    public Feedback() {
    }

    public Feedback(int productId, String name, double price, String description, String imageURL, int categoryId, int restaurantId, int feedbackId, String feedback, Date date, double rateStar, String imageAvatar, String nameAccount) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
        this.feedbackId = feedbackId;
        this.feedback = feedback;
        this.date = date;
        this.rateStar = rateStar;
        this.imageAvatar = imageAvatar;
        this.nameAccount = nameAccount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getRateStar() {
        return rateStar;
    }

    public void setRateStar(double rateStar) {
        this.rateStar = rateStar;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    @Override
    public String toString() {
        return "Feedback{" + "productId=" + productId + ", name=" + name + ", price=" + price + ", description=" + description + ", imageURL=" + imageURL + ", categoryId=" + categoryId + ", restaurantId=" + restaurantId + ", feedbackId=" + feedbackId + ", feedback=" + feedback + ", date=" + date + ", rateStar=" + rateStar + ", imageAvatar=" + imageAvatar + ", nameAccount=" + nameAccount + '}';
    }

}
