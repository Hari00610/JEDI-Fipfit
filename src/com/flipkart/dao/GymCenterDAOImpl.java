package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.flipkart.constants.SQLConstants.SQL_SEND_APPROVAL_GYM_CENTRE_BY_ID_QUERY;

public class GymCenterDAOImpl implements GymCenterDAO {
    private Connection conn = null;
    private PreparedStatement statement = null;
    AdminDAOImpl adminDAO = new AdminDAOImpl();

        public List<GymCentre> getAllCentresByOwmerId(String gymOwnerId) {
        List<GymCentre> gymCentreListofOwner = new ArrayList<>();

            String fetcehdOwnerID = "";
            try {
                conn = DBConnection.connect();
                statement = conn.prepareStatement(SQLConstants.GET_USER_ID_FRROM_USER);
                statement.setString(1, gymOwnerId);

                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    fetcehdOwnerID = rs.getString("userId");
                }
            }catch (SQLException se) {
                    se.printStackTrace();
                }


        List<GymCentre> allGymCentres = adminDAO.getGymCentersList();
        for (GymCentre gymcentre : allGymCentres) {

            String id = gymcentre.getOwnerId();
            if (id.equalsIgnoreCase(fetcehdOwnerID)) {
                gymCentreListofOwner.add(gymcentre);
            }
        }

        return gymCentreListofOwner;
    }
    public static String generateRandomString(int length) {
        // Character set from which to generate the random string
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

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



    public List<GymCentre> getGymCentreListByCity(String city) {
        List<GymCentre> allCentreByCity = new ArrayList<>();

        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms centres by City..");
            statement = conn.prepareStatement(SQLConstants.FETCH_GYM_CENTRES_BY_CITY);
            statement.setString(1, city);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                GymCentre gymCentre = new GymCentre(
                        rs.getString("centreId"),
                        rs.getString("ownerId"),
                        rs.getString("centreName"),
                        rs.getString("gstNo"),
                        rs.getString("city"),
                        rs.getInt("capacity"),
                        rs.getInt("approved"),
                        rs.getInt("amountPerSlot")
                );
                allCentreByCity.add(gymCentre);
            }
            //System.out.println("The gym centre has been approved!");
        } catch (SQLException se) {
            System.out.println("Error in SQL statement");
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return allCentreByCity;
    }

    @Override
    public Integer getCapacityFromcentreId(String centreId) {
        Integer gymCentreCapacity = 0;
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms centre capacity");
            statement = conn.prepareStatement(SQLConstants.FETCH_GYM_CENTRE_CAPACITY);
            statement.setString(1, centreId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                gymCentreCapacity = rs.getInt("capacity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gymCentreCapacity;
    }

    public void addGymCentre(String gymId,String ownerId,String gymCentreName, String gstin, String city,int capacity,boolean isapproved,float price) {
        try{

            String priceee = Float.toString(price);
            conn = DBConnection.connect();

            statement = conn.prepareStatement(SQLConstants.GET_USER_ID_FRROM_USER);
            statement.setString(1, ownerId);

            ResultSet rs = statement.executeQuery();
            String fetcehdOwnerID="";
            while(rs.next()) {
                fetcehdOwnerID = rs.getString("userId");
            }
            statement=null;
            statement = conn.prepareStatement(SQLConstants.ADD_GYM_CENTRE_QUERY);
            statement.setString(1, generateRandomString(6));
            statement.setString(2, fetcehdOwnerID);
            statement.setString(3, gymCentreName);
            statement.setString(4, gstin);
            statement.setString(5, city);
            statement.setInt(6, capacity);
            statement.setInt(7,(isapproved==true?1:0));
            statement.setString(8,priceee );
            boolean i=statement.execute();
            System.out.println("Gym Centre Added Successfully");
        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void sendRequestForApprovalOfCentre( String gymcentreName,String username) {
        String fetcehdOwnerID ="";
        try {
            conn = DBConnection.connect();
            System.out.println("Gym Centre Approval Request sent to Admin\n");



            conn = DBConnection.connect();
            statement = conn.prepareStatement(SQLConstants.GET_USER_ID_FRROM_USER);
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                fetcehdOwnerID = rs.getString("userId");
            }
            statement =null;
            statement = conn.prepareStatement(SQL_SEND_APPROVAL_GYM_CENTRE_BY_ID_QUERY);
            statement.setInt(1, 2);
            statement.setString(2, gymcentreName);
            statement.setString(3, fetcehdOwnerID);

            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    @Override
    public float getCostFromcentreId(String centreId) {
        float gymCentreCost = 0;
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms centre cost");
            statement = conn.prepareStatement(SQLConstants.FETCH_GYM_CENTRE_COST);
            statement.setString(1, centreId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                gymCentreCost = rs.getInt("amountPerSlot");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gymCentreCost;
    }
}
