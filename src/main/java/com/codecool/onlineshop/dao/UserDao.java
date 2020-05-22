package com.codecool.onlineshop.dao;


import com.codecool.onlineshop.model.Admin;
import com.codecool.onlineshop.model.Customer;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.model.userRole;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DataBaseDao<User>{

    public List<User> getUsers(String query) {
        List<User> users = new ArrayList<>();
        connectToDB();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                if (resultSet.getInt("role") == userRole.CUSTOMER.getUserRoleID()) {
                    User customer = new Customer(id, email, password, name, surname);
                    users.add(customer);
                } else {
                    User admin = new Admin(id, email, password, name, surname);
                    users.add(admin);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void printAll() {
        printFromDB("SELECT * FROM users");
    }

    @Override
    public void addItemToDB(String[] values) {
        String[] columns = {"name", "surname", "email", "password", "role"};
        for (int i = 0; i < columns.length; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        String table = "users";
        insert(table, columns, values);
    }

    @Override
    public List<User> getAll() {
        return getUsers("SELECT * FROM users;");
    }

    @Override
    public void updateItem(String id, String column, String newValue) {
        newValue = String.format("'%s", newValue);
        updateById("users", id, column, newValue);
    }

    @Override
    public void print(String column, String condition) {
        printFromDB("users", column, condition);
    }
}
