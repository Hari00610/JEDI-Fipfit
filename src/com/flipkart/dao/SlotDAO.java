package com.flipkart.dao;

import com.flipkart.bean.Slot;

import java.sql.Timestamp;
import java.util.List;

    /**
     * @author JEDI-09
     * Interface for slot DAO operations
     */
public interface SlotDAO {

    /**
     * Adds a new slot to the database.
     *
     * @param  slot the slot to be added
     */
    public void addSlot(Slot slot);


    /**
     * Retrieves a Slot object from the database based on the provided gymCentreId and timestamp.
     *
     * @param  gymCentreId the unique identifier of the gym centre
     * @param  timestamp the timestamp of the slot
     * @return the Slot object associated with the gymCentreId and timestamp, or null if not found
     */
    public String getSlotbyCentreIdAndTimeStamp(String gymCentreId, Timestamp timestamp);
}
