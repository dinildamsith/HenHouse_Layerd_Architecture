package lk.ijse.henHouse.util;

import lk.ijse.henHouse.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql, Object... args) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement prsm = connection.prepareStatement(sql);

        for (int i = 0; i <args.length ; i++) {
            prsm.setObject((i+1),args[i]);
        }
        if (sql.startsWith("SELECT")|| sql.startsWith("select")){
            return (T) prsm.executeQuery();
        }
        return (T) (Boolean) (prsm.executeUpdate()>0);
    }
}
