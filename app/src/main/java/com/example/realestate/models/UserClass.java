package com.example.realestate.models;

public class UserClass {

    private String Username;
    private String Phone;
    private String ObjectID;

    public UserClass(String Username, String Phone, String ObjectID) {
        this.Username = Username;
        this.Phone = Phone;
        this.ObjectID = ObjectID;
    }

    public String getUsername() {
        return Username;
    }

    public String getPhone() {
        return Phone;
    }

    public String getObjectID() {
        return ObjectID;
    }
}
