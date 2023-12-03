package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.TobepaidTDto;
import org.example.dto.tm.PaymentCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TobepaidModel {
    public boolean savePaid(TobepaidTDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO tobepaid VALUES(?, ?, ?, ? , ? ,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setDouble(3, dto.getTotalcost());//totalcost
        pstm.setInt(4, dto.getNeed());//need
        pstm.setString(5, dto.getId());//need
        pstm.setString(6, dto.getName());//need

        return pstm.executeUpdate() > 0;
    }

    public List<TobepaidTDto> loadAllPaid() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM tobepaid";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<TobepaidTDto> itemList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            itemList.add(new TobepaidTDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }

        return itemList;
    }

    public TobepaidTDto searchPaid(String code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM tobepaid WHERE code = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();

        TobepaidTDto dto = null;

        if(resultSet.next()) {
            dto = new TobepaidTDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return dto;
    }



    public boolean updatePaid(List<PaymentCartTm> paymentCartTmList) throws SQLException {
        for(PaymentCartTm tm : paymentCartTmList) {
            System.out.println("tobepaid: " + tm);
            if(!updatePaid(tm.getCode(), tm.getNeed())) {
                return false;
            }
        }
        return true;
    }

    public boolean updatePaid(String code, int need) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE tobepaid SET need = need - ? WHERE code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, need);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0; //false
    }

    public int countNeed() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(Need) AS Need_Count FROM tobepaid";
       PreparedStatement pstm =  connection.prepareStatement(sql);

       ResultSet resultSet = pstm.executeQuery();

       if(resultSet.next()){
           return resultSet.getInt(1);
       }
       return 0;
    }
}
