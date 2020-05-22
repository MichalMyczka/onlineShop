package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.model.Admin;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.UI;

import java.util.List;

public class LoginController {
    RegistrationController registrationController;
    private UI ui;
    private boolean loggedAsAdmin;
    private MenuController menuController;


    public LoginController() {
        ui = new UI();
        logInOrRegister();
        User user = logIn();
        setMenuController(user);
        mainMenuChoice(user);
    }

    private User loginTry(String email, String password) {
        List<User> users;
        users = getSameUser(email, password);
        return users.isEmpty() ? null : users.get(0);
    }

    private void setMenuController(User user) {
        menuController = loggedAsAdmin ? new AdminMenuController(user, ui) :
                new CustomerMenuController(user, ui);
    }

    private List<User> getSameUser(String email, String password) {
        return new UserDao().getUsers(
                "SELECT * FROM users WHERE email = '" + email + "' AND password = '"
                        + password + "';");
    }

    private void createNewUser() {
        registrationController = new RegistrationController();
    }

    private User logIn() {
        User loggedUser;
        String email;
        do {
            email = ui.takeUserInput("Email: ").toLowerCase();
            String password = ui.takeUserInput("Password: ");
            loggedUser = loginTry(email, password);
        } while (loggedUser == null);
        System.out.println("Logged in");
        loggedAsAdmin = loggedUser instanceof Admin;
        return loggedUser;
    }

    private void logInOrRegister() {
        boolean registered = false;
        do {
            ui.displayLogInOrRegisterMenu();
            String input = ui.takeUserInput("Choose option");
            if (input.equals("2")) {
                createNewUser();
            } else if (input.equals("1")) {
                registered = true;
                }
            }while (!registered);
    }
    private void mainMenuChoice(User loggedUser) {
        menuController.handleMenu(menuController.getMainMenuMap(),
                loggedAsAdmin ? ui::displayAdminMainMenu : ui::displayCustomerMainMenu);
    }
}
