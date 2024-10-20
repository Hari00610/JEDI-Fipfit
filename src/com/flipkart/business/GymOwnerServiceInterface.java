package com.flipkart.business;

import com.flipkart.bean.GymOwner;

    /**
     * @author JEDI-09
     * Interface for GymOwner Dao
     */
public interface GymOwnerServiceInterface {

    /**
     * Registers a new GymOwner.
     *
     * @return  the registered GymOwner
     */
    public GymOwner register();

    /**
     * Logs in a GymOwner by checking the provided username and password against the GymOwnerDAOImpl.
     *
     * @param  userName    the username of the GymOwner
     * @param  password    the password of the GymOwner
     * @return             true if the login is successful, false otherwise
     */
    public boolean gymOwnerLogin(String userName, String password);

    /**
     * Changes the password of a gym owner.
     *
     * @param  userName    the username of the gym owner
     * @param  old_password    the current password of the gym owner
     * @param  new_password    the new password to be set
     */
    public void gymOwnerChangePassword(String userName,String old_password,String new_password);

    /**
     * Retrieves and displays information about all gym owners in a tabular format.
     */
    public void viewAllGymOwners();

    /**
     * Retrieves the Gym Owner ID based on the provided username and password.
     *
     * @param  userName    the username of the Gym Owner
     * @param  password    the password of the Gym Owner
     * @return             the Gym Owner ID
     */
    public String getGymOwnerId(String userName, String password);

    /**
     * Sends a request for approval of a gym owner.
     *
     * @param  gymOwnerId  the ID of the gym owner to request approval for
     */
    public void requestGymOwnerApproval(String gymOwnerId);
}
