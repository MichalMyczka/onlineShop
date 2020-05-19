package com.codecool.onlineshop;
import com.codecool.onlineshop.dao.*;

public class App {
    public static void main(String[] args) {

        ProductDao productDao = new ProductDao();
        productDao.printFromDB("products", "*", "");
    }
}
