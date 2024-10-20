package com.flipkart.business;

import com.flipkart.bean.GymCentre;

import java.util.List;

    /**
     * @author JEDI-09
     * Interface for GymCenter Service
     */
public interface GymCenterServiceInterface {
    /**
     * Retrieves a list of GymCentres based on the provided gymOwnerId.
     *
     * @param  gymOwnerId  the ID of the gym owner
     * @return             a list of GymCentres associated with the given gymOwnerId
     */
    List<GymCentre> getAllCentresByOwmerId(String gymOwnerId);

    /**
     * Adds a new gym center with the provided details.
     *
     * @param  gymId         the ID of the gym
     * @param  userName      the username of the gym owner
     * @param  gymCentreName the name of the gym center
     * @param  gstin         the GST identification number
     * @param  city          the city where the gym center is located
     * @param  capacity      the capacity of the gym center
     * @param  isapproved    whether the gym center is approved or not
     * @param  price         the price of the gym center
     */
    void addCenter(String gymId,String userName,String gymCentreName, String gstin, String city,int capacity,boolean isapproved,float price);

    /**
     * Requests approval for a gym center.
     *
     * @param  gymCentreName    the name of the gym center
     * @param  userName         the username of the gym owner
     */
    void requestGymCentreApproval(String gymCentreName, String userName);
}
