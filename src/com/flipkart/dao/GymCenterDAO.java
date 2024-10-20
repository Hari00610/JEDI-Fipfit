package com.flipkart.dao;

import com.flipkart.bean.GymCentre;

import java.util.List;

    /**
     * @author JEDI-09
     * Interface for gym center DAO operations
     */
public interface GymCenterDAO {


    /**
     * Retrieves a list of GymCentres based on the provided gymOwnerId.
     *
     * @param  gymOwnerId  the ID of the gym owner
     * @return             a list of GymCentres associated with the given gymOwnerId
     */
    List<GymCentre> getAllCentresByOwmerId(String gymOwnerId) ;

    /**
     * Retrieves a list of GymCentres based on the provided city.
     *
     * @param  city  the city for which GymCentres need to be retrieved
     * @return       a list of GymCentres associated with the given city
     */
    public List<GymCentre> getGymCentreListByCity(String city);

    /**
     * Retrieves the capacity from the gym center with the given center ID.
     *
     * @param  centreId   the ID of the gym center
     * @return            the capacity of the gym center
     */
    public Integer getCapacityFromcentreId(String centreId);

    /**
     * Adds a new gym center with the provided details.
     *
     * @param  gymId         the ID of the gym
     * @param  ownerId       the ID of the owner
     * @param  gymCentreName the name of the gym center
     * @param  gstin         the GST identification number
     * @param  city          the city where the gym center is located
     * @param  capacity      the capacity of the gym center
     * @param  isapproved    whether the gym center is approved or not
     * @param  price         the price of the gym center
     */
    public void addGymCentre(String gymId,String ownerId,String gymCentreName, String gstin, String city,int capacity,boolean isapproved,float price);

    /**
     * Sends a request for approval of a gym center.
     *
     * @param  gymcentreName    the name of the gym center
     * @param  username         the username of the gym owner
     */
    public void sendRequestForApprovalOfCentre( String gymcentreName,String username);

    /**
     * Retrieves the cost from the gym center with the given center ID.
     *
     * @param  centreId   the ID of the gym center
     * @return            the cost of the gym center
     */
    public float getCostFromcentreId(String centreId);
}
