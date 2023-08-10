package com.studentsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class students_dbImp implements studentInterface{

    private final Connection connection;
    public students_dbImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStudent(Students student) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO students (first_name, last_name, gender, grade) VALUES (?, ?, ?, ?)"))
        {
            statement.setString(1, student.getFirst_name());
            statement.setString(2, student.getLast_name());
            statement.setString(3, String.valueOf(student.getGender()));
            statement.setDouble(4, student.getGrades());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void deleteStudent(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM students WHERE id = ?"))
        {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(Students student) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE students SET first_name = ?, last_name = ?, gender = ?, grade = ? WHERE id = ?"))
        {
            statement.setString(1, student.getFirst_name());
            statement.setString(2, student.getLast_name());
            statement.setString(3, String.valueOf(student.getGender()));
            statement.setDouble(4, student.getGrades());
            statement.setInt(5, student.getStudentId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public Students viewStudentById(int id) {
        Students student = null;
        ResultSet rs;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM students WHERE id = ?"))
        {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while(rs.next()) {
                int studentId = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String genderStr = rs.getString("gender");
                char gender = genderStr != null && genderStr.length() == 1 ? genderStr.charAt(0) : ' ';
                double grade = rs.getDouble("grade");
                student = new Students(studentId, first_name, last_name, gender, grade);
            }
            rs.close();
        } catch (SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
        return student;
    }

    @Override
    public List<Students> displayAllStudent() {
        Students student = null;
        ResultSet rs;
        List<Students> studentList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM students"))
        {
            rs = statement.executeQuery();
            while(rs.next()) {
                int studentId = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String genderStr = rs.getString("gender");
                char gender = genderStr != null && genderStr.length() == 1 ? genderStr.charAt(0) : ' ';
                double grade = rs.getDouble("grade");
                student = new Students(studentId, first_name, last_name, gender, grade);
                studentList.add(student);
            }
            rs.close();
        } catch (SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }
        return studentList;
    }
}
