package com.example.demo.connection;

import com.example.demo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    int st;//status

    public static void main(String[] args) {
        ScheduleDao t = new ScheduleDao();
        // System.out.println(t.fetchAll());
        t.fetchAll().forEach(System.out::println);
        ScheduleDB d = new ScheduleDB();
        d.setTeacherId(2);
        d.setDisciplineId(1);
        d.setTimeId(1);
        t.insert(d);
    }

    public int insert(ScheduleDB schedule) {
        connection = ConnectionFactory.getConnection();
        try {
            String query = "insert into schedule(teacher_id, discipline_id, time_id) values (?, ?, ?)";
            ps = connection.prepareStatement(query);

            ps.setInt(1, schedule.getTeacherId());
            ps.setInt(2, schedule.getDisciplineId());
            ps.setInt(3, schedule.getTimeId());


            st = ps.executeUpdate();
            System.out.println("inserted schedule " + st);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }


    public int delete(int id) {
        connection = ConnectionFactory.getConnection();
        try {
            String query = "delete from teacher where id=? ";
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            st = ps.executeUpdate();
            System.out.println("deleted course " + st);
        } catch (Exception e) {
            st = -2;
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }


    public List<Schedule> fetchAll() {

        List<Schedule> schedules =  new ArrayList<>();

        connection = ConnectionFactory.getConnection();
        try {
            String query = "select schedule.id as \"schedule_id\", discipline.id as \"discipline_id\", discipline.name as \"name\",\n" +
                    "       teacher.id as \"teacher_id\", teacher.*, time.*, time.id as \"time_id\" from schedule\n" +
                    "    inner join discipline  on discipline.id = schedule.discipline_id\n" +
                    "    inner join teacher  on schedule.teacher_id = teacher.id\n" +
                    "    inner join time  on time.id = schedule.time_id";

            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();
            schedules = createTeacherListFromResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return schedules;
    }

    private List<Schedule> createTeacherListFromResultSet(ResultSet resultSet) {

        List<Schedule> schedules = new ArrayList<>();
        try {
            while (resultSet.next()) {

                Schedule schedule = new Schedule();
                Teacher teacher = new Teacher();
                Discipline discipline = new Discipline();
                TimeEn time = new TimeEn();


                schedule.setId(resultSet.getInt("schedule_id"));

                teacher.setId(resultSet.getInt("teacher_id"));
                teacher.setFirstName(resultSet.getString("firstname"));
                teacher.setLastName(resultSet.getString("lastname"));
                teacher.setMiddleName(resultSet.getString("middlename"));

                schedule.setTeacher(teacher);

                discipline.setId(resultSet.getInt("discipline_id"));
                discipline.setName(resultSet.getString("name"));
                schedule.setDiscipline(discipline);

                time.setId(resultSet.getInt("time_id"));
                time.setTime(resultSet.getTime("time"));
                schedule.setTime(time);

                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
}
