package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.bean.BookingDetails;

import java.util.List;

    /**
     * @author JEDI-09
     * Interface for booking DAO operations
     */
public interface BookingDAO {
        /**
         * Retrieves a list of booking details for a specific customer.
         *
         * @param  username   the username of the customer
         * @return a list of BookingDetails objects representing the customer's bookings
         */
    public List<BookingDetails> getBookingByCustomerId(String username);
        /**
         * Adds a new booking for a customer.
         *
         * @param  username   the username of the customer
         * @param  scheduleId the ID of the schedule being booked
         * @return a String indicating the success of the booking process
         */
    public String addBooking(String username, String scheduleId);
        /**
         * Cancels a booking by its ID.
         *
         * @param  bookingID  the ID of the booking to be cancelled
         */
    public void cancelBookingById(String bookingID);
        /**
         * Retrieves a Booking object from the database based on the provided bookingId.
         *
         * @param  bookingId  the unique identifier of the booking
         * @return the Booking object associated with the bookingId, or null if not found
         */
    public Booking getBookingByBookingId(String bookingId);
}

