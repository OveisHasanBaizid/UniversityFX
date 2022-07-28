package com.example.universityfx.Models;

public class GradeReport {
    private Student student;
    private Course course;
    private double grade;

    public GradeReport(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = -1;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return student + " > : " + course + " -> " + grade;
    }
}
