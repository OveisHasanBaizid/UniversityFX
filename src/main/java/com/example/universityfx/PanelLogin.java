package com.example.universityfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class PanelLogin {
    @FXML
    private Button btnGod , btnStudent , btnProfessor , btnDepartmentOfficer , btnExit;

    public void initialize(){
        temp();
    }
    @FXML
    public void clickBtnProfessor() throws IOException {

        String[] info = display("Name" , "Department");
        Professor professor = DataBase.getProfessor(info[0]);
        if (professor==null || !professor.getDepartment().equals(DataBase.getDepartment(info[1])))
            showMessage("The information entered is incorrect.");
        else{
            DataBase.professorHolder = professor;
            Stage stage = (Stage) btnProfessor.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuProfessor.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void temp(){
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
    @FXML
    public void clickBtnStudent() throws IOException {
        temp();
        String[] info = display("Name" , "Student Number");
        Student student = DataBase.getStudent(info[1]);
        if (student==null || student.getName().compareToIgnoreCase(info[0])!=0)
            showMessage("The information entered is incorrect.");
        else{
            DataBase.studentHolder = student;
            Stage stage = (Stage) btnStudent.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuStudent.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void clickBtnGod() throws IOException {
        Stage stage = (Stage) btnGod.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuGod.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void clickBtnDepartmentOfficer() {

    }
    @FXML
    public void clickBtnExit() {
        System.exit(0);
    }
    public static String[] display(String s1 , String s2) {
        final String[] username_password = new String[2];

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField text1 = new TextField();
        TextField text2 = new TextField();

        Button button = new Button("Submit");

        button.setOnAction(actionEvent -> {
            username_password[0] = text1.getText();
            username_password[1] = text2.getText();
            stage.close();
        });

        Label label2 = new Label(s1+":");
        Label label3 = new Label(s2+":");

        GridPane layout = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        layout.add(text1, 1,1);
        layout.add(text2, 1,2);
        layout.add(button, 1,3);
        layout.add(label2, 0,1);
        layout.add(label3, 0,2);

        Scene scene = new Scene(layout, 300, 130);
        stage.setTitle("Dialog");
        stage.setScene(scene);
        stage.showAndWait();

        return username_password;
    }
    public void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().setPrefSize(300, 100);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
