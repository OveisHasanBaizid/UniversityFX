package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuCoursesDepartment {
    Department department;
    @FXML
    TableColumn columnNumber , columnName , columnCredits, columnInstructor , columnNStudent;
    @FXML
    TableView<Course> table;
    @FXML
    Button btnExit;
    @FXML
    TextField textField_Name, textField_NCourse , textField_ID , textField_NProfessor;

    public void initialize() {
        department = DataBase.departmentHolder;

        textField_Name.setText(department.getName());
        textField_ID.setText(department.getId());
        textField_NCourse.setText(String.valueOf(department.getCourses().size()));
        textField_NProfessor.setText(String.valueOf(department.getFaculty().size()));

        showTableCourses();
    }
    public void clickItemTable(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2 && !table.getSelectionModel().isEmpty()) {
                Course course = table.getSelectionModel().getSelectedItem();
                DataBase.courseHolder = course;
                nextPage("MenuStudentsCoursesDepartment.fxml");
            }
        }
    }
    @FXML
    public void clickBtnExit() throws IOException {
        nextPage("PanelLogin.fxml");
    }
    public void showTableCourses() {
        columnNumber.setCellFactory(new NumberTableCellFactory<>(1));

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));

        columnNStudent.setCellValueFactory(new PropertyValueFactory<>("numberStudent"));


        columnInstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        columnInstructor.setCellFactory(column -> new TableCell<Course, Professor>() {
            @Override
            protected void updateItem(Professor professor, boolean b) {
                super.updateItem(professor, b);
                if (b || professor == null)
                    setText("");
                else
                    setText(professor.getName());
            }
        });

        ObservableList<Course> data = FXCollections.observableArrayList(DataBase.getDepartment(department.getName()).getCourses());
        table.setItems(data);
    }
    public void nextPage(String namePage) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(namePage)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clickBtnAddCourse() throws IOException {
        nextPage("AddCourseToDepartment.fxml");
    }
}
