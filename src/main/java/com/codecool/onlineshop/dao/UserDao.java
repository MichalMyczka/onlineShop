package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserDao extends DataBaseDao{
    @Override
    public void printFromDB(String table, String columns, String condition) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public void addItemToDB(String[] values) {

    }

    public void addUser(User user) {
        connectToDB();
        PreparedStatement addUser;
        String addString = "INSERT INTO Users (name, email, password, surname) VALUES (?, ?, ?, ?, ?)";
        try {
            addUser = connection.prepareStatement(addString);
            addUser.setString(1, user.getName());
            addUser.setString(2, user.getEmail());
            addUser.setString(3, user.getPassword());
            addUser.setString(4, user.getSurname());
            addUser.executeUpdate();
            addUser.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        connectToDB();
        try {
            ResultSet results = statement.executeQuery("SELECT * FROM Users;");
            while (results.next()) {
                users.add(createUser(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String email, String password) {
        connectToDB();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE email = ? and password = ?;");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet results = statement.executeQuery();
            List<User> users = getUsers();
            int indexDifference = 1;
            int id = results.getInt("id") - indexDifference;
            results.close();
            statement.close();
            connection.close();
            return users.get(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new NoSuchElementException("There isn't user with specified data in database");
    }

    private User createUser(ResultSet results) throws SQLException{
        int userId = results.getInt("id");
        String name = results.getString("name");
        String password = results.getString("password");
        String email = results.getString("email");
        String surname = results.getString("surname");
        return new User(userId, name, password, email, surname);
    }


}
