package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ManageCourses {
    @FXML
    TableColumn columnNumber , columnName , columnCredits , columnDepartment , columnInstructor , columnNStudent;
    @FXML
    TableView<Course> table;
    @FXML
    Button btnExit , btnAdd;
    public void initialize() {
        showTableCourses();
    }
    @FXML
    public void clickBtnExit() throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuGod.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void clickBtnAdd() throws IOException {
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddCourse.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showTableCourses() {
        columnNumber.setCellFactory(new NumberTableCellFactory<>(1));

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));

        columnNStudent.setCellValueFactory(new PropertyValueFactory<>("numberStudent"));

        columnDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        columnDepartment.setCellFactory(column -> new TableCell<Course, Department>() {
            @Override
            protected void updateItem(Department department, boolean b) {
                super.updateItem(department, b);
                if (b || department == null)
                    setText("");
                else
                    setText(department.getName());
            }
        });

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

        ObservableList<Course> data = FXCollections.observableArrayList(DataBase.courses);
        table.setItems(data);
    }
}
