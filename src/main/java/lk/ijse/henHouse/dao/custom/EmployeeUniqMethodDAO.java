package lk.ijse.henHouse.dao.custom;

import lk.ijse.henHouse.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeUniqMethodDAO {
    String getEmployeeCount()throws SQLException, ClassNotFoundException;
    String getLastEmployeeId()throws SQLException, ClassNotFoundException;
    EmployeeDTO searchEmployeeByText(String txt)throws SQLException, ClassNotFoundException;
    EmployeeDTO search_by_ids()throws SQLException, ClassNotFoundException;
}
