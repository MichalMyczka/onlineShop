package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.dao.OrderDao;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.UI;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuController {

    private final ProductDao productDao;
    private final UserDao userDao;
    private final OrderDao orderDao;
    protected final UI ui;
    protected User user;
    protected Map<String, Runnable> productsMenuMap;


    private MenuController(User user, UI ui) {
        this.user = user;
        this.ui = ui;
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


}
