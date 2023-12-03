package org.example.model;


import org.example.db.DbConnection;
import org.example.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    public static StudentDto searchStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM student WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        StudentDto dto = null;

        if(resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String cus_name = resultSet.getString(2);
            String cus_address = resultSet.getString(3);
            String cus_tel = resultSet.getString(4);
            String birthday = resultSet.getString(5);
            String email = resultSet.getString(6);
            String nic = resultSet.getString(7);

            dto = new StudentDto(cus_id, cus_name, cus_address, cus_tel,birthday,email,nic);
        }
        return dto;
    }

    public boolean saveStudent(StudentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO student VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getTel());
        pstm.setString(5, dto.getBirth());
        pstm.setString(6, dto.getEmail());
        pstm.setString(7, dto.getNic());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<StudentDto> getAllStudent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM student";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StudentDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new StudentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)

                    )
            );
        }
        return dtoList;
    }

    public boolean deleteStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM student WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public boolean updateStudent(StudentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE student SET name = ?, address = ?, tel = ?,birthday=?,email=?,nic=? WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getTel());
        pstm.setString(4, dto.getId());
        pstm.setString(5, dto.getBirth());
        pstm.setString(6, dto.getEmail());
        pstm.setString(7, dto.getNic());


        return pstm.executeUpdate() > 0;
    }

    public int getStudentCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(*) FROM student";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt(1);
        }

        return 0;
    }

    public List<StudentDto> getStudent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM student";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StudentDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new StudentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }
        return dtoList;
    }
}
