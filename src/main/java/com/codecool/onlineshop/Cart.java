package com.codecool.onlineshop;

import java.util.Map;

public class Cart {

    private final Map<Product, Integer> productsInCart;

    public Cart(Map<Product, Integer> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public Map<Product, Integer> getProductsInCart() {
        return productsInCart;
    }

    public void addToCart(Product product) {
        //to do
    }

    public void editCart(Product product, int amount) {
        //to do
    }

    public void emptyCart() {
        productsInCart.clear();
    }

}
