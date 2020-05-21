package com.codecool.onlineshop.dao;

public class CustomerDao extends DataBaseDao{
    @Override
    public void printFromDB(String table, String columns, String condition) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public void addItemToDB(String[] values) {
        String[] columns = {"name", "price", "quantity",
                "category_id", "is_available"};
        values[0] = String.format("'%s'", values[0]);
        insert("products", columns, values);
    }
}
