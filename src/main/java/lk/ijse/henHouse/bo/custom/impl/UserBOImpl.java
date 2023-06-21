package lk.ijse.henHouse.bo.custom.impl;

import lk.ijse.henHouse.bo.custom.UserBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.UserDAO;
import lk.ijse.henHouse.dto.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(User user) throws SQLException {
        return userDAO.saveUser(new User(user.getId(),user.getUser_name(),user.getPassword()));
    }
}
