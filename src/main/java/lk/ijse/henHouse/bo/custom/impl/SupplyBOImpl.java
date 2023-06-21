package lk.ijse.henHouse.bo.custom.impl;

import lk.ijse.henHouse.bo.custom.SupplyBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.SupplyDAO;
import lk.ijse.henHouse.dao.custom.impl.SupplyDAOImpl;
import lk.ijse.henHouse.dao.custom.impl.SupplyUniqMethodDAOImpl;
import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;
import lk.ijse.henHouse.entity.Supply;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyBOImpl implements SupplyBO {

    SupplyDAO supplyDAO = (SupplyDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLY);

    @Override
    public ArrayList<SupplyDTO> getAllSupply() throws SQLException, ClassNotFoundException {
        ArrayList<SupplyDTO> allSupply = new ArrayList<>();
        ArrayList<SupplyTM> supplyDTOS = supplyDAO.getAll();
        for (SupplyTM supplyDTO : supplyDTOS){
            allSupply.add(new SupplyDTO( supplyDTO.getItemCode(), supplyDTO.getSupplyId(), supplyDTO.getManufacturingDate(), supplyDTO.getExpiringDate(),supplyDTO.getUnitPrice(), supplyDTO.getQty()));
        }
        return allSupply;
    }

    @Override
    public boolean saveSupply(SupplyDTO supplyDTO) throws SQLException, ClassNotFoundException {
        return supplyDAO.save(new SupplyTM(supplyDTO.getSupplier_Id(),supplyDTO.getItem_code(),supplyDAO.generateNewID(),supplyDTO.getQty(),supplyDTO.getUnitSellingPrice(),supplyDTO.getExpireDate(),supplyDTO.getManufactureDate()));
    }

    @Override
    public String generateNewSupplyID() throws SQLException, ClassNotFoundException {
        return supplyDAO.generateNewID();
    }

}
