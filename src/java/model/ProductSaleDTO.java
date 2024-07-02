/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hailt
 */
public class ProductSaleDTO {
        private int productId;
        private String name;
        private int quantity;
        private double salePrice;
        private double discount;
        private String imageURL;
        private double price;
        private int timeFrame;

    public ProductSaleDTO() {
    }

    public ProductSaleDTO(int productId, String name, int quantity, double salePrice, double discount, String imageURL, double price, int timeFrame) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.discount = discount;
        this.imageURL = imageURL;
        this.price = price;
        this.timeFrame = timeFrame;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "ProductSaleDTO{" + "productId=" + productId + ", name=" + name + ", quantity=" + quantity + ", salePrice=" + salePrice + ", discount=" + discount + ", imageURL=" + imageURL + ", price=" + price + ", timeFrame=" + timeFrame + '}';
    }

    

}
