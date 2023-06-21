package lk.ijse.henHouse.dao.custom.impl;

import lk.ijse.henHouse.dao.custom.SupplyUniqMethodDAO;
import lk.ijse.henHouse.dto.SupplyDTO;
import lk.ijse.henHouse.dto.tm.SupplyTM;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SupplyUniqMethodDAOImpl implements SupplyUniqMethodDAO{
    @Override
    public String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] strings = currentOrderId.split("Stock-");
            int id = Integer.parseInt(strings[1]);
            id++;

            return (id<10)?"Stock-00"+id:(id>=10)?"Stock-0"+id:"Stock-"+id;
        }
        return "Stock-001";
    }

    @Override
    public boolean updateDetails(SupplyDTO supplyDTO, String stockId) throws SQLException {
        return CrudUtil.execute("UPDATE Supply SET Expire_date=?,Manufacture_Date=?,Unit_Selling_price=?,Qty=? WHERE StockId = ?",
                supplyDTO.getExpireDate(),supplyDTO.getManufactureDate(),supplyDTO.getUnitSellingPrice(),supplyDTO.getQty(),stockId);

    }

    @Override
    public SupplyTM getSupplyDetails(String stockId) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT StockId,Item_code,Supplier_id,Qty,Unit_selling_price,Expire_date,Manufacture_date FROM Supply WHERE StockId=?", stockId);
        if (resultSet.next()) {
            return new SupplyTM(resultSet.getString(1),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getInt(4), resultSet.getDouble(5),
                    LocalDate.parse(resultSet.getString(6)), LocalDate.parse(resultSet.getString(7)));
        }
        return null;
    }
}
