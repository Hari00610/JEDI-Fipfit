package com.flipkart.business;

import com.flipkart.bean.BookingDetails;

import java.util.Date;
import java.util.List;

    /**
     * @author JEDI-09
     * Interface for Booking Service
     */
public interface BookingServiceInterface {

    /**
     * Retrieves a list of bookings for the specified customer.
     *
     * @param  username  the username of the customer
     * @return a list of BookingDetails objects representing the customer's bookings
     */
    List<BookingDetails> getBookingByCustomerId(String username);

    /**
     * Cancels a booking for the specified customer.
     *
     * @param  bookingID  the ID of the booking to be cancelled
     */
    void cancelBooking(String bookingID);

}
