package com.codecool.onlineshop.dao;

public interface Dao{

    void connectToDB();
    void printFromDB(String table, String columns, String condition);
    void printAll();
    void addItemToDB(String[] values);
    void updateItem(String id, String column, String newValue);
}
