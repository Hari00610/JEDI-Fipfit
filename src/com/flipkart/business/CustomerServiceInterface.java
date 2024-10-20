package com.flipkart.business;

import com.flipkart.bean.BookingDetails;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.exceptions.WrongCredentialsException;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

    /**
     * @author JEDI-09
     * Interface for Customer Service.
     */
public interface CustomerServiceInterface {

    /**
     * Retrieves a list of gym centers based on the provided city.
     *
     * @param  city  the city for which gym center details are requested
     * @return a list of GymCentre objects representing gym centers in the specified city
     */
    List<GymCentre> getAllGymCenterDetailsByCity(String city);

    /**
     * Retrieves a list of bookings for the specified customer.
     *
     * @param  customerId  the ID of the customer whose bookings are requested
     * @return a list of BookingDetails objects representing the customer's bookings
     */
    List<BookingDetails> getCustomerBookings(String customerId);

    /**
     * Book a slot for the specified customer.
     *
     * @param  userID  the ID of the customer who is booking the slot
     * @param  date  the date on which the slot is being booked
     * @param  slotId  the ID of the slot being booked
     * @param  centreId  the ID of the gym center where the slot is being booked
     * @return true if the slot is successfully booked, false otherwise
     */
    boolean bookSlot(String userID,Date date, String slotId,String centreId);

    /**
     * Cancels a booking for the specified customer.
     *
     * @param  bookingID  the ID of the booking to be cancelled
     */
    void cancelBooking(String bookingID);

    /**
     * Logins the customer with the provided username and password.
     *
     * @param  userName   description of parameter
     * @param  password   description of parameter
     * @return description of return value
     */
    public boolean customerLogin(String userName, String password);

    /**
     * Registers a new customer.
     *
     * @return	the newly registered Customer object
     */
    public Customer register();

    /**
     * Returns profile of a customer for provided userName.
     *
     * @param  customerId  description of parameter
     * @return description of return value
     */
    public Customer viewMyProfile(String customerId);

    /**
     * Changes the password of the customer.
     *
     * @param  userName  description of parameter
     * @param  old_password  description of parameter
     * @param  new_password  description of parameter
     * @throws WrongCredentialsException
     */
    public void customerChangePassword(String userName,String old_password,String new_password) throws WrongCredentialsException;

    /**
     * Returns slotId from GymCentre and timestamp
     *
     * @param  gymOwner	description of parameter
     * @param  timestamp	description of parameter
     * @return	description of return value
     */
    public String getSlotIdFromGymCentreAndTimestamp(String gymOwner, Timestamp timestamp);

    /**
     * Returns customerId from name and password
     *
     * @param  userName	description of parameter
     * @param  password	description of parameter
     * @return description of return value
     */
    public String getCustomerIdFromNameAndPass(String userName, String password) throws WrongCredentialsException;

    /**
     * Retrieves the count of bookings for a given slot ID.
     *
     * @param  slotId  the ID of the slot for which the booking count is requested
     * @return the count of bookings for the specified slot
     */
    public Integer getBookingCountFromSlotId(String slotId);

    /**
     * Retrieves the capacity of a gym center based on the provided center ID.
     *
     * @param  centreId  the ID of the gym center for which the capacity is requested
     * @return the capacity of the specified gym center
     */
    public Integer getGymCentreCapacityFromCentreId(String centreId);

    /**
     * Adds a new schedule to the database.
     *
     * @param  timestamp	description of parameter
     * @param  slotId	description of parameter
     * @return	description of return value
     */
    public String addSchedule(Timestamp timestamp, String slotId);

    /**
     * Retrieves the cost of a gym center based on the provided center ID.
     *
     * @param  centreId  the ID of the gym center for which the cost is requested
     * @return the cost of the specified gym center
     */
    public float getGymCentreCostFromCentreId(String centreId);

    /**
     * Adds a new payment to the database.
     *
     * @param  bookingID	description of parameter
     * @param  amountPaid	description of parameter
     * @return description of return value
     */
    public String addPayment(String bookingID, float amountPaid);
}
