package com.codecool.onlineshop.dao;


import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.view.UI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends DataBaseDao<Product> {

    UI ui = new UI();

    public List<Product> getProducts(String query) {
        List<Product> products = new ArrayList<>();
        connectToDB();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                int quantity = resultSet.getInt("quantity");
                int categoryId = resultSet.getInt("category_id");
                boolean isAvailable = resultSet.getBoolean("is_available");

                Product product = new Product(id, name, price, quantity, categoryId, isAvailable);
                products.add(product);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  products;
    }


    @Override
    public void updateItem(String id, String column, String newValue) {
        newValue = column.toLowerCase().equals("name") ? String.format("'%s", newValue) : newValue;
        updateById("products", id, column, newValue);
    }

    @Override
    public void print(String column, String condition) {
        printFromDB("products", column, condition);
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
        printFromDB("SELECT * FROM products;");
    }

    @Override
    public List<Product> getAll() {
        return getProducts("SELECT * FROM products;");
    }
}
