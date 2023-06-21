package lk.ijse.henHouse.dao.custom;
import lk.ijse.henHouse.dto.CustomerDTO;
import java.sql.SQLException;


public interface CustomerUniqMethodDAO {
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;
    public String getCustomerCount()throws SQLException, ClassNotFoundException;
}
