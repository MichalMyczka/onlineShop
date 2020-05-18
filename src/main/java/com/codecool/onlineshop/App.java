package com.codecool.onlineshop;
import com.codecool.onlineshop.dao.*;

public class App {
    public static void main(String[] args) {

        System.out.println("test");
        Dao dao = new Dao();
        dao.connectToDB();

    }
}
