package com.example.universityfx;

public class GradeReport {
    private final Student student;
    private final Course course;
    private double grade;

    public GradeReport(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = -1;
    }
    public GradeReport(String line) {
        String[] array = line.split(",");
        this.student = DataBase.getStudent(array[0]);
        this.course = DataBase.getCourse(array[1]);
        this.grade = Double.parseDouble(array[2]);
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

    public String coder(){
        return student.getStudent()+","+course.getName()+","+grade;
    }
}
