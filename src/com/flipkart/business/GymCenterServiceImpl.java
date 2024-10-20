package com.flipkart.business;

import com.flipkart.dao.GymCenterDAOImpl;
import com.flipkart.bean.GymCentre;

import java.util.List;

public class GymCenterServiceImpl implements GymCenterServiceInterface {
    GymCenterDAOImpl gymCenterDAOImpl = new GymCenterDAOImpl();



    public List<GymCentre> getAllCentresByOwmerId(String gymOwnerId) {
        return gymCenterDAOImpl.getAllCentresByOwmerId(gymOwnerId);
    }
    public void addCenter(String gymId,String userName,String gymCentreName, String gstin, String city,int capacity,boolean isapproved,float price) {
        //takes gymCenter details

        gymCenterDAOImpl.addGymCentre(gymId,userName,gymCentreName,gstin,city,capacity,isapproved,price );
        return;

    }
    public void requestGymCentreApproval(String gymCentreName, String userName){
        gymCenterDAOImpl.sendRequestForApprovalOfCentre(gymCentreName, userName);
    }


}