package com.codecool.onlineshop.model;

public enum userRole {
    ADMIN(1), CUSTOMER(2);

    final int userRoleID;

    userRole(int userRoleID) {
        this.userRoleID = userRoleID;
    }

    public int getUserRoleID() {
        return userRoleID;
    }
}
