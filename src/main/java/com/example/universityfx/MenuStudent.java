package com.example.universityfx;
import com.example.universityfx.Models.DataBase;
import com.example.universityfx.Models.Department;
import com.example.universityfx.Models.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class MenuStudent {
    Student student;
    @FXML
    Button btnExit, btnRemove , btnAdd;
    @FXML
    TextField textField_Name , textField_stdNumber , textField_BirthDate , textField_Department;

    public void initialize(){
        student = DataBase.studentHolder;

        textField_Name.setText(student.getName());
        textField_stdNumber.setText(student.getStudent());
        textField_Department.setText(student.getDepartment().getName());
        textField_BirthDate.setText(student.getBirthDate().toString());
    }
    @FXML
    public void clickBtnExit() throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PanelLogin.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void clickBtnAdd() {
    }
    @FXML
    public void clickBtnRemove() {
    }
}
