package com.codecool.onlineshop.view;

import com.jakewharton.fliptables.FlipTableConverters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UI {

    public void printTableFromDB(ResultSet resultSet, String message) throws SQLException {
        System.out.println(message);
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    public String addProductPrintMsg() {
        return "Print from products";
    }
}
