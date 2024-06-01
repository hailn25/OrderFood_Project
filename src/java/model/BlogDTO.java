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
public class BlogDTO {
    private int blogId;
    private String title;
    private String content;
    private String imageURL;
    private String summary;
    private String nameUpdateBy;
    private boolean status;
    private Date createDate;
    private Date updateDate;

    public BlogDTO() {
    }

    public BlogDTO(int blogId, String title, String content, String imageURL, String summary, String nameUpdateBy, boolean status, Date createDate, Date updateDate) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.imageURL = imageURL;
        this.summary = summary;
        this.nameUpdateBy = nameUpdateBy;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNameUpdateBy() {
        return nameUpdateBy;
    }

    public void setNameUpdateBy(String nameUpdateBy) {
        this.nameUpdateBy = nameUpdateBy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "BlogDTO{" + "blogId=" + blogId + ", title=" + title + ", content=" + content + ", imageURL=" + imageURL + ", summary=" + summary + ", nameUpdateBy=" + nameUpdateBy + ", status=" + status + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }
    
    
}
