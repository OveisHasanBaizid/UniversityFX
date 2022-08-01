package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class AddProfessor {
    @FXML
    TextField textField_Name;
    @FXML
    Button btnSave, btnBack;
    @FXML
    Spinner<String> spinnerDepartment, spinnerRank;
    @FXML
    DatePicker datePicker;

    public void initialize() {
        List<String> departments = new ArrayList<>();
        DataBase.departments.forEach(d -> departments.add(d.getName()));
        ObservableList<String> depart = FXCollections.observableArrayList(departments);
        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<>(depart);
        spinnerDepartment.setValueFactory(valueFactory);

        List<String> ranks = new ArrayList<>();
        ranks.add(AcademicRank.PROFESSOR.toString());
        ranks.add(AcademicRank.ASSISTANT_PROFESSOR.toString());
        ranks.add(AcademicRank.ASSOCIATE_PROFESSOR.toString());

        ObservableList<String> rank1 = FXCollections.observableArrayList(ranks);
        SpinnerValueFactory<String> valueFactoryRank = //
                new SpinnerValueFactory.ListSpinnerValueFactory<>(rank1);
        spinnerRank.setValueFactory(valueFactoryRank);
    }


    public void clickBtnBack() throws IOException {
        nextPage("ManageProfessors.fxml");
    }

    public void clickBtnSave() throws IOException {
        if (textField_Name.getText().isEmpty())
            showMessage("The name of the professor must not be empty.", "Error");
        else if (datePicker.toString().isEmpty())
            showMessage("The date of birth of the professor has not been selected.", "Error");
        else if (spinnerDepartment.getValue().isEmpty())
            showMessage("The department of the professor has not been selected.", "Error");
        else if (DataBase.getProfessor(textField_Name.getText()) != null)
            showMessage("There is another professor with this name.", "Error");
        else {
            Professor professor = new Professor(textField_Name.getText(), datePicker.getValue()
                    , DataBase.getDepartment(spinnerDepartment.getValue()), Professor.getRank(spinnerRank.getValue()));

            DataBase.professors.add(professor);
            showMessage("New professor added successfully.", "Message");

            nextPage("ManageProfessors.fxml");
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
