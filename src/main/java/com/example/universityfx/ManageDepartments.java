package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ManageDepartments {
    @FXML
    Button btnExit, btnAdd;
    @FXML
    TableColumn columnNumber , columnName , columnID;
    @FXML
    TableView<Department> table;

    public void initialize() {
        showTableDepartment();
    }

    @FXML
    public void clickBtnExit() throws IOException {
        nextPage("MenuGod.fxml");
    }

    @FXML
    public void clickBtnAdd() throws IOException {
        nextPage("AddDepartment.fxml");
    }

    public void showTableDepartment() {
        columnNumber.setCellFactory(new NumberTableCellFactory<>(1));

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));

        table.setEditable(false);
        ObservableList<Department> data = FXCollections.observableArrayList(DataBase.departments);
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
