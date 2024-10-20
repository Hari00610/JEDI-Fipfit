package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.util.List;

    /**
     * @author JEDI-09
     * Interface for admin DAO operations
     */
public interface AdminDAO {
    /**
     * Retrieves the list of Gym Owners.
     *
     * @return a list of GymOwner objects representing the Gym Owners
     */
    List<GymOwner> getGymOwnersList();

    /**
     * Validates all Gym Owners.
     *
     * This method retrieves the list of Gym Owners and validates each one.
     * The validation process involves checking if the Gym Owner is approved or not.
     * If the Gym Owner is not approved, their approval status is updated in the database.
     *
     */
    public void validateAllGymOwners();

    /**
     * Validates all Gym Owners By ID.
     *
     * @param  ownerId    description of parameter
     * @param  isApproved description of parameter
     */
    public void validateGymOwnerByID(String ownerId, int isApproved);

    /**
     * Retrieves all Gym Centres.
     *
     * @return         	description of return value
     */
    List<GymCentre> getGymCentersList();

    /**
     *Validates all Gym Centres..
     *
     */
    public void validateAllGymCentres();

    /**
     * Validates all Gym Centres By ID.
     *
     * @param  gymCentreId	description of parameter
     * @param  isApproved	description of parameter
     */
    public void validateGymCentreByID(String gymCentreId, int isApproved);
}
