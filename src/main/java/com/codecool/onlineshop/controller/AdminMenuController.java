package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.dao.OrderDao;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.dao.UserDao;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.UI;

public class AdminMenuController extends MenuController {

    private final ProductDao productDao;
    private final UserDao userDao;
    private final OrderDao orderDao;

    public AdminMenuController(User user, UI ui) {
        super(user, ui);
        productDao = new ProductDao();
        userDao = new UserDao();
        orderDao = new OrderDao();
        addToProductsMenuMap();

    }

    private void addToProductsMenuMap() {
        productsMenuMap.put("4", this::addProduct);
        productsMenuMap.put("5", this::editProduct);
        productsMenuMap.put("6", this::removeProduct);
        productsMenuMap.put("7", this::addCategory);
        productsMenuMap.put("8", this::printAllCategories);
    }

    private void addProduct() {
        System.out.println("Chosen option - add product");
        String name = ui.takeUserInput("Enter product name: ");
        String price = ui.takeUserInput("Enter product price: ");
        String quantity = ui.takeUserInput("Enter product quantity: ");
        String categoryID = ui.takeUserInput("Enter product category: ");
        String isAvailable = ui.takeUserInput("Set product availability: 0 - unavailable," +
                " 1 - available");
        String[] values = { name, price, quantity, categoryID, isAvailable };
        productDao.addItemToDB(values);
    }

    private void editProduct() {
        System.out.println("Chosen option - edit product");
        String id = ui.takeUserInput("Enter product id: ");
        String column = ui.takeUserInput("Enter product column: ");
        String newValue = ui.takeUserInput("Enter new value: ");
        productDao.updateItem(id, column, newValue);
    }

    private void removeProduct() {
        System.out.println("Chosen option - remove product");
        String id = ui.takeUserInput("Enter product id: ");
        productDao.removeById("products", id);
    }

    private void addCategory() {
        System.out.println("Chosen option - add category");
        String name = ui.takeUserInput("Enter category name: ");
        productDao.addCategory(name);
    }

    private void printAllCategories() {
        System.out.println("Chosen option - print all categories");
        userDao.printFromDB("categories", "*", "");
    }
}
