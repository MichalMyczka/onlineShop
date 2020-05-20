package com.codecool.onlineshop.dao;


import com.codecool.onlineshop.view.UI;

public class ProductDao extends DataBaseDao {

    UI ui = new UI();

    @Override
    public void printFromDB(String query, String message) {
        super.printFromDB(query, message);
    }

    @Override
    public void connectToDB() {
        super.connectToDB();
    }

    @Override
    public void printFromDB(String table, String columns, String condition) {
        String message = ui.addProductPrintMsg();
        super.printFromDB(table, columns, condition, message);
    }

    @Override
    public void addItemToDB(String[] values) {
        String[] columns = {"name", "price", "quantity",
                "category_id", "is_available"};
        values[0] = String.format("'%s'", values[0]);
        insert("products", columns, values);
    }

    @Override
    public void printAll() {
        String message = "All products:";
        printFromDB("SELECT * FROM products;", message);
    }
}
