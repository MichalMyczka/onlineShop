package com.codecool.onlineshop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Map<Product, Integer> productsInCart;

    public Cart() {
        productsInCart = new HashMap<>();
    }

    public Map<Product, Integer> getProductsInCart() {
        return productsInCart;
    }

    public void addToCart(Product product) {
        productsInCart.merge(product, 1, Integer::sum);
    }

    public void editCart(Product product, int amount) {
        productsInCart.put(product, amount);
    }

    public void emptyCart() {
        productsInCart.clear();
    }

    public void clearOnceNoProducts() {
        productsInCart.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

}
