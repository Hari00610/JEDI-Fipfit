package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.exceptions.WrongCredentialsException;

    /**
     * @author JEDI-09
     * Interface for customer DAO operations
     */
public interface CustomerDAO {
    /**
     * Registers a new customer with the given information.
     *
     * @param  userName the username of the customer
     * @param  password the password of the customer
     * @param  email the email of the customer
     * @param  phoneNumber the phone number of the customer
     * @param  cardNumber the card number of the customer
     * @return the newly registered customer
     */
    Customer registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber);

    /**
     * Retrieves a customer with the given username.
     *
     * @param  customerId the username of the customer
     * @return the customer with the given username
     */
    Customer getCustomerById(String customerId);

    /**
     * Checks if a customer with the given username and password exists.
     *
     * @param  username the username of the customer
     * @param  password the password of the customer
     * @return true if the customer exists, false otherwise
     */
    boolean checkCustomerDetails(String username, String password);

    /**
     * Retrieves the customer with the given username and password.
     *
     * @param  userName the username of the customer
     * @param  password the password of the customer
     * @return the customer with the given username and password
     */
    String getCustomerIdFromNameAndPass(String userName, String password) throws WrongCredentialsException;

    /**
     * Changes the password of the customer with the given username.
     *
     * @param  userName the username of the customer
     * @param  oldPassword the old password of the customer
     * @param  newPassword the new password of the customer
     */
    void changeCustomerPassword(String userName, String oldPassword, String newPassword) throws WrongCredentialsException;
}