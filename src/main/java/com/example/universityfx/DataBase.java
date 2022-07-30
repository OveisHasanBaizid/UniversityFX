package com.example.universityfx;

import java.util.ArrayList;
import java.util.List;

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

    public static Course getCourse(String name){
        for (Course c:courses) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }
    public static List<Course> getAllCoursesDepartment(Department department){
        List<Course> coursesDepartment = new ArrayList<>();
        for (Course c:courses) {
            if (c.getDepartment().equals(department))
                coursesDepartment.add(c);
        }
        return coursesDepartment;
    }
}