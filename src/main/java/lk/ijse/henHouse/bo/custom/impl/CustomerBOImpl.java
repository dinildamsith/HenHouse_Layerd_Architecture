package lk.ijse.henHouse.bo.custom.impl;
import javafx.collections.ObservableList;
import lk.ijse.henHouse.bo.custom.CustomerBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.CustomerDAO;
import lk.ijse.henHouse.dto.CustomerDTO;
import lk.ijse.henHouse.dto.tm.CustomerTM;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.henHouse.entity.Customer;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAOImpl = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        ArrayList<Customer> all =customerDAOImpl.getAll();

        for (Customer customer : all){
            allCustomer.add(new CustomerDTO(customer.getCustomerId(),customer.getCustomerName(),customer.getContact(),customer.getAddress(),customer.getGmail()));
        }
        return allCustomer;
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAOImpl.save(new Customer(dto.getCustomerId(),dto.getCustomerName(),dto.getContact(),dto.getAddress(),dto.getGmail()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAOImpl.update(new Customer(dto.getCustomerId(),dto.getCustomerName(),dto.getContact(),dto.getAddress(),dto.getGmail()));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAOImpl.exist(id);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAOImpl.generateNewID();
    }

    @Override
    public ObservableList<CustomerTM> search(String id) throws SQLException, ClassNotFoundException {
        return customerDAOImpl.search(id);
    }

    @Override
    public ObservableList getIds() throws SQLException, ClassNotFoundException {
        return customerDAOImpl.getIds();
    }
}
