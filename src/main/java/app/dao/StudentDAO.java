package app.dao;

import app.model.Student;

import java.sql.Connection;
import java.sql.*;
import java.util.*;


public class StudentDAO {
    private final String url = "jdbc:mysql://localhost:3306/webApp";
    private final String username = "root";
    private final String password = "123456";

    private final String CREATE_QUERY = "INSERT INTO students(username, password) VALUES(?, ?)";
    private final String READ_QUERY = "SELECT * FROM students WHERE id=?";
    private final String UPDATE_QUERY = "UPDATE students SET username=?, password=? WHERE id=?";
    private final String DELETE_QUERY = "DELETE FROM students WHERE id=?";
    private final String GET_ALL_QUERY = "SELECT * FROM students";

    public Student create(Student student) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, student.getUsername());
            statement.setString(2, student.getPassword());
            statement.executeUpdate();
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student read(int id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new Student(id, username, password);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student update(Student student) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, student.getUsername());
            statement.setString(2, student.getPassword());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                students.add(new Student(id, username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
