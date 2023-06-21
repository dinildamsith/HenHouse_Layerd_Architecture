package lk.ijse.henHouse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.henHouse.bo.custom.SupplierBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.SupplierDAO;
import lk.ijse.henHouse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.henHouse.dto.Supplier;
import lk.ijse.henHouse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSupplier = new ArrayList<>();
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier sup : suppliers){
            allSupplier.add(new Supplier(sup.getId(),sup.getName(),sup.getDesc(),sup.getEmail(),sup.getContact(),sup.getAddress()));
        }
        return supplierDAO.getAll();
    }

    @Override
    public boolean saveSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplier.getId(),supplier.getName(),supplier.getDesc(), supplier.getEmail(),supplier.getContact(),supplier.getAddress()));
    }

    @Override
    public boolean updateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplier.getName(),supplier.getDesc(),supplier.getEmail(),supplier.getContact(),supplier.getAddress(),supplier.getId()));
    }

    @Override
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public ObservableList searchSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(id);
    }
}
