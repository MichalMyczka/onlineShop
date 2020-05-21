package com.codecool.onlineshop.dao;

import com.codecool.onlineshop.view.UI;
import com.jakewharton.fliptables.FlipTableConverters;
import com.codecool.onlineshop.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao extends DataBaseDao{

    UI ui = new UI();

    @Override
    public void printFromDB(String table, String columns, String condition) {
        String message = ui.addOrderPrintMsg();
        super.printFromDB(table, columns, condition, message);
    }

    @Override
    public void printAll() {
        String message = "All orders:";
        printFromDB("SELECT * FROM orders;", message);
    }

    @Override
    public void addItemToDB(String[] values) {
        String[] columns = {"id", "customer_id", "status"};
        values[0] = String.format("'%s'", values[0]);
        insert("orders", columns, values);

    }

    @Override
    public void connectToDB() {
        super.connectToDB();
    }


    public void updateOrderStatus ( int orderId, String status){
        connectToDB();
        PreparedStatement updateOrderStatus;
        String updateOrderStatusString = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
        try {
            updateOrderStatus = connection.prepareStatement(updateOrderStatusString);
            updateOrderStatus.setInt(2, orderId);
            updateOrderStatus.setString(1, status);
            updateOrderStatus.executeUpdate();
            updateOrderStatus.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showOrders(User user) {
        String sql = "SELECT * FROM Orders\n" +
                "WHERE user_id = " + user.getId()+"";
        connectToDB();
        try {
            ResultSet rs  = statement.executeQuery(sql);
            System.out.println(FlipTableConverters.fromResultSet(rs));
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
