package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.UI;

import java.sql.*;

public class Dao<T> {

    protected Connection connection;
    protected Statement statement;
    private UI ui;

    public void connectToDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/onlineShopDB");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        } catch (SQLException e) {
            System.out.println("Could not connect to DB " + e.getMessage());
        }
        System.out.println("connected to db"); //for testing
    }

    public void printTableFromDB(String query) {
        connectToDB();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            ui.printTableFromDB(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
