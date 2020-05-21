package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.model.User;

import java.util.List;

public class UserLogin {

    public User loginTry(String email, String password) {
        List<User> users;
        users = getSameUser(email, password);
        return users.isEmpty() ? null : users.get(0);
    }

    private List<User> getSameUser(String email, String password) {
        return new UserDao().getUsers(
                "SELECT * FROM users WHERE email = '" + email + "' AND password = '"
                        + password + "';");
    }
}
