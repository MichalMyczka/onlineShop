package com.codecool.onlineshop;
import com.codecool.onlineshop.dao.*;
import com.codecool.onlineshop.model.Product;

import java.util.List;

public class App {
    public static void main(String[] args) {

        ProductDao productDao = new ProductDao();
        productDao.printFromDB("products","*", "");
        productDao.printAll();
        List<Product> products = productDao.getProducts("SELECT * FROM products;");
        productDao.print("*", "id = 1");
    }
}
