package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.view.UI;

import java.sql.*;
import java.util.List;

public abstract class DataBaseDao<T> implements Dao {

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

    public void printFromDB(String table, String columns, String condition) {
        String where = condition.isEmpty() ? "" : "WHERE " + condition;
        String query = String.format("SELECT %s FROM %s %s;", columns, table, where);
        printFromDB(query);
    }

    protected void printFromDB(String query) {
        connectToDB();
        try {
            ResultSet results = statement.executeQuery(query);
            ui.printTableFromDB(results);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void insert(String table, String[] columns, String[] values) {
        String columnsAsQuery = String.join(",", columns);
        String valuesAsQuery = String.join(",", values);
        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", table,
                columnsAsQuery, valuesAsQuery);
        connectToDB();
        try {
            statement.execute(query);
//            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void updateById(String table, String id, String column, String newValue) {
        String condition = String.format("id = %s", id);
        update(table, column, newValue, condition);
    }

    protected void update(String table, String column, String newValue, String condition) {
        if (column.toLowerCase().equals("id")) {
            System.out.println("Unable to change id");
            return;
        }
        String query = String.format("UPDATE %s SET %s = %s WHERE %s;", table, column, newValue, condition);
        connectToDB();
        try {
            System.out.println("imhere");
            statement.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeById(String table, String id) {
        String query = String.format("DELETE FROM %s WHERE Id = %s;", table, id);
        connectToDB();
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract void printAll();
    public abstract void addItemToDB(String[] values);
    public abstract List<T> getAll();
    public abstract void updateItem(String id, String column, String newValue);
    public abstract void print(String column, String condition);

}
