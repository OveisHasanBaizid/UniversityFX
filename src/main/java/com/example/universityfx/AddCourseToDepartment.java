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

public class AddCourseToDepartment {
    @FXML
    Button btnBack , btnSave;
    @FXML
    TextField textField_name , textField_credits;
    @FXML
    public void clickBtnBack() throws IOException {
        nextPage("MenuCoursesDepartment.fxml");
    }
    @FXML
    public void clickBtnSave() throws IOException {
        if (textField_name.getText().isEmpty())
            showMessage("The name of the course must not be empty.", "Error");
        else if (textField_credits.getText().isEmpty())
            showMessage("The credits of the course must not be empty.", "Error");
        else if (!checkCredits(textField_credits.getText()))
            showMessage("The entered credits course is invalid.", "Error");
        else if (DataBase.getCourse(textField_name.getText())!= null){
            showMessage("There is another course with this name.","Error");
        }else{
            Course course = new Course(textField_name.getText(),Integer.parseInt(textField_credits.getText())
                    ,DataBase.departmentHolder,null);
            DataBase.courses.add(course);
            showMessage("New course added successfully.","Message");
            nextPage("MenuCoursesDepartment.fxml");
        }
    }
    public boolean checkCredits(String s){
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
