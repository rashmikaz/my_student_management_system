package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Registrationdto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrationmodel {
    public boolean saveRegistration(Registrationdto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO registration VALUES(? , ? , ? , ? , ? ,? ,? )";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getRegistrationId());
        pstm.setString(2, dto.getStudentId());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getName());
        pstm.setString(5, dto.getFees());
        pstm.setString(6, dto.getCourseId());
        pstm.setString(7, dto.getCourseName());




        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
}
