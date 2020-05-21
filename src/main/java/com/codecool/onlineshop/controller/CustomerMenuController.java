package com.codecool.onlineshop.controller;

import com.codecool.onlineshop.dao.OrderDao;
import com.codecool.onlineshop.dao.ProductDao;
import com.codecool.onlineshop.model.Cart;
import com.codecool.onlineshop.model.Product;
import com.codecool.onlineshop.model.User;
import com.codecool.onlineshop.view.UI;

import java.util.List;

public class CustomerMenuController extends MenuController {
    private final Cart cart;
    private final ProductDao productDao;
    private final OrderDao orderDao;


    public CustomerMenuController(User user, UI ui) {
        super(user, ui);
        cart = new Cart();
        productDao = new ProductDao();
        orderDao = new OrderDao();
        addToProductsMenuMap();
    }

    private void addToProductsMenuMap() {
        productsMenuMap.put("4", this::addToCart);
        productsMenuMap.put("9", this::showCart);
    }

    private void addToCart() {
        System.out.println("Chosen option - add product to cart");
        String id = ui.takeUserInput("Enter product id to add to cart: ");
        int amount = ui.getNumericInput("Enter amount: ", 1, 10);

        List<Product> productList = productDao.getProducts("SELECT * FROM Products WHERE " +
                "id =" + id + ";");

        if(productList.isEmpty()) {
            ui.getEmptyInput("No product found.");
            return;
        }
        if (productList.get(0).getQuantity() < amount) {
            ui.getEmptyInput("Not enough products.");
            return;
        }
        for (int i = 0; i < amount; i++) {
            this.cart.addToCart(productList.get(0));
        }
    }

    private void showCart() {
        System.out.println("work in progress");
        //to do
    }
}
