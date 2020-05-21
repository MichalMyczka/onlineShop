package com.codecool.onlineshop.model;

public class Product {

    private final int id;
    private final String name;
    private final float price;
    private final  int quantity;
    private final int categoryID;
    private final Boolean isAvailable;
    // private final int rating;

    public Product(int id, String name, float price, int quantity, int categoryID,
                   boolean isAvailable){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

//    public int getRating() {
//        return rating;
//    }

}
