package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.view.UI;

import java.sql.*;

public abstract class DataBaseDao implements Dao {

    protected Connection connection;
    protected Statement statement;
    private final UI ui = new UI();

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

    public void printFromDB(String table, String columns, String condition, String message) {
        String where = condition.isEmpty() ? "" : "WHERE " + condition;
        String query = String.format("SELECT %s FROM %s %s;", columns, table, where);
        printFromDB(query, message);
    }

    public void printFromDB(String query, String message) {
        connectToDB();
        try {
            ResultSet results = statement.executeQuery(query);
            ui.printTableFromDB(results, message);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void insert(String table, String[] columns, String[] values) {
        String columnsQuery = String.join(",", columns);
        String valuesQuery = String.join(",", values);
        String query = String.format("INSERT INTO %s (%s) VALUES (%s);",
                table, columnsQuery, valuesQuery);
        connectToDB();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract void printAll();

    public abstract void addItemToDB(String[] values);

}
