package com.codecool.onlineshop.model;

public class User {
    private final int id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;

    public User(int id, String email, String password, String name, String surname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
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
}
