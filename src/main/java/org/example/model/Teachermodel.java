package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Moduledto;
import org.example.dto.Teacherdto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Teachermodel {
    public boolean saveTeacher(Teacherdto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO teacher VALUES(?, ?, ?, ? , ? , ? , ? , ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getTeacherid());
        pstm.setString(2, dto.getTeachername());
        pstm.setString(3, dto.getModuleid());
        pstm.setString(4, dto.getModulename());
        pstm.setString(5, dto.getEmailaddress());
        pstm.setString(6, dto.getPhonenumber());
        pstm.setString(7, dto.getNicnumber());
        pstm.setString(8, dto.getQualification());


        System.out.println("Excuting sql:" + pstm.toString());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }
    public int getTeachersCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM teacher";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1);
        }

        return 0;
    }
    public List<Teacherdto> getAllTeacher() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM teacher";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Teacherdto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new Teacherdto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)


                    )
            );
        }
        return dtoList;
    }
}


