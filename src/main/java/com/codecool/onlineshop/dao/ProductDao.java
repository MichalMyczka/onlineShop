package com.codecool.onlineshop.dao;

public class ProductDao extends DataBaseDao {

    @Override
    public void printFromDB(String query) {
        super.printFromDB(query);
    }

    @Override
    public void connectToDB() {
        super.connectToDB();
    }

    @Override
    public void printFromDB(String table, String columns, String condition) {
        super.printFromDB(table, columns, condition);
    }

    @Override
    public void printRowFromDB() {

    }
}
