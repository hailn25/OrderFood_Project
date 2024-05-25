/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Product {
    private int productId;
    private String name;
    private double price;
    private String description;
    private String image;
    private int categoryId;
    private int restaurentId;
    private int quantity;
    private boolean status;
    private String address;

    public Product() {
    }

    public Product(int productId, String name, double price, String description, String image, int categoryId, int restaurentId, int quantity, boolean status, String address) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
        this.restaurentId = restaurentId;
        this.quantity = quantity;
        this.status = status;
        this.address = address;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRestaurentId() {
        return restaurentId;
    }

    public void setRestaurentId(int restaurentId) {
        this.restaurentId = restaurentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", price=" + price + ", description=" + description + ", image=" + image + ", categoryId=" + categoryId + ", restaurentId=" + restaurentId + ", quantity=" + quantity + ", status=" + status + ", address=" + address + '}';
    }

    
}
