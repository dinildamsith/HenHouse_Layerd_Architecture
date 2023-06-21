package lk.ijse.henHouse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.henHouse.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException;

    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException;
    ObservableList getIds() throws SQLException, ClassNotFoundException;
}
