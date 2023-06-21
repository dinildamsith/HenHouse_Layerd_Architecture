package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.bo.SuperBO;
import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;

import java.sql.SQLException;

public interface SupplyUniqMethodBO extends SuperBO {
    String splitOrderId(String currentOrderId);
    boolean updateDetails(SupplyDTO supplyDTO, String stockId) throws SQLException;
    SupplyTM getSupplyDetails(String stockId) throws SQLException;
}
