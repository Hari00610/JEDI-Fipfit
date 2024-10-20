package com.flipkart.business;

import com.flipkart.bean.BookingDetails;
import com.flipkart.dao.BookingDAOImpl;

import java.util.List;
import com.flipkart.exceptions.BookingFailedException;

/**
 * Implementation of Booking Service.
 */
public class BookingServiceImpl implements BookingServiceInterface{

    private final BookingDAOImpl bookingDAO = new BookingDAOImpl();

    @Override
    public List<BookingDetails> getBookingByCustomerId(String username){
        return bookingDAO.getBookingByCustomerId(username);
    }

    @Override
    public void cancelBooking(String bookingID) {
        try {
            bookingDAO.cancelBookingById(bookingID);
        } catch (BookingFailedException e) {
            System.out.println(e.getMessage());
        }
    }
}
