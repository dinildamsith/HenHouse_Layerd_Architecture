package lk.ijse.henHouse.bo.custom.impl;

import lk.ijse.henHouse.bo.custom.SupplierDetailBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.ItemDAO;
import lk.ijse.henHouse.dao.custom.SupplyDAO;
import lk.ijse.henHouse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.SupplyDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.SupplyUniqMethodDAOImpl;
import lk.ijse.henHouse.db.DBConnection;
import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierDetailsBOImpl implements SupplierDetailBO {

    SupplyDAO supplyDAOImpl = (SupplyDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLY);
    ItemDAO itemDAOImpl = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @SneakyThrows
    @Override
    public boolean save(SupplyTM supplyDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            boolean isSavedSupply = supplyDAOImpl.save(supplyDTO);
            if (isSavedSupply) {
                boolean isUpdateItem = itemDAOImpl.updateItem(supplyDTO.getItemCode(), supplyDTO.getQty());
                if (isUpdateItem) {
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public  boolean update(SupplyDTO supplyDTO, int qty, String stockId) throws SQLException {
        ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
        Connection connection = DBConnection.getInstance().getConnection();
        SupplyUniqMethodDAOImpl supplyUniqMethodDAO = new SupplyUniqMethodDAOImpl();
        try {
            connection.setAutoCommit(false);
            boolean isUpdatedSupply = supplyUniqMethodDAO.updateDetails(supplyDTO,stockId);
            System.out.println(isUpdatedSupply);
            if (isUpdatedSupply) {
                boolean isUpdateItem = itemDAOImpl.updateItem(supplyDTO.getItem_code(),qty-supplyDTO.getQty());
                if (isUpdateItem) {
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

}
