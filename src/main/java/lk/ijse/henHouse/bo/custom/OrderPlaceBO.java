package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderPlaceBO {
    boolean placeOrder(String orderId, String date, String customerId, ArrayList<OrderDetailsDTO> items) throws SQLException;
}
