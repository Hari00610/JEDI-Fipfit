package com.flipkart.business;

import com.flipkart.bean.Slot;
import com.flipkart.dao.SlotDAOImpl;

import java.util.List;

public class SlotServiceImpl implements SlotServiceInterface {

    private static SlotDAOImpl slotDAO = new SlotDAOImpl();

    @Override
    public void addSlotsForGym(String gymCentreId, List<Slot> slotList) {
        System.out.println("Adding all slots to gym: " + gymCentreId);
        for(Slot slot : slotList) {
            slot.setCentreId(gymCentreId);
            slotDAO.addSlot(slot);
        }
    }
}
