package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Resultdto;
import org.example.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Resultmodel {

    public boolean addResult(Resultdto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO database_design_and_implementation VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getModuleid());
        pstm.setString(2, dto.getStudentid());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getMarks());
        pstm.setString(5, dto.getPorf());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
}
