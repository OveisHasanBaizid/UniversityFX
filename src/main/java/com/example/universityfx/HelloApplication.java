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
    public static void main(String[] args) throws IOException {
        read();
        launch();

    }
    public static void read() throws IOException {
       DataBase.readDepartments();
       DataBase.readProfessors();
       DataBase.readCourses();
       DataBase.readStudents();
       DataBase.readGradeReports();
    }
}