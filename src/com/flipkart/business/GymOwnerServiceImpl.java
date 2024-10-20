package com.flipkart.business;

import com.flipkart.dao.GymOwnerDAOImpl;
import com.flipkart.bean.GymOwner;
import com.flipkart.exceptions.LoginFailedException;
import com.flipkart.exceptions.RegistrationFailedException;

import java.util.List;
import java.util.Scanner;

public class GymOwnerServiceImpl implements GymOwnerServiceInterface{
    private static GymOwnerDAOImpl gymOwnerDAOImpl = new GymOwnerDAOImpl();
    public static Scanner scanner = new Scanner(System.in); // has to be imported from main client

    @Override
    public GymOwner register() {
        System.out.println("Enter your UserName");
        String userName = scanner.next();

        System.out.println("Enter your Passkey");
        String password = scanner.next();

        System.out.println("Enter your Email");
        String email = scanner.next();

        System.out.println("Enter your PAN Number");
        String panNumber = scanner.next();

        System.out.println("Enter your Card Number");
        String cardNumber = scanner.next();

        try {
            GymOwner registeredOwner = gymOwnerDAOImpl.registerGymOwner(userName, password, email, panNumber, cardNumber);
            System.out.println("Registered successfully!! ");
            return registeredOwner;
        } catch(RegistrationFailedException e){
            throw new RegistrationFailedException("Failed to register Gym Owner");
        }
    }

    @Override
    public boolean gymOwnerLogin(String userName, String password) {
        try{
        return gymOwnerDAOImpl.loginGymOwner(userName, password);
        }
        catch (LoginFailedException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void gymOwnerChangePassword(String userName, String old_password, String new_password) {
        System.out.println("Successfully changed the password");
    }

    @Override
    public void viewAllGymOwners() {
        List<GymOwner> gymOwners = gymOwnerDAOImpl.getGymOwnerList();

        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-20s | %-15s |\n",
                "Username", "Email", "Card Details", "Pan Number");

        for (GymOwner gymOwner : gymOwners) {
            System.out.println("--------------------------------------------------------------------");
            System.out.printf("| %-10s | %-10s | %-20s | %-15s |\n",
                    gymOwner.getUserName(), gymOwner.getEmail(), gymOwner.getCardDetails(), gymOwner.getPanNumber());
        }
        System.out.println("--------------------------------------------------------------------");


    }

    @Override
    public void requestGymOwnerApproval(String gymOwnerId) {
        gymOwnerDAOImpl.sendOwnerApprovalRequest(gymOwnerId);
    }

    @Override
    public String getGymOwnerId(String userName, String password) {
        return gymOwnerDAOImpl.getGymOwnerId(userName, password);
    }
}
