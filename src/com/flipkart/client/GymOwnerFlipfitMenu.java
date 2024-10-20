package com.flipkart.client;



import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.business.GymOwnerServiceImpl;
import com.flipkart.business.GymCenterServiceImpl;
import com.flipkart.business.SlotServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Constants.INVALID_CHOICE_ERROR;
import static com.flipkart.constants.Constants.PREVIOUS_MENU_MESSAGE;


/**
 * @author JEDI-09
 * This class represents the Gym Owner Flipfit Menu.
 *
 */
public class GymOwnerFlipfitMenu {

    public static Scanner scanner = new Scanner(System.in);

    GymOwnerServiceImpl gymOwnerService = new GymOwnerServiceImpl();
    GymCenterServiceImpl gymCentreService = new GymCenterServiceImpl();
    private SlotServiceImpl slotServiceImpl = new SlotServiceImpl();
    /**
     * Logs in the Gym Owner with the given userName and password.
     *
     * @param userName the username of the Gym Owner
     * @param password the password of the Gym Owner
     * @return true if the login is successful, false otherwise
     * @throws ParseException if there is an error parsing the date
     */
    public boolean gymOwnerLogin(String userName, String password) throws ParseException {
        if(gymOwnerService.gymOwnerLogin(userName, password)){
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Successfully logged in as Gym Owner");
            gymOwnerClientMainPage(userName, password);
        }
        else{
            System.out.println("Invalid credentials");
        }
        return true;
    }
    /**
     * Registers a new Gym Owner by calling the `register` method of the `gymOwnerService` object.
     * The registered Gym Owner's username and password are then passed to the `gymOwnerClientMainPage` method.
     *
     * @throws ParseException if there is an error parsing the date
     */
    public void register() throws ParseException {
        GymOwner registeredGymOwner = gymOwnerService.register();
        gymOwnerClientMainPage(registeredGymOwner.getUserName(), registeredGymOwner.getPassword());
    }
    /**
     * Changes the password of a gym owner.
     *
     * @param userName the username of the gym owner
     * @param old_password the current password of the gym owner
     * @param new_password the new password to be set for the gym owner
     */
    public void gymOwnerChangePassword(String userName,String old_password,String new_password){
        gymOwnerService.gymOwnerChangePassword(userName, old_password, new_password);
    }
    /**
     * Main page for the gym owner client with various options like viewing gym centres, adding new gym center, etc.
     *
     * @param userName the username of the gym owner
     * @param password the password of the gym owner
     * @throws ParseException if there is an error parsing the date
     */

    public void gymOwnerClientMainPage(String userName, String password) throws ParseException {
        System.out.println("Welcome to gym owner main page!!");
        while(true) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("" +
                    "0. View all Gym Centres\n" +
                    "1. Send Gym Owner Approval Request\n" +
                    "2. Add new Gym Center\n" +
                    "3. Request Gym Centre Approval\n" +
                    "4. Add Slots to Gym Centre\n" +
                    "5. Go Back to Previous Menu"
            );
            System.out.println("---------------------------------------------------------------------------");
            int choice = scanner.nextInt();
            switch(choice){
                case 0:
                    List<GymCentre> allGymCentres = gymCentreService.getAllCentresByOwmerId(userName);
                    if(allGymCentres.size()>0){
                        System.out.println("--------------------------------------------------------------------");
                        System.out.printf("| %-15s | %-10s | %-10s | %-20s | %-15s |\n",
                                "Centre Id","Centre Name", "Capacity", "City", "Amount per slot");
                        for(GymCentre gymcenter:allGymCentres ){
                            System.out.println("--------------------------------------------------------------------");
                            System.out.printf("| %-15s | %-10s | %-10s | %-20s | %-15s |\n",
                                    gymcenter.getCentreId(),gymcenter.getCentreName(), gymcenter.getCapacity(), gymcenter.getCity(), gymcenter.getAmountPerSlot());
                        }
                    }
                    else{
                        System.out.println("No gym centres to view");
                    }
                    break;

                case 1:
                    String gymOwnerId = gymOwnerService.getGymOwnerId(userName, password);
                    gymOwnerService.requestGymOwnerApproval(gymOwnerId);
                    System.out.println("Gym owner request sent to Admin\n");
                    break;

                case 2:
                    String gymId ="";

                    System.out.println("Enter Gym Centre name: ");
                    String gymCentreName = scanner.next();

                    System.out.println("Enter Gym Centre GSTIN: ");
                    String gstin = scanner.next();

                    System.out.println("Enter Gym Centre city: ");
                    String city = scanner.next();

                    System.out.println("Enter Gym Centre capacity: ");
                    int capacity = scanner.nextInt();

                    System.out.println("Enter price: : ");
                    float price = scanner.nextFloat();
                    boolean isapproved = false;

                    gymCentreService.addCenter(gymId,userName,gymCentreName,gstin,city,capacity,isapproved,price );
                    System.out.println("New Gym center added\n");

                    break;

                case 3:
                    System.out.println("Enter gym centre Name: ");
                    String gymCentrename = scanner.next();
                    gymCentreService.requestGymCentreApproval(gymCentrename,userName);
                    break;

                case 4:
                    boolean isAdding = true;
                    String centreId = null;
                    String slotId = null;
                    String time = null;

                    List<Slot> newSlotList = new ArrayList<>();
                    while (isAdding) {
                        System.out.println("Enter new slot id: ");
                        slotId = scanner.next();

                        System.out.println("Enter Gym Centre Id: ");
                        centreId = scanner.next();

                        scanner.nextLine();

                        System.out.println("Enter time in 24h format (hh:mm:ss): ");
                        time = scanner.nextLine();

                        try {
                            // Use the correct format for parsing time
                            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                            Date localTime = formatter.parse(time);

                            newSlotList.add(new Slot(
                                    slotId,
                                    centreId,
                                    localTime
                            ));

                            String addChoice = null;
                            System.out.println("Do you want to enter more slots (y/n)?: ");
                            addChoice = scanner.next();
                            addChoice = addChoice.toLowerCase();

                            if(addChoice.equals("n") || addChoice.equals("no")) {
                                isAdding = false;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid time format.");
                        }
                    }

                    slotServiceImpl.addSlotsForGym(centreId, newSlotList);
                    System.out.println("Slots added in the Gym centre\n");
                    break;

                case 5:
                    System.out.println(PREVIOUS_MENU_MESSAGE);
                    return;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
            }
        }

    }
}