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
public class Voucher {
    private int voucherId;
    private String voucherName;
    private String description;
    private int quantity;
    private Date releaseDate;
    private Date finishDate;
    private int status;
    private float discount;
    private int voucherCategoryId;

    public Voucher() {
    }

    public Voucher(int voucherId, String voucherName, String description, int quantity, Date releaseDate, Date finishDate, int status, float discount, int voucherCategoryId) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.description = description;
        this.quantity = quantity;
        this.releaseDate = releaseDate;
        this.finishDate = finishDate;
        this.status = status;
        this.discount = discount;
        this.voucherCategoryId = voucherCategoryId;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getVoucherCategoryId() {
        return voucherCategoryId;
    }

    public void setVoucherCategoryId(int voucherCategoryId) {
        this.voucherCategoryId = voucherCategoryId;
    }

    @Override
    public String toString() {
        return "Voucher{" + "voucherId=" + voucherId + ", voucherName=" + voucherName + ", description=" + description + ", quantity=" + quantity + ", releaseDate=" + releaseDate + ", finishDate=" + finishDate + ", status=" + status + ", discount=" + discount + ", voucherCategoryId=" + voucherCategoryId + '}';
    }

    
}
