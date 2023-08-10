package com.studentsDB;

public class Students {
    private int studentId;
    private String first_name;
    private String last_name;
    private char gender;
    private double grades;

    public Students(String first_name, String last_name, char gender, double grades) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.grades = grades;
    }

    public Students(int studentId, String first_name, String last_name, char gender, double grades) {
        this.studentId = studentId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.grades = grades;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Students{" +
                "studentId=" + studentId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender=" + gender +
                ", grades=" + grades +
                '}';
    }
}
