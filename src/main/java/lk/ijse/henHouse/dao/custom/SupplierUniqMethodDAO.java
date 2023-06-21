package lk.ijse.henHouse.dao.custom;

import lk.ijse.henHouse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierUniqMethodDAO {
    String getLastSupplierId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllDetails() throws SQLException;
    SupplierDTO getSupplierDetails(String supId) throws SQLException;

}
