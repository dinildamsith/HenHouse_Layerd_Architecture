package lk.ijse.henHouse.dao.custom;

import lk.ijse.henHouse.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO {
    boolean saveOrderDetails(ArrayList<OrderDetailsDTO> items) throws SQLException;
    boolean save(OrderDetailsDTO details) throws SQLException;
}
