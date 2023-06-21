package lk.ijse.henHouse.dao.custom.impl;

import lk.ijse.henHouse.dao.custom.EmployeeUniqMethodDAO;
import lk.ijse.henHouse.dto.EmployeeDTO;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeUniqMethodDAOImpl implements EmployeeUniqMethodDAO {
    @Override
    public String getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT COUNT(Employee_id) FROM employee");
        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getLastEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Employee_id from employee order by Employee_id DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public EmployeeDTO searchEmployeeByText(String txt) throws SQLException, ClassNotFoundException {
        txt="%"+txt+"%";
        ResultSet rs = CrudUtil.execute("select * from employee where Employee_id LIKE ? OR Employee_name LIKE ? OR Gmail LIKE ? OR Mobile_num LIKE ?",txt,txt,txt,txt);
        if(rs.next()){
//            String emp_id = rs.getString(1);
//            String emp_name = rs.getString(2);
//            String emp_contact = rs.getString(3);
//            String emp_job = rs.getString(4);
//            String emp_email = rs.getString(5);
//            double emp_salary = rs.getDouble(6);
//            String emp_address = rs.getString(7);
//
//            return new Employee(emp_id,emp_name,emp_contact,emp_job,emp_email,emp_salary,emp_address);
            return new EmployeeDTO(rs.getString("emp_id"),rs.getString("emp_name"),rs.getString("emp_contact"),rs.getString("emp_job"),rs.getString("emp_email"),rs.getDouble("emp_salary"),rs.getString("emp_address"));
        }
        return null;
    }

    @Override
    public EmployeeDTO search_by_ids() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE Employee_id = ?");
        if (resultSet.next()) {
            return new EmployeeDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6), resultSet.getString(7));
        }
        return null;
    }
}
