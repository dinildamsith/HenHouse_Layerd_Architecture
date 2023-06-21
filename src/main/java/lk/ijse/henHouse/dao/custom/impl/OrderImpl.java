package lk.ijse.henHouse.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.henHouse.dao.custom.OrderDAO;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderImpl implements OrderDAO {
    @Override
    public String generateOrderId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT Order_id FROM orders ORDER BY Order_id DESC LIMIT 1");
        return rs.next() ? String.format("O-%03d", (Integer.parseInt(rs.getString(1).replace("O-", "")) + 1)) : "O-001";
    }

    @Override
    public boolean saveOrder(String orderId, String date, String customerId) throws SQLException {
        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?)",orderId,date,customerId);
    }

    @Override
    public String getOrderCount() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(Order_id) FROM orders");
        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }


    }

