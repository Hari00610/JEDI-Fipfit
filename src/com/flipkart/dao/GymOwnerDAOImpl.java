package com.flipkart.dao;


import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.flipkart.bean.Role;
import com.flipkart.constants.SQLConstants;
import com.flipkart.exceptions.LoginFailedException;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.utils.DBConnection;

public class GymOwnerDAOImpl implements GymOwnerDAO {

    private Connection conn = null;
    private PreparedStatement statement = null;
    AdminDAOImpl adminDAO = new AdminDAOImpl();
    private List<GymOwner> gymOwnerList = new ArrayList<>();
    public List<GymOwner> getGymOwnerList(){
        return gymOwnerList;
    }
    public void setGymOwnerList(List<GymOwner> gymOwnerList){
        this.gymOwnerList = gymOwnerList;

    }
    public static String generateRandomString(int length) {
        // Character set from which to generate the random string
        String charset = "0123456789";

        // Random object to select characters from the charset
        Random random = new Random();

        // StringBuilder to build the resulting random string
        StringBuilder sb = new StringBuilder();

        // Generate random characters
        for (int i = 0; i < length; i++) {
            // Generate a random index between 0 and charset.length()-1
            int randomIndex = random.nextInt(charset.length());

            // Append the character at the random index to the string builder
            sb.append(charset.charAt(randomIndex));
        }

        // Convert StringBuilder to String and return the generated random string
        return sb.toString();
    }
    public GymOwner registerGymOwner(String userName,String password,String email,String panNumber,String cardNumber){
        GymOwner gymOwner = new GymOwner();
        try{
            conn  = DBConnection.connect();
            statement = conn.prepareStatement(SQLConstants.ADD_USER);
            String userid = generateRandomString(8);
            statement.setString(1,userid);
            statement.setString(2,userName);
            statement.setString(3, email);
            statement.setString(4,password);
            String role = "GymOwner";
            statement.setString(5,role);


            statement.execute();
            statement = null;
            statement = conn.prepareStatement(SQLConstants.REGISTER_GYM_OWNER);

            statement.setString(1,panNumber);
            statement.setBoolean(2,false);
            statement.setString(3, cardNumber);
            statement.setString(4,userid);

            statement.execute();

            System.out.println("Registration Success\n");
            gymOwner.setUserName(userName);
            gymOwner.setPassword(password);
            gymOwner.setEmail(email);
            gymOwner.setPanNumber(panNumber);
            gymOwner.setCardDetails(cardNumber);

            return gymOwner;

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Try again with a different Username \n");
            throw new RegistrationFailedException("Failed to register Gym Owner");
        }
    }
    public boolean loginGymOwner(String username,String password){
        try {
            conn = DBConnection.connect();
            ResultSet result;
            try {
                statement = conn.prepareStatement(SQLConstants.LOGIN_GYM_OWNER);
                statement.setString(1, username);
                statement.setString(2, password);
                result = statement.executeQuery();
                while (result.next()) {
                    if (username.equals(result.getString("userName")) && password.equals(result.getString("password"))) {
                        System.out.println("Login Success\n");
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("SQL Exception\n");
                throw new LoginFailedException("Failed to login Gym Owner");
            }
        }catch (SQLException e){
            System.out.println("SQL Exception\n");
        }
        return false;
    }
    public List<GymOwner> getPendingGymOwnerList(){
        List<GymOwner>pendingGymOwner = new ArrayList<>();

        for(GymOwner gymowner:gymOwnerList){
            if(gymowner.isApproved()==0){
                pendingGymOwner.add(gymowner);
            }

        }
        return pendingGymOwner;
    }

    @Override
    public void sendOwnerApprovalRequest(String gymOwnerId){
        try {
            conn = DBConnection.connect();
            System.out.println("Sending gym owner approval request..");
            statement = conn.prepareStatement(SQLConstants.SEND_GYM_OWNER_APPROVAL_REQ_QUERY);
            statement.setString(1,gymOwnerId);

            statement.executeUpdate();

        } catch (SQLException se) { se.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public String getGymOwnerId(String userName, String password) {
        List<GymOwner> gymOwnerList = adminDAO.getGymOwnersList();
        for (GymOwner gymowner: gymOwnerList) {
            if (gymowner.getUserName().equals(userName) && gymowner.getPassword().equals(password)) {
                return gymowner.getUserID();
            }
        }
        return "";
    }
}
