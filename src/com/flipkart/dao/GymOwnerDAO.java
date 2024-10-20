package com.flipkart.dao;
import com.flipkart.bean.GymOwner;

import java.util.List;

    /**
     * @author JEDI-09
     * Interface for gym owner DAO operations
     */
public interface GymOwnerDAO {
    /**
     * Retrieves the list of Gym Owners.
     *
     * @return a list of GymOwner objects representing the Gym Owners
     */
    public List<GymOwner> getGymOwnerList();

    /**
     * Sets the list of Gym Owners.
     *
     * @param gymOwnerList a list of GymOwner objects representing the Gym Owners
     */
    public void setGymOwnerList(List<GymOwner> gymOwnerList);

    /**
     * Registers a new GymOwner with the given userName, password, email, panNumber, and cardNumber.
     *
     * @param  userName    the username of the GymOwner
     * @param  password    the password of the GymOwner
     * @param  email       the email of the GymOwner
     * @param  panNumber   the PAN number of the GymOwner
     * @param  cardNumber  the card number of the GymOwner
     * @return              the newly registered GymOwner
     */
    public GymOwner registerGymOwner(String userName,String password,String email,String panNumber,String cardNumber);

    /**
     * Retrieves the list of pending Gym Owners.
     *
     * @return         a list of GymOwner objects representing the pending Gym Owners
     */
    public List<GymOwner> getPendingGymOwnerList();

    /**
     * Logs in a gym owner.
     *
     * @param  username the username of the gym owner
     * @param  password the password of the gym owner
     * @return          true if the login is successful, false otherwise
     */
    public boolean loginGymOwner(String username,String password);

    /**
     * Sends an approval request for a gym owner with the specified ID.
     *
     * @param  gymOwnerId  the ID of the gym owner for approval request
     */
    public void sendOwnerApprovalRequest(String gymOwnerId);

    /**
     * Retrieves the Gym Owner ID based on the provided username and password.
     *
     * @param  userName    the username of the Gym Owner
     * @param  password    the password of the Gym Owner
     * @return             the Gym Owner ID
     */
    public String getGymOwnerId(String userName, String password);

}
