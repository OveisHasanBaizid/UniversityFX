package com.example.universityfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class RegisterStudentToCourse {
    Course course;
    @FXML
    TextField textField_Name, textField_StudentNumber;
    @FXML
    Button btnSave, btnBack;
    @FXML
    DatePicker datePicker;
    public void initialize() {
        course = DataBase.courseHolder;
    }
    @FXML
    public void clickBtnBack() throws IOException {
        nextPage("MenuStudentsCoursesDepartment.fxml");
    }

    @FXML
    public void clickBtnSave() throws IOException {
        if (textField_Name.getText().isEmpty())
            showMessage("The name of the student must not be empty.", "Error");
        else if (textField_StudentNumber.getText().isEmpty())
            showMessage("The student number of the student must not be empty.", "Error");
        else if (!checkStudentNumber(textField_StudentNumber.getText())
                || textField_StudentNumber.getText().length()<10 || textField_StudentNumber.getText().length()>10)
            showMessage("The entered student number is invalid.", "Error");
        else if (datePicker.toString().isEmpty())
            showMessage("The date of birth of the student has not been selected.", "Error");
       else if (DataBase.getStudent(textField_StudentNumber.getText())!= null){
            showMessage("There is another student with this student number.","Error");
        }else{
            Student student = new Student(textField_StudentNumber.getText(),textField_Name.getText()
                    ,datePicker.getValue(),DataBase.departmentHolder);
            DataBase.students.add(student);

            DataBase.gradeReports.add(new GradeReport(student,course));
            showMessage("New student added successfully.","Message");
            nextPage("MenuStudentsCoursesDepartment.fxml");
        }
    }
    public boolean checkStudentNumber(String s){
        if (s.isEmpty())
            return false;
        for (int i = 0; i < s.length() ; i++) {
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
    public void showMessage(String message, String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().setPrefSize(300, 100);
        alert.setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void nextPage(String namePage) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(namePage)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
