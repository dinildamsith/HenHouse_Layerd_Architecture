package lk.ijse.henHouse.dao.custom.impl;

import lk.ijse.henHouse.dao.custom.OrderDetailsDAO;
import lk.ijse.henHouse.dto.OrderDetailsDTO;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsImpl implements OrderDetailsDAO {
    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetailsDTO> items) throws SQLException {
        for (OrderDetailsDTO details : items) {
            if (!save(details)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(OrderDetailsDTO details) throws SQLException {
        return CrudUtil.execute("INSERT INTO order_details VALUES (?,?,?,?,?)",details.getOrderId(),details.getItemCode(),details.getQty(),details.getTotal(),details.getUnitPrice());
    }
}
