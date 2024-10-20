package com.flipkart.client;

import com.flipkart.bean.Role;
import com.flipkart.exceptions.WrongCredentialsException;

import java.text.ParseException;
import java.util.Scanner;

import static com.flipkart.constants.Constants.*;

/**
 * @author JEDI-09
 *  * This class represents main menu for the Flipfit application.
 */
public class FlipfitApplicationClient {

    public static int userId = 0;
    public static Scanner scanner = new Scanner(System.in);

    private static final AdminFlipfitMenu adminFlipfitMenu = new AdminFlipfitMenu();
    private static final CustomerFlipfitMenu customerFlipfitMenu = new CustomerFlipfitMenu();
    private static final GymOwnerFlipfitMenu gymOwnerFlipfitMenu = new GymOwnerFlipfitMenu();

    /**
     * Displays the main menu and handles user input to navigate through the application.
     *
     * @throws IllegalArgumentException if an invalid choice is entered
     */
    private static void mainPage(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("1. Login\n2. Registration of the GYM Customer\n3. Registration of the GYM Owner\n4. Change Password\n5. Exit");
        System.out.println("---------------------------------------------------------------------------");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registration(Role.CUSTOMER);
                break;
            case 3:
                registration(Role.GYMOWNER);
                break;
            case 4:
                change_password();
                break;
            case 5:
                System.out.println(EXIT_MESSAGE);
                return;
            default:
                System.out.println(INVALID_CHOICE_ERROR);
                break;
        }
        mainPage();
    }
    /**
     * Prompts the user to enter their Role, UserName, and Passkey, and then logs them in based on their Role.
     *
     * @throws Exception if an error occurs during the login process
     */
    private static void login(){
        try {
            System.out.println("Enter your Role");
            Role role = Role.valueOf(scanner.next().toUpperCase());

            System.out.println("Enter your UserName");
            String userName = scanner.next();

            System.out.println("Enter your Passkey");
            String password = scanner.next();

            switch (role){
                case ADMIN:
                    adminFlipfitMenu.adminLogin(userName,password);
                    break;
                case GYMOWNER:
                    gymOwnerFlipfitMenu.gymOwnerLogin(userName,password);
                    break;
                case CUSTOMER:
                    customerFlipfitMenu.customerLogin(userName,password);
                    break;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        }catch (Exception e){
            System.out.println(INVALID_CHOICE_ERROR);
        }
    }
    /**
     * A method that handles the registration process based on the Role provided.
     *
     * @param  role  the Role of the user for registration
     */
    private static void registration(Role role){
        try {
            switch (role){
                case ADMIN:
                    System.out.println("Admin is already registered");
                    mainPage();
                    break;
                case CUSTOMER:
                    customerFlipfitMenu.register();
                    break;
                case GYMOWNER:
                    gymOwnerFlipfitMenu.register();
                    break;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        }catch (Exception e){
            System.out.println(INVALID_CHOICE_ERROR);
        }
    }
    /**
     * A method to change the password based on the user's role.
     * @return void
     */
    private static void change_password(){
        try {
            System.out.println("Enter your Role");
            Role role = Role.valueOf(scanner.next().toUpperCase());

            System.out.println("Enter your UserName");
            String userName = scanner.next();

            System.out.println("Enter your Old Passkey");
            String old_password = scanner.next();
            System.out.println("Enter your New Passkey");
            String new_password = scanner.next();

            switch (role){
                case ADMIN:
                    adminFlipfitMenu.adminChangePassword(userName,old_password,new_password);
                    break;
                case GYMOWNER:
                    gymOwnerFlipfitMenu.gymOwnerChangePassword(userName,old_password,new_password);
                    break;
                case CUSTOMER:
                    customerFlipfitMenu.customerChangePassword(userName,old_password,new_password);
                    break;
                default:
                    System.out.println(INVALID_CHOICE_ERROR);
                    break;
            }
        } catch (IllegalArgumentException e){
            System.out.println(INVALID_CHOICE_ERROR);
        } catch (WrongCredentialsException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * The main function of the program. It prints a welcome message and calls the mainPage function.
     *
     * @param  args  the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        mainPage();
    }
}