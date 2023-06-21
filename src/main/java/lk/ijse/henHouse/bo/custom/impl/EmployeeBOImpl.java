package lk.ijse.henHouse.bo.custom.impl;
import javafx.collections.ObservableList;
import lk.ijse.henHouse.bo.custom.EmployeeBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.EmployeeDAO;
import lk.ijse.henHouse.dto.EmployeeDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAOImpl = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployee= new ArrayList<>();
        ArrayList<EmployeeDTO> all = employeeDAOImpl.getAll();

        for (EmployeeDTO emp : all){
            allEmployee.add(new EmployeeDTO(emp.getId(),emp.getName(),emp.getContact(),emp.getJob(),emp.getGmail(),emp.getSalary(),emp.getAddress()));
        }
        return allEmployee;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAOImpl.save(new EmployeeDTO(dto.getId(),dto.getName(),dto.getContact(),dto.getJob(),dto.getGmail(),dto.getSalary(),dto.getAddress()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAOImpl.update(new EmployeeDTO(dto.getId(),dto.getName(),dto.getContact(),dto.getJob(),dto.getGmail(),dto.getSalary(),dto.getAddress()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAOImpl.exist(id);
    }

    @Override
    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException {
        return employeeDAOImpl.generateNewID();
    }

    @Override
    public ObservableList getIds() throws SQLException, ClassNotFoundException {
        return employeeDAOImpl.getIds();
    }
}
