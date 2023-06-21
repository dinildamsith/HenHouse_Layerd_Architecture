package lk.ijse.henHouse.dao.custom.impl;

import lk.ijse.henHouse.dao.custom.UserDAO;
import lk.ijse.henHouse.dto.User;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean saveUser(User user) throws SQLException {
        return CrudUtil.execute("insert into user(User_id,User_name,Password) values(?, ?, ?)",user.getId(),user.getUser_name(),user.getPassword());
    }
}
