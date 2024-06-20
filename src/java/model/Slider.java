/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author quoch
 */
public class Slider {
    private int sliderId;
    private String sliderTitle;
    private String imageURL;
    private int Arrange;
    private boolean status;
    private int updateBy;
    private Date createDate;
    private Date updateDate;
    private String backLink;

    public Slider() {
    }

    public Slider(int sliderId, String sliderTitle, String imageURL, int Arrange, boolean status, int updateBy, Date createDate, Date updateDate, String backLink) {
        this.sliderId = sliderId;
        this.sliderTitle = sliderTitle;
        this.imageURL = imageURL;
        this.Arrange = Arrange;
        this.status = status;
        this.updateBy = updateBy;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.backLink = backLink;
    }

    public int getSliderId() {
        return sliderId;
    }

    public void setSliderId(int sliderId) {
        this.sliderId = sliderId;
    }

    public String getSliderTitle() {
        return sliderTitle;
    }

    public void setSliderTitle(String sliderTitle) {
        this.sliderTitle = sliderTitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getArrange() {
        return Arrange;
    }

    public void setArrange(int Arrange) {
        this.Arrange = Arrange;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getBackLink() {
        return backLink;
    }

    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }

    @Override
    public String toString() {
        return "Slider{" + "sliderId=" + sliderId + ", sliderTitle=" + sliderTitle + ", imageURL=" + imageURL + ", Arrange=" + Arrange + ", status=" + status + ", updateBy=" + updateBy + ", createDate=" + createDate + ", updateDate=" + updateDate + ", backLink=" + backLink + '}';
    }
    
    
}
