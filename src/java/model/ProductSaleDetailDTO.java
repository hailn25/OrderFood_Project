/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hailt
 */
public class ProductSaleDetailDTO {
    private int productId;
    private String name;
    private String description;
    private String imageURL;
    private int quantity;
    private double discount;
    private double salePrice;
    private double price;
    public ProductSaleDetailDTO() {
    }

    public ProductSaleDetailDTO(int productId, String name, String description, String imageURL, int quantity, double discount, double salePrice, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.discount = discount;
        this.salePrice = salePrice;
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

    @Override
    public String toString() {
        return "ProductSaleDetailDTO{" + "productId=" + productId + ", name=" + name + ", description=" + description + ", imageURL=" + imageURL + ", quantity=" + quantity + ", discount=" + discount + ", salePrice=" + salePrice + ", price=" + price + '}';
    }

    

    
    
    
}
