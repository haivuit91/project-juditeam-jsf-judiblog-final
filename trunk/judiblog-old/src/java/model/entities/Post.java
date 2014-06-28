/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Post {

    private int postID;
    private String title;
    private String content;
    private String imagePath;
    private Date postDate;
    private User userID;
    private Category catID;
    private boolean isActive;

    public Post() {

    }

    public Post(int postID, String title, String content, String imagePath, Date postDate, User userID, Category catID, boolean isActive) {
        this.postID = postID;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.postDate = postDate;
        this.userID = userID;
        this.catID = catID;
        this.isActive = isActive;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Category getCatID() {
        return catID;
    }

    public void setCatID(Category catID) {
        this.catID = catID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

   

   

   

}
