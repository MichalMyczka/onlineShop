package com.codecool.onlineshop.view;

import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UI {

    public void printTableFromDB(ResultSet resultSet) throws SQLException {
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    public String addProductPrintMsg() {
        return "Print from products";
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

    public String getStringInput() {
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


//
//    public void displayMenu() {
//        clearScreen();
//        boolean isRunning = true;
//        welcomeScreen();
//        int userInput = getNumericInput("", 0, 4);
//        while (isRunning) {
//            if (userInput == 1) {
//
//            } else if (userInput == 2) {
//
//            } else if (userInput == 3) {
//
//            } else if (userInput == 0) {
//                exitProgram();
//            } else {
//
//            }
//        }
//    }

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
}
