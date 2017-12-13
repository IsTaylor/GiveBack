package com.example.owner.giveback.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by owner on 12/11/17.
 */

public class Post {

    private String userId;
    private String author;
    private String title;
    private String description;
    private Map<String, String> usersJoined;

    public Post() {
    }

    public Post(String userId, String author, String title, String description) {
        this.userId = userId;
        this.author = author;
        this.title = title;
        this.description = description;
        usersJoined = new HashMap<>();
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

    public Map<String, String> getUsersJoined() {
        return usersJoined;
    }

    public void setUsersJoined(Map<String, String> usersJoined) {
        this.usersJoined = usersJoined;
    }

}