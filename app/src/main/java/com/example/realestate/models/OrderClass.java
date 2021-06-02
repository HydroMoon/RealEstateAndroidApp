package com.example.realestate.models;

public class OrderClass {

    private String name;
    private String objectID;
    private String buyID;
    private String userID;
    private String propertyNumber;
    private String phone;
    private int status;

    public OrderClass(String name, String objectID, String buyID, String userID, int status, String propertyNumber, String phone) {
        this.name = name;
        this. objectID = objectID;
        this.buyID = buyID;
        this.userID = userID;
        this.status = status;
        this.propertyNumber = propertyNumber;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getObjectID() {
        return objectID;
    }

    public String getBuyID() {
        return buyID;
    }

    public String getUserID() {
        return userID;
    }

    public int getStatus() {
        return status;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public String getPhone() {
        return phone;
    }
}
