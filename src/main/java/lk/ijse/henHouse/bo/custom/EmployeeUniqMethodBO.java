package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeUniqMethodBO {
    String getEmployeeCount() throws SQLException, ClassNotFoundException;
    String getLastEmployeeId() throws SQLException, ClassNotFoundException;
    EmployeeDTO searchEmployeeByText(String txt) throws SQLException, ClassNotFoundException;
    EmployeeDTO search_by_Employees_ids() throws SQLException, ClassNotFoundException;
}
