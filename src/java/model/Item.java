/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Item {

    private Product product;
    private int quantity;
    private double price;
    private double discountedPrice;

    public Item() {
    }

    public Item(Product product, int quantity, double price, double discountedPrice) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String toString() {
        return "Item{" + "product=" + product + ", quantity=" + quantity + ", price=" + price + ", discountedPrice=" + discountedPrice + '}';
    }

  
}