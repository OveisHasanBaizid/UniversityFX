package com.example.universityfx;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private String name;
    private String id;

    public Department(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public Department(String line) {
        String array[] = line.split(",");
        this.name = array[0];
        this.id = array[1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return name.equals(that.name) && Objects.equals(id, that.id);
    }
    public List<Student> getStudent(){
        List<Student> studentsDepart = new ArrayList<>();
        for (Student s: DataBase.students) {
            if (s.getDepartment().equals(this))
                studentsDepart.add(s);
        }
        return studentsDepart;
    }
    public List<Course> getCourses(){
        List<Course> courses = new ArrayList<>();
        for (Course c: DataBase.courses) {
            if (c.getDepartment().equals(this))
                courses.add(c);
        }
        return courses;
    }
    public List<Professor> getFaculty(){
        List<Professor> professors = new ArrayList<>();
        for (Course c: DataBase.courses) {
            if (c.getDepartment().equals(this) && (!c.getInstructor().getName().equals("Null"))){
                professors.add(c.getInstructor());
            }

        }
        return professors;
    }
    public String coder(){
        return name+","+id;
    }
}
