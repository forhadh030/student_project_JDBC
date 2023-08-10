package com.studentsDB;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Administrative {

    public static studentInterface studentsInt = new students_dbImp(JDBConnection.getConnection());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("""
                    ===== \nWELCOME TO STUDENT DATABASE =====\s
                         _____________________________\s
                              How may we help you?\s
                         1 - Add a Student
                         2 - Remove a Student
                         3 - Update a Student
                         4 - Look up a Student
                         5 - View All Students
                         6 - View All Students (sorted by first name)
                         7 - View TOP 3 Performing Student
                         8 - Exit Program
                    """);

            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 1 -> addStudents(scanner);
                case 2 -> deleteStudent(scanner);
                case 3 -> updateStudent(scanner);
                case 4 -> viewStudentById(scanner);
                case 5 -> displayAllStudent();
                case 6 -> viewSortedStudents();
                case 7 -> viewSortedGrade();
                case 8 -> System.out.println("Goodbye, please come again.");
                default -> System.out.println("Invalid option, please try again.");
            }
        } while(choice != 8);
    }

    public static void addStudents(Scanner scanner) {
        System.out.print("Please Enter Student First Name: ");
        String first_name = scanner.nextLine();
        System.out.print("Please Enter Student Last Name: ");
        String last_name = scanner.nextLine();
        System.out.print("Please Enter Student Gender (M or F): ");
        char gender = scanner.nextLine().charAt(0);
        System.out.print("Please Enter Student Grade: ");
        double grade = scanner.nextDouble();
        Students student = new Students(first_name, last_name, gender, grade);
        studentsInt.addStudent(student);
        System.out.print("Student added successfully.");
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.print("Enter the ID of the student you want to remove: ");
        int id = scanner.nextInt();
        if(!studentsInt.displayAllStudent().isEmpty()) {
            studentsInt.deleteStudent(id);
            System.out.println("Successfully removed student from database");
        } else {
            System.out.println("We cannot locate the student.");
        }
    }

    public static void updateStudent(Scanner scanner) {
        System.out.print("Enter ID of the student you want to update: ");
        int id = scanner.nextInt();
        Students student = studentsInt.viewStudentById(id);
        scanner.nextLine();
        if(student != null) {
            System.out.print("Enter First Name of the Student: ");
            String first_name = scanner.nextLine();
            System.out.print("Enter last Name of the Student: ");
            String last_name = scanner.nextLine();
            System.out.print("Enter The Gender of the Student: ");
            char gender = scanner.nextLine().charAt(0);
            System.out.print("Enter The Student Grade: ");
            double grade = scanner.nextDouble();

            student.setFirst_name(first_name.isEmpty() ? student.getFirst_name() : first_name);
            student.setLast_name(last_name.isEmpty() ? student.getLast_name() : last_name);
            student.setGender(gender == 'M' || gender == 'F' ? student.getGender() : gender);
            student.setGrades(grade == 0 ? student.getGrades() : grade);
            studentsInt.updateStudent(student);
            System.out.println("Student has successfully been updated.");
        } else {
            System.out.println("We cannot locate the student.");
        }
    }

    public static void viewStudentById(Scanner scanner) {
        System.out.print("Please Enter Student ID: ");
        int id = scanner.nextInt();
        if(studentsInt.viewStudentById(id) != null) {
            System.out.println(studentsInt.viewStudentById(id));
        } else {
            System.out.println("We cannot locate the student.");
        }
    }

    public static void displayAllStudent() {
        if(!studentsInt.displayAllStudent().isEmpty()) {
            studentsInt.displayAllStudent().forEach(System.out::println);
        } else {
            System.out.println("We cannot locate the student.");
        }
    }

    public static void viewSortedStudents() {
        List<Students> allStudents = studentsInt.displayAllStudent();
        if (!allStudents.isEmpty()) {
            allStudents.stream()
                    .sorted(Comparator.comparing(Students::getFirst_name))
                    .forEach(System.out::println);
        } else {
            System.out.println("We cannot locate any students.");
        }
    }

    public static void viewSortedGrade() {
        List<Students> allStudents = studentsInt.displayAllStudent();
        if (!allStudents.isEmpty()) {
            allStudents.stream()
                    .sorted(Comparator.comparing(Students::getGrades, Comparator.reverseOrder()))
                    .limit(3)
                    .forEach(System.out::println);
        } else {
            System.out.println("We cannot locate any students.");
        }
    }

}
