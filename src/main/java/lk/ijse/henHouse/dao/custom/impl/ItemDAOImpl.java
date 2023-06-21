package lk.ijse.henHouse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.henHouse.dao.custom.ItemDAO;
import lk.ijse.henHouse.dto.ItemDTO;
import lk.ijse.henHouse.dto.OrderDetailsDTO;
import lk.ijse.henHouse.dto.tm.DashboardTM;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ObservableList<String> getItemIds() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT Item_code FROM item ORDER BY Item_code");
        ObservableList<String> items = FXCollections.observableArrayList();
        while (rs.next()){
            items.add(rs.getString(1));
        }
        return items;
    }

    @Override
    public ItemDTO getItem(String id) throws SQLException {
        ResultSet rs= CrudUtil.execute("SELECT * FROM item WHERE Item_code = ?",id);
        if (rs.next()){
            return new ItemDTO(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
        }
        return null;
    }

    @Override
    public boolean updateItemQty(ArrayList<OrderDetailsDTO> items) throws SQLException {
        for (OrderDetailsDTO details : items) {
            if (!updateQty(details)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(OrderDetailsDTO details) throws SQLException {
        return CrudUtil.execute("UPDATE item SET Qty_on_hand = (Qty_on_hand - ?) WHERE Item_code = ?",details.getQty(),details.getItemCode());
    }

    @Override
    public ObservableList<DashboardTM> getItemDetails() throws SQLException {
        ResultSet rs= CrudUtil.execute("SELECT Item_code,Description,Qty_on_hand FROM item ORDER BY Item_code");
        ObservableList<DashboardTM> items = FXCollections.observableArrayList();
        while (rs.next()){
            items.add(new DashboardTM(rs.getString(1),rs.getString(2),rs.getInt(3)));
        }
        return items;
    }

    @Override
    public ArrayList<String> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT Item_code FROM Item");
        ArrayList<String> itemIdList=new ArrayList<>();
        while (rst.next()) {
            itemIdList.add(rst.getString(1));
        }

        return itemIdList;
    }

    @Override
    public ItemDTO getItemDetails(String value) throws SQLException {
        ResultSet resultSet=CrudUtil.execute("SELECT Description,Qty_on_hand FROM Item WHERE Item_code=?",value);
        if (resultSet.next()){
            return new ItemDTO(resultSet.getString(1),resultSet.getInt(2));
        }
        return null;
    }

    @Override
    public boolean updateItem(String item_code, int qty) throws SQLException {
        return CrudUtil.execute("UPDATE item SET Qty_on_hand = (Qty_on_hand + ?) WHERE Item_code = ?",qty,item_code);
    }
}
