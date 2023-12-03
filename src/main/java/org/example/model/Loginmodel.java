package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Logindto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loginmodel {
    public Logindto searchUser(String userName,String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,userName);
        pstm.setString(2,password);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            Logindto loginDto = new Logindto();
            loginDto.setUserName(resultSet.getString("user_name"));
            loginDto.setPassword(resultSet.getString("password"));

            return loginDto;
        }else {
            return null;
        }
    }
}
