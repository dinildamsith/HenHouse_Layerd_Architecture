package lk.ijse.henHouse.bo.custom.impl;

import lk.ijse.henHouse.bo.custom.EmployeeUniqMethodBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.EmployeeUniqMethodDAO;
import lk.ijse.henHouse.dto.EmployeeDTO;

import java.sql.SQLException;

public class EmployeeUniqMethodBOImpl implements EmployeeUniqMethodBO {

    EmployeeUniqMethodDAO employeeUniqMethodDAOImpl = (EmployeeUniqMethodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE_UNIQ);

    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeUniqMethodDAOImpl.getEmployeeCount();
    }

    @Override
    public String getLastEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeUniqMethodDAOImpl.getLastEmployeeId();
    }

    @Override
    public EmployeeDTO searchEmployeeByText(String txt) throws SQLException, ClassNotFoundException {
        return employeeUniqMethodDAOImpl.searchEmployeeByText(txt);
    }

    @Override
    public EmployeeDTO search_by_Employees_ids() throws SQLException, ClassNotFoundException {
        return employeeUniqMethodDAOImpl.search_by_ids();
    }
}
