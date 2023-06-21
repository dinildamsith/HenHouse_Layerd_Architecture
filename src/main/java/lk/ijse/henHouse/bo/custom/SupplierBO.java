package lk.ijse.henHouse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.henHouse.bo.SuperBO;
import lk.ijse.henHouse.dto.Supplier;
import lk.ijse.henHouse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException;
    String generateNewSupplierID() throws SQLException, ClassNotFoundException;
    ObservableList searchSupplier(String id) throws SQLException, ClassNotFoundException;
}
