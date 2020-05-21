package com.codecool.onlineshop.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final int id;
    private final List<Product> basketProducts;
    private final int customerID;
    private final LocalDateTime creationDate;
    private final LocalDateTime paymentDate;
    private final OrderStatus status;

    public Order(int id, List<Product> basketProducts, int customerID,
                 LocalDateTime creationDate, LocalDateTime paymentDate, OrderStatus status) {
        this.id = id;
        this.basketProducts = basketProducts;
        this.customerID = customerID;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public List<Product> getBasketProducts() {
        return basketProducts;
    }

    public int getCustomerID() {
        return customerID;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
