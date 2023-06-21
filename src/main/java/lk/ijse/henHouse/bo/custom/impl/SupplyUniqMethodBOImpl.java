package lk.ijse.henHouse.bo.custom.impl;

import lk.ijse.henHouse.bo.custom.SupplyUniqMethodBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.SupplyUniqMethodDAO;
import lk.ijse.henHouse.dao.custom.impl.SupplyUniqMethodDAOImpl;
import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;

import java.sql.SQLException;

public class SupplyUniqMethodBOImpl implements SupplyUniqMethodBO {

    SupplyUniqMethodDAO supplyUniqMethodDAO = (SupplyUniqMethodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLY_UNIQ);

    @Override
    public String splitOrderId(String currentOrderId) {
        return supplyUniqMethodDAO.splitOrderId(currentOrderId);
    }

    @Override
    public boolean updateDetails(SupplyDTO supplyDTO, String stockId) throws SQLException {
        return supplyUniqMethodDAO.updateDetails(supplyDTO,stockId);
    }

    @Override
    public SupplyTM getSupplyDetails(String stockId) throws SQLException {
        return supplyUniqMethodDAO.getSupplyDetails(stockId);
    }
}
