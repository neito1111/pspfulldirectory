package com.example.demo.connection;

import com.example.demo.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    int st;//status

    public static void main(String[] args) {
        TeacherDao t = new TeacherDao();
       // System.out.println(t.fetchAll());
        t.fetchAll().forEach(System.out::println);
    }

    public int insert(Teacher teacher) {
        connection = ConnectionFactory.getConnection();
        try {
            String query = "insert into teacher(firstname, lastname, middlename) values (?, ?, ?)";
            ps = connection.prepareStatement(query);

            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getMiddleName());


            st = ps.executeUpdate();
            System.out.println("inserted course " + st);
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

    public int update(Teacher teacher) {
        connection = ConnectionFactory.getConnection();
        try {
            String query = "update teacher set firstname = ?, lastname = ?, middlename = ?  where id=? ";
            ps = connection.prepareStatement(query);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setString(3, teacher.getMiddleName());
            ps.setInt(4, teacher.getId());
            st = ps.executeUpdate();
            System.out.println("updated student " + st);
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


    public List<Teacher> fetchAll() {

        List<Teacher> teacherList =  new ArrayList<>();

        connection = ConnectionFactory.getConnection();
        try {
            String query = "select * from teacher order by id asc";

            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();
            teacherList = createTeacherListFromResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return teacherList;
    }

    private List<Teacher> createTeacherListFromResultSet(ResultSet resultSet) {
        List<Teacher> teacherList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Teacher teacher =  new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setFirstName(resultSet.getString("firstName"));
                teacher.setLastName(resultSet.getString("lastName"));
                teacher.setMiddleName(resultSet.getString("middleName"));
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }
}


