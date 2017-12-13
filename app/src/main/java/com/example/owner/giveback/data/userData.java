package com.example.owner.giveback.data;

/**
 * Created by owner on 12/10/17.
 */

public class userData {

    private String name;
    private boolean admin;

    public userData(){

    }

    public userData(String name, boolean admin) {
        this.name = name;
        this.admin = admin;
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

