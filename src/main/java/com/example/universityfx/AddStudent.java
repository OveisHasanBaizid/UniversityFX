package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddStudent {
    @FXML
    TextField textField_Name, textField_StudentNumber;
    @FXML
    Button btnSave, btnBack;
    @FXML
    Spinner<String> spinner;
    @FXML
    DatePicker datePicker;

    @FXML
    public void initialize() {
        List<String> departments = new ArrayList<>();
        DataBase.departments.forEach(d -> departments.add(d.getName()));
        ObservableList<String> depart = FXCollections.observableArrayList(departments);
        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<>(depart);
        spinner.setValueFactory(valueFactory);
    }

    @FXML
    public void clickBtnBack() throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageStudents.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        else if (spinner.getValue().isEmpty())
            showMessage("The department of the student has not been selected.", "Error");
        else if (DataBase.getStudent(textField_StudentNumber.getText())!= null){
            showMessage("There is another student with this student number.","Error");
        }else{
            Student student = new Student(textField_Name.getText(),textField_StudentNumber.getText()
                    ,datePicker.getValue(),DataBase.getDepartment(spinner.getValue()));
            DataBase.students.add(student);
            showMessage("New student added successfully.","Message");

            Stage stage = (Stage) btnBack.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageStudents.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public boolean checkStudentNumber(String s){
        if (s.isEmpty())
            return false;
        for (int i = 0; i < s.length() ; i++) {
            if (Character.isDigit(s.charAt(i)))
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
}
