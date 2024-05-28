/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ProductSale {
    private int id;
    private String name;
    private String image;
    private double salePrice;
    private double discount;
    private int quantity;
    private int rateStar;

    public ProductSale() {
    }

    public ProductSale(int id, String name, String image, double salePrice, double discount, int quantity, int rateStar) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.salePrice = salePrice;
        this.discount = discount;
        this.quantity = quantity;
        this.rateStar = rateStar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRateStar() {
        return rateStar;
    }

    public void setRateStar(int rateStar) {
        this.rateStar = rateStar;
    }

    @Override
    public String toString() {
        return "ProductSale{" + "id=" + id + ", name=" + name + ", image=" + image + ", salePrice=" + salePrice + ", discount=" + discount + ", quantity=" + quantity + ", rateStar=" + rateStar + '}';
    }

    
    
}
