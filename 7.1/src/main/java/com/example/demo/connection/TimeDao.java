package com.example.demo.connection;

import com.example.demo.Discipline;
import com.example.demo.Schedule;
import com.example.demo.Teacher;
import com.example.demo.TimeEn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeDao {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    int st;//status

    public static void main(String[] args) {
        TimeDao t = new TimeDao();
        // System.out.println(t.fetchAll());
        t.fetchAll().forEach(System.out::println);
    }

    public List<TimeEn> fetchAll() {

        List<TimeEn> timeEns =  new ArrayList<>();

        connection = ConnectionFactory.getConnection();
        try {
            String query = "select * from time";

            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();
            timeEns = createTeacherListFromResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return timeEns;
    }

    private List<TimeEn> createTeacherListFromResultSet(ResultSet resultSet) {

        List<TimeEn> timeEns = new ArrayList<>();
        try {
            while (resultSet.next()) {

                TimeEn time = new TimeEn();
                time.setId(resultSet.getInt("id"));
                time.setTime(resultSet.getTime("time"));
                timeEns.add(time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeEns;
    }
}
