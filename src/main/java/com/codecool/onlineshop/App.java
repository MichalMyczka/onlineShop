package com.codecool.onlineshop;
import com.codecool.onlineshop.dao.*;
import com.codecool.onlineshop.view.UI;

public class App {
    public static void main(String[] args) {



        ProductDao productDao = new ProductDao();
        OrderDao orderDao = new OrderDao();
        productDao.printFromDB("products", "*", "");
        productDao.printAll();

        UI ui = new UI();
        ui.displayMenu();
    }
}
