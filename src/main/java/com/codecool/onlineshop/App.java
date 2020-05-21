package com.codecool.onlineshop;
import com.codecool.onlineshop.controller.LoginController;
import com.codecool.onlineshop.dao.*;
import com.codecool.onlineshop.model.Product;

import com.codecool.onlineshop.view.UI;


import java.util.List;

public class App {
    public static void main(String[] args) {
        UI ui = new UI();
        UserDao userDao = new UserDao();


        ProductDao productDao = new ProductDao();
        productDao.printFromDB("products","*", "");
        productDao.printAll();
        List<Product> products = productDao.getProducts("SELECT * FROM products;");
        productDao.print("*", "id = 1");
        userDao.printAll();
        LoginController loginController = new LoginController();

            }
}
