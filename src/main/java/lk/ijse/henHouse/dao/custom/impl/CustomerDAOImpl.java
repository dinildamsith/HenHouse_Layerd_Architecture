package lk.ijse.henHouse.dao.custom.impl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.henHouse.dao.custom.CustomerDAO;
import lk.ijse.henHouse.dto.tm.CustomerTM;
import lk.ijse.henHouse.entity.Customer;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT Customer_id,Customer_name,Customer_contact,Customer_address,Customer_gmail FROM customer ORDER BY Customer_id");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (resultSet.next()){
            allCustomers.add(new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)",entity.getCustomerId(),entity.getCustomerName(),entity.getContact(),entity.getAddress(),entity.getGmail());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET Customer_name = ?, Customer_contact = ?, Customer_gmail = ?, Customer_address = ? WHERE Customer_id = ?",entity.getCustomerName(),entity.getContact(),entity.getGmail(),entity.getAddress(),entity.getCustomerId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT Customer_id FROM customer WHERE Customer_id = ?",id);
        return resultSet.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT Customer_id FROM customer ORDER BY Customer_id DESC LIMIT 1");
        return resultSet.next() ? String.format("C-%03d", (Integer.parseInt(resultSet.getString(1).replace("C-", "")) + 1)) : "C-001";
    }

    @Override
    public ObservableList<CustomerTM> search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Customer_id,Customer_name,Customer_contact,Customer_address,Customer_gmail FROM customer WHERE Customer_id = ?",id);
        ObservableList<CustomerTM> customer = FXCollections.observableArrayList();
        while (rs.next()){
            customer.add(new CustomerTM(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        return customer;
    }

    @Override
    public ObservableList getIds() throws SQLException, ClassNotFoundException {
        ResultSet rs =CrudUtil.execute("SELECT Customer_id FROM customer ORDER BY Customer_id");
        ObservableList<String> customers = FXCollections.observableArrayList();
        while (rs.next()){
            customers.add(rs.getString(1));
        }
        return customers;
    }



}
