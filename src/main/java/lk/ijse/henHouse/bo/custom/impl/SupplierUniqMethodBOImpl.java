package lk.ijse.henHouse.bo.custom.impl;

import lk.ijse.henHouse.bo.custom.SupplierUniqMethodBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.SupplierUniqMethodDAO;
import lk.ijse.henHouse.dao.custom.impl.SupplierUniqMethodDAOImpl;
import lk.ijse.henHouse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierUniqMethodBOImpl implements SupplierUniqMethodBO {
    SupplierUniqMethodDAO supplierUniqMethodDAO = (SupplierUniqMethodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER_UNIQ);

    @Override
    public String getLastSupplierId() throws SQLException, ClassNotFoundException {
        return supplierUniqMethodDAO.getLastSupplierId();
    }

    @Override
    public ArrayList<String> getAllSupplierDetails() throws SQLException {
        return supplierUniqMethodDAO.getAllDetails();
    }

    @Override
    public SupplierDTO getSupplierDetails(String supId) throws SQLException {
        return supplierUniqMethodDAO.getSupplierDetails(supId);
    }
}
