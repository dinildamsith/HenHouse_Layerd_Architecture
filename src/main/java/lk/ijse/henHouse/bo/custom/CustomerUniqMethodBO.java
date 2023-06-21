package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerUniqMethodBO {
    CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;
    String getCustomerCount() throws SQLException, ClassNotFoundException;
}
