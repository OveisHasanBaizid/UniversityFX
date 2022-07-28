package com.example.universityfx.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private String name;
    private String id;

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, String id) {
        this.name = name;
        this.id = id;
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
            if (s.getStudent().equals(this))
                studentsDepart.add(s);
        }
        return studentsDepart;
    }
    public List<Professor> getFaculty(){
        List<Professor> professors = new ArrayList<>();
        for (Course c: DataBase.courses) {
            if (c.getDepartment().equals(this))
                professors.add(c.getInstructor());
        }
        return professors;
    }
}
