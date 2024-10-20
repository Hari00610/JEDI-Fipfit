package com.flipkart.business;

import com.flipkart.bean.BookingDetails;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.dao.*;
import com.flipkart.exceptions.WrongCredentialsException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

/**
 * Implementation of Customer Service.
 */
public class CustomerServiceImpl implements  CustomerServiceInterface{

    public static Scanner scanner = new Scanner(System.in);
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final GymCenterDAO gymCenterDAO = new GymCenterDAOImpl();
    private final SlotDAO slotDAO = new SlotDAOImpl();
    private final ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    private final BookingServiceInterface bookingService = new BookingServiceImpl();
    private final BookingDAOImpl bookingDAO = new BookingDAOImpl();
    private final PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

    @Override
    public List<GymCentre> getAllGymCenterDetailsByCity(String city) {
        return gymCenterDAO.getGymCentreListByCity(city);
    }

    @Override
    public List<BookingDetails> getCustomerBookings(String username){
        //takes userId and returns List<Bookings>
        return bookingService.getBookingByCustomerId(username);
    }
    @Override
    public boolean bookSlot(String userID, Date date, String slotId, String centreId) {
        return true;
    }

    @Override
    public void cancelBooking(String bookingID) {
        bookingService.cancelBooking(bookingID);
    }

    @Override
    public boolean customerLogin(String userName, String password) {
        if (customerDAO.checkCustomerDetails(userName, password)) {
            System.out.println("Successfully logged in as Customer");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer register() {
        System.out.println("Enter your UserName");
        String userName = scanner.next();

        System.out.println("Enter your Passkey");
        String password = scanner.next();

        System.out.println("Enter your Email");
        String email = scanner.next();

        System.out.println("Enter your Phone Number");
        String phoneNumber = scanner.next();

        System.out.println("Enter your Card Number");
        String cardNumber = scanner.next();

        return customerDAO.registerCustomer(userName, password, email, phoneNumber, cardNumber);
    }

    @Override
    public String getCustomerIdFromNameAndPass(String userName, String password) throws WrongCredentialsException {
        return customerDAO.getCustomerIdFromNameAndPass(userName, password);
    }

    @Override
    public Integer getBookingCountFromSlotId(String slotId) {
        return scheduleDAO.getSlotsBookedCountFromSlotId(slotId);
    }

    @Override
    public Integer getGymCentreCapacityFromCentreId(String centreId) {
        return gymCenterDAO.getCapacityFromcentreId(centreId);
    }

    @Override
    public String addSchedule(Timestamp timestamp, String slotId) {
        return scheduleDAO.addSchedule(timestamp,slotId);
    }

    @Override
    public float getGymCentreCostFromCentreId(String centreId) {
        return  gymCenterDAO.getCostFromcentreId(centreId);
    }

    @Override
    public String addPayment(String bookingID, float amountPaid) {
        return paymentDAO.addPayment(bookingID,amountPaid);
    }

    public String addBooking(String username, String scheduleId) {
        return bookingDAO.addBooking(username, scheduleId);
    }

    @Override
    public String getSlotIdFromGymCentreAndTimestamp(String gymOwner, Timestamp timestamp) {
        return slotDAO.getSlotbyCentreIdAndTimeStamp(gymOwner,timestamp);
    }

    public Customer viewMyProfile(String customerId)
    {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public void customerChangePassword(String userName,String old_password,String new_password) throws WrongCredentialsException {
        customerDAO.changeCustomerPassword(userName, old_password, new_password);
    }

}
