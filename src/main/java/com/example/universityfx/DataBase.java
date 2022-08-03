package com.example.universityfx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static ArrayList<GradeReport> gradeReports = new ArrayList<>();
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Department> departments = new ArrayList<>();
    public static ArrayList<Professor> professors = new ArrayList<>();


    public static Student studentHolder = null;
    public static Professor professorHolder = null;
    public static Department departmentHolder = null;
    public static Course courseHolder = null;

    public static Student getStudent(String studentNumber){
        for (Student s:students) {
            if (s.getStudent().equals(studentNumber))
                return s;
        }
        return null;
    }
    public static Department getDepartment(String name){
        for (Department p:departments) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }
    public static Professor getProfessor(String name){
        for (Professor p:professors) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }
    public static Course getCourse(String name){
        for (Course c:courses) {
            if (c.getName().compareToIgnoreCase(name)==0)
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
    //--------------------------------------------------------------------------------
    public static void writeDepartments() throws IOException {
        FileWriter fileWriter = new FileWriter("departments.txt");
        StringBuilder stringBuilder = new StringBuilder();
        for (Department department : departments) {
            stringBuilder.append(department.coder()).append("\n");
        }
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }

    public static void readDepartments() throws IOException {
        departments = new ArrayList<>();
        FileReader fileReader = new FileReader("departments.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String s;
        while ((s = br.readLine()) != null) {
           departments.add(new Department(s));
        }
        fileReader.close();
    }
    //--------------------------------------------------------------------------------
    public static void writeProfessors() throws IOException {
        FileWriter fileWriter = new FileWriter("professors.txt");
        StringBuilder stringBuilder = new StringBuilder();
        for (Professor professor : professors) {
            stringBuilder.append(professor.coder()).append("\n");
        }
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }

    public static void readProfessors() throws IOException {
        professors = new ArrayList<>();
        FileReader fileReader = new FileReader("professors.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String s;
        while ((s = br.readLine()) != null) {
            professors.add(new Professor(s));
        }
        fileReader.close();
    }
    //--------------------------------------------------------------------------------
    public static void writeStudents() throws IOException {
        FileWriter fileWriter = new FileWriter("students.txt");
        StringBuilder stringBuilder = new StringBuilder();
        for (Student student : students) {
            stringBuilder.append(student.coder()).append("\n");
        }
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }

    public static void readStudents() throws IOException {
        students = new ArrayList<>();
        FileReader fileReader = new FileReader("students.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String s;
        while ((s = br.readLine()) != null) {
            students.add(new Student(s));
        }
        fileReader.close();
    }
    //--------------------------------------------------------------------------------
    public static void writeCourses() throws IOException {
        FileWriter fileWriter = new FileWriter("courses.txt");
        StringBuilder stringBuilder = new StringBuilder();
        for (Course course : courses) {
            stringBuilder.append(course.coder()).append("\n");
        }
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }

    public static void readCourses() throws IOException {
        courses = new ArrayList<>();
        FileReader fileReader = new FileReader("courses.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String s;
        while ((s = br.readLine()) != null) {
            courses.add(new Course(s));
        }
        fileReader.close();
    }
    //--------------------------------------------------------------------------------
    public static void writeGradeReports() throws IOException {
        FileWriter fileWriter = new FileWriter("gradeReports.txt");
        StringBuilder stringBuilder = new StringBuilder();
        for (GradeReport g : gradeReports) {
            stringBuilder.append(g.coder()).append("\n");
        }
        fileWriter.write(stringBuilder.toString());
        fileWriter.close();
    }

    public static void readGradeReports() throws IOException {
        gradeReports = new ArrayList<>();
        FileReader fileReader = new FileReader("gradeReports.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String s;
        while ((s = br.readLine()) != null) {
            gradeReports.add(new GradeReport(s));
        }
        fileReader.close();
    }
}