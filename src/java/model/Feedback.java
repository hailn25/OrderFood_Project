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
    private int id;
    private double rateStar;
    private String feedback;
    private String image;
    private String name;
    private String avatar;
    private String nameProduct;
    private Date date;

    public Feedback() {
    }

    public Feedback(int id, double rateStar, String feedback, String image, String name, String avatar, String nameProduct, Date date) {
        this.id = id;
        this.rateStar = rateStar;
        this.feedback = feedback;
        this.image = image;
        this.name = name;
        this.avatar = avatar;
        this.nameProduct = nameProduct;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRateStar() {
        return rateStar;
    }

    public void setRateStar(double rateStar) {
        this.rateStar = rateStar;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", rateStar=" + rateStar + ", feedback=" + feedback + ", image=" + image + ", name=" + name + ", avatar=" + avatar + ", nameProduct=" + nameProduct + ", date=" + date + '}';
    }
    
}
