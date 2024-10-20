package com.flipkart.client;

import com.flipkart.bean.BookingDetails;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.business.CustomerServiceImpl;
import com.flipkart.exceptions.RegistrationFailedException;
import com.flipkart.exceptions.WrongCredentialsException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.flipkart.constants.Constants.INVALID_CHOICE_ERROR;
import static com.flipkart.constants.Constants.PREVIOUS_MENU_MESSAGE;


/**
 * @author JEDI-09
 * This class represents the customer Flipfit Menu.
 */
public class CustomerFlipfitMenu {
    public static Scanner scanner = new Scanner(System.in);

    CustomerServiceImpl customerService = new CustomerServiceImpl();
    /**
     * Logs in the customer with the given userName and password, and navigates to the main page if successful.
     *
     * @param  userName the username of the customer
     * @param  password the password of the customer
     * @throws WrongCredentialsException if the login credentials are incorrect
     */
    public void customerLogin(String userName, String password) throws WrongCredentialsException {
        try {
            if (customerService.customerLogin(userName, password)) {
                String customerId = customerService.getCustomerIdFromNameAndPass(userName, password);
                customerClientMainPage(userName, customerId);
            } else {
                System.out.println("Invalid Credentials!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Registers a customer by invoking the registration process, obtaining customer details,
     * and navigating to the main page upon successful registration.
     *
     * @throws RegistrationFailedException if the registration process fails
     */
    public void register() throws RegistrationFailedException {
        try {
            Customer customer = customerService.register();
            String customerId = customerService.getCustomerIdFromNameAndPass(customer.getUserName(),
                    customer.getPassword());
            customerClientMainPage(customer.getUserName(), customerId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Book a slot for the user by providing location, selecting a gym center, and managing the booking process.
     *
     * @param userName the username of the user booking the slot
     */
    private void bookSlotSubMenu(String userName){
        //Get Location for filter
        System.out.println("Provide Location to search :");
        String location = scanner.next();
        List<GymCentre> centreListByLocation = customerService.getAllGymCenterDetailsByCity(location);
        // Print All Centres
        for(GymCentre gymCentreAtLocation : centreListByLocation){
            System.out.println(" -------------------------------------------------------");
            System.out.println("The gym centre id:"+gymCentreAtLocation.getCentreId());
            System.out.println("The gym centre Name:"+gymCentreAtLocation.getCentreName());
            System.out.println("The gym amount:"+gymCentreAtLocation.getAmountPerSlot());
            System.out.println(" -------------------------------------------------------");

        }

        if(centreListByLocation.isEmpty()){
            System.out.println("Sorry we dont have any gym centre's in this city!");
            bookSlotSubMenu(userName);
            return;
        }

        System.out.print("Choose a gymCentre ID to proceed:");
        String chosenGym = scanner.next();
        //Select Date
        Timestamp sqlTimestamp = getTimestamp();
        String slotId = customerService.getSlotIdFromGymCentreAndTimestamp(chosenGym,sqlTimestamp);

        if(slotId == null){
            System.out.println("No slots available for the selected centre and time");
            bookSlotSubMenu(userName);
            return;
        }

        System.out.println("Slot id is as follows:" + slotId);

        Integer currBookingCount = customerService.getBookingCountFromSlotId(slotId);

        System.out.println("The no. of  bookings under my slot currently are: " + currBookingCount);

        Integer maximumBookingCapacity = customerService.getGymCentreCapacityFromCentreId(chosenGym);

        System.out.println("The maximum number of bookings in the given slot possible are : " + maximumBookingCapacity);

        if(currBookingCount < maximumBookingCapacity){
            float gymCentreCost = customerService.getGymCentreCostFromCentreId(chosenGym);
            System.out.println(" -------------------------------------------------------");
            System.out.println("Would you like to continue to payment? Enter Y/N to pay "+gymCentreCost);
            System.out.println(" -------------------------------------------------------");
            String paymentChoice = scanner.next();
            if(Objects.equals(paymentChoice, "Y")){
                String scheduleId = customerService.addSchedule(sqlTimestamp,slotId);
                String bookingId = customerService.addBooking(userName,scheduleId);
                String paymentId = customerService.addPayment(bookingId,gymCentreCost);
                System.out.println(" -------------------------------------------------------------------------------------------------");
                System.out.println("Booking successfull!, booking Id for my brother's booking: " + bookingId);
                System.out.println(" -------------------------------------------------------------------------------------------------");
            }
            else{
                System.out.println("Redirecting to selection page");
                bookSlotSubMenu(userName);
            }

        }
        else{
            System.out.println("Sorry brother, no slots available");
        }
    }
    /**
     * Selects a date and time, parses it, and returns a SQL timestamp.
     *
     * @return the SQL timestamp extracted from the user input date and time
     */
    private Timestamp getTimestamp(){
        //Select Date
        while (true) {
            // Clean buffer
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // This ensures the buffer is clean
            }

            // Select Date
            System.out.print("Enter date and time (yyyy-MM-dd HH:mm:ss): ");
            String userInput = scanner.nextLine();

            // Debugging print
            System.out.println("User input: '" + userInput + "'");

            // Check for empty input
            if (userInput.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid date and time.");
                continue;
            }

            // Parse and format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setLenient(false); // Ensures strict parsing

            try {
                java.util.Date date = dateFormat.parse(userInput);
                // Convert date to SQL timestamp
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss");
            }
        }
    }
    /**
     * Prints the customer profile information.
     *
     * @param customer the customer object containing the profile information
     */
    public void printCustomerProfile(Customer customer) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("USER ID: "+ customer.getUserID());
        System.out.println("USER NAME: "+ customer.getUserName());
        System.out.println("EMAIL: "+  customer.getEmail());
        System.out.println("CONTACT: "+  customer.getCustomerPhone());
        System.out.println("CARD DETAILS: "+  customer.getCardDetails());
        System.out.println("---------------------------------------------------------------------------");
    }
    /**
     * Cancels a booking for a user.
     *
     * @param userName the username of the user whose booking is to be cancelled
     */
    private void cancelBookingSubMenu(String userName){
        System.out.println("Select the Booking you want to cancel: ");
        printUserPlan(userName);
        System.out.println("Enter bookingId which you want to cancel....");
        String bookingId = scanner.next();
        customerService.cancelBooking(bookingId);

    }
    /**
     * Prints the user's booking plan based on the provided username.
     *
     * @param userName the username for which the booking plan is to be printed
     */
    private void printUserPlan(String userName){
        System.out.println("Bookings : ");
//        List<UserPlan> allUserPlan= customerService.getCustomerPlan(userName);
        List<BookingDetails> bookingList = customerService.getCustomerBookings(userName);
        if (bookingList.isEmpty()) {
            System.out.println("No bookings found for user: " + userName);
            return;
        }

        // Print header
        System.out.printf("%-40s %-15s %-20s %-15s %n",  "Booking ID", "Date", "Center Name", "City");
        System.out.println("---------------------------------------------------------------");

        // Iterate over the list and print each booking
        for (BookingDetails booking : bookingList) {
            System.out.printf("%-40s %-15s %-20s %-15s %n",
                    booking.getBookingId(),
                    booking.getDate().toString(),
                    booking.getCentreName(),
                    booking.getCity());
        }
    }
    /**
     * Customer main page function that allows the user to interact with various options such as viewing profile, booking gym slots, viewing bookings, and canceling bookings.
     *
     * @param username the username of the customer
     * @param customerId the customer ID
     */
    public void customerClientMainPage(String username, String customerId) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Welcome to customer main page!!");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Login TIME: "+currentTime);
        System.out.println("---------------------------------------------------------------------------");

        while(true) {
            System.out.println("1. View Profile \n2. Book a slot in Gym \n3. View Bookings\n4. Cancel Bookings\n5. Go Back to previous menu");
            System.out.println("---------------------------------------------------------------------------");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Customer customerProfile = customerService.viewMyProfile(customerId);
                    printCustomerProfile(customerProfile);
                    break;

                case 2:
                    System.out.println("Viewing all Available slots");
                    bookSlotSubMenu(username);
                    break;

                case 3:
                    printUserPlan(username);
                    break;

                case 4:
                    cancelBookingSubMenu(username);
                    break;

                case 5:
                    System.out.println(PREVIOUS_MENU_MESSAGE);
                    return;

                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        }
    }
    /**
     * Changes the password of a customer.
     *
     * @param  userName the username of the customer
     * @param  old_password the current password of the customer
     * @param  new_password the new password to be set for the customer
     * @throws WrongCredentialsException if the old password is incorrect
     */
    public void customerChangePassword(String userName,String old_password,String new_password) throws WrongCredentialsException {
        customerService.customerChangePassword(userName, old_password, new_password);
    }

}
