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
        nextPage("ManageStudents.fxml");
    }
    @FXML
    public void clickBtnManageCourses() throws IOException {
        nextPage("ManageCourses.fxml");
    }
    @FXML
    public void clickBtnManageProfessors() throws IOException {
        nextPage("ManageProfessors.fxml");
    }
    @FXML
    public void clickBtnManageDepartments() throws IOException {
        nextPage("ManageDepartments.fxml");
    }
    @FXML
    public void clickBtnExit() throws IOException {
        nextPage("PanelLogin.fxml");
    }
    public void nextPage(String namePage) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(namePage)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
