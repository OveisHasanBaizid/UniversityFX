package com.example.universityfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuGod {
    @FXML
    Button btnManageStudents , btnManageCourses , btnManageProfessors, btnManageDepartments , btnExit;
    @FXML
    public void clickBtnManageStudents() throws IOException {
        Stage stage = (Stage) btnManageStudents.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ManageStudents.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void clickBtnManageCourses() {
    }
    @FXML
    public void clickBtnManageProfessors() {
    }
    @FXML
    public void clickBtnManageDepartments() {
    }
    @FXML
    public void clickBtnExit() throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PanelLogin.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
