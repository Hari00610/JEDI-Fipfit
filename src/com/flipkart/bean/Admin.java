package com.flipkart.bean;

public class Admin extends User {

    public Admin() {
        super("0","root", "admin@flipfit.com", "123", Role.ADMIN);
    }

}
