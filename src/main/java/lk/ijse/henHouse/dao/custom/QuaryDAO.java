package lk.ijse.henHouse.dao.custom;

import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface QuaryDAO {
    XYChart.Series<String, Double> getIncome() throws SQLException;
}
