package lk.ijse.henHouse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.henHouse.bo.custom.ItemBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.ItemDAO;
import lk.ijse.henHouse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.henHouse.dto.ItemDTO;
import lk.ijse.henHouse.dto.OrderDetailsDTO;
import lk.ijse.henHouse.dto.tm.DashboardTM;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAOImpl = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean updateItem(String item_code, int qty) throws SQLException {
        return itemDAOImpl.updateItem(item_code,qty);
    }
    @Override
    public boolean updateQty(OrderDetailsDTO details) throws SQLException {
        return itemDAOImpl.updateQty(new OrderDetailsDTO(details.getQty(),details.getItemCode()));
    }

    @Override
    public ItemDTO getItem(String id) throws SQLException {
        return itemDAOImpl.getItem(id);
    }

    @Override
    public boolean updateItemQty(ArrayList<OrderDetailsDTO> items) throws SQLException {
        return itemDAOImpl.updateItemQty(items);
    }



    @Override
    public ObservableList<DashboardTM> getItemDetails() throws SQLException {
        return itemDAOImpl.getItemDetails();
    }

    @Override
    public ArrayList<String> getAll() throws SQLException {
        return itemDAOImpl.getAll();
    }

    @Override
    public ItemDTO getItemDetails(String value) throws SQLException {
        return itemDAOImpl.getItemDetails(value);
    }


    @Override
    public ObservableList<String> getItemIds() throws SQLException {
        return itemDAOImpl.getItemIds();
    }
}
