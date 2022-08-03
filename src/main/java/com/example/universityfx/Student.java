package com.example.universityfx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String student;
    private String name;
    private LocalDate birthDate;
    private Department department;

    public Student(String student, String name, LocalDate birthDate, Department department) {
        this.student = student;
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
    }
    public Student(String line) {
        String[] array = line.split(",");
        this.student = array[0];
        this.name = array[1];
        this.birthDate = LocalDate.parse(array[2]);
        this.department = DataBase.getDepartment(array[3]);
    }
    public String getStudent() {
        return student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void takeCourse(Course course){
        DataBase.gradeReports.add(new GradeReport(this,course));
        course.increaseParticipants();
    }
    public int finishCourse(Course course , double grade){
        if (grade<0 || grade>20)
            return 1;
        for (GradeReport gradeReport:DataBase.gradeReports) {
            if (gradeReport.getCourse().equals(course) && gradeReport.getStudent().getStudent().equals(student)){
                if (gradeReport.getGrade()!=-1)
                    return 2;
                gradeReport.setGrade(grade);
                break;
            }
        }
        course.decreaseParticipants();
        return 0;
    }
    public List<GradeReport> getGradeReports(){
        List<GradeReport> gradeReportsStudent = new ArrayList<>();
        for (GradeReport gradeReport:DataBase.gradeReports) {
            if (gradeReport.getStudent().equals(this))
                gradeReportsStudent.add(gradeReport);
        }
        return gradeReportsStudent;
    }
    public List<Course> getCourse(){
        List<Course> courses = new ArrayList<>();
        for (GradeReport gradeReport:DataBase.gradeReports) {
            if (gradeReport.getStudent().equals(this))
                courses.add(gradeReport.getCourse());
        }
        return courses;
    }
    public GradeReport getGradeReport(String nameCourse){
        for (GradeReport g: getGradeReports()) {
            if (g.getCourse().getName().compareToIgnoreCase(nameCourse)==0)
                return g;
        }
        return null;
    }
    public String coder(){
        return student+","+name+","+birthDate+","+department.getName();
    }
}
