package com.codecool.onlineshop.model;

import com.codecool.onlineshop.dao.ProductDao;

public class User {
    private int id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final ProductDao productDao;

    public User(int id, String email, String password, String name, String surname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        productDao = new ProductDao();

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    private void incrementID() {
        this.id++;
    }
}
