package org.example.model;



import org.example.db.DbConnection;

import java.sql.*;
import java.time.LocalDate;

public class newPaymentmodel {
    public String generateNextPaymentId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitPaymentId(resultSet.getString(1));
        }
        return splitPaymentId(null);
    }

    private String splitPaymentId(String currentPaymentId) {
        if(currentPaymentId != null) {
            String[] split = currentPaymentId.split("P0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "P00" + id;
        } else {
            return "P001";
        }
    }

    public boolean saveOrder(String paymentId, String studentId, LocalDate date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO payment VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, paymentId);
        pstm.setString(2, studentId);
        pstm.setDate(3, Date.valueOf(date));

        return pstm.executeUpdate() > 0;
    }
}
