package com.flipkart.bean;

public class User {

    private String userID;
    private String userName;
    private String email;
    private String password;
    private Role role;

    public User() {
    }

    public User(String id, String userName, String email, String password, Role role) {
        this.userID = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
