package lk.ijse.henHouse.bo.custom.impl;
import lk.ijse.henHouse.bo.custom.OrderBO;
import lk.ijse.henHouse.dao.DAOFactory;
import lk.ijse.henHouse.dao.custom.OrderDAO;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAOImpl = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String generateOrderId() throws SQLException {
        return orderDAOImpl.generateOrderId();
    }

    @Override
    public boolean saveOrder(String orderId, String date, String customerId) throws SQLException {
        return orderDAOImpl.saveOrder(orderId,date,customerId);
    }

    @Override
    public String getOrderCount() throws SQLException {
        return orderDAOImpl.getOrderCount();
    }
}
