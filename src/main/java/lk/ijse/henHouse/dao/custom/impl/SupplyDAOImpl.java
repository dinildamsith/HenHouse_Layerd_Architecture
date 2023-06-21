package lk.ijse.henHouse.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.henHouse.dao.custom.SupplyDAO;
import lk.ijse.henHouse.dao.custom.SupplyUniqMethodDAO;
import lk.ijse.henHouse.dto.tm.SupplyTM;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



public class SupplyDAOImpl implements SupplyDAO {
    @Override
    public ArrayList<SupplyTM> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplyTM> supplyList=new ArrayList<>();
        ResultSet resultSet= CrudUtil.execute("SELECT StockId,Item_code,Supplier_id,Qty,Unit_selling_price,Expire_date FROM Supply");
        while (resultSet.next()){
            supplyList.add(new SupplyTM(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5), LocalDate.parse(resultSet.getString(6))));
        }
        return supplyList;

    }

    @Override
    public boolean save(SupplyTM supplyDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Supply VALUES (?,?,?,?,?,?,?)", supplyDTO.getExpiringDate(), supplyDTO.getManufacturingDate(), supplyDTO.getUnitPrice(), supplyDTO.getQty(), supplyDTO.getItemCode(), supplyDTO.getSupplyId(),generateNewID());
    }

    @Override
    public boolean update(SupplyTM entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        SupplyUniqMethodDAO supplyUniqMethodDAO = new SupplyUniqMethodDAOImpl();
        ResultSet resultSet = CrudUtil.execute("SELECT StockId FROM Supply ORDER BY StockId DESC LIMIT 1");

        if(resultSet.next()) {
            return supplyUniqMethodDAO.splitOrderId(resultSet.getString(1));
        }
        return supplyUniqMethodDAO.splitOrderId(null);
    }

    @Override
    public ObservableList search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
