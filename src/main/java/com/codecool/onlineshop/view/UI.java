package com.codecool.onlineshop.view;

import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.model.User;
import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class UI {

    public void printTableFromDB(ResultSet resultSet, String message) throws SQLException {
        System.out.println(message);
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    public String addProductPrintMsg() {
        return "Print from products";
    }

    public String addOrderPrintMsg(){
        return "Print from orders";
    }

    Scanner scanner = new Scanner(System.in);

    private static boolean isNumericValue(String userInput) {
        return !userInput.equals("") && userInput.matches("^[0-9]*$");
    }

    private boolean isNumberInRange(String userInput, int from, int to) {
        int option = Integer.parseInt(userInput);
        return option >= from && option < to;
    }

    public int getNumericInput(String title, int from, int to) {
        scanner.useDelimiter(System.lineSeparator());
        String userInput = "";
//        System.out.print(title);
        boolean validInput = false;
        while (!validInput) {
            userInput = scanner.next();
            if (isNumericValue(userInput) && isNumberInRange(userInput, from, to)) {
                validInput = true;
            }
        }
        return Integer.parseInt(userInput);
    }

    public String getStringInput(String message) {
        System.out.println(message);
        String userInputString;
        Scanner scanner = new Scanner(System.in);
        userInputString = scanner.nextLine();
        return userInputString;
    }

    public float getFloatInput(String title, float minRange, float maxRange) {
        System.out.println(title);
        String userInput;
        float userFloat = 1;
        boolean validInput = false;
        while (!validInput) {
            userInput = scanner.next();
            if (isNumericValue(userInput)) {
                userFloat = Float.parseFloat(userInput);
                if (userFloat >= minRange && userFloat <= maxRange) {
                    validInput = true;
                }
            }
        }
        return userFloat;
    }

    public void getEmptyInput(String message) {
        System.out.println(message);
        scanner.next();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }



    public void displayMenu() {
        clearScreen();
        boolean isRunning = true;
        welcomeScreen();
        System.out.println("Choose Option:\n (1) Create Account\n (2) Login\n (0) Quit");
        int userInput = getNumericInput("", 0, 4);
        while (isRunning) {
            if (userInput == 1) {
                createNewUser();
            }
            else if (userInput == 2) {
                login();
            }
            else if (userInput == 0) {
                exitProgram();
            }
            else {
                System.out.println("enter correct number");
            }
        }
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9998) + 1;
    }

    public void welcomeScreen(){
        clearScreen();
        System.out.println("WELCOME TO ONLINE SHOP!");
    }

    public void menuOptions(){
        System.out.println("Please Choose:");
    }


    public void creatorsList(){
        clearScreen();
        System.out.println("This Project Was Created By:\n Marlena Jakubowska\n Artur Jakubowski\n Michał Myczka\n");
    }

    public void exitProgram() {
        clearScreen();
        String[][] data = {{"Bye Bye"}};
        System.exit(0);
    }

    public void displayAdminMenu(){
        clearScreen();
        Map<Integer, String> adminMenu = makeAdminChoiceMap();
        System.out.println("Admin MENU: ");
        adminMenu.forEach((k,v) -> System.out.println(k+". "+v));
    }

    private Map<Integer, String> makeAdminChoiceMap() {
        Map<Integer, String> adminChoiceMap = new HashMap<>();
        adminChoiceMap.put(1, "Create new product");
        adminChoiceMap.put(2, "Edit product");
        adminChoiceMap.put(3, "Deactivate product");
        adminChoiceMap.put(4, "Create product category");
        adminChoiceMap.put(5, "Edit product category");
        adminChoiceMap.put(6, "Check orders statuses");
        adminChoiceMap.put(7, "Discount product");
        adminChoiceMap.put(8, "Check statistics");
        adminChoiceMap.put(9, "Logout");
        return adminChoiceMap;
    }

    public void displayCustomerMenu(){
        clearScreen();
        Map<Integer, String> customerMenu = makeCustomerChoiceMap();
        System.out.println("Customer MENU: ");
        customerMenu.forEach((k,v) -> System.out.println(k+". "+v));
    }

    private Map<Integer, String> makeCustomerChoiceMap() {
        Map<Integer, String> customerChoiceMap = new HashMap<>();
        customerChoiceMap.put(1, "Show my basket");
        customerChoiceMap.put(2, "Add product to basket");
        customerChoiceMap.put(3, "Remove product from basket");
        customerChoiceMap.put(4, "Edit product's quantity");
        customerChoiceMap.put(5, "Place an order");
        customerChoiceMap.put(6, "Show my previous orders");
        customerChoiceMap.put(7, "Show all available products with rates");
        customerChoiceMap.put(8, "Show products by category");
        customerChoiceMap.put(9, "Check availability of product");
        customerChoiceMap.put(10, "Rate product");
        customerChoiceMap.put(11, "Statistics of orders");
        customerChoiceMap.put(12, "Logout");
        return customerChoiceMap;
    }



    private void createNewUser() {
        UserDao userDao = new UserDao();
        String name = getStringInput("Enter your name: ");
        String surname = getStringInput("Enter your surname: ");
        String email = getStringInput("Enter your email: ");
        String password = getStringInput("Enter your password: ");
        try {
            User user = new User(0, name, password, email, surname);
            userDao.addUser(user);
            getEmptyInput("Account successfully created!\nPress any key to back to main menu.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void login() {
        UserDao userDao = new UserDao();
        clearScreen();
        String email = getStringInput("Enter Email: ");
        String password = getStringInput("Enter Password: ");
        User user = userDao.getUser(email, password);
        boolean isRunning = true;
        while(isRunning)
        if(email.contains("admin") && password.contains("admin")) {
            displayAdminMenu();
            isRunning = false;
        }
        else if(email.contains("user") && password.contains("user")){
            displayCustomerMenu();
            isRunning = false;
        }
        else{
            System.out.println("wrong data");
        }
    }
}
