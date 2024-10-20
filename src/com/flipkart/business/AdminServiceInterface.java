package com.flipkart.business;

    /**
     * @author JEDI-09
     * Interface for Admin Service
     */
public interface AdminServiceInterface {

    /**
     * Checks if the given userName and password combination is valid.
     *
     * @param  userName   the userName to be checked
     * @param  password   the password to be checked
     * @return            true if the userName and password combination is valid, false otherwise
     */
    public boolean isUserValid(String userName, String password);

    /**
     * Log in for Admin.
     *
     * @param  userName   the userName to be checked
     * @param  password   the password to be checked
     * @return            true if the userName and password combination is valid, false otherwise
     */
    public boolean adminLogin(String userName, String password);

    /**
     * Changes the password of the admin.
     *
     * @param  userName      the userName of the admin
     * @param  old_password  the old password of the admin
     * @param  new_password  the new password of the admin
     */
    public void adminChangePassword(String userName, String old_password, String new_password);

    /**
     * Retrieves and displays a list of all Gym Owners.
     *
     */
    public void viewAllGymOwners();

    /**
     * Validates all Gym Owners.
     *
     * This method retrieves the list of Gym Owners and validates each one.
     * The validation process involves checking if the Gym Owner is approved or not.
     * If the Gym Owner is not approved, their approval status is updated in the database.
     */
    public void validateAllGymOwners();

    /**
     * Validates a Gym Owner by ID.
     *
     * @param  ownerId    the ID of the Gym Owner to validate
     * @param  isApproved the approval status of the Gym Owner
     */
    public void validateGymOwnerByID(String ownerId, int isApproved);

    /**
     * Retrieves and displays a list of all Gym Centres.
     *
     */
    public void viewAllGymCentres();

    /**
     * Validates all Gym Centres..
     *
     */
    public void validateAllGymCentres();

    /**
     * Validates a Gym Centre by its ID.
     *
     * @param  gymCentreId   the ID of the Gym Centre to validate
     * @param  isApproved    the approval status of the Gym Centre
     */
    public void validateGymCentreByID(String gymCentreId, int isApproved);
}
