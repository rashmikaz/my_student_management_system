package org.example.model;


import org.example.db.DbConnection;
import org.example.dto.tm.PaymentCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDetailModel {
    public boolean savePaymentDetails(String paymentId, List<PaymentCartTm> paymentCartTmList) throws SQLException {
        for(PaymentCartTm tm : paymentCartTmList) {
            if(!savePaymentDetails(paymentId, tm)) {
                return false;
            }
        }
        return true;
    }

    private boolean savePaymentDetails(String paymentId, PaymentCartTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO payment_detail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, paymentId);
        pstm.setString(2, tm.getCode());
        pstm.setInt(3, tm.getNeed());
        pstm.setDouble(4, tm.getTotalcost());

        return pstm.executeUpdate() > 0;
    }

    public int countRs() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(rs) AS rs_Count FROM payment_detail";
        PreparedStatement pstm =  connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
}
