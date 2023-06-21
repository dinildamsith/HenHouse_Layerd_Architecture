package lk.ijse.henHouse.dao.custom.impl;

import lk.ijse.henHouse.dao.custom.CustomerUniqMethodDAO;
import lk.ijse.henHouse.dto.CustomerDTO;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerUniqMethodDAOImpl implements CustomerUniqMethodDAO {
    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM customer WHERE Customer_id = ?",id);
        if (rs.next()) {
            return new CustomerDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        return null;
    }

    @Override
    public String getCustomerCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(Customer_id) FROM customer");
        if (rs.next()){
            return rs.getString(1);
        }
        return null;

    }
}
