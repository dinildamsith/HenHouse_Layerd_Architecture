package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.bo.SuperBO;
import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplyBO extends SuperBO {
    ArrayList<SupplyDTO> getAllSupply() throws SQLException, ClassNotFoundException;
    boolean saveSupply(SupplyDTO supplyDTO) throws SQLException, ClassNotFoundException;
    //boolean updateSupply(SupplyDTO entity) throws SQLException, ClassNotFoundException;
    String generateNewSupplyID() throws SQLException, ClassNotFoundException;

}
