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
    public void addItemToDB(String[] values) {
        String[] columns = {"name", "price", "quantity",
                "category_id", "is_available"};
        values[0] = String.format("'%s'", values[0]);
        insert("products", columns, values);
    }
}
