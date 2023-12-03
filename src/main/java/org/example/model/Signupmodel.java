package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Signupdto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Signupmodel {
    public boolean saveUser(Signupdto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO user VALUES(?, ?, ?, ?,?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getFirstName());
        pstm.setString(2, dto.getLastName());
        pstm.setString(3, dto.getEmailAddress());
        pstm.setString(4, dto.getUsername());
        pstm.setString(5, dto.getPhoneNumber());
        pstm.setString(6, dto.getPassword());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public static List<Signupdto> getAllUsers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user");
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Signupdto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Signupdto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }
        return dtoList;
    }
}
