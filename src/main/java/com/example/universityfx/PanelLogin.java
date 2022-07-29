package com.example.universityfx;

import com.example.universityfx.Models.DataBase;
import com.example.universityfx.Models.Department;
import com.example.universityfx.Models.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class PanelLogin {
    @FXML
    private Button btnGod , btnStudent , btnProfessor , btnDepartmentOfficer , btnExit;

    @FXML
    public void clickBtnProfessor() {

    }
    @FXML
    public void clickBtnStudent() throws IOException {
        DataBase.students.add(new Student("9817023116","Oveis", LocalDate.now(),new Department("KCSnk","1")));
        String[] info = display();
        Student student = DataBase.getStudent(info[1]);
        if (student==null)
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
    public void clickBtnGod(){

    }
    @FXML
    public void clickBtnDepartmentOfficer() {

    }
    @FXML
    public void clickBtnExit() {
        System.exit(0);
    }
    public static String[] display() {
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

        Label label2 = new Label("Name:");
        Label label3 = new Label("Student Number:");

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
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
