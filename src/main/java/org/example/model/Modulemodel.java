package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.Moduledto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Modulemodel {
    public boolean saveModule(Moduledto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO module VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getModuleid());
        pstm.setString(2, dto.getModulename());
        pstm.setString(3, dto.getDescription());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public List<Moduledto> getAllModules() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM module";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Moduledto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new Moduledto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)

                    )
            );
        }
        return dtoList;
    }

    public List<Moduledto> getAllofModules() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM module";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Moduledto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new Moduledto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)

                    )
            );
        }
        return dtoList;
    }


}
