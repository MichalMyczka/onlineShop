package com.codecool.onlineshop;

import java.util.List;

public class Order {
    private final int id;
    private final int customerID;
    private final List<Product> productsInOrder;
    private final String status;

    public Order(int id, int customerID, List<Product> productsInOrder, String status) {
        this.id = id;
        this.customerID = customerID;
        this.productsInOrder = productsInOrder;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public List<Product> getProductsInOrder() {
        return productsInOrder;
    }

    public String getStatus() {
        return status;
    }
}
