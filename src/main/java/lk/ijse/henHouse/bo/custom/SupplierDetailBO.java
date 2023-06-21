package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;

import java.sql.SQLException;

public interface SupplierDetailBO {
    boolean save(SupplyTM supplyTM) throws SQLException;
     boolean update(SupplyDTO supplyDTO, int qty, String stockId) throws SQLException;
}
