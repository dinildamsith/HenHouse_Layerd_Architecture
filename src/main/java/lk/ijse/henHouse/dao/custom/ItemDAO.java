package lk.ijse.henHouse.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.henHouse.dto.ItemDTO;
import lk.ijse.henHouse.dto.OrderDetailsDTO;
import lk.ijse.henHouse.dto.tm.DashboardTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    public  ObservableList<String> getItemIds() throws SQLException;
    public ItemDTO getItem(String id) throws SQLException;
    public  boolean updateItemQty(ArrayList<OrderDetailsDTO> items) throws SQLException;
    boolean updateQty(OrderDetailsDTO details) throws SQLException;
    ObservableList<DashboardTM> getItemDetails() throws SQLException;
    ArrayList<String> getAll() throws SQLException;
    ItemDTO getItemDetails(String value) throws SQLException;
    boolean updateItem(String item_code, int qty) throws SQLException;
}
