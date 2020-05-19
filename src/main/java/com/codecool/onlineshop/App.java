package com.codecool.onlineshop;
import com.codecool.onlineshop.dao.*;

public class App {
    public static void main(String[] args) {

        Dao dao = new Dao();
        dao.printFromDB("products", "*", "");

    }
}
