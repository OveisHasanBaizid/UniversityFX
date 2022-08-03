package com.example.universityfx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ManageProfessors {

    public TableColumn columnNumber , columnName , columnBirthDate , columnDepartment , columnRank;
    public TableView<Professor> table;
    public Button btnExit , btnAdd;

    public void initialize() {
        showTableProfessor();
    }

    public void clickBtnExit() throws IOException {
        nextPage("MenuGod.fxml");
    }

    public void clickBtnAdd() throws IOException {
        nextPage("AddProfessor.fxml");
    }
    public void showTableProfessor() {
        columnNumber.setCellFactory(new NumberTableCellFactory<>(1));

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));

        columnDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        columnDepartment.setCellFactory(column -> new TableCell<Professor, Department>() {
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
        ObservableList<Professor> data = FXCollections.observableArrayList(DataBase.professors);
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
