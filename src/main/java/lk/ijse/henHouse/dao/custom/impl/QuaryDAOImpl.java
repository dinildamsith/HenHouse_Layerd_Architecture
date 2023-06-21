package lk.ijse.henHouse.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.henHouse.dao.custom.QuaryDAO;
import lk.ijse.henHouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuaryDAOImpl implements QuaryDAO {
    @Override
    public XYChart.Series<String, Double> getIncome() throws SQLException {
        String sql = "SELECT MONTHNAME(Order_date),SUM(qty*unitPrice) FROM order_details\n" +
                "JOIN orders o on o.Order_id = order_details.Order_id GROUP BY MONTHNAME(Order_date) ORDER BY MONTHNAME(Order_date) DESC ";
        ResultSet rs = CrudUtil.execute(sql);
        XYChart.Series<String, Double> data = new XYChart.Series<>();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        double[] income = new double[12];
        while (rs.next()){
            for (int i=0; i< months.length; i++){
                if (rs.getString(1).equals(months[i])){
                    income[i] = rs.getDouble(2);
                }
            }
        }
        int i = 0;
        while (months.length > i){
            data.getData().add(new XYChart.Data<>(months[i],income[i]));
            i++;
        }
        return data;
      }
    }

