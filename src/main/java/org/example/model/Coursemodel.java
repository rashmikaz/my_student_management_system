package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Coursedto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Coursemodel {
    public boolean Addforcourse(Coursedto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO course VALUES(? , ? , ? , ? , ? ,? )";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getCourseId());
        pstm.setString(2, dto.getCourseName());
        pstm.setString(3, dto.getDescription());
        pstm.setString(4, dto.getDuration());
        pstm.setString(5, dto.getQualification());
        pstm.setString(6, dto.getCost());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<Coursedto> getAllCourse() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM course";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Coursedto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new Coursedto(
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

    public int getCourseCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM course";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1);
        }

        return 0;
    }
}
