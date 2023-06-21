package lk.ijse.henHouse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.henHouse.bo.custom.impl.EmployeeUniqMethodBOImpl;
import lk.ijse.henHouse.dao.custom.EmployeeDAO;
import lk.ijse.henHouse.dto.EmployeeDTO;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select * from employee");
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        while (rs.next()){
            allEmployee.add(new EmployeeDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7)));
        }
        return allEmployee;
    }

    @Override
    public boolean save(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into employee(Employee_id,Employee_name,Mobile_num,Job_title,Gmail,salary,employee_address) values(?,?,?,?,?,?,?)",entity.getId(),entity.getName(),entity.getContact(),entity.getJob(),entity.getGmail(),entity.getSalary(),entity.getAddress());
    }

    @Override
    public boolean update(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update employee set Employee_name=?,Mobile_num=?,Job_title=?,Gmail=?,salary=?,employee_address=? where Employee_id=?",entity.getName(),entity.getContact(),entity.getJob(),entity.getGmail(),entity.getSalary(),entity.getAddress(),entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT Employee_id FROM employee WHERE Employee_id = ?",id);
        return resultSet.next();
    }

    @Override
    public ObservableList getIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Employee_id FROM employee ");
        ObservableList<String>employees = FXCollections.observableArrayList();
        while (rs.next()){
            employees.add(rs.getString(1));
        }
        return employees;

    }
    @Override
    public ObservableList search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT Employee_id,Employee_name,employee_address,Mobile_num,gmail,salary FROM employee WHERE Employee_id = ?",id);
        ObservableList<EmployeeDTO> customer = FXCollections.observableArrayList();

        return customer;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        EmployeeUniqMethodBOImpl employeeUniqMethodBO = new EmployeeUniqMethodBOImpl();
        String lastEmployeeIdId=employeeUniqMethodBO.getLastEmployeeId();
        if(lastEmployeeIdId==null){
            return "E0001";
        }else{
            String[] split=lastEmployeeIdId.split("[E]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newEmployeeId=String.format("E%04d", lastDigits);
            return newEmployeeId;
        }
    }
}
