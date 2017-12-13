package com.example.owner.giveback.data;

/**
 * Created by owner on 12/11/17.
 */

public class Post {

    private String userId;
    private String author;
    private String title;
    private String description;

    public Post() {
    }

    public Post(String userId, String author, String title, String description) {
        this.userId = userId;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return description;
    }

    public void setBody(String body) {
        this.description = body;
    }
}