package com.codecool.onlineshop.dao;

public interface Dao{

    void connectToDB();
    void printFromDB(String table, String columns, String condition);
    void addItemToDB(String[] values);
}
