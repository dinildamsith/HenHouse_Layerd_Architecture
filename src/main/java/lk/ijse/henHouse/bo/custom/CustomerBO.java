package lk.ijse.henHouse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.henHouse.bo.SuperBO;
import lk.ijse.henHouse.dto.CustomerDTO;
import lk.ijse.henHouse.dto.tm.CustomerTM;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    public String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    ObservableList<CustomerTM> search(String id) throws SQLException, ClassNotFoundException;
    ObservableList getIds() throws SQLException, ClassNotFoundException;

}
