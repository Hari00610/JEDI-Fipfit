package com.flipkart.bean;
import java.util.List;

public class GymOwner extends User {

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public int isApproved() {
        return isApproved;
    }

    public void setApproved(int approved) {

        isApproved = approved;
    }

    public List<String> getGymCentreLists() {
        return gymCentreLists;
    }

    public void setGymCentreLists(List<String> gymCentreLists) {
        this.gymCentreLists = gymCentreLists;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    public GymOwner() {
    }

    public GymOwner(String userId, String userName, String email, String password, String panNumber, int isApproved, String cardDetails) {
        super(userId, userName, email, password, Role.GYMOWNER);
        this.panNumber = panNumber;
        this.isApproved = isApproved;
        this.cardDetails = cardDetails;
    }

    private String panNumber;
    private int isApproved;
    private List<String> gymCentreLists;
    private String cardDetails;
}
