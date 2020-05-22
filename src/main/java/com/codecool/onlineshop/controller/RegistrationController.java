package com.codecool.onlineshop.controller;
import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.UI;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController {
    private final UI ui;

    public RegistrationController() {
        ui = new UI();
        startRegistration();
    }

    public void startRegistration() {
        inputUserData();
    }

    private void inputUserData() {
        String email = ui.takeUserInput("Enter email").toLowerCase();
        UserDao userDao = new UserDao();
        List<User> duplicatedEmailUsers = userDao.getUsers("SELECT * FROM Users WHERE email = \"" + email + "\";");
        if (alreadyUsedEmail(duplicatedEmailUsers)) {
            ui.getEmptyInput("Email already used");
            return;
        }
        if (!isEmaiInputValid(email)) {
            ui.getEmptyInput("Invalid email address");
            return;
        }
        String password = ui.takeUserInput("Enter password");
        String name = ui.takeUserInput("Enter name");
        String surname = ui.takeUserInput("Enter surname");
        String role = "2";

        String[] values = {name, surname, email, password, role};
        userDao.addItemToDB(values);
    }

    public boolean  alreadyUsedEmail(List<User> duplicatedEmailUsers) {
        return !duplicatedEmailUsers.isEmpty();
    }

    public boolean isEmaiInputValid(String email) {
        String p = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
                "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String setRoleAsTwo() {
        return "2";
    }
}
