package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.LoginFailedException;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.WrongCredentialsException;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.flipkart.constants.SQLConstants.*;

/**
 * Implementation for Customer data accessor object.
 */
public class CustomerDAOImpl implements CustomerDAO {
    List<Customer> customerList = new ArrayList<>();

    @Override
    public Customer registerCustomer(String username, String password, String email, String phoneNumber, String cardNumber) throws RegistrationFailedException{
        String customerId = UUID.randomUUID().toString();
        Customer newCustomer = new Customer(customerId, username, email, password, phoneNumber, cardNumber);
        try {
            if(checkCustomerDetails(username, password)) {
                throw new RegistrationFailedException("Please enter a different userName or password.");
            }
            Connection conn = DBConnection.connect();
            PreparedStatement stmtUser = conn.prepareStatement(ADD_NEW_USER);
            stmtUser.setString(1, customerId);
            stmtUser.setString(2, username);
            stmtUser.setString(3, email);
            stmtUser.setString(4, password);
            stmtUser.setString(5, "Customer");
            stmtUser.executeUpdate();
            stmtUser.close();
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_CUSTOMER);
            stmt.setString(1, phoneNumber);
            stmt.setString(2, cardNumber);
            stmt.setString(3, customerId);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Customer Registered!!");
        } catch (RegistrationFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new RegistrationFailedException("Failed to register the user. Try again.");
        }
        return newCustomer;
    }

    @Override
    public Customer getCustomerById(String customerId){
        Customer customer = new Customer();
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_BY_ID);
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            customer.setCustomerPhone(rs.getString("customerPhone"));
            customer.setCardDetails(rs.getString("cardDetails"));
            stmt.close();

            PreparedStatement stmtUser = conn.prepareStatement(GET_USER_BY_ID);
            stmtUser.setString(1, customerId);
            ResultSet rsUser = stmtUser.executeQuery();
            rsUser.next();
            customer.setEmail(rsUser.getString("email"));
            customer.setUserID(rsUser.getString("userId"));
            customer.setPassword(rsUser.getString("password"));
            customer.setUserName(rsUser.getString("userName"));
            stmtUser.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean checkCustomerDetails(String username, String password) {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(CUSTOMER_LOGIN_QUERY);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, "Customer");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                stmt.close();
                return true;
            }
            stmt.close();
        } catch (Exception exp) {
            System.out.println("Oops! An error occurred. Try again later.");
            throw new LoginFailedException("Customer logIn failed");
        }
        return false;
    }

    @Override
    public String getCustomerIdFromNameAndPass(String userName, String password) throws WrongCredentialsException {
        String customerId = "";
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_CUSTOMER_ID_BY_USERNAME_PASSWORD);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customerId = rs.getString("userId");
            } else {
                throw new WrongCredentialsException();
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return customerId;
    }

    @Override
    public void changeCustomerPassword(String userName, String oldPassword, String newPassword) throws WrongCredentialsException {
        try {
            String customerId = getCustomerIdFromNameAndPass(userName, oldPassword);
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMER_PASSWORD);
            stmt.setString(1, newPassword);
            stmt.setString(2, customerId);
            stmt.setString(3, "Customer");
            int rs = stmt.executeUpdate();
            if (rs != 0){
                stmt.close();
                return;
            }
            stmt.close();

    } catch (SQLException exp) {
        throw new WrongCredentialsException();
    } catch (Exception exp) {
        exp.printStackTrace();
     }
    }
}