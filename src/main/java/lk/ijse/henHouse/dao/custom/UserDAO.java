package lk.ijse.henHouse.dao.custom;

import lk.ijse.henHouse.dto.User;

import java.sql.SQLException;

public interface UserDAO {
    boolean saveUser(User user) throws SQLException;
}
