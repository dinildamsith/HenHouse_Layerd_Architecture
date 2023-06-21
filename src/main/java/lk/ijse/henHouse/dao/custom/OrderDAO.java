package lk.ijse.henHouse.dao.custom;

import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface OrderDAO {
    String generateOrderId() throws SQLException;
    boolean saveOrder(String orderId, String date, String customerId) throws SQLException;
    String getOrderCount() throws SQLException;

}
