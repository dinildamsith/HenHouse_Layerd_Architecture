package lk.ijse.henHouse.bo.custom.impl;

import lk.ijse.henHouse.bo.custom.CustomerUniqMethodBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.CustomerUniqMethodDAO;
import lk.ijse.henHouse.dao.custom.impl.CustomerUniqMethodDAOImpl;
import lk.ijse.henHouse.dto.CustomerDTO;

import java.sql.SQLException;

public class CustomerUniqMethodBOImpl implements CustomerUniqMethodBO {

    CustomerUniqMethodDAO customerUniqMethodDAOImpl = (CustomerUniqMethodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER_UNIQ);

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerUniqMethodDAOImpl.getCustomer(id);
    }

    @Override
    public String getCustomerCount() throws SQLException, ClassNotFoundException {
        return customerUniqMethodDAOImpl.getCustomerCount();
    }
}
