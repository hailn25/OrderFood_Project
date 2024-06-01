/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quoch
 */
public class RestaurantDTO {
    private String name;
    private String address;
    private double rateStar;
    private String imageAvatar;

    public RestaurantDTO() {
    }

    public RestaurantDTO(String name, String address, double rateStar, String imageAvatar) {
        this.name = name;
        this.address = address;
        this.rateStar = rateStar;
        this.imageAvatar = imageAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "RestaurantDTO{" + "name=" + name + ", address=" + address + ", rateStar=" + rateStar + ", imageAvatar=" + imageAvatar + '}';
    }
    

}
