package lk.ijse.henHouse.bo.custom.impl;
import lk.ijse.henHouse.bo.BOFactory;
import lk.ijse.henHouse.bo.custom.ItemBO;
import lk.ijse.henHouse.bo.custom.OrderBO;
import lk.ijse.henHouse.bo.custom.OrderPlaceBO;
import lk.ijse.henHouse.dao.custom.impl.OrderDetailsImpl;
import lk.ijse.henHouse.db.DBConnection;
import lk.ijse.henHouse.dto.OrderDetailsDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderPlaceBOImpl implements OrderPlaceBO {

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    @Override
    public boolean placeOrder(String orderId, String date, String customerId, ArrayList<OrderDetailsDTO> items) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        OrderDetailsImpl orderDetailsImpl = new OrderDetailsImpl();
        try {
            connection.setAutoCommit(false);
            boolean isSaved = orderBO.saveOrder(orderId,date,customerId);
            if (isSaved){
                boolean isDetailsSaved = orderDetailsImpl.saveOrderDetails(items);
                if (isDetailsSaved){
                    boolean isUpdated = itemBO.updateItemQty(items);
                    if (isUpdated){
                        connection.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }

    }
}
