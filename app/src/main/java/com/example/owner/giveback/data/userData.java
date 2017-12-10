package com.example.owner.giveback.data;

/**
 * Created by owner on 12/10/17.
 */

public class userData {

    private String userID;
    private String name;
    private boolean admin;

    public userData(){

    }

    public userData(String userID, String name, boolean admin) {
        this.userID = userID;
        this.name = name;
        this.admin = admin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

