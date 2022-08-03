package com.example.universityfx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private int credits;
    private Department department;
    private Professor instructor;
    private int numberStudent;

    public Course(String line) {
        String[] array = line.split(",");
        this.name = array[0];
        setCredits(Integer.parseInt(array[1]));
        this.department = DataBase.getDepartment(array[2]);
        this.instructor = array[3].equals("Null")?null: DataBase.getProfessor(array[3]);
        this.numberStudent = Integer.parseInt(array[4]);
    }

    public Course(String name, int credits, Department department, Professor instructor) {
        this.name = name;
        setCredits(credits);
        this.department = department;
        this.instructor = instructor;
        this.numberStudent = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits > 0 && credits <= 6)
            this.credits = credits;
        else
            this.credits = 3;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Professor getInstructor() {
        if (instructor==null)
            return new Professor("Null", LocalDate.now(),department,null);
        return instructor;
    }

    public void setInstructor(Professor instructor) {
        this.instructor = instructor;
    }

    public int getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(int numberStudent) {
        this.numberStudent = numberStudent;
    }
    public void increaseParticipants(){
        numberStudent++;
    }
    public void decreaseParticipants(){
        numberStudent--;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return credits == course.credits
                && name.equals(course.name)
                && department.equals(course.department)
                && instructor.equals(course.instructor);
    }
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        for (GradeReport g:DataBase.gradeReports) {
            if (g.getCourse().equals(this))
                students.add(g.getStudent());
        }
        return students;
    }
    public String coder(){
        return name+","+credits+","+department.getName()+","+(instructor==null?"Null":instructor.getName())+","+numberStudent;
    }
}
