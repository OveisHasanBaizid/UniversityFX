package com.example.universityfx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Professor {
    private String name;
    private LocalDate birthDate;
    private Department department;
    private AcademicRank rank;

    public Professor() {
        this.birthDate = LocalDate.now();
    }
    public Professor(String line) {
        String array[] = line.split(",");
        this.name = array[0];
        this.birthDate = LocalDate.parse(array[1]);
        this.department = DataBase.getDepartment(array[2]);
        this.rank = getRank(array[3]);
    }

    public Professor(String name, LocalDate birthDate, Department department, AcademicRank rank) {
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
        this.rank = rank;
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

    public AcademicRank getRank() {
        return rank;
    }

    public void setRank(AcademicRank rank) {
        this.rank = rank;
    }
    public static AcademicRank getRank(String s){
        if (s.equals("PROFESSOR"))
            return AcademicRank.PROFESSOR;
        if (s.equals("ASSISTANT_PROFESSOR"))
            return AcademicRank.ASSISTANT_PROFESSOR;
        return AcademicRank.ASSOCIATE_PROFESSOR;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(name, professor.name)
                && birthDate!=null && professor.birthDate!=null && department!=null
                && birthDate.equals(professor.birthDate)
                && department.equals(professor.department)
                && rank == professor.rank;
    }

    public List<Course> getCourse(){
        List<Course> courses = new ArrayList<>();
        for (Course c:DataBase.courses) {
            if (c.getInstructor().equals(this))
                courses.add(c);
        }
        return courses;
    }

    @Override
    public String toString() {
        return name;
    }

    public String coder(){
        return name+","+birthDate+","+department.getName()+","+rank;
    }
}
