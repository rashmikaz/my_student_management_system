package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.DatabaseImplementationattendancecontrollerdto;
import org.example.dto.Teacherdto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseImplementationattendancecontrollermodel {

    public static boolean AddAttendant(DatabaseImplementationattendancecontrollerdto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO DatabaseImplementationattendance VALUES (?,?,?,?)");

        pstm.setString(1, dto.getStudentid());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getType());

        return pstm.executeUpdate() > 0;
    }

    public List<DatabaseImplementationattendancecontrollerdto> getAllattendance() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM DatabaseImplementationattendance";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<DatabaseImplementationattendancecontrollerdto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new DatabaseImplementationattendancecontrollerdto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );
        }
        return dtoList;
    }
}
