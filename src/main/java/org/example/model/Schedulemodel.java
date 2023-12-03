package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Scheduledto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Schedulemodel {

    public boolean saveSchedule(Scheduledto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO schedule VALUES(?, ?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getProgram());
        pstm.setString(2, dto.getModule());
        pstm.setString(3, dto.getTeacher());
        pstm.setString(4, dto.getDay());
        pstm.setString(5, dto.getStart());
        pstm.setString(6, dto.getEnd());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<Scheduledto> getAllSchedule() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM schedule";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Scheduledto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new Scheduledto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;
    }
}
