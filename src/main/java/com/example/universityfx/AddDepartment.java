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
import java.util.Random;

public class AddDepartment {
    @FXML
    Button btnBack , btnSave;
    @FXML
    TextField textField_name , textField_ID;

    @FXML
    public void clickBtnBack() throws IOException {
        nextPage("ManageDepartments.fxml");
    }

    @FXML
    public void clickBtnSave() throws IOException {
        if (textField_name.getText().isEmpty())
            showMessage("The name of the department must not be empty.", "Error");
        else if (textField_ID.getText().isEmpty())
            showMessage("The id of the department must not be empty.", "Error");
        else if (DataBase.getDepartment(textField_name.getText())!= null){
            showMessage("There is another department with this name.","Error");
        }else{
            Department department = new Department(textField_name.getText(),textField_ID.getText());
            DataBase.departments.add(department);
            DataBase.writeDepartments();
            showMessage("New department added successfully.","Message");

            nextPage("ManageDepartments.fxml");
        }
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
