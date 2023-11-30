package com.kh.mini_sample.Dao;

import com.kh.mini_sample.connection.Common;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class TimetalbeDao {
    JdbcTemplate jdbcTemplate;
    @Autowired
    Common common;

    public String getTimetable(int id,String dayNm) throws SQLException {
        jdbcTemplate = new JdbcTemplate(common);

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement timetableStatement = connection.prepareStatement("SELECT * FROM timetable WHERE id = ? AND dayNm = ?")){
             timetableStatement.setInt(1,id);
             timetableStatement.setString(2,dayNm);
            try( ResultSet timetableResultSet = timetableStatement.executeQuery()){
                String schedule =null;
                while (timetableResultSet.next()) {
                    schedule = timetableResultSet.getString("schedule");
                }
                return schedule;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
