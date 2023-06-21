package lk.ijse.henHouse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.henHouse.dao.custom.SupplierDAO;
import lk.ijse.henHouse.dao.custom.SupplierUniqMethodDAO;
import lk.ijse.henHouse.dto.Supplier;
import lk.ijse.henHouse.dto.tm.CustomerTM;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.execute("select * from suppliers");
        ArrayList<Supplier> list=new ArrayList<>();
        while (rs.next()){
            list.add(new Supplier(rs.getString(1),rs.getString(2),rs.getString(3)
                    ,rs.getString(4),rs.getString(5),rs.getString(6)));
        }
        return list;
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into suppliers values(?,?,?,?,?,?)",supplier.getId(),supplier.getName(),supplier.getDesc(), supplier.getEmail(),supplier.getContact(),supplier.getAddress());
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update suppliers set Supplier_name=?,Description=?,Email=?,Contact=?, Address=? where Supplier_id=?",supplier.getName(),supplier.getDesc(),supplier.getEmail(),supplier.getContact(),supplier.getAddress(),supplier.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
       SupplierUniqMethodDAOImpl supplierUniqMethodDAO = new SupplierUniqMethodDAOImpl();
        String lastSupplierIdId=supplierUniqMethodDAO.getLastSupplierId();
        if(lastSupplierIdId==null){
            return "S0001";
        }else{
            String[] split=lastSupplierIdId.split("[S]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newEmployeeId=String.format("S%04d", lastDigits);
            return newEmployeeId;
        }
    }

    @Override
    public ObservableList search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from suppliers where Supplier_id=?",id);
        ObservableList<Supplier> supplier = FXCollections.observableArrayList();
        while (rs.next()){
            supplier.add(new Supplier(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        }
        return supplier;
    }

    @Override
    public ObservableList getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
