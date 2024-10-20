package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.bean.BookingDetails;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.flipkart.constants.SQLConstants.CANCEL_BOOKING_BY_ID;
import static com.flipkart.constants.SQLConstants.GET_BOOKING_BY_BOOKING_ID;

public class BookingDAOImpl implements BookingDAO{
    public List<BookingDetails> getBookingByCustomerId(String username) throws BookingFailedException {
        List<BookingDetails> allBookingDetailsList = new ArrayList<>();
        String getUserIdQuery = "SELECT userId FROM user WHERE userName = ?";
        String getBookingsQuery = "SELECT b.bookingId, b.userId, b.scheduleId, s.slotId, slot.centreId, gym_centre.centreName, gym_centre.city, s.date FROM booking b JOIN schedule s ON b.scheduleId = s.scheduleId JOIN slot ON s.slotId = slot.slotId JOIN gym_centre ON slot.centreId = gym_centre.centreId WHERE b.userId = ?";


        try {
            Connection conn = DBConnection.connect();

            // Step 1: Get userId using username
            PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdQuery);
            getUserIdStmt.setString(1, username);
            ResultSet userIdRs = getUserIdStmt.executeQuery();

            String userId = null;
            if (userIdRs.next()) {
                userId = userIdRs.getString("userId");
            } else {
                return allBookingDetailsList; // Return empty list if no user found
            }

            // Step 2: Get bookings using userId
            PreparedStatement getBookingsStmt = conn.prepareStatement(getBookingsQuery);
            getBookingsStmt.setString(1, userId);
            ResultSet bookingsRs = getBookingsStmt.executeQuery();


            while (bookingsRs.next()) {
//                System.out.println("Result set has data");
                BookingDetails bookingDetails = new BookingDetails(
                        bookingsRs.getString("bookingId"),
                        bookingsRs.getDate("date"),
                        bookingsRs.getString("centreName"),
                        bookingsRs.getString("city")
                );
                allBookingDetailsList.add(bookingDetails);
            }

        }catch(SQLException sql) {
            throw new BookingFailedException("Could not retrieve Bookings by customer id:  " + username);
        }  catch (Exception e) {
            System.out.println("Oops! An error occurred. Try again later.");
            e.printStackTrace();
        }
        return allBookingDetailsList;
    }

    public String addBooking(String username, String scheduleId) throws BookingFailedException{
        String bookingId = UUID.randomUUID().toString();
        String getUserIdQuery = "SELECT userId FROM user WHERE userName = ?";
        String insertBookingQuery = "INSERT INTO booking (bookingId, userId, scheduleId) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.connect()) {
            // Step 1: Retrieve the userId based on the provided username
            PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdQuery);
            getUserIdStmt.setString(1, username);
            ResultSet userIdRs = getUserIdStmt.executeQuery();

            String userId = null;
            if (userIdRs.next()) {
                userId = userIdRs.getString("userId");
                System.out.println("Found userId: " + userId);
            } else {
                System.out.println("No user found with username: " + username);
                return null; // Return null if no user found
            }

            // Step 2: Insert a new booking record into the booking table
            PreparedStatement insertBookingStmt = conn.prepareStatement(insertBookingQuery);
            insertBookingStmt.setString(1, bookingId);
            insertBookingStmt.setString(2, userId);
            insertBookingStmt.setString(3, scheduleId);

            int rowsInserted = insertBookingStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Booking successfully added with bookingId: " + bookingId);
            } else {
                System.out.println("Failed to add booking");
                return null; // Return null if the booking insertion failed
            }

        } catch (SQLException e) {
            new BookingFailedException("Oops! An error occurred. Try again later.");
            e.printStackTrace();
            return null; // Return null if an exception occurred
        }

        return bookingId;
    }


    public void cancelBookingById(String bookingID) throws BookingFailedException {
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(CANCEL_BOOKING_BY_ID);
            stmt.setString(1, bookingID);
            System.out.println("for cancelling bookingId  "+bookingID);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();

            if (rowsAffected > 0) {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Booking successfully cancelled with bookingId: " + bookingID);
                System.out.println("---------------------------------------------------------------------------");
            } else {
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("Failed to cancel booking with bookingId: " + bookingID);
                System.out.println("---------------------------------------------------------------------------");
            }

        } catch (SQLException se)
        {
            se.printStackTrace();
        } catch(Exception e) {
            System.out.println("Oops! An error occurred. Try again later.");
        }
    }

    public Booking getBookingByBookingId(String bookingId) throws BookingFailedException{
        Booking booking  = null;
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(GET_BOOKING_BY_BOOKING_ID);
            stmt.setString(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                booking = new Booking(
                        rs.getString("bookingId"),
                        rs.getString("userID"),
                        rs.getString("scheduleID")
                );

            }
        } catch(SQLException sql) {
            throw new BookingFailedException("Could not fetch booking by bookingId: " + bookingId);
        } catch(Exception excep) {
            System.out.println("Oops! An error occurred. Try again later.");
        }
        return booking;
    }

}


