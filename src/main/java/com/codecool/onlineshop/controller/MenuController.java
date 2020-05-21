package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.dao.OrderDao;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.model.Admin;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.UI;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public abstract class MenuController {

    private final ProductDao productDao;
    private final UserDao userDao;
    private final OrderDao orderDao;
    protected final UI ui;
    protected User user;
    protected Map<String, Runnable> productsMenuMap;
    protected final Map<String, Runnable> mainMenuMap;


    protected MenuController(User user, UI ui) {
        this.user = user;
        this.ui = ui;
        mainMenuMap = new HashMap<>();
        createProductsMenuMap();
        this.productDao = new ProductDao();
        this.userDao = new UserDao();
        this.orderDao = new OrderDao();
    }

    private void createProductsMenuMap() {
        productsMenuMap = new HashMap<>();
        productsMenuMap.put("1", this::getAllProducts);
        productsMenuMap.put("2", this::getProductsByCategory);
        productsMenuMap.put("3", this::getProductsWhichContain);
    }

    private void getAllProducts() {
        productDao.printAll();
    }

    private void getProductsByCategory() {
        String category = ui.takeUserInput("Enter category: ");
        String table = "products JOIN categories ON products.category_id = categories.id;";
        String condition = "categories.name = '" + category + "'";
        productDao.printFromDB(table, "*", condition);
    }

    private void getProductsWhichContain() {
        String column = ui.takeUserInput("Enter column: ");
        String toFind = ui.takeUserInput("Enter what you want to find: ");
        productDao.print("*", column + " LIKE '%" + toFind + "%';");
    }

    public void handleMenu(Map<String, Runnable> menuMap, Runnable uiMenu) {
        boolean isRunning = true;
        do {
            uiMenu.run();
            String input = ui.takeUserInput("Choose option ");
            if (input.equals("0")) {
                isRunning = false;
                continue;
            }
            try {
                menuMap.get(input).run();
            } catch (NullPointerException e) {
                System.out.println("No such option");
            }
        } while (isRunning);
    }

    protected void browseProducts() {
        if (user instanceof Admin) {
            handleMenu(productsMenuMap, ui::displayAdminBrowseProductsMenu);
        } else {
            handleMenu(productsMenuMap, ui::displayCustomerBrowseProductsMenu);
        }
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }

    protected void userProfile() {
    // to do
        System.out.println("work in progress");
    }

    protected void getOrdersByUserId() {
        // TO DO
        System.out.println("Work in progress");
    }

}
