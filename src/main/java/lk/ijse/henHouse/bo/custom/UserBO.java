package lk.ijse.henHouse.bo.custom;

import lk.ijse.henHouse.dto.User;

import java.sql.SQLException;

public interface UserBO {
    boolean saveUser(User user) throws SQLException;
}
