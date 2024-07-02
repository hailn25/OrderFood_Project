/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hailt
 */
public class ProductSaleDTO1 {
    private int productId;
    private String name;
    private boolean isFlashSale;
    private String ImageURL;
    private int quantity;
    private double discount;
    private double salePrice;
    private double price;
    private int timeFrame;

    public ProductSaleDTO1() {
    }

    public ProductSaleDTO1(int productId, String name, boolean isFlashSale, String ImageURL, int quantity, double discount, double salePrice, double price, int timeFrame) {
        this.productId = productId;
        this.name = name;
        this.isFlashSale = isFlashSale;
        this.ImageURL = ImageURL;
        this.quantity = quantity;
        this.discount = discount;
        this.salePrice = salePrice;
        this.price = price;
        this.timeFrame = timeFrame;
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

    public boolean isIsFlashSale() {
        return isFlashSale;
    }

    public void setIsFlashSale(boolean isFlashSale) {
        this.isFlashSale = isFlashSale;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    @Override
    public String toString() {
        return "ProductSaleDTO1{" + "productId=" + productId + ", name=" + name + ", isFlashSale=" + isFlashSale + ", ImageURL=" + ImageURL + ", quantity=" + quantity + ", discount=" + discount + ", salePrice=" + salePrice + ", price=" + price + ", timeFrame=" + timeFrame + '}';
    }
    
}
