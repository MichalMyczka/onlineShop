package com.codecool.onlineshop.view;

import com.codecool.onlineshop.model.Product;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class UI {

    public void printTableFromDB(ResultSet resultSet) throws SQLException {
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    Scanner scanner = new Scanner(System.in);

    private static boolean isNumericValue(String userInput) {
        return !userInput.equals("") && userInput.matches("^[0-9]*$");
    }

    public String takeUserInput(String message) {
        System.out.println(message);
        boolean validInput = true;
        String userInput;
        do {
            if(!validInput) {
                System.out.println("Please enter at least one character");
            }
            validInput = false;
            userInput = scanner.nextLine();
            if(!userInput.equals("")) {
                validInput = true;
            }
        } while (!validInput);
        return userInput;
    }

    public void displayLogInOrRegisterMenu() {
        print(new String[]{"Welcome to OnlineShop",
                "(1) Login",
                "(2) Register"});
    }

    public void print(String[] toPrint) {
        for (String string : toPrint) {
            System.out.println(string);
        }
    }

    public void getEmptyInput(String message) {
        System.out.println(message);
        scanner.next();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public void creatorsList(){
        clearScreen();
        System.out.println("This Project Was Created By:\n Marlena Jakubowska\n Artur Jakubowski\n Michał Myczka\n");
    }

    public void displayBrowseUsersMenu() {
        print(new String[] { " Browse Users Menu",
                "(1) Show all users",
                "(2) Browse users by id",
                "(3) Browse users by column",
                "(4) Remove user",
                "(0) Back" });
    }

    public void displayAdminMainMenu() {
        print(new String[] { " Admin Main Menu",
                "(1) Browse products",
                "(2) Browse users",
                "(3) Browse orders",
                "(0) Exit" });
    }

    public void displayCustomerMainMenu() {
        print(new String[] { " Customer Main Menu",
                "(1) Show profile",
                "(2) Show order history",
                "(3) Browse products",
                "(9) Open Cart",
                "(0) Exit" });
    }

    public void displayAdminBrowseProductsMenu() {
        print(new String[] { " Admin Browse Products Menu",
                "(1) Show all products",
                "(2) Browse by category",
                "(3) Find products by column",
                "(4) Add new product",
                "(5) Edit product",
                "(6) Remove product",
                "(7) Add new category",
                "(8) Show all categories",
                "(0) Back" });
    }

    public void displayCustomerBrowseProductsMenu() {
        print(new String[] { " Customer Browse Products Menu",
                "(1) Show all products",
                "(2) Browse by category",
                "(3) Find products by column",
                "(4) Add product to cart by ID",
                "(9) Open Cart",
                "(0) Back" });
    }

    public void printCart(Map<Product, Integer> map) {
        String[][] data = new String[map.size()][3];
        int i = 0;
        for (Map.Entry<Product, Integer> entry : map.entrySet()) {
            data[i][0] = entry.getKey().getId() + "";
            data[i][1] = entry.getKey().getName();
            data[i++][2] = entry.getValue() + "";
        }
        String[] headers = { "Id", "Product name", "Quantity" };
        System.out.println(FlipTable.of(headers, data));
    }
}
