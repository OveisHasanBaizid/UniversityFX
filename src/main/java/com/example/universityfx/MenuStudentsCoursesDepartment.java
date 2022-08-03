package com.example.universityfx;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuStudentsCoursesDepartment {
    Department department;
    Course course;
    @FXML
    ComboBox comboBoxProfessors;
    @FXML
    Button btnExit, btnAddProfessor , btnAddNewStudent , btnAddStudent;
    @FXML
    TextField textField_NameDepartment , textField_Credits , textField_NameCourse , textField_NStudent;
    @FXML
    TableColumn columnNumber , columnName , columnStudentNumber , columnBirthDate , columnDepartment;
    @FXML
    TableView<Student> table;

    public void initialize() {
        department = DataBase.departmentHolder;
        course = DataBase.courseHolder;
        textField_NameDepartment.setText(department.getName());
        textField_Credits.setText(String.valueOf(course.getCredits()));
        textField_NameCourse.setText(course.getName());
        textField_NStudent.setText(String.valueOf(course.getNumberStudent()));

        List<String> professors = new ArrayList<>();
        professors.add("");
        DataBase.professors.forEach(d -> {
            if (d.getDepartment().equals(department)) {
                professors.add(d.getName());
            }
        });
        ObservableList<String> p = FXCollections.observableArrayList(professors);

        comboBoxProfessors.setItems(p);
        comboBoxProfessors.setValue(DataBase.courseHolder.getInstructor().getName());

        comboBoxProfessors.valueProperty().addListener(
                (ChangeListener<String>) (ov, t, t1) -> DataBase.courseHolder.setInstructor(DataBase.getProfessor(t1)));

        showTableStudent();
    }

    @FXML
    public void clickBtnExit() throws IOException {
        nextPage("MenuCoursesDepartment.fxml");
    }

    @FXML
    public void clickBtnRegisterStudent() throws IOException {
        DataBase.courseHolder = course;
        DataBase.writeStudents();
        DataBase.writeGradeReports();
        nextPage("RegisterStudentToCourse.fxml");
    }
    @FXML
    public void clickBtnAddStudent() throws IOException {
        String numberStudent = showDialog_1_input("Add Student")[0];
        if (numberStudent!=null){
            Student student = DataBase.getStudent(numberStudent);
            if (student==null){
                showMessage("There is no student with this student number in the system.");
            }else if(course.getStudents().equals(student)){
                showMessage("This student has already been added to this course.");
            }else {
                DataBase.gradeReports.add(new GradeReport(student,course));
                DataBase.writeGradeReports();
                showMessage("Student to course added successfully");
                nextPage("MenuStudentsCoursesDepartment.fxml");
            }
        }
    }
    @FXML
    public void clickBtnAddProfessor() throws IOException {
        nextPage("AddNewProfessorToCourse.fxml");
    }
    public String[] showDialog_1_input(String m) {
        Dialog dialog = new Dialog();
        final String[] grade = new String[1];
        dialog.setHeaderText(null);
        dialog.setTitle(m);
        dialog.setResizable(true);
        Label label1 = new Label("Student Number: ");
        TextField text1 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCANCEL = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeOk) {
                return text1.getText();
            }
            return null;
        });
        Optional result = dialog.showAndWait();
        result.ifPresent(pair -> {
            grade[0] = result.get().toString();
        });
        return grade;
    }
    public void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().setPrefSize(300, 100);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
        ObservableList<Student> data = FXCollections.observableArrayList(course.getStudents());
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
