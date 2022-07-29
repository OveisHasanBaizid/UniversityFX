package com.example.universityfx.Models;

import java.util.ArrayList;

public class DataBase {
    public static ArrayList<GradeReport> gradeReports = new ArrayList<>();
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();

    public static Student studentHolder = null;

    public static Student getStudent(String studentNumber){
        for (Student s:students) {
            if (s.getStudent().equals(studentNumber))
                return s;
        }
        return null;
    }
}