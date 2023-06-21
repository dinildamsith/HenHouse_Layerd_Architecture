package lk.ijse.henHouse.dao.custom.impl;

import lk.ijse.henHouse.dao.custom.SupplierUniqMethodDAO;
import lk.ijse.henHouse.dto.SupplierDTO;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierUniqMethodDAOImpl implements SupplierUniqMethodDAO {

    @Override
    public String getLastSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Supplier_id from suppliers order by Supplier_id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllDetails() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT Supplier_id FROM Suppliers");

        ArrayList<String> supIdList=new ArrayList<>();

        while (rst.next()) {
            supIdList.add(rst.getString(1));
        }

        return supIdList;
    }

    @Override
    public SupplierDTO getSupplierDetails(String supId) throws SQLException {
        ResultSet resultSet= CrudUtil.execute("SELECT Supplier_name,Description FROM Suppliers WHERE Supplier_id=?",supId);
        if (resultSet.next()){
            return new SupplierDTO(resultSet.getString(1),resultSet.getString(2));
        }
        return null;
    }
}
