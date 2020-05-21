package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.model.Order;
import com.codecool.onlineshop.model.OrderStatus;
import com.codecool.onlineshop.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends DataBaseDao<Order> {

    public List<Order> getOrders(String query) {
        List<Order> orders = new ArrayList<>();
        connectToDB();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                ProductDao productDao = new ProductDao();
                List<Product> basketProducts = productDao.getBasketProducts(id);
                int customerId = resultSet.getInt("customer_id");
                OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("status"));
                String creationDateString = resultSet.getString("creation_date");
                LocalDateTime creationDate = LocalDateTime.parse(creationDateString);
                String paymentDateString = resultSet.getString("payment_date");
                LocalDateTime paymentDate;
                try {
                    paymentDate = LocalDateTime.parse(paymentDate);
                } catch (java.time.format.DateTimeParseException e) {
                    paymentDate = null;
                }
                Order order = new Order(id,basketProducts, customerId, creationDate, paymentDate, status);
                orders.add(order);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }



    @Override
    public void printAll() {
        printFromDB("SELECT * FROM orders;");
    }

    @Override
    public void addItemToDB(String[] values) {
        String[] columns = {"customer_id", "creation_date", "payment_date", "status"};
        for (int i = 1; i < 4; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        insert("orders", columns, values);
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void updateItem(String id, String column, String newValue) {
        newValue = String.format("'%s'", newValue);
        updateById("orders", id, column, newValue);
    }

    @Override
    public void print(String column, String condition) {
        printFromDB("orders", column, condition);
    }
}
