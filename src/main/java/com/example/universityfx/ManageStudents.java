package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ManageStudents {
    @FXML
    Button btnExit, btnAdd;
    @FXML
    TableColumn columnNumber , columnName , columnStudentNumber , columnBirthDate , columnDepartment;
    @FXML
    TableView<Student> table;

    public void initialize() {
        showTableStudent();
    }

    @FXML
    public void clickBtnExit() throws IOException {
        nextPage("MenuGod.fxml");
    }

    @FXML
    public void clickBtnAdd() throws IOException {
        nextPage("AddStudent.fxml");
    }

    public void showTableStudent() {
        columnNumber.setCellFactory(new NumberTableCellFactory<>(1));

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnStudentNumber.setCellValueFactory(new PropertyValueFactory<>("student"));

        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        columnDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        columnDepartment.setCellFactory(column -> new TableCell<Student, Department>() {
            @Override
            protected void updateItem(Department department, boolean b) {
                super.updateItem(department, b);
                if (b || department == null)
                    setText("");
                else
                    setText(department.getName());
            }
        });
        table.setEditable(false);
        ObservableList<Student> data = FXCollections.observableArrayList(DataBase.students);
        table.setItems(data);
    }
    public void nextPage(String namePage) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(namePage)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
