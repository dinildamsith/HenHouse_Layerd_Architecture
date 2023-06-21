package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.bo.SuperBO;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    String generateOrderId() throws SQLException;
    boolean saveOrder(String orderId, String date, String customerId) throws SQLException;
    String getOrderCount() throws SQLException;
}
