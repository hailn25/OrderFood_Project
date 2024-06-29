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

    public ProductSaleDTO() {
    }

    public ProductSaleDTO(int productId, String name, int quantity, double salePrice, double discount, String imageURL) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.discount = discount;
        this.imageURL = imageURL;
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
        return "ProductSaleDTO{" + "productId=" + productId + ", name=" + name + ", quantity=" + quantity + ", salePrice=" + salePrice + ", discount=" + discount + ", imageURL=" + imageURL + '}';
    }
        
}
