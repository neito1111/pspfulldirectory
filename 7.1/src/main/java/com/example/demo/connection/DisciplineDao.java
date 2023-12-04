package com.example.demo.connection;

import com.example.demo.Discipline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDao {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    int st;//status


    public int insert(Discipline discipline) {
        connection = ConnectionFactory.getConnection();
        try {
            String query = "insert into discipline(name) values (?)";
            ps = connection.prepareStatement(query);

            ps.setString(1, discipline.getName());

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

    public int update(Discipline discipline) {
        connection = ConnectionFactory.getConnection();
        try {
            String query = "update discipline set name = ?  where id=? ";
            ps = connection.prepareStatement(query);
            ps.setString(1, discipline.getName());
            ps.setInt(2, discipline.getId());
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
            String query = "delete from discipline where id=? ";
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


    public List<Discipline> fetchAll() {

        List<Discipline> disciplines =  new ArrayList<>();

        connection = ConnectionFactory.getConnection();
        try {
            String query = "select * from discipline order by id asc";

            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();
            disciplines = createDisciplineListFromResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return disciplines;
    }

    private List<Discipline> createDisciplineListFromResultSet(ResultSet resultSet) {
        List<Discipline> disciplines = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Discipline discipline =  new Discipline();
                discipline.setId(resultSet.getInt("id"));
                discipline.setName(resultSet.getString("name"));

                disciplines.add(discipline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }
}
