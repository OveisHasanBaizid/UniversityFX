package com.example.universityfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PanelLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("University");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        temp();
        launch();

    }
    public static void temp(){
        Department department = new Department("k","1");
        Student student1 = new Student("9817023116","Oveis", LocalDate.now(),department);
        Student student2 = new Student("98","Ov", LocalDate.now(),department);
        Professor professor = new Professor("p",LocalDate.now(),department,AcademicRank.PROFESSOR);
        Course course = new Course("Course1",4,department,professor);
        DataBase.courses.add(course);
        DataBase.students.add(student1);
        DataBase.students.add(student2);
        DataBase.professors.add(professor);
        DataBase.departments.add(department);
        student1.takeCourse(course);
    }
}