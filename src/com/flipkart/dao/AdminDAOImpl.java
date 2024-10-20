package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO{

    private Connection conn = null;
    private PreparedStatement statement = null;

    public List<GymCentre> getGymCentersList() {
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms centres..");
            statement = conn.prepareStatement(SQLConstants.SQL_VIEW_ALL_GYM_CENTRES);
            ResultSet rs = statement.executeQuery();

            List<GymCentre> gymCentres = new ArrayList<>();
            while(rs.next()) {
                GymCentre gymCentre = new GymCentre(
                        rs.getString("centreId"),
                        rs.getString("ownerId"),
                        rs.getString("centreName"),
                        rs.getString("gstNo"),
                        rs.getString("city"),
                        rs.getInt("capacity"),
                        rs.getInt("approved"),
                        rs.getFloat("amountPerSlot")
                );
                gymCentres.add(gymCentre);
            }
            return gymCentres;
        }
        catch (SQLException se)
        {
            se.printStackTrace();
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void validateAllGymCentres() {
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms centres..");

            statement = conn.prepareStatement(SQLConstants.SQL_APPROVE_ALL_GYM_CENTRES);
            statement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validateGymCentreByID(String gymCentreId, int isApproved) {
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms centres..");

            statement = conn.prepareStatement(SQLConstants.SQL_APPROVE_GYM_CENTRE_BY_ID_QUERY);
            statement.setInt(1, isApproved);
            statement.setString(2, gymCentreId);
            statement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GymOwner> getGymOwnersList() {
        List<GymOwner> gymOwnerList = new ArrayList<>();

        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement(SQLConstants.FETCH_ALL_GYM_OWNERS_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                GymOwner owner = new GymOwner(
                        rs.getString("userId"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("panNumber"),
                        rs.getInt("Approved"),
                        rs.getString("cardDetails")
                );
                gymOwnerList.add(owner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gymOwnerList;
    }

    @Override
    public void validateAllGymOwners() {
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms owners..");

            statement = conn.prepareStatement(SQLConstants.SQL_APPROVE_GYM_OWNER_ALL);
            statement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validateGymOwnerByID(String ownerId, int isApproved) {
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching gyms owners..");

            statement = conn.prepareStatement(SQLConstants.SQL_APPROVE_GYM_OWNER_BY_ID_QUERY);
            statement.setInt(1, isApproved);
            statement.setString(2, ownerId);
            statement.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
