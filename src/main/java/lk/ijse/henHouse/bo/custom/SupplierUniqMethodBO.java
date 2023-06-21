package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.bo.SuperBO;
import lk.ijse.henHouse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierUniqMethodBO extends SuperBO {
    String getLastSupplierId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierDetails() throws SQLException;
    SupplierDTO getSupplierDetails(String supId) throws SQLException;

}
