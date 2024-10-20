package com.flipkart.bean;

public class GymCentre {

 private String centreId;
 private String ownerId;
 private String centreName;
 private String gstNo;
 private String city;
 private int capacity;
 private int isApproved;
 private float amountPerSlot;

    public GymCentre(String centreId, String ownerId, String centreName, String gstNo, String city, int capacity, int isApproved, float amountPerSlot) {
        this.centreId = centreId;
        this.ownerId = ownerId;
        this.centreName = centreName;
        this.gstNo = gstNo;
        this.city = city;
        this.capacity = capacity;
        this.isApproved = isApproved;
        this.amountPerSlot = amountPerSlot;
    }

    public String getCentreId() {
        return centreId;
    }

    public void setCentreId(String centreId) {
        this.centreId = centreId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int isApproved() {
        return isApproved;
    }

    public void setApproved(int approved) {
        isApproved = approved;
    }

    public float getAmountPerSlot() {
        return amountPerSlot;
    }

    public void setAmountPerSlot(float amountPerSlot) {
        this.amountPerSlot = amountPerSlot;
    }
}
