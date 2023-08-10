package com.studentsDB;

import java.util.List;

public interface studentInterface {
    void addStudent(Students student);
    void deleteStudent(int id);
    void updateStudent(Students student);
    Students viewStudentById(int id);
    List<Students> displayAllStudent();
}